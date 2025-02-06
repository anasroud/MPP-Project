package librarysystem;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.BookException;
import business.Checkout;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;

public class OverdueWindow extends JFrame implements LibWindow{

	private static final long serialVersionUID = 1L;
	public final static OverdueWindow INSTANCE =new OverdueWindow();
	private boolean isInitialized = false;
	private JPanel contentPane;
	private JTextField isbn;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OverdueWindow frame = new OverdueWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OverdueWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660, 500);
		setTitle("Overdue Menu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel isbnLabel = new JLabel("ISBN:");
		isbnLabel.setBounds(51, 36, 46, 14);
		contentPane.add(isbnLabel);
		
		isbn = new JTextField();
		isbn.setBounds(97, 33, 222, 20);
		contentPane.add(isbn);
		isbn.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(39, 36, 16, 14);
		contentPane.add(lblNewLabel);
		
		JButton searchButton = new JButton("Search...");
		searchButton.setBounds(407, 33, 89, 23);
		submitButtonListener(searchButton);
		contentPane.add(searchButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(500, 33, 89, 23);
		clearButtonListener(clearButton);
		contentPane.add(clearButton);
		
		JLabel overdueButton = new JLabel("Overdue info.");
		overdueButton.setBounds(39, 80, 82, 14);
		contentPane.add(overdueButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 105, 563, 280);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ISBN", "Title", "Copy Number", "Member ID", "Due date"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton backButton = new JButton("<=Back");
		backButton.setBounds(504, 407, 98, 23);
		addBackButtonListener(backButton);
		contentPane.add(backButton);
	}
	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			OverdueWindow.INSTANCE.setVisible(false);
			if (SystemController.currentAuth.equals(Auth.LIBRARIAN))
				LibrarionWindow.INSTANCE.setVisible(true);
			else
				BothUserWindow.INSTANCE.setVisible(true);
		});
	}
	private void clearButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			isbn.setText("");
		});
	}
	private void submitButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			ControllerInterface c = new SystemController();
			List<Checkout> recordList = c.checkOverdue(isbn.getText());
			if(recordList.size() > 0) {
				for (int i = 0; i < recordList.size(); i++) {					
					table.getModel().setValueAt(recordList.get(i).getCopy().getBook().getIsbn(), i, 0);
					table.getModel().setValueAt(recordList.get(i).getCopy().getBook().getTitle(), i, 1);
					table.getModel().setValueAt(recordList.get(i).getCopy().getCopyNum(), i, 2);
					table.getModel().setValueAt(recordList.get(i).getMemId(), i, 3);
					table.getModel().setValueAt(recordList.get(i).getDueDate(), i, 4);
			}	
			}else {
				JOptionPane.showMessageDialog(this, "Data not found.");
			}
		});
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
	}
}
