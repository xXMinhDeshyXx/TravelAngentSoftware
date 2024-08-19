package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Window;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import dao.DestinationDAO;
import dao.ServiceDAO;
import dao.TourDAO;
import model.ScheduledDestination;
import model.ScheduledService;
import model.Service;
import model.Tour;
import model.User;

public class ScheduleTourFrm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel lblDestination;
	public JTable tblDesPreview;
	public JTable tblSerPreview;
	private JLabel lblService;
	private JLabel lblPrice;
	private JLabel lblVND;
	private JPanel pnSearch;
	private JPanel pnTourInfo;
	public JTextField fldSearch;
	private JComboBox cbSearchOption;
	private JButton btnSearch;
	public JTable tblSearchResult;
	private JButton btnAdd;
	private JButton btnRemove;
	private JLabel lblTourName;
	public JTextField txtTourName;
	private JLabel lblTourDes;
	private JButton btnNewButton_4;
	public JTextArea taTourDes;
	private JScrollPane scrollPane;
	public User user;
	public JDateChooser dcStartDate;
	public JDateChooser dcEndDate;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	public ScheduledDestination ScheDes;
	
	public boolean mode;//to set between Destination and Service Mode
	public int totalPrice = 0;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ScheduleTourFrm(User user) {
		super();
		this.user = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 600);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		
		JPanel pnPreview = new JPanel();
		pnPreview.setBorder(new TitledBorder(null, "Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnSearch = new JPanel();
		pnSearch.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnTourInfo = new JPanel();
		pnTourInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnTourInfo, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
						.addComponent(pnSearch, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnPreview, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(pnPreview, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addComponent(pnSearch, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnTourInfo, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pnTourInfo.setLayout(null);
		
		lblTourName = new JLabel("Tour name:");
		lblTourName.setBounds(6, 6, 75, 16);
		pnTourInfo.add(lblTourName);
		
		txtTourName = new JTextField();
		txtTourName.setBounds(6, 21, 655, 29);
		pnTourInfo.add(txtTourName);
		txtTourName.setColumns(10);
		
		lblTourDes = new JLabel("Tour description:");
		lblTourDes.setBounds(6, 51, 122, 16);
		pnTourInfo.add(lblTourDes);
		
		btnNewButton_4 = new JButton("Save tour");
		
		btnNewButton_4.setBounds(561, 125, 117, 29);
		pnTourInfo.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Clear");
		btnNewButton_5.setBounds(561, 84, 117, 29);
		pnTourInfo.add(btnNewButton_5);
		
		JScrollPane spnTourDes = new JScrollPane();
		spnTourDes.setBounds(6, 73, 555, 81);
		pnTourInfo.add(spnTourDes);
		
		taTourDes = new JTextArea();
		spnTourDes.setViewportView(taTourDes);
		pnSearch.setLayout(null);
		
		fldSearch = new JTextField();
		fldSearch.setBounds(6, 3, 484, 32);
		pnSearch.add(fldSearch);
		fldSearch.setColumns(10);
		
		cbSearchOption = new JComboBox();
		cbSearchOption.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		cbSearchOption.setModel(new DefaultComboBoxModel(new String[] {"Destination", "Service"}));
		cbSearchOption.setBounds(493, 7, 99, 26);
		pnSearch.add(cbSearchOption);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(586, 6, 92, 26);
		pnSearch.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 662, 302);
		pnSearch.add(scrollPane);
		
		tblSearchResult = new JTable();
		scrollPane.setViewportView(tblSearchResult);
		tblSearchResult.setBorder(null);
		tblSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(555, 348, 117, 29);
		pnSearch.add(btnAdd);
		
		dcStartDate = new JDateChooser();
		dcStartDate.setBorder(null);
		dcStartDate.setBounds(78, 348, 183, 26);
		pnSearch.add(dcStartDate);
		
		dcEndDate = new JDateChooser();
		dcEndDate.setBounds(360, 348, 183, 26);
		pnSearch.add(dcEndDate);
		
		lblStartDate = new JLabel("Start time");
		lblStartDate.setBounds(6, 353, 61, 16);
		pnSearch.add(lblStartDate);
		
		lblEndDate = new JLabel("End time");
		lblEndDate.setBounds(297, 353, 61, 16);
		pnSearch.add(lblEndDate);
		pnPreview.setLayout(null);
		
		lblDestination = new JLabel("Destinations:");
		lblDestination.setBounds(6, 26, 93, 16);
		pnPreview.add(lblDestination);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 48, 317, 194);
		pnPreview.add(scrollPane_1);
		
		tblDesPreview = new JTable();
		scrollPane_1.setViewportView(tblDesPreview);
		tblDesPreview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDesPreview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblDesPreview.setRowSelectionAllowed(true);
	    tblDesPreview.setColumnSelectionAllowed(false);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(16, 271, 317, 194);
		pnPreview.add(scrollPane_2);
		
		tblSerPreview = new JTable();
		scrollPane_2.setViewportView(tblSerPreview);
		tblSerPreview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSerPreview.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblSerPreview.setRowSelectionAllowed(true);
		tblSerPreview.setColumnSelectionAllowed(false);
		
		lblService = new JLabel("Services:");
		lblService.setBounds(6, 254, 61, 16);
		pnPreview.add(lblService);
		
		lblPrice = new JLabel("Total price:");
		lblPrice.setBounds(16, 477, 71, 16);
		pnPreview.add(lblPrice);
		
		lblVND = new JLabel("0 VND");
		lblVND.setBounds(105, 477, 200, 16);
		pnPreview.add(lblVND);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(216, 515, 117, 29);
		pnPreview.add(btnRemove);
		mainPanel.setLayout(gl_mainPanel);
		
		ScheDes = new ScheduledDestination(); //create a new list of scheduled destination
		
		
		
	    tblDesPreview.setRowSelectionAllowed(true);
	    tblDesPreview.setColumnSelectionAllowed(false);
		
		
		btnSearch.addActionListener(new ActionListener() {
			
			public void clearTbl() {
				DefaultTableModel def = (DefaultTableModel) tblSearchResult.getModel();
				def.setRowCount(0);
				
			}

			public void setVisibleDate (boolean x) { // make start time and end time option visible if searched with Destination 
				lblStartDate.setVisible(x);
				lblEndDate.setVisible(x);
				dcStartDate.setVisible(x);
				dcEndDate.setVisible(x);
			}
			
			public void actionPerformed(ActionEvent e) {//need to clear start and end time after adding it to the list
				clearTbl();
				String select = String.valueOf(cbSearchOption.getSelectedItem());
				String search = fldSearch.getText();
				String des = "Destination";
				String ser = "Service";
				System.out.println(select);
				
				if(select.equalsIgnoreCase(des)) {
					mode = false;
					setVisibleDate(true);
					DestinationDAO destinationdao = new DestinationDAO();
					destinationdao.searchDestination(search, ScheduleTourFrm.this);
				}
				
				else if(select.equalsIgnoreCase(ser)) {
					mode = true;
					setVisibleDate(false);
					ServiceDAO servicedao = new ServiceDAO();
					int k = ScheDes.getSize();
					
					for(ScheduledService schedule : ScheDes.schedulelist) {
						int g = schedule.getDes().getId();
						System.out.println("Traversed destination: "+schedule.getDes().getName());
						servicedao.searchService(search, g, ScheduleTourFrm.this);
					}
					
					
				}
				else {
					
					System.out.println("No");
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void resetTotalPrice() {
				int d = ScheDes.getSize();
				int pricecounted = 0;
				for(ScheduledService schedule : ScheDes.schedulelist) {
					for(Service ser : schedule.servicelist) {
						pricecounted += ser.getPrice();
					}
				}
				totalPrice = pricecounted;
				lblVND.setText(totalPrice + " VND");
				
			}
			
			public void actionPerformed(ActionEvent e) {
				String select = String.valueOf(cbSearchOption.getSelectedItem());
				
				if (mode==false) {//mode false = destination adding
					DestinationDAO destinationdao1 = new DestinationDAO();
					destinationdao1.transferSelectedRow(ScheduleTourFrm.this);
				}
				else if(mode==true) {
					ServiceDAO servicedao1 = new ServiceDAO();
					if(!ScheDes.CheckIfEmpty()) {
						//will add a "check if destination is on the list" later
						
						servicedao1.transferSelectedRow(ScheduleTourFrm.this);
						resetTotalPrice();
					}
					else {
						JOptionPane.showMessageDialog(null, "Destination list is empty!");
					}
					
					
				}
				
				
			}
		});
		tblDesPreview.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            public void updateServiceTable(int desID) {
            	DefaultTableModel serPreviewModel = (DefaultTableModel) tblSerPreview.getModel();
            	serPreviewModel.setRowCount(0);//reset Service Preview table
            	for(ScheduledService s : ScheDes.schedulelist) {
            		if(s.getDes().getId()==desID) {
            			for(Service y : s.servicelist) {
            				String id = Integer.toString(y.getId());
            				String name = y.getName();
            				String type = y.getType();
            				String sp = y.getSp();
            				String price = Integer.toString(y.getPrice());
            				
            				serPreviewModel.addRow(new String[]{id,name,type,sp,price}); //add destination to the preview table
            			}
            		}
            	}
            	
            }
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblDesPreview.getSelectedRow();
                    if (selectedRow != -1) {
                    	tblDesPreview.setColumnSelectionAllowed(false);
                    	tblSerPreview.clearSelection();
                        int desID = Integer.parseInt(tblDesPreview.getValueAt(selectedRow, 0).toString());
                        updateServiceTable(desID);
                        
                    }
                }
            }
            
        });
		
		tblSerPreview.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
                if (!e.getValueIsAdjusting()) {
                	int selectedRow = tblSerPreview.getSelectedRow();
                    if (selectedRow != -1) {
                    	tblSerPreview.setColumnSelectionAllowed(false);
	                	tblDesPreview.clearSelection();
	                	
                    }
                }
            }
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void resetTotalPriceAfterRemove() {
				if(ScheDes.CheckIfEmpty()) {
					lblVND.setText("0 VND");
					return;
				}
				int d = ScheDes.getSize();
				int pricecounted = 0;
				for(int i = 0; i<d; i++) {
					int n = ScheDes.getSchedule(i).getSize();
					for(int j=0; j<n; j++) {
						int x = ScheDes.getSchedule(i).getSer(j).getPrice();
						pricecounted+=x;
					}
				}
				totalPrice = pricecounted;
				lblVND.setText(totalPrice + " VND");
				
			}
			
			public void actionPerformed(ActionEvent e) {
				int selectedRowSer = tblSerPreview.getSelectedRow();
				int selectedRowDes = tblDesPreview.getSelectedRow();
				if(selectedRowSer!=-1) {
					ServiceDAO servicedao1 = new ServiceDAO();
					servicedao1.RemoveSer(ScheduleTourFrm.this);
				}
				else if(selectedRowDes!=-1) {
					DestinationDAO destinationdao1 = new DestinationDAO();
					destinationdao1.RemoveDes(ScheduleTourFrm.this);
				}
				resetTotalPriceAfterRemove();
				
				
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = txtTourName.getText();
				String b = taTourDes.getText();
				Tour tournew = new Tour(a,b);
				ScheDes.setTour(tournew);

				if(!a.isEmpty()) {
					if(!ScheDes.CheckIfEmpty()) {
						TourDAO tourdao = new TourDAO();
						tourdao.addTour(ScheduleTourFrm.this);
						JOptionPane.showMessageDialog(null, "Tour has been saved to the database!");
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill in at least 1 Destination and 1 Service before saving!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please fill in all the fields before saving!");
				}
				
				
			}
		});
		
		
	}
}

