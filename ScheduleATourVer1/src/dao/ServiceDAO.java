package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Destination;
import model.ScheduledService;
import model.Service;
import view.ScheduleTourFrm;

public class ServiceDAO extends DAO {
	public ServiceDAO() {
		super();
	}
	public void searchService(String s,int n, ScheduleTourFrm placeholderfrm) {
        String sql = 
        		"""
        		
				select tblServicePrice.ID, 
						tblService.name as Name,
						tblService.type as Type,
				        tblServiceProvider.name as ServiceProvider,
				        tblServicePrice.ServicePrice as Price, 
				        tblServiceOfDestination.IDDestination as DestinationID
				from tblServicePrice
				inner join tblService on tblService.ID = tblServicePrice.ServiceID
				inner join tblServiceProvider on tblServicePrice.ServiceProviderID = tblServiceProvider.ID
				inner join tblServiceOfDestination on tblServicePrice.ServiceID = tblServiceOfDestination.IDService
				where tblService.type = ? and tblServiceOfDestination.IDDestination = ?
				;
                
        		"""
        		
        		;//need to fix this later, prolly by ignoring case
        
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, n);
            ResultSet rs = ps.executeQuery();
            
            if (placeholderfrm != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                DefaultTableModel model = (DefaultTableModel) placeholderfrm.tblSearchResult.getModel();
                
                int cols = rsmd.getColumnCount(); // Get column number
                String[] colName = new String[cols]; // Get all column names into an array
                for (int i = 0; i < cols; i++) {
                    colName[i] = rsmd.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(colName);
                
                String id, name, type,serviceprovider,price,desid;
                while (rs.next()) {
                    id = rs.getString(1);
                    name = rs.getString(2);
                    type = rs.getString(3);
                    serviceprovider = rs.getString(4);
                    price = rs.getString(5);
                    desid = rs.getString(6);
               
                    String[] row = {id, name, type,serviceprovider,price,desid};
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	 public boolean checkListSer(ScheduleTourFrm placeholderfrm,Service ser,int desID) {
	    	try {
	    		for(ScheduledService x : placeholderfrm.ScheDes.schedulelist) {
	    			if(x.getDes().getId()==desID) {
	    				for(Service y : x.servicelist) {
	    					if(y.getId()==ser.getId()) {
	    						return false;
	    					}
	    				}
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
        		String id,name,type,sp,price,desid;
        		
                
                DefaultTableModel searchResultModel = (DefaultTableModel) placeholderfrm.tblSearchResult.getModel();
                DefaultTableModel serPreviewModel = (DefaultTableModel) placeholderfrm.tblSerPreview.getModel();
                
                
                id = searchResultModel.getValueAt(selectedRow, 0).toString();
                name = searchResultModel.getValueAt(selectedRow, 1).toString();
                type = searchResultModel.getValueAt(selectedRow, 2).toString();
                sp = searchResultModel.getValueAt(selectedRow, 3).toString();
                price = searchResultModel.getValueAt(selectedRow, 4).toString();
                desid = searchResultModel.getValueAt(selectedRow, 5).toString();
                String[] previewrows ={"ID","Name","Type","ServiceProvider","Price"};
                
                serPreviewModel.setColumnIdentifiers(previewrows);           	
                
               	int idholder = Integer.parseInt(id);
               	int priceholder = Integer.parseInt(price);
               	int desidholder = Integer.parseInt(desid);
               	System.out.println("The current service is being added to destination with id: "+desidholder);
               	
               	Service ser = new Service(idholder,name,type,sp,priceholder);//create a new service
               	
               	ser.setDesID(desidholder);
               	if(checkListSer(placeholderfrm,ser,desidholder)) {
               		serPreviewModel.addRow(new String[]{id,name,type,sp,price});//changing
               		
               		for(ScheduledService x : placeholderfrm.ScheDes.schedulelist) {
    	    			if(x.getDes().getId()==desidholder) {
    	    				x.addSer(ser);
    	    				System.out.println("A service has been added to the list: "+ser.toString() + " at index "+ x.getSize()+ "of destiation with id: "+ desidholder);
    	    			}
    	    		}
               		serPreviewModel.setRowCount(0);//reset Service preview table
               		for(ScheduledService s : placeholderfrm.ScheDes.schedulelist) {
                		if(s.getDes().getId()==desidholder) {
                			for(Service y : s.servicelist) {
                				String id1 = Integer.toString(y.getId());
                				String name1 = y.getName();
                				String type1 = y.getType();
                				String sp1 = y.getSp();
                				String price1 = Integer.toString(y.getPrice());
                				
                				serPreviewModel.addRow(new String[]{id1,name1,type1,sp1,price1}); //add destination to the preview table
                			}
                		}
                	}
               		
               	}
               	else {
            		JOptionPane.showMessageDialog(null, "This option has already been added!");
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
	
	public void RemoveSer(ScheduleTourFrm placeholderfrm) {
    	
    	DefaultTableModel desPreviewModel = (DefaultTableModel) placeholderfrm.tblDesPreview.getModel();
    	DefaultTableModel serPreviewModel = (DefaultTableModel) placeholderfrm.tblSerPreview.getModel();
    	
    	int selectedRow = placeholderfrm.tblSerPreview.getSelectedRow();
    	if (selectedRow != -1) {
    		String id = serPreviewModel.getValueAt(selectedRow, 0).toString();
    		int idholder = Integer.parseInt(id);
    		int d = placeholderfrm.ScheDes.getSize();
    		
    		for(int i = 0; i<d; i++) {
				int n = placeholderfrm.ScheDes.getSchedule(i).getSize();
				for(int j=0; j<n; j++) {
					if(placeholderfrm.ScheDes.getSchedule(i).getSer(j).getId()==idholder) {
						placeholderfrm.ScheDes.getSchedule(i).removeSer(j);
						System.out.println("A service with id "+idholder+ "has been removed from a schedule with index "+ i);
						break;
					}
				}
			}
    		serPreviewModel.removeRow(selectedRow);
        } 
    	else {
            JOptionPane.showMessageDialog(null, "No row selected");
        }
    	
    }
}

//add remove and clear DONE? add a remove service, make it so that only 1 of 2 table is selected, will do later
//add a sort to the service list
//prevent time frame overlap DONE
//add Database for scheduled service and des and tour DONE
//connect to database by adding a save button DONE

//restrucutred the whole thing, now things are easier to manage and deleting the destination will automatically delete the ser list (not in the preview table yet) DONE

//redo the "Choosing scheduled destination then it appear the list of that schedule DONE

//redo the Database by adding a middle entity between Des and Ser, rewrite the query so that the context stays the same DONE

//redo the TourDAO DONE

//redo





