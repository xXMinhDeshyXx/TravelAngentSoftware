package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBManagerHomeFrm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;

	public DBManagerHomeFrm(User user) {
		super();
		this.user = user;
		
		setBackground(new Color(238, 238, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("System Manager");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(148, 6, 167, 46);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Schedule a Tour");
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(user.getName());
				ScheduleTourFrm schedule  = new ScheduleTourFrm(user);
				schedule.setLocationRelativeTo(null);
				schedule.setVisible(true);
				DBManagerHomeFrm.this.dispose();
			}
		});
		btnNewButton.setBounds(135, 64, 167, 29);
		contentPane.add(btnNewButton);
	}

}
