package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import service.GenerateOTP;
import service.SendOTPService2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingResetPassword {

	JFrame resetframe;
	private JTextField jOTP;
	private JTextArea fEmail;
	private String genOTP;
	private JButton submitBtn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingResetPassword window = new SwingResetPassword();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingResetPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		resetframe = new JFrame();
		resetframe.setSize(670,348);
		resetframe.setLocationRelativeTo(null);
		resetframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resetframe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Email-ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(49, 45, 107, 31);
		resetframe.getContentPane().add(lblNewLabel);
		
		fEmail = new JTextArea();
		fEmail.setBounds(189, 45, 410, 31);
		resetframe.getContentPane().add(fEmail);
		
		JButton sendOTPbtn = new JButton("Send OTP");
		sendOTPbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!fEmail.getText().equals("")) {
					String genOTP2=GenerateOTP.getOPT();
					genOTP=genOTP2;
					
					SendOTPService2.sendOTP2(fEmail.getText(),genOTP2);
					jOTP.setEnabled(true);
					submitBtn.setEnabled(true);
					
					}else {
						JOptionPane.showMessageDialog(null,"Please Enter You Email Address");
					}
			}
		});
		sendOTPbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sendOTPbtn.setBounds(234, 118, 125, 31);
		resetframe.getContentPane().add(sendOTPbtn);
		
		JLabel lblNewLabel_1 = new JLabel("Enter OTP :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(132, 186, 93, 21);
		resetframe.getContentPane().add(lblNewLabel_1);
		
		jOTP = new JTextField();
		jOTP.setEnabled(false);
		jOTP.setBounds(267, 178, 167, 31);
		resetframe.getContentPane().add(jOTP);
		jOTP.setColumns(10);
		
		submitBtn = new JButton("Submit");
		submitBtn.setEnabled(false);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jOTP.getText().equals(genOTP)) {
					//call reset password
					SwingGiveNewPassword pw=new SwingGiveNewPassword(fEmail.getText());
					pw.newpwframe.setVisible(true);
					resetframe.dispose();
				}else if(jOTP.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please Enter OTP");
				}else {
					JOptionPane.showMessageDialog(null,"Wrong OTP");
				}
			}
		});
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		submitBtn.setBounds(266, 237, 93, 31);
		resetframe.getContentPane().add(submitBtn);
	}
}
