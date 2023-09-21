package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingEncryptOrHide {

	public JFrame Selectframe;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingEncryptOrHide window = new SwingEncryptOrHide();
//					window.Selectframe.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingEncryptOrHide() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Selectframe = new JFrame();
		Selectframe.setSize(450,300);
		Selectframe.setLocationRelativeTo(null);
		Selectframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Selectframe.getContentPane().setLayout(null);
		
		JButton eptDptBtn = new JButton("File Encryptor/Decryptor");
		eptDptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileEncyptorAndDecryptor f=new FileEncyptorAndDecryptor();
				f.encyptorDecryptorFrame.setVisible(true);
				Selectframe.dispose();
			}
		});
		eptDptBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		eptDptBtn.setBounds(30, 109, 164, 44);
		Selectframe.getContentPane().add(eptDptBtn);
		
		JLabel lblNewLabel = new JLabel("Secure File Manager");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(30, 35, 383, 35);
		Selectframe.getContentPane().add(lblNewLabel);
		
		JButton btnHideunhideFile = new JButton("Hide/Unhide File");
		btnHideunhideFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWelcomeView su=new SwingWelcomeView();
				su.loginFrame.setVisible(true);
				
				Selectframe.dispose();
			}
		});
		btnHideunhideFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHideunhideFile.setBounds(238, 109, 143, 44);
		Selectframe.getContentPane().add(btnHideunhideFile);
	}
}
