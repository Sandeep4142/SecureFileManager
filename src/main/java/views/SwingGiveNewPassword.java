package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UserDAO;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SwingGiveNewPassword {

	JFrame newpwframe;
	private JTextField repassword;
	private JPasswordField password;
	private String femail;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingGiveNewPassword window = new SwingGiveNewPassword();
//					window.newpwframe.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingGiveNewPassword(String email) {
		this.femail=email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		newpwframe = new JFrame();
		newpwframe.setSize(670,348);
		newpwframe.setLocationRelativeTo(null);
		newpwframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newpwframe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Set New Password :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 55, 142, 26);
		newpwframe.getContentPane().add(lblNewLabel);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password :");
		lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReenterPassword.setBounds(59, 121, 155, 26);
		newpwframe.getContentPane().add(lblReenterPassword);
		
		repassword = new JTextField();
		repassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		repassword.setBounds(238, 117, 273, 35);
		newpwframe.getContentPane().add(repassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		password.setBounds(238, 51, 273, 35);
		newpwframe.getContentPane().add(password);
		
		JButton changePassword = new JButton("Submit");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(repassword.getText().equals("") || password.getPassword().equals(null) ) {
					JOptionPane.showMessageDialog(null,"Enter password");
				}else if(repassword.getText().equals(new String(password.getPassword()))) {
					try {
						UserDAO.changePassword(femail, repassword.getText());
						JOptionPane.showMessageDialog(null,"Password Reset Successfully !");
						SwingWelcomeView su=new SwingWelcomeView();
						su.loginFrame.setVisible(true);
						
						newpwframe.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Both passwords must be same !");
				}
				
			}
		});
		changePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changePassword.setBounds(212, 225, 173, 35);
		newpwframe.getContentPane().add(changePassword);
	}
}
