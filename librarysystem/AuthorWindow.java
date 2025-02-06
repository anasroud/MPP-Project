package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.FormSpecs;
//import com.jgoodies.forms.layout.RowSpec;

import business.Address;
import business.Author;

import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AuthorWindow extends JFrame implements LibWindow {
	public static final AuthorWindow INSTANCE = new AuthorWindow();
	private static final long serialVersionUID = 1L;
	private boolean isInitialized = false;
	private JPanel contentPane;
	private JTextField city;
	private JTextField street;
	private JTextField zip;
	private JTextField state;
	private TextField fname;
	private TextField lname;
	private TextField bio;
	private JTextField telephone;
	static int row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorWindow frame = new AuthorWindow();
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
	public AuthorWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Author");
		//row = BookWindow.table.getSelectedRow();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fNameLabel = new JLabel("First Name:");
		fNameLabel.setBounds(15, 16, 89, 14);
		contentPane.add(fNameLabel);

		fname = new TextField();
		fname.setBounds(147, 16, 179, 22);
		fname.setText(BookWindow.table.getModel().getValueAt(row, 0) != null
				? BookWindow.table.getModel().getValueAt(row, 0).toString()
				: "");
		contentPane.add(fname);

		JLabel lNameLabel = new JLabel("Last Name:");
		lNameLabel.setBounds(15, 43, 89, 14);
		contentPane.add(lNameLabel);

		lname = new TextField();
		lname.setBounds(147, 43, 179, 22);
		lname.setText(BookWindow.table.getModel().getValueAt(row, 1) != null
				? BookWindow.table.getModel().getValueAt(row, 1).toString()
				: "");
		contentPane.add(lname);

		JLabel bioLabel = new JLabel("Bio:");
		bioLabel.setBounds(15, 75, 63, 14);
		contentPane.add(bioLabel);

		bio = new TextField();
		bio.setBounds(147, 71, 179, 22);
		bio.setText(BookWindow.table.getModel().getValueAt(row, 2) != null
				? BookWindow.table.getModel().getValueAt(row, 2).toString()
				: "");
		contentPane.add(bio);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setBounds(15, 102, 63, 14);
		contentPane.add(cityLabel);

		city = new JTextField();
		city.setBounds(147, 99, 179, 20);
		city.setText(BookWindow.table.getModel().getValueAt(row, 4) != null
				? BookWindow.table.getModel().getValueAt(row, 4).toString()
				: "");
		contentPane.add(city);
		city.setColumns(10);

		JLabel streetLabel = new JLabel("Street:");
		streetLabel.setBounds(15, 128, 63, 14);
		contentPane.add(streetLabel);

		street = new JTextField();
		street.setBounds(147, 125, 179, 20);
		street.setText(BookWindow.table.getModel().getValueAt(row, 5) != null
				? BookWindow.table.getModel().getValueAt(row, 5).toString()
				: "");
		contentPane.add(street);
		street.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		stateLabel.setBounds(15, 154, 63, 14);
		contentPane.add(stateLabel);

		state = new JTextField();
		state.setBounds(147, 151, 179, 20);
		state.setText(BookWindow.table.getModel().getValueAt(row, 6) != null
				? BookWindow.table.getModel().getValueAt(row, 6).toString()
				: "");
		contentPane.add(state);
		state.setColumns(10);

		JLabel zipLabel = new JLabel("Zip Code:");
		zipLabel.setBounds(15, 180, 81, 14);
		contentPane.add(zipLabel);

		zip = new JTextField();
		zip.setBounds(147, 177, 179, 20);
		zip.setText(BookWindow.table.getModel().getValueAt(row, 7) != null
				? BookWindow.table.getModel().getValueAt(row, 7).toString()
				: "");
		contentPane.add(zip);
		zip.setColumns(10);

		JButton closeButton = new JButton("Close");
		closeButton.setBounds(262, 227, 71, 23);
		addCloseButtonListener(closeButton);
		contentPane.add(closeButton);

		JButton addButton = new JButton("Add");
		addButton.setBounds(171, 227, 81, 23);
		addButtonListener(addButton);
		contentPane.add(addButton);

		JLabel telephoneLabel = new JLabel("Telephone:");
		telephoneLabel.setBounds(15, 206, 68, 14);
		contentPane.add(telephoneLabel);

		telephone = new JTextField();
		telephone.setBounds(147, 203, 179, 20);
		telephone.setText(BookWindow.table.getModel().getValueAt(row, 3) != null
				? BookWindow.table.getModel().getValueAt(row, 3).toString()
				: "");
		contentPane.add(telephone);
		telephone.setColumns(10);
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

	private void addCloseButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			AuthorWindow.INSTANCE.setVisible(false);
		});
	}

	private void addButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			Address address = new Address(street.getText(), city.getText(), state.getText(), zip.getText());
			Author author = new Author(fname.getText(), lname.getText(), telephone.getText(), address, bio.getText());
			BookWindow.authors.add(author);
			BookWindow.table.getModel().setValueAt(fname.getText(), row, 0);
			BookWindow.table.getModel().setValueAt(lname.getText(), row, 1);
			BookWindow.table.getModel().setValueAt(bio.getText(), row, 2);
			BookWindow.table.getModel().setValueAt(telephone.getText(), row, 3);
			BookWindow.table.getModel().setValueAt(city.getText(), row, 4);
			BookWindow.table.getModel().setValueAt(street.getText(), row, 5);
			BookWindow.table.getModel().setValueAt(state.getText(), row, 6);
			BookWindow.table.getModel().setValueAt(zip.getText(), row, 7);
			JOptionPane.showMessageDialog(this, "Successfully added.");
			AuthorWindow.INSTANCE.setVisible(false);
		});
	}

}
