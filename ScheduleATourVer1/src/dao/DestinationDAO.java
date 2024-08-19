package dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Destination;
import model.ScheduledService;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import view.ScheduleTourFrm;

public class DestinationDAO extends DAO {
    public DestinationDAO() {
        super();
    }

    public void searchDestination(String s, ScheduleTourFrm placeholderfrm) {
        String sql = "SELECT * FROM tblDestination WHERE name = ?";//need to fix this later, prolly by ignoring case
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            
            if (placeholderfrm != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                DefaultTableModel model = (DefaultTableModel) placeholderfrm.tblSearchResult.getModel();
                model.setRowCount(0);
                int cols = rsmd.getColumnCount(); // Get column number
                String[] colName = new String[cols]; // Get all column names into an array
                for (int i = 0; i < cols; i++) {
                    colName[i] = rsmd.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(colName);
                
                String id, name, type;
                while (rs.next()) {
                    id = rs.getString(1);
                    name = rs.getString(2);
                    type = rs.getString(3);
               
                    String[] row = {id, name, type};
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public boolean checkListDes(ScheduleTourFrm placeholderfrm,Destination des) {
    	//check if the list has already have the destination or overlapped time frame
    	try {
    		int k = placeholderfrm.ScheDes.getSize();
    		for(int i = 0; i<k; i++) {
    			//check availability
    			if(placeholderfrm.ScheDes.getSchedule(i).getDes().getId()==des.getId()) {
    				return false;
    			}
    		}
   		return true;
    	}
    	catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
    
    public boolean checkOverlapping(ScheduleTourFrm placeholderfrm, String start1, String end1) {
    	int k = placeholderfrm.ScheDes.getSize();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	
    	try {
    		Date start = dateFormat.parse(start1);
        	Date end = dateFormat.parse(end1);
        	if (!start.before(end)){
    			return false;
    		}
    		for(int i = 0; i<k; i++) {
	    		Date startdate = dateFormat.parse(placeholderfrm.ScheDes.getSchedule(i).getDes().getStarttime());
	    		Date enddate = dateFormat.parse(placeholderfrm.ScheDes.getSchedule(i).getDes().getEndtime());
	    		
	    		if((start.before(enddate) && start.after(startdate))||
	    		   (end.before(enddate) && end.after(startdate))||
	    		   (start.equals(startdate) && end.equals(enddate))||
	    		   (start.equals(startdate))||
	    		   (end.equals(enddate))) {
	    			return false;
	    		}
    		}
    		return true;
    	}
    	catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
    
    
    public void transferSelectedRow(ScheduleTourFrm placeholderfrm) {
        int selectedRow = placeholderfrm.tblSearchResult.getSelectedRow();
        
        try {
        	if (selectedRow != -1) {
        		System.out.println("transfer");
        		String id,name,location;
        		Date startdate,enddate;
                
                DefaultTableModel searchResultModel = (DefaultTableModel) placeholderfrm.tblSearchResult.getModel();
                DefaultTableModel desPreviewModel = (DefaultTableModel) placeholderfrm.tblDesPreview.getModel();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                
                id = searchResultModel.getValueAt(selectedRow, 0).toString();
                name = searchResultModel.getValueAt(selectedRow, 1).toString();
                location = searchResultModel.getValueAt(selectedRow, 2).toString();
                startdate = placeholderfrm.dcStartDate.getDate();
                enddate = placeholderfrm.dcEndDate.getDate();
                
                String[] previewrows ={"ID","Name","Location","StartDate","EndDate"};
                
                desPreviewModel.setColumnIdentifiers(previewrows);
                
                if(startdate!=null&&enddate!=null) {
                	System.out.println("notMT");
                	String startdateStr = dateFormat.format(startdate);
                    String enddateStr = dateFormat.format(enddate);
                	
                	int idholder = Integer.parseInt(id);
                	
                	Destination des = new Destination(idholder,name, location, startdateStr, enddateStr); //generate new destination
                	ScheduledService schedule = new ScheduledService();//create a new schedule
                	schedule.addDes(des);//add destination to the schedule
                	
                	if(checkOverlapping(placeholderfrm,startdateStr,enddateStr)) {
                		if(checkListDes(placeholderfrm,des)) {
                    		desPreviewModel.addRow(new String[]{id, name, location, startdateStr, enddateStr}); //add destination to the preview table
                    		
                    		
                    		placeholderfrm.ScheDes.addSchedule(schedule);//add a new schedule object with only a destination to the list
                        	System.out.println("A new schedule has been added to the schedule list with destination: "+des.toString() + " at index "+ placeholderfrm.ScheDes.getSize());
                        	
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, "This option has already been added!");
                    	}
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Time frame overlapped, please choose another time frame");
                	}
                	
                	
                	
                }
                else {
                	System.out.println("MT");
                	JOptionPane.showMessageDialog(null, "Please add both start and end date");
                }
                
            } 
        	
        	else {
                JOptionPane.showMessageDialog(placeholderfrm, "Please select a row to transfer", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        catch(Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    public void RemoveDes(ScheduleTourFrm placeholderfrm) {
    	
    	DefaultTableModel desPreviewModel = (DefaultTableModel) placeholderfrm.tblDesPreview.getModel();
    	DefaultTableModel serPreviewModel = (DefaultTableModel) placeholderfrm.tblSerPreview.getModel();
    	
    	int selectedRow = placeholderfrm.tblDesPreview.getSelectedRow();
    	if (selectedRow != -1) {
    		String id = desPreviewModel.getValueAt(selectedRow, 0).toString();
    		int idholder = Integer.parseInt(id);
    		int k = placeholderfrm.ScheDes.getSize();
    		
    		for(int i = 0; i<k; i++) {
    				if( placeholderfrm.ScheDes.getSchedule(i).getDes().getId()==idholder) {
    					placeholderfrm.ScheDes.removeSchedule(i);
    				}
    				break;
    		}
    		desPreviewModel.removeRow(selectedRow);
    		serPreviewModel.setRowCount(0);
        } 
    	else {
            JOptionPane.showMessageDialog(null, "No row selected");
        }
    	
    }
    
}
