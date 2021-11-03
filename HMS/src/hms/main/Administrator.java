package hms.main;

import javax.swing.*;

import hms.admin.panels.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Administrator extends Resources implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// panels
	JPanel navigation = new JPanel();

	// panels from package hms.admin.panels
	HomePanel homePanel = new HomePanel();
	TransactionPanel transactionPanel = new TransactionPanel();
	RoomInformationPanel roomPanel = new RoomInformationPanel();
	ServicesPanel servicesPanel = new ServicesPanel();
	EmployeePanel employeePanel = new EmployeePanel();
	BusinessStatsPanel businessStatsPanel = new BusinessStatsPanel();
	AccountPanel accountPanel = new AccountPanel();

	void Panels() {
		// navigation panel
		navigation.setPreferredSize(new Dimension(400, 1080));
		navigation.setBackground(navyBlue);
		navigation.setLayout(null);
		navigation.add(newLogo);
		navigation.add(navLabel);
		navigation.add(home);
		navigation.add(transaction);
		navigation.add(rooms);
		navigation.add(services);
		navigation.add(employee);
		navigation.add(businessStats);
		navigation.add(account);

		// layered panel
		layer.setPreferredSize(new Dimension(1420, 1080));

		// adding to the layered panel
		layer.add(homePanel);
		layer.add(transactionPanel);
		layer.add(roomPanel);
		layer.add(employeePanel);
		layer.add(servicesPanel);
		layer.add(businessStatsPanel);
		layer.add(accountPanel);

		// addinglogoutButton to accountPanel
		logoutButton.setBounds(620, 760, 225, 60);
		logoutButton.setBackground(navyBlue);
		logoutButton.setForeground(babyBlue);
		logoutButton.setFocusable(false);
		logoutButton.setFont(robotoBold14);
		logoutButton.addMouseListener(this);
		accountPanel.add(logoutButton);

	}

	// layered pane
	static JLayeredPane layer = new JLayeredPane();

	// labels
	JLabel newLogo = new JLabel(logo);
	JLabel navLabel = new JLabel("NAVIGATION");
	JLabel home = new JLabel("HOME");
	JLabel transaction = new JLabel("TRANSACTION");
	JLabel rooms = new JLabel("ROOMS");
	JLabel services = new JLabel("SERVICES");
	JLabel employee = new JLabel("EMPLOYEES");
	JLabel businessStats = new JLabel("BUSINESS STATISTICS");
	JLabel account = new JLabel("ACCOUNT SETTINGS");
	JLabel lol = new JLabel("Lol");

	void Labels() {
		newLogo.setBounds(20, 40, 87, 49);

		navLabel.setBounds(234, 58, 147, 29);
		navLabel.setFont(robotoNav);
		navLabel.setForeground(blueGreen);

		home.setBounds(23, 154, 300, 23);
		home.setFont(robotoBold);
		home.setForeground(babyBlue);
		home.addMouseListener(this);

		transaction.setBounds(23, home.getY() + 46, 300, 23);
		transaction.setFont(robotoBold);
		transaction.setForeground(babyBlue);
		transaction.addMouseListener(this);

		rooms.setBounds(23, transaction.getY() + 46, 300, 23);
		rooms.setFont(robotoBold);
		rooms.setForeground(babyBlue);
		rooms.addMouseListener(this);
		rooms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Switch_Screen(roomPanel);

			}
		});

		services.setBounds(23, rooms.getY() + 46, 300, 23);
		services.setFont(robotoBold);
		services.setForeground(babyBlue);
		services.addMouseListener(this);

		employee.setBounds(23, services.getY() + 46, 300, 23);
		employee.setFont(robotoBold);
		employee.setForeground(babyBlue);
		employee.addMouseListener(this);

		businessStats.setBounds(23, employee.getY() + 46, 300, 23);
		businessStats.setFont(robotoBold);
		businessStats.setForeground(babyBlue);
		businessStats.addMouseListener(this);

		account.setBounds(23, 774, 300, 23);
		account.setFont(robotoBold);
		account.setForeground(babyBlue);
		account.addMouseListener(this);
	}

	// buttons
	JButton logoutButton = new JButton("SIGN OUT");

	Administrator() {
		this.setSize(1820, 1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(babyBlue);
		this.setTitle("HMS - Administrator");
		this.setIconImage(icon);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		Labels();
		Panels();
		Switch_Screen(homePanel);

		this.add(navigation, BorderLayout.WEST);
		this.getContentPane().add(layer, BorderLayout.EAST);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void Switch_Screen(JPanel p) {
		layer.removeAll();
		layer.add(p);
		layer.repaint();
		layer.revalidate();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == home) {

			Switch_Screen(homePanel);
		} else if (e.getSource() == transaction) {

			Switch_Screen(transactionPanel);
		} else if (e.getSource() == businessStats) {

			Switch_Screen(businessStatsPanel);
		} else if (e.getSource() == account) {

			Switch_Screen(accountPanel);
		} else if (e.getSource() == logoutButton) {

			int response = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				new Login();
				this.dispose();
			}
		} else if (e.getSource() == services) {
			Switch_Screen(servicesPanel);
		} else if (e.getSource() == employee) {
			Switch_Screen(employeePanel);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == home) {

			home.setForeground(orangeAccent);
		} else if (e.getSource() == transaction) {

			transaction.setForeground(orangeAccent);
		} else if (e.getSource() == rooms) {
			rooms.setForeground(orangeAccent);
		} else if (e.getSource() == employee) {

			employee.setForeground(orangeAccent);
		} else if (e.getSource() == businessStats) {

			businessStats.setForeground(orangeAccent);
		} else if (e.getSource() == account) {

			account.setForeground(orangeAccent);
			AccountPanel.show_account_info();
		} else if (e.getSource() == services) {

			services.setForeground(orangeAccent);

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == home) {

			home.setForeground(babyBlue);
		} else if (e.getSource() == transaction) {

			transaction.setForeground(babyBlue);
		} else if (e.getSource() == rooms) {

			rooms.setForeground(babyBlue);
		} else if (e.getSource() == employee) {

			employee.setForeground(babyBlue);
		} else if (e.getSource() == businessStats) {

			businessStats.setForeground(babyBlue);
		} else if (e.getSource() == account) {

			account.setForeground(babyBlue);
		} else if (e.getSource() == services) {

			services.setForeground(babyBlue);

		}

	}

}
