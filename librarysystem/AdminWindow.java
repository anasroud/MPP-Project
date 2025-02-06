package librarysystem;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.UtilityClass;

public class AdminWindow extends JFrame implements LibWindow {
	public static final AdminWindow INSTANCE = new AdminWindow();
	private static final long serialVersionUID = 1L;
	private boolean isInitialized = false;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow frame = new AdminWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class AddLibraryMemberWindowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AddLibraryMemberWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(AddLibraryMemberWindow.INSTANCE);
			AddLibraryMemberWindow.INSTANCE.setVisible(true);

		}

	}

	class LoopkUpBookWindowListener implements ActionListener {
		//
		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			LookUpBookWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(LookUpBookWindow.INSTANCE);
			LookUpBookWindow.INSTANCE.setVisible(true);

		}

	}

	/**
	 * Create the frame.
	 */
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		setSize(660, 500);
		setTitle("Admin Menu");
		UtilityClass.centerFrameOnDesktop(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton addNewMember = new JButton("Add Member");
		addNewMember.setBounds(252, 80, 156, 41);
		addNewMember.addActionListener(new AddLibraryMemberWindowListener());
		contentPane.add(addNewMember);

		JButton addNewBook = new JButton("Add Book");
		addNewBook.setBounds(252, 143, 156, 41);
		addBookButtonListener(addNewBook);
		contentPane.add(addNewBook);

		JButton lookUp = new JButton("Add Copies...");
		lookUp.setBounds(252, 205, 156, 41);
		lookUp.addActionListener(new LoopkUpBookWindowListener());
		contentPane.add(lookUp);

		JButton backButton = new JButton("<= Back");
		backButton.setBounds(507, 414, 95, 23);
		addBackButtonListener(backButton);
		contentPane.add(backButton);
		JLabel jlabelLabel = new JLabel("");
		jlabelLabel.setBounds(0, 0, 265, 463);
		contentPane.add(jlabelLabel);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		JFrame adminFrame = new JFrame("Admin");
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setSize(400, 200);

		JButton addMemberButton = new JButton("Add Member");
		JButton addBookButton = new JButton("Add Book");
		JButton addCopyButton = new JButton("Add Copy");

		adminFrame.getContentPane().add(addMemberButton);
		adminFrame.getContentPane().add(addBookButton);
		adminFrame.getContentPane().add(addCopyButton);
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

	private void addBookButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			BookWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(BookWindow.INSTANCE);
			BookWindow.INSTANCE.setVisible(true);

		});
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}
	
}
