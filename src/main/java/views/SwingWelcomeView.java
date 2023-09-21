package views;

import java.awt.EventQueue;
import views.SwingSignUp;



import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import dao.UserDAO;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;

public class SwingWelcomeView {

	public JFrame loginFrame;
	private JTextField fEmail;
	private JTextField fPassword;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingWelcomeView window = new SwingWelcomeView();
//					window.loginFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingWelcomeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setSize(670,504);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fEmail.getText().equals("") || fPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill complete information");
				}else {
				login(fEmail.getText(),fPassword.getText());
				}
			}
		});
		loginBtn.setBounds(49, 305, 220, 44);
		loginFrame.getContentPane().add(loginBtn);
		
		JButton SIGNUP = new JButton("SIGNUP");
		SIGNUP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		SIGNUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingSignUp su=new SwingSignUp();
				su.signUpFrame.setVisible(true);
				loginFrame.dispose();
			
			}
		});
		SIGNUP.setBounds(313, 305, 308, 44);
		loginFrame.getContentPane().add(SIGNUP);
		
		fEmail = new JTextField();
		fEmail.setBounds(313, 42, 299, 60);
		loginFrame.getContentPane().add(fEmail);
		fEmail.setColumns(10);
		
		fPassword = new JTextField();
		fPassword.setColumns(10);
		fPassword.setBounds(313, 132, 299, 60);
		loginFrame.getContentPane().add(fPassword);
		
		JLabel lblNewLabel = new JLabel("EMAIL");
		lblNewLabel.setBackground(new Color(112, 128, 144));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 48, 183, 60);
		loginFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBackground(new Color(0, 206, 209));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(86, 131, 183, 60);
		loginFrame.getContentPane().add(lblPassword);
		
		JButton btnNewButton = new JButton("forgot password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingResetPassword rpw=new SwingResetPassword();
				rpw.resetframe.setVisible(true);
				loginFrame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(203, 385, 202, 33);
		loginFrame.getContentPane().add(btnNewButton);
	}
	
	
	public void login(String email,String password) {

		try {
			if(UserDAO.isExists(email)) {
				if(UserDAO.checkPassword(email, password)) {
					SwingUserView2 uv=new SwingUserView2(email);
					uv.userFrame.setVisible(true);
					loginFrame.dispose();
					
				
				}else {
					JOptionPane.showMessageDialog(null,"Wrong Password");
				}
			}else {
				JOptionPane.showMessageDialog(null,"User Not Found");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
