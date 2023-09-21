package views;

import java.awt.EventQueue;
import views.SwingWelcomeView;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.User;
import service.GenerateOTP;
import service.SendOTPService2;
import service.UserService;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class SwingSignUp {

	JFrame signUpFrame;
	private JTextField fEmail;
	private JTextField fName;
	private JTextField fPassword;
	private JTextField fOTP;
	private JButton fOTPbtn;
	public static String genOTP="";

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingSignUp window = new SwingSignUp();
//					window.signUpFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingSignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		signUpFrame = new JFrame();
		signUpFrame.setSize(670,504);
		signUpFrame.setLocationRelativeTo(null);

		signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUpFrame.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("REGISTER");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String otp=fOTP.getText();
				String name=fName.getText();
				String password=fPassword.getText();
				String mail=fEmail.getText();
				boolean valid=SwingSignUp.signUp(genOTP,otp,name,password,mail);
				if(valid) {
					SwingWelcomeView su=new SwingWelcomeView();
					su.loginFrame.setVisible(true);
					
					signUpFrame.dispose();
					System.out.println("Registered");

				}
				
				
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSubmit.setBounds(173, 376, 308, 44);
		signUpFrame.getContentPane().add(btnSubmit);
		
		fEmail = new JTextField();
		fEmail.setColumns(10);
		fEmail.setBounds(302, 39, 299, 60);
		signUpFrame.getContentPane().add(fEmail);
		
		fName = new JTextField();
		fName.setEnabled(false);
		fName.setColumns(10);
		fName.setBounds(302, 204, 299, 60);
		signUpFrame.getContentPane().add(fName);
		
		JLabel lblNewLabel = new JLabel("EMAIL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(155, 38, 113, 60);
		signUpFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(155, 203, 113, 60);
		signUpFrame.getContentPane().add(lblName);
		
		fPassword = new JTextField();
		fPassword.setEnabled(false);
		fPassword.setColumns(10);
		fPassword.setBounds(302, 290, 299, 60);
		signUpFrame.getContentPane().add(fPassword);
		
		fOTP = new JTextField();
		fOTP.setEnabled(false);
		fOTP.setColumns(10);
		fOTP.setBounds(451, 121, 150, 60);
		signUpFrame.getContentPane().add(fOTP);
		
		JLabel lblPassword_1 = new JLabel("SET PASSWORD");
		lblPassword_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_1.setBounds(155, 289, 113, 60);
		signUpFrame.getContentPane().add(lblPassword_1);
		
		JLabel lblOtp = new JLabel("OTP");
		lblOtp.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtp.setBounds(328, 120, 113, 60);
		signUpFrame.getContentPane().add(lblOtp);
		
		fOTPbtn = new JButton("Send Otp");
		
		fOTPbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!fEmail.getText().equals("")) {
				String genOTP2=GenerateOTP.getOPT();
				genOTP=genOTP2;
				
				SendOTPService2.sendOTP2(fEmail.getText(),genOTP2);
				fOTP.setEnabled(true);
				fName.setEnabled(true);
				fPassword.setEnabled(true);
				
				}else {
					JOptionPane.showMessageDialog(null,"Please Enter You Email Address");
				}
			}
		});
		fOTPbtn.setBounds(119, 140, 133, 41);
		signUpFrame.getContentPane().add(fOTPbtn);
	}
	
	
	public static boolean signUp(String genOTP,String otp,String name,String password,String email) {
		if(name.equals("")||otp.equals("")||password.equals("")) {
			JOptionPane.showMessageDialog(null,"Enter Complete Information");
			return false;
		}
	
		if(otp.equals(genOTP)) {

			User user=new User(name,email,password);
			int response=UserService.saveUser(user);
			switch(response) {
			case 0:JOptionPane.showMessageDialog(null,"Registered Succesfully");break;
			case 1:JOptionPane.showMessageDialog(null,"User Already Exists");break;
			default:System.out.println("Something is wrong.");
			}
			
			return true;			
		}else {
			JOptionPane.showMessageDialog(null,"Wrong OTP");
			return false;
		}
	
		
	}
}
