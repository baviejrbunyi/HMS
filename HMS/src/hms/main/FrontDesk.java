package hms.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import hms.employee.panels.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;

public class FrontDesk extends Resources implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// labels
	JLabel newLogo = new JLabel(logo);
	JLabel navLabel = new JLabel("NAVIGATION");
	JLabel home = new JLabel("HOME");
	JLabel transaction = new JLabel("TRANSACTION");
	JLabel bookings = new JLabel("VERIFY BOOKINGS");
	JLabel history = new JLabel("HISTORY");
	JLabel rooms = new JLabel("ROOMS");
	JLabel account = new JLabel("ACCOUNT SETTINGS");

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

		bookings.setBounds(23, transaction.getY() + 46, 300, 23);
		bookings.setFont(robotoBold);
		bookings.setForeground(babyBlue);
		bookings.addMouseListener(this);

		history.setBounds(23, bookings.getY() + 46, 300, 23);
		history.setFont(robotoBold);
		history.setForeground(babyBlue);
		history.addMouseListener(this);

		rooms.setBounds(23, history.getY() + 46, 300, 23);
		rooms.setFont(robotoBold);
		rooms.setForeground(babyBlue);
		rooms.addMouseListener(this);

		account.setBounds(23, 774, 300, 23);
		account.setFont(robotoBold);
		account.setForeground(babyBlue);
		account.addMouseListener(this);

	}

	// panels
	JPanel navigation = new JPanel();
	HomePanel homePanel = new HomePanel();
	public static TransactionPanel transactionPanel = new TransactionPanel();
	BookingsPanel bookingsPanel = new BookingsPanel();
	HistoryPanel historyPanel = new HistoryPanel();
	RoomsPanel roomsPanel = new RoomsPanel();
	AccountPanel accountPanel = new AccountPanel();
	JButton logoutButton = new JButton("SIGN OUT");

	void Panels() {
		navigation.setPreferredSize(new Dimension(400, 1080));
		navigation.setBackground(navyBlue);
		navigation.setLayout(null);
		navigation.add(newLogo);
		navigation.add(navLabel);
		navigation.add(home);
		navigation.add(transaction);
		navigation.add(bookings);
		navigation.add(history);
		navigation.add(rooms);
		navigation.add(account);

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

	void Layered_Pane() {

		layer.setPreferredSize(new Dimension(1420, 1080));

		layer.add(homePanel);
		layer.add(transactionPanel);
		layer.add(bookingsPanel);
		layer.add(historyPanel);
		layer.add(roomsPanel);
		layer.add(accountPanel);

	}

	FrontDesk() {
		this.setSize(1820, 1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(babyBlue);
		this.setTitle("HMS - Cashier");
		this.setIconImage(icon);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());

		Labels();
		Panels();
		Layered_Pane();

		getContentPane().add(navigation, BorderLayout.WEST);
		this.getContentPane().add(layer, BorderLayout.EAST);
		this.setLocationRelativeTo(null);
		Switch_Screen(homePanel);
		this.setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logout_Time();
			}
		});
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
		} else if (e.getSource() == bookings) {

			Switch_Screen(bookingsPanel);
		} else if (e.getSource() == history) {

			Switch_Screen(historyPanel);
		} else if (e.getSource() == rooms) {

			Switch_Screen(roomsPanel);
		} else if (e.getSource() == account) {

			Switch_Screen(accountPanel);
		} else if (e.getSource() == logoutButton) {

			int response = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				logout_Time();
				new Login();
				this.dispose();
			}
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
		} else if (e.getSource() == bookings) {

			bookings.setForeground(orangeAccent);
		} else if (e.getSource() == history) {

			history.setForeground(orangeAccent);
		} else if (e.getSource() == rooms) {

			rooms.setForeground(orangeAccent);
		} else if (e.getSource() == account) {

			account.setForeground(orangeAccent);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == home) {

			home.setForeground(babyBlue);
		} else if (e.getSource() == transaction) {

			transaction.setForeground(babyBlue);
		} else if (e.getSource() == bookings) {

			bookings.setForeground(babyBlue);
		} else if (e.getSource() == history) {

			history.setForeground(babyBlue);
		} else if (e.getSource() == rooms) {

			rooms.setForeground(babyBlue);
		} else if (e.getSource() == account) {

			account.setForeground(babyBlue);
		}

	}

	/**
	 * Fetches the time of the user's log out. The time fetched will be stored in
	 * the database.
	 * 
	 */

	public void logout_Time() {

		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String last_logout_time = timeFormat.format(time);

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update `hotel_management_system`.`attendance` SET `last_logout_time` = '" + last_logout_time
					+ "' WHERE (`id` = '" + Login.getAttendanceId() + "');";

			Statement statement = connection.createStatement();
			statement.execute(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
