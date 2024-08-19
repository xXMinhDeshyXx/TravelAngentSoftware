package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import model.Service;
import model.Tour;
import view.ScheduleTourFrm;


public class TourDAO extends DAO{

	public TourDAO() {
		super();
	}
	public void addTour(ScheduleTourFrm placeholderfrm) {
		String sqlAddTour = "INSERT INTO tblTour(Name, Description,UserID) VALUES(?,?,?)";
		String sqlAddScheduledDestination = "INSERT INTO tblScheduledDestination(ScheduledDestinationID,TourID,StartDate,EndDate) VALUES(?,?,?,?)";
		String sqlAddScheduledService = "INSERT INTO tblScheduledService(ScheduledDesID,ServicePriceID) VALUES(?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		try {
			con.setAutoCommit(false);
			Tour tour1 = placeholderfrm.ScheDes.tour;
			PreparedStatement ps = con.prepareStatement(sqlAddTour, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tour1.getName());
			ps.setString(2, tour1.getDescription());
			ps.setInt(3, placeholderfrm.user.getId());
			System.out.println(placeholderfrm.user.getId()+" "+placeholderfrm.user.getName());
			ps.executeUpdate();			
			//get id of the newly inserted tour
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				int tourID = generatedKeys.getInt(1);
				placeholderfrm.ScheDes.tour.setId(generatedKeys.getInt(1));
				
				//insert scheduled destination
				int k = placeholderfrm.ScheDes.getSize();
				for(int i=0; i<k; i++) {
					ps = con.prepareStatement(sqlAddScheduledDestination, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, placeholderfrm.ScheDes.getSchedule(i).getDes().getId());
					ps.setInt(2, tourID);
					ps.setString(3,  placeholderfrm.ScheDes.getSchedule(i).getDes().getStarttime());
					ps.setString(4,  placeholderfrm.ScheDes.getSchedule(i).getDes().getEndtime());

					ps.executeUpdate();			
					//get id of the new inserted scheduled service
					generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
						int ScheDesID =generatedKeys.getInt(1);
						int g =  placeholderfrm.ScheDes.getSchedule(i).getSize();//traverse the ser list to add a ser
						for (int j=0; j<g; j++) {//traverse the ser list to add a ser
							Service ser = placeholderfrm.ScheDes.getSchedule(i).getSer(j);
							int SerID = ser.getId();
							int SerDesID = ser.getDesID();
							ps = con.prepareStatement(sqlAddScheduledService);
							ps.setInt(1, ScheDesID);
							ps.setInt(2, SerID);	
							ps.executeUpdate();	//add service to scheduled service list
						}
					}		
				}
			}
			con.commit();
			placeholderfrm.ScheDes.schedulelist.clear();
			DefaultTableModel searchResultModel = (DefaultTableModel) placeholderfrm.tblSearchResult.getModel();
            DefaultTableModel serPreviewModel = (DefaultTableModel) placeholderfrm.tblSerPreview.getModel();
            DefaultTableModel desPreviewModel = (DefaultTableModel) placeholderfrm.tblDesPreview.getModel();
            searchResultModel.setRowCount(0);
            serPreviewModel.setRowCount(0);
            desPreviewModel.setRowCount(0);
            placeholderfrm.fldSearch.setText("");
            placeholderfrm.txtTourName.setText("");
            placeholderfrm.taTourDes.setText("");
			//con.commit();//set this line into comment in JUnit test mode
		}catch(Exception e) {
				
			try {				
				con.rollback();
				System.out.println("Weird error 1");
			}catch(Exception ex) {
				System.out.println("Weird error 2");
				ex.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {				
				con.setAutoCommit(true);//set this line into comment in JUnit test mode
			}catch(Exception ex) {
				System.out.println("Weird error 3");
				ex.printStackTrace();
			}
		}
		
	}
}
