package views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;

public class FileEncyptorAndDecryptor {

	JFrame encyptorDecryptorFrame;
	private JTextField fKey;
	private JLabel fNameText;
	private JLabel fPathText;
	private JLabel fPath;
	private JLabel fFileName;
	
	private String filename;
	private String filepath;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FileEncyptorAndDecryptor window = new FileEncyptorAndDecryptor();
//					window.encyptorDecryptorFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public FileEncyptorAndDecryptor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		encyptorDecryptorFrame = new JFrame();
		encyptorDecryptorFrame.setSize(500,400);
		encyptorDecryptorFrame.setLocationRelativeTo(null);
		encyptorDecryptorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		encyptorDecryptorFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter 3 digit Key :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(102, 25, 137, 42);
		encyptorDecryptorFrame.getContentPane().add(lblNewLabel);
		
		fKey = new JTextField();
		fKey.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fKey.setHorizontalAlignment(SwingConstants.CENTER);
		fKey.setBounds(249, 31, 96, 31);
		encyptorDecryptorFrame.getContentPane().add(fKey);
		fKey.setColumns(10);
		
		JButton fEncryptBtn = new JButton("Encrypt");
		fEncryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fPathText.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please Select File");
				}else if(fKey.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please enter Key");
				}else if(fKey.getText().length()!=3){
					JOptionPane.showMessageDialog(null,"Please enter valid Key");
				}
				else {
					encrypt();
				}
			}
		});
		fEncryptBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fEncryptBtn.setBounds(89, 260, 134, 42);
		encyptorDecryptorFrame.getContentPane().add(fEncryptBtn);
		
		JButton fDecrytptBtn = new JButton("Decrypt");
		fDecrytptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fPathText.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please Select File");
				}else if(fKey.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please enter Key");
				}else if(fKey.getText().length()!=3){
					JOptionPane.showMessageDialog(null,"Please enter valid Key");
				}
				else {
					decrypt();
				}
			}
		});
		fDecrytptBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fDecrytptBtn.setBounds(251, 260, 134, 42);
		encyptorDecryptorFrame.getContentPane().add(fDecrytptBtn);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				System.out.println(fileChooser.getSelectedFile().getPath());
				filename=fileChooser.getSelectedFile().getName();
				filepath=fileChooser.getSelectedFile().getPath();
				
				fFileName.setVisible(true);
				fPath.setVisible(true);
				
				
				fNameText.setText(filename);
				fPathText.setText(filepath);
			}
		});
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectFile.setBounds(155, 105, 134, 42);
		encyptorDecryptorFrame.getContentPane().add(btnSelectFile);
		
		fPath = new JLabel("Path :");
		fPath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fPath.setBounds(38, 214, 53, 13);
		fPath.setVisible(false);
		encyptorDecryptorFrame.getContentPane().add(fPath);
		
		fFileName = new JLabel("File Name :");
		fFileName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fFileName.setBounds(38, 180, 68, 13);
		fFileName.setVisible(false);
		encyptorDecryptorFrame.getContentPane().add(fFileName);
		
		fNameText = new JLabel("");
		fNameText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fNameText.setBounds(160, 180, 310, 13);
		encyptorDecryptorFrame.getContentPane().add(fNameText);
		
		fPathText = new JLabel("");
		fPathText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fPathText.setBounds(85, 214, 368, 13);
		encyptorDecryptorFrame.getContentPane().add(fPathText);
	}
	
	private void encrypt() {
		
		String extraByte="enc"+new String(fKey.getText().getBytes());
		
		
		try { 
	        Integer.parseInt(fKey.getText()); 
	    } catch(NumberFormatException e) { 
	    	JOptionPane.showMessageDialog(null,"Enter valid key");
	        return ; 
	    } catch(NullPointerException e) {
	    	JOptionPane.showMessageDialog(null,"Enter valid key");
	    	return;
	    }
		
		
		int key=Integer.parseInt(fKey.getText());

		File file = new File(filepath);
		
		

		
		try {
			FileInputStream fis = new FileInputStream(file);
			FileInputStream fis2 = new FileInputStream(file);
			
			
			
			if(fis.available()>6) {
			
				byte data[]=new byte[fis.available()-6];
				byte check[]=new byte[6];
			
				fis.read(data);
				fis.read(check);
			
			
				//checking if file is already encrypted
				String checkStr=new String(check);
				if(checkStr.startsWith("enc")) {
					JOptionPane.showMessageDialog(null, "File is already encrypted");
					fis.close();
					return;
				}else {
					
					byte data2[]=new byte[fis2.available()];
					fis2.read(data2);
					
				
					int i=0;
					for(byte b:data2) {
						
						data2[i]=(byte)(b^key);
						
						i++;
					}
				
					
				
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(data2);
					fos.write(extraByte.getBytes());
					fos.close();
					fis2.close();
					fis.close();
					JOptionPane.showMessageDialog(null,"Encrypted ");
					
				}
				
			
			}else {
				
				byte data[]=new byte[fis2.available()];
				fis2.read(data);
				
			
				int i=0;
				for(byte b:data) {
					data[i]=(byte)(b^key);
					i++;
				}
			
			
			
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data);
				fos.write(extraByte.getBytes());
				fos.close();
				fis2.close();
				JOptionPane.showMessageDialog(null,"Encrypted ");
				
			}
			
//			fFileName.setVisible(false);
//			fPath.setVisible(false);	
//			fNameText.setText("");
//			fPathText.setText("");
//			fKey.setText("");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	private void decrypt() {
		
		
		try { 
	        Integer.parseInt(fKey.getText()); 
	    } catch(NumberFormatException e) { 
	    	JOptionPane.showMessageDialog(null,"Enter valid key");
	        return ; 
	    } catch(NullPointerException e) {
	    	JOptionPane.showMessageDialog(null,"Enter valid key");
	    	return;
	    }
		
		int key=Integer.parseInt(fKey.getText());
		

		File file = new File(filepath);
		try {
			FileInputStream fis = new FileInputStream(file);
			
			
			
			if(fis.available()>6) {
			
				byte data[]=new byte[fis.available()-6];

				byte check[]=new byte[6];
			
				fis.read(data);
				fis.read(check);
			
			
				//checking if file is encrypted
				String checkStr=new String(check);
				if(checkStr.startsWith("enc")) {
					
					
					if(checkStr.endsWith(fKey.getText())) {
						
						int i=0;
						for(byte b:data) {
							
							data[i]=(byte)(b^key);
							
							i++;
						}
						
					
					
					
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(data);
						fos.close();
						fis.close();
						JOptionPane.showMessageDialog(null,"Decrypted ");
						
					}
					else {
						JOptionPane.showMessageDialog(null,"Wrong Key");
					}
	
					
				}else {
					JOptionPane.showMessageDialog(null, "File is already in original state..!");
					fis.close();
	
				}
			
			}else {
				
				JOptionPane.showMessageDialog(null, "File is already in original state..!");
				fis.close();
			}
				
			
				
			
//			fFileName.setVisible(false);
//			fPath.setVisible(false);	
//			fNameText.setText("");
//			fPathText.setText("");
//			fKey.setText("");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
