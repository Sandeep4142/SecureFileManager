package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DataDAO;
import dao.UserDAO;
import model.Data;

import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingUserView2 {

	JFrame userFrame;
	private JLabel fPath;
	private JLabel fFileName;
	private JLabel fNameText;
	private JLabel fPathText;
	private String filename;
	private String filepath;
	
	private static String email;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	
	DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table_1;
	private JTextField fileID;
	private JLabel userName;
	private JButton logOutBtn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SwingUserView2 window = new SwingUserView2(email);
//					window.userFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SwingUserView2(String email) {
		this.email=email;
		
		try {
			initialize(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showHiddenFiles();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(final String email) throws SQLException {
		
		//USER NAME 
		
		String user = "Welcome "+UserDAO.getUserName(email);
		
		
		//////
		userFrame = new JFrame();
		
		userFrame.setSize(876,528);
		userFrame.setLocationRelativeTo(null);
		
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userFrame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 56, 842, 425);
		userFrame.getContentPane().add(tabbedPane);
		
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Hide Files", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton chooseBtn = new JButton("Select File");
		chooseBtn.addActionListener(new ActionListener() {
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
				
//				File file = fileChooser.getSelectedFile();
			}
		});
		chooseBtn.setBounds(269, 31, 116, 34);
		panel_1.add(chooseBtn);
		
		JButton hideBtn = new JButton("Hide File");
		hideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fPathText.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please select file first");
				
				}else {
				
				fFileName.setVisible(false);
				fPath.setVisible(false);
				
				fNameText.setText("");
				fPathText.setText("");
				
				
				File f=new File(filepath);
				Data file=new Data(0,f.getName(),filepath,email);
				try {
				DataDAO.hideFile(file);
				
				
				//update table
				model=(DefaultTableModel)table_1.getModel();
				model.setRowCount(0);
				showHiddenFiles();
				}catch (IOException e1) {
					System.out.println("File not Found");
//					e.printStackTrace();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
		
				
				JOptionPane.showMessageDialog(null,"File Hidden Succefully");		
			}
			}
		});
		hideBtn.setBounds(443, 31, 116, 34);
		panel_1.add(hideBtn);
		
		fFileName = new JLabel("File Name : ");
		fFileName.setFont(new Font("Tahoma", Font.BOLD, 11));
		fFileName.setBounds(120, 130, 65, 24);
		fFileName.setVisible(false);
		panel_1.add(fFileName);
		
		fPath = new JLabel("Path :");
		fPath.setFont(new Font("Tahoma", Font.BOLD, 11));
		fPath.setBounds(120, 165, 65, 24);
		fPath.setVisible(false);
		panel_1.add(fPath);
		
		fNameText = new JLabel("");
		fNameText.setFont(new Font("Tahoma", Font.BOLD, 11));
		fNameText.setBounds(206, 130, 374, 24);
		panel_1.add(fNameText);
		
		fPathText = new JLabel("");
		fPathText.setFont(new Font("Tahoma", Font.BOLD, 11));
		fPathText.setBounds(206, 165, 602, 24);
		panel_1.add(fPathText);
		
		panel = new JPanel();
		tabbedPane.addTab("Show Hidden Files", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(31, 37, 741, 268);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table_1.getSelectedRow();
				fileID.setText(model.getValueAt(i, 0).toString());
				
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "FILE NAME", "PATH"
			}
		));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(160);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(397);
		scrollPane_1.setViewportView(table_1);
		
		JLabel selectIDlabel = new JLabel("Enter file ID to UNHIDE  :");
		selectIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectIDlabel.setBounds(153, 341, 167, 20);
		panel.add(selectIDlabel);
		
		fileID = new JTextField();
		fileID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileID.setBounds(330, 342, 64, 19);
		panel.add(fileID);
		fileID.setColumns(10);
		
		JButton btnNewButton = new JButton("UNHIDE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unHideFiles(fileID.getText());
				fileID.setText("");
				model=(DefaultTableModel)table_1.getModel();
				model.setRowCount(0);
				showHiddenFiles();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(421, 341, 118, 21);
		panel.add(btnNewButton);
		
		userName = new JLabel("");
		userName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(179, 20, 467, 36);
		userName.setText(user);
		userFrame.getContentPane().add(userName);
		
		logOutBtn = new JButton("LOGOUT");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWelcomeView su=new SwingWelcomeView();
				su.loginFrame.setVisible(true);
				
				userFrame.dispose();
			}
		});
		logOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logOutBtn.setBounds(715, 10, 124, 21);
		userFrame.getContentPane().add(logOutBtn);
		

		
		
	}
	
	private void showHiddenFiles() {
		
		try {
			List<Data> files = DataDAO.getAllFiles(email);
			Object obj[][]=new Object[files.size()][3];
			
			model=(DefaultTableModel)table_1.getModel();
			Object[] row=new Object[3];
			

	
			for(Data file:files) {
				System.out.println(file.getId()+" - "+file.getFilename());
				
				row[0]=file.getId();
				row[1]=file.getFilename();
				row[2]=file.getPath();
				
				model.addRow(row);

			}
	
			
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	private void unHideFiles(String fileID) {
		
		try { 
	        Integer.parseInt(fileID); 
	    } catch(NumberFormatException e) { 
	    	JOptionPane.showMessageDialog(null,"Enter valid ID");
	        return ; 
	    } catch(NullPointerException e) {
	    	JOptionPane.showMessageDialog(null,"Enter valid ID");
	    	return;
	    }
		
		
		List<Data> files =null;		
		try {
		 files = DataDAO.getAllFiles(email);
		

		int id=Integer.parseInt(fileID);
		
		boolean isValidID=false;
		for(Data file1:files) {
			if(file1.getId()==id) {
				isValidID=true;
				break;
			}
		}
		if(isValidID) {
			DataDAO.unhide(id);
			JOptionPane.showMessageDialog(null,"File UnHidden Successfully");
		}else {
			JOptionPane.showMessageDialog(null,"Enter valid ID");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
