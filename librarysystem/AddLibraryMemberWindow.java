package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.BookException;
import business.LibraryMember;
import business.LibrarySystemException;
import business.RandomNumber;
import business.SystemController;
import business.UtilityClass;
import dataaccess.Auth;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSlider;

public class AddLibraryMemberWindow extends JFrame implements LibWindow {

	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	public static final AddLibraryMemberWindow INSTANCE = new AddLibraryMemberWindow();

	private boolean isInitialized = false;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField memberID;
	private JTextField telephone;
	private JTextField city;
	private JTextField street;
	private JTextField state;
	private JTextField zip;
	private JLabel memberIDLabel;
	/**
	 * @wbp.nonvisual location=154,809
	 */

	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel telephoneLabel;
	private JLabel cityLabel;
	private JLabel streetLabel;
	private JLabel stateLabel;
	private JLabel zipLabel;
	private JLabel pictureLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibraryMemberWindow frame = new AddLibraryMemberWindow();
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
	public AddLibraryMemberWindow() {
		setSize(660, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UtilityClass.centerFrameOnDesktop(this);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add New Library Members");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(199, 11, 239, 25);
		mainPane.add(lblNewLabel);

		firstName = new JTextField();
		firstName.setBounds(82, 85, 96, 20);
		mainPane.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setBounds(261, 85, 96, 20);
		mainPane.add(lastName);
		lastName.setColumns(10);

		memberID = new JTextField();
		memberID.setBounds(82, 50, 96, 20);
		memberID.setText(Integer.toString(RandomNumber.generateRandomFourDigitNumber()));
		mainPane.add(memberID);
		memberID.setColumns(10);

		telephone = new JTextField();
		telephone.setBounds(82, 116, 96, 20);
		mainPane.add(telephone);
		telephone.setColumns(10);

		city = new JTextField();
		city.setBounds(82, 170, 96, 20);
		mainPane.add(city);
		city.setColumns(10);

		street = new JTextField();
		street.setBounds(430, 170, 96, 20);
		mainPane.add(street);
		street.setColumns(10);

		state = new JTextField();
		state.setBounds(82, 201, 96, 20);
		mainPane.add(state);
		state.setColumns(10);

		zip = new JTextField();
		zip.setBounds(261, 170, 96, 20);
		mainPane.add(zip);
		zip.setColumns(10);

		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(new Color(255, 255, 255));
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		submitButton.setIcon(null);
		submitButton.setForeground(new Color(102, 153, 0));
		submitButtonListener(submitButton);
		submitButton.setBounds(248, 230, 91, 31);
		mainPane.add(submitButton);

		memberIDLabel = new JLabel("Member ID");
		memberIDLabel.setBounds(10, 53, 67, 14);
		mainPane.add(memberIDLabel);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(10, 85, 67, 14);
		mainPane.add(firstNameLabel);

		lastNameLabel = new JLabel("LastName");
		lastNameLabel.setBounds(199, 88, 62, 14);
		mainPane.add(lastNameLabel);

		telephoneLabel = new JLabel("Phone");
		telephoneLabel.setBounds(10, 119, 49, 14);
		mainPane.add(telephoneLabel);

		cityLabel = new JLabel("City");
		cityLabel.setBounds(10, 173, 49, 14);
		mainPane.add(cityLabel);

		streetLabel = new JLabel("Street");
		streetLabel.setBounds(389, 173, 49, 14);
		mainPane.add(streetLabel);

		stateLabel = new JLabel("State");
		stateLabel.setBounds(10, 204, 49, 14);
		mainPane.add(stateLabel);

		zipLabel = new JLabel("ZIP");
		zipLabel.setBounds(215, 173, 49, 14);
		mainPane.add(zipLabel);

		JButton backButton = new JButton("<---");
		backButton.setIcon(null);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setForeground(new Color(128, 64, 64));
		backButton.setBounds(0, 0, 62, 25);
		addBackButtonListener(backButton);
		mainPane.add(backButton);

		pictureLabel = new JLabel("");
		pictureLabel.setIcon(new ImageIcon(AddLibraryMemberWindow.class.getResource("/librarysystem/book.jpg")));
		pictureLabel.setBounds(0, 278, 666, 219);
		mainPane.add(pictureLabel);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		addressLabel.setBounds(261, 142, 82, 20);
		mainPane.add(addressLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(192, 192, 192));
		separator.setBounds(10, 142, 516, 1);
		mainPane.add(separator);
	}

	@Override
	public boolean isInitialized() {

		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	private void submitButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			String memberId = memberID.getText();
			String fName = firstName.getText();
			String lName = lastName.getText();
			String phone = telephone.getText();
			String cityVal = city.getText();
			String zipVal = zip.getText();
			String stateVal = state.getText();
			String streetVal = street.getText();
			Address a = new Address(streetVal, cityVal, stateVal, zipVal);
			LibraryMember lb = new LibraryMember(memberId, fName, lName, phone, a);
			SystemController controller = new SystemController();
			try {
				controller.addMember(lb);
				JOptionPane.showMessageDialog(AddLibraryMemberWindow.this,
						"Member Successfullt created:\n" + "Member ID: " + lb.getMemberId() + "\n" + "Name: "
								+ lb.getFirstName() + " " + lb.getLastName() + "\n" + "Phone: " + lb.getTelephone()
								+ "\n" + "Address: " + lb.getAddress());
				memberID.setText(Integer.toString(RandomNumber.generateRandomFourDigitNumber()));
				firstName.setText("");
				lastName.setText("");
				telephone.setText("");
				city.setText("");
				zip.setText("");
				state.setText("");
				street.setText("");
			} catch (LibrarySystemException ex) {
				JOptionPane.showMessageDialog(AddLibraryMemberWindow.this, "Error: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		});
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			AddLibraryMemberWindow.this.setVisible(false);
			if (SystemController.currentAuth.equals(Auth.ADMIN))
				AdminWindow.INSTANCE.setVisible(true);
			else
				BothUserWindow.INSTANCE.setVisible(true);
		});
	}
}
