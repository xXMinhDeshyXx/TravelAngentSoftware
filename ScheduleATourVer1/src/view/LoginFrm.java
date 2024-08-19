package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.*;

public class LoginFrm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fldUsername;
	private JTextField fldPassword;
	private JButton btnLoginButton;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public LoginFrm() 
	{
		initComponents();
		createEvents();
	}
	
	public void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginLabel = new JLabel("Login");
		lblLoginLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLoginLabel.setBounds(198, 6, 61, 37);
		contentPane.add(lblLoginLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(67, 77, 69, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(67, 123, 61, 16);
		contentPane.add(lblPassword);
		
		fldUsername = new JTextField();
		fldUsername.setBounds(150, 67, 186, 37);
		contentPane.add(fldUsername);
		fldUsername.setColumns(10);
		
		fldPassword = new JPasswordField();
		fldPassword.setColumns(10);
		fldPassword.setBounds(150, 113, 186, 37);
		contentPane.add(fldPassword);
		
		btnLoginButton = new JButton("Login");
		btnLoginButton.setBounds(164, 175, 117, 29);
		contentPane.add(btnLoginButton);
	}
	
	
	public void createEvents() {
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cfUsername = fldUsername.getText();
				String cfPassword = fldPassword.getText();
				User user = new User();
				user.setUsername(cfUsername);
				user.setPassword(cfPassword);
				UserDAO userdao = new UserDAO();
				if (userdao.checkLogin(user)) {
					DBManagerHomeFrm newDBMHomeFrm = new DBManagerHomeFrm(user);
					newDBMHomeFrm.setLocationRelativeTo(null);
					newDBMHomeFrm.setVisible(true);
					LoginFrm.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!");
				}
			}
		});
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
