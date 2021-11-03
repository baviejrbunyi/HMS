package hms.admin.panels;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hms.frames.CheckIn;
import hms.frames.CheckOut;
import hms.frames.Dialog;
import hms.frames.ReSched;
import hms.main.Login;
import hms.objects.Transactions;

public class TransactionPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4640245301475511232L;

	// labels
	private JLabel panelTitle = new JLabel("TRANSACTIONS");

	private JLabel bookingInfoLabel = new JLabel("BOOKING INFORMATION");

	private JLabel transactionIdLabel = new JLabel("Transaction Id:");
	private JLabel transactionDateLabel = new JLabel("Transaction Date:");

	private JLabel transactionId = new JLabel("46");
	private JLabel transactionDate = new JLabel("2021-05-14");

	private JLabel userInformationLabel = new JLabel("User Information");
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel addressLabel = new JLabel("Address:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel mobileLabel = new JLabel("Mobile Number:");

	private JLabel name = new JLabel("Bavie A. Bunyi");
	private JTextArea address = new JTextArea("Manghinao Proper Bauan Batangas");
	private JLabel email = new JLabel("bbunyi7@email.com");
	private JLabel mobile = new JLabel("09217903647");

	private JLabel roomInformationLabel = new JLabel("Room Information");
	private JLabel roomNoLabel = new JLabel("Room Number:");
	private JLabel roomTypeLabel = new JLabel("Room Type:");
	private JLabel servicesLabel = new JLabel("Services:");
	private JLabel checkInLabel = new JLabel("Check In:");
	private JLabel checkOutLabel = new JLabel("Check Out:");

	private JLabel roomNo = new JLabel("006");
	private JLabel roomType = new JLabel("Single");
	private JLabel services = new JLabel("Package 1");
	private JLabel checkIn = new JLabel("2021-05-16");
	private JLabel checkOut = new JLabel("2021-05-19");

	private JLabel paymentInformationLabel = new JLabel("Payment Information");
	private JLabel methodLabel = new JLabel("Method:");
	private JLabel totalLabel = new JLabel("Total:");
	private JLabel downPaymentLabel = new JLabel("Down Payment:");
	private JLabel balanceLabel = new JLabel("Balance:");
	private JLabel statusLabel = new JLabel("Status:");

	private JLabel method = new JLabel("Visa 091");
	private JLabel total = new JLabel("4500");
	private JLabel downPayment = new JLabel("500");
	private JLabel balance = new JLabel("4000");
	private JLabel status = new JLabel("Pending");

	private JLabel services_cost = new JLabel();
	private JLabel rate_per_night = new JLabel();

	private final String[] filterOptions = { "Filter", "All", "Checked In", "This Day", "Due", "Cancelled", "Completed",
			"Confirmed" };
	private JComboBox<String> filter = new JComboBox<String>(filterOptions);

	private JTextField searchField = new JTextField("Search transactions by ID");

	void Labels() {

		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		bookingInfoLabel.setBounds(0, 0, 200, 20);
		bookingInfoLabel.setFont(robotoBold14);

		transactionIdLabel.setBounds(0, 40, 150, 20);
		transactionIdLabel.setFont(robotoPlain14);

		transactionDateLabel.setBounds(0, 70, 150, 20);
		transactionDateLabel.setFont(robotoPlain14);

		transactionId.setBounds(150, 40, 150, 20);
		transactionId.setFont(robotoPlain14);

		transactionDate.setBounds(150, 70, 150, 20);
		transactionDate.setFont(robotoPlain14);

		userInformationLabel.setBounds(0, 130, 150, 20);
		userInformationLabel.setFont(robotoBold14);

		nameLabel.setBounds(0, 160, 150, 20);
		nameLabel.setFont(robotoPlain14);

		addressLabel.setBounds(0, 190, 150, 20);
		addressLabel.setFont(robotoPlain14);

		emailLabel.setBounds(0, 270, 150, 20);
		emailLabel.setFont(robotoPlain14);

		mobileLabel.setBounds(0, 300, 150, 20);
		mobileLabel.setFont(robotoPlain14);

		name.setBounds(150, 160, 150, 20);
		name.setFont(robotoPlain14);

		address.setBounds(150, 190, 150, 50);
		address.setFont(robotoPlain14);
		address.setBackground(null);
		address.setWrapStyleWord(true);
		address.setLineWrap(true);
		address.setFocusable(false);

		email.setBounds(150, 270, 150, 20);
		email.setFont(robotoPlain14);

		mobile.setBounds(150, 300, 150, 20);
		mobile.setFont(robotoPlain14);

		roomInformationLabel.setBounds(400, 130, 150, 20);
		roomInformationLabel.setFont(robotoBold14);

		roomNoLabel.setBounds(400, 160, 150, 20);
		roomNoLabel.setFont(robotoPlain14);

		roomTypeLabel.setBounds(400, 190, 150, 20);
		roomTypeLabel.setFont(robotoPlain14);

		servicesLabel.setBounds(400, 220, 150, 20);
		servicesLabel.setFont(robotoPlain14);

		checkInLabel.setBounds(400, 250, 150, 20);
		checkInLabel.setFont(robotoPlain14);

		checkOutLabel.setBounds(400, 280, 150, 20);
		checkOutLabel.setFont(robotoPlain14);

		roomNo.setBounds(550, 160, 150, 20);
		roomNo.setFont(robotoPlain14);
		roomNo.setAlignmentX(Component.RIGHT_ALIGNMENT);

		roomType.setBounds(550, 190, 150, 20);
		roomType.setFont(robotoPlain14);
		roomType.setAlignmentX(Component.RIGHT_ALIGNMENT);

		services.setBounds(550, 220, 150, 20);
		services.setFont(robotoPlain14);
		services.setAlignmentX(Component.RIGHT_ALIGNMENT);

		checkIn.setBounds(550, 250, 150, 20);
		checkIn.setFont(robotoPlain14);
		checkIn.setAlignmentX(Component.RIGHT_ALIGNMENT);

		checkOut.setBounds(550, 280, 150, 20);
		checkOut.setFont(robotoPlain14);
		checkOut.setAlignmentX(Component.RIGHT_ALIGNMENT);

		paymentInformationLabel.setBounds(700, 130, 150, 20);
		paymentInformationLabel.setFont(robotoBold14);

		totalLabel.setBounds(700, 160, 150, 20);
		totalLabel.setFont(robotoPlain14);

		downPaymentLabel.setBounds(700, 190, 150, 20);
		downPaymentLabel.setFont(robotoPlain14);

		balanceLabel.setBounds(700, 220, 150, 20);
		balanceLabel.setFont(robotoPlain14);

		methodLabel.setBounds(700, 250, 150, 20);
		methodLabel.setFont(robotoPlain14);

		statusLabel.setBounds(700, 280, 150, 20);
		statusLabel.setFont(robotoPlain14);

		total.setBounds(850, 160, 150, 20);
		total.setFont(robotoPlain14);
		total.setAlignmentX(Component.RIGHT_ALIGNMENT);

		downPayment.setBounds(850, 190, 150, 20);
		downPayment.setFont(robotoPlain14);
		downPayment.setAlignmentX(Component.RIGHT_ALIGNMENT);

		balance.setBounds(850, 220, 150, 20);
		balance.setFont(robotoPlain14);
		balance.setAlignmentX(Component.RIGHT_ALIGNMENT);

		method.setBounds(850, 250, 150, 20);
		method.setFont(robotoPlain14);
		method.setAlignmentX(Component.RIGHT_ALIGNMENT);

		status.setBounds(850, 280, 150, 20);
		status.setFont(robotoPlain14);

		filter.setBounds(850, 0, 150, 25);
		filter.setFont(robotoPlain14);
		filter.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String allQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && confirmed = 'Yes' && status != 'Cancelling'";
				String checkedInQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ "&& status = 'Checked In' order by check_in asc";
				String thisDayQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && check_in = '" + java.time.LocalDate.now() + "' order by check_in asc";
				String dueQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && check_in < '" + java.time.LocalDate.now()
						+ "' && status = 'Confirmed' order by check_in asc";
				String cancelledQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && ( status = 'Cancelled' || status = 'Cancelled by Staff') order by check_in asc";
				String completedQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && status = 'Completed' order by check_in asc";
				String confirmedQuery = "select * from transaction where ref_id = " + Login.getCompanyId()
						+ " && confirmed = 'Yes' && status = 'Confirmed' order by check_in asc";

				switch (filter.getSelectedIndex()) {
				case 0:
					try {
						display_table(data_list(allQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 1:
					try {
						display_table(data_list(allQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 2:
					try {
						display_table(data_list(checkedInQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 3:
					try {
						display_table(data_list(thisDayQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 4:
					try {
						display_table(data_list(dueQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 5:
					try {
						display_table(data_list(cancelledQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 6:
					try {
						display_table(data_list(completedQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 7:
					try {
						display_table(data_list(confirmedQuery), table);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;

				}
			}
		});

		searchField.setBounds(100, 0, 750, 25);
		searchField.setFont(robotoPlain14);
		searchField.setBorder(BorderFactory.createCompoundBorder(searchField.getBorder(),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		searchField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (searchField.getText().equals("Search transactions by ID")) {
					searchField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (searchField.getText().equals("")) {
					searchField.setText("Search transactions by ID");
				}
			}
		});
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() >= 48 && e.getKeyChar() <= 57) || e.getKeyCode() == 8) {

				} else if (e.getKeyCode() == 10) {

					searchById(searchField.getText(), table);

				} else {

					searchField.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter numerical values only");
					searchField.setEditable(true);
				}
			}
		});

		// adding to the bookingInfoPanel
		bookingInfoPanel.add(bookingInfoLabel);

		bookingInfoPanel.add(transactionIdLabel);
		bookingInfoPanel.add(transactionDateLabel);

		bookingInfoPanel.add(transactionId);
		bookingInfoPanel.add(transactionDate);

		bookingInfoPanel.add(userInformationLabel);
		bookingInfoPanel.add(nameLabel);
		bookingInfoPanel.add(addressLabel);
		bookingInfoPanel.add(emailLabel);
		bookingInfoPanel.add(mobileLabel);

		bookingInfoPanel.add(name);
		bookingInfoPanel.add(address);
		bookingInfoPanel.add(email);
		bookingInfoPanel.add(mobile);

		bookingInfoPanel.add(roomInformationLabel);
		bookingInfoPanel.add(roomNoLabel);
		bookingInfoPanel.add(roomTypeLabel);
		bookingInfoPanel.add(servicesLabel);
		bookingInfoPanel.add(checkInLabel);
		bookingInfoPanel.add(checkOutLabel);

		bookingInfoPanel.add(roomNo);
		bookingInfoPanel.add(roomType);
		bookingInfoPanel.add(services);
		bookingInfoPanel.add(checkIn);
		bookingInfoPanel.add(checkOut);

		bookingInfoPanel.add(paymentInformationLabel);
		bookingInfoPanel.add(totalLabel);
		bookingInfoPanel.add(downPaymentLabel);
		bookingInfoPanel.add(balanceLabel);
		bookingInfoPanel.add(methodLabel);
		bookingInfoPanel.add(statusLabel);

		bookingInfoPanel.add(method);
		bookingInfoPanel.add(total);
		bookingInfoPanel.add(downPayment);
		bookingInfoPanel.add(balance);
		bookingInfoPanel.add(status);

		bookingInfoPanel.add(rate_per_night);
		bookingInfoPanel.add(services_cost);

		// adding to the panel
		this.add(panelTitle);
		searchAndFilterPanel.add(filter);
		searchAndFilterPanel.add(searchField);

	}

	// button
	private JButton rightButton = new JButton("CANCEL");
	private JButton centerButton = new JButton("RE-SCHED");
	private JButton leftButton = new JButton("CHECK IN");
	private JButton deleteButton = new JButton("DELETE");
	private JButton searchButton = new JButton("SEARCH");

	void Buttons() {

		leftButton.setBounds(0, 350, 100, 40);
		leftButton.setFont(robotoBold10);
		leftButton.setBackground(navyBlue);
		leftButton.setForeground(babyBlue);
		leftButton.setFocusable(false);
		leftButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (leftButton.getText().equals("CHECK OUT")) {
					check_out();
				} else {
					check_in();
				}
			}
		});

		centerButton.setBounds(150, 350, 100, 40);
		centerButton.setFont(robotoBold10);
		centerButton.setBackground(navyBlue);
		centerButton.setForeground(babyBlue);
		centerButton.setFocusable(false);

		centerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (centerButton.getText().equals("RE-SCHED")) {
					new ReSched(Integer.parseInt(transactionId.getText()), checkIn.getText(), checkOut.getText());
				}
			}
		});

		rightButton.setBounds(300, 350, 100, 40);
		rightButton.setFont(robotoBold10);
		rightButton.setBackground(navyBlue);
		rightButton.setForeground(babyBlue);
		rightButton.setFocusable(false);

		rightButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (rightButton.getText().equals("CANCEL")) {
					cancel_booking(Integer.parseInt(transactionId.getText()));
				}
			}
		});

		deleteButton.setBounds(900, 350, 100, 40);
		deleteButton.setFont(robotoBold10);
		deleteButton.setBackground(navyBlue);
		deleteButton.setForeground(babyBlue);
		deleteButton.setFocusable(false);

		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() > 0) {
					delete_transaction(Integer.parseInt(transactionId.getText()));
				} else {
					JOptionPane.showMessageDialog(null, "No room selected");
				}
			}
		});

		searchButton.setBounds(0, 0, 100, 25);
		searchButton.setForeground(babyBlue);
		searchButton.setBackground(navyBlue);
		searchButton.setFocusable(false);
		searchButton.setFont(robotoBold10);

		searchButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				searchById(searchField.getText(), table);
			}
		});

		// adding to bookingInfoPanel
		bookingInfoPanel.add(rightButton);
		bookingInfoPanel.add(centerButton);
		bookingInfoPanel.add(leftButton);
		bookingInfoPanel.add(deleteButton);
		searchAndFilterPanel.add(searchButton);

	}

	// table
	private JTable table;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Room Number", "Guest Name", "Check In", "Check Out", "Status", "Date", "Address",
				"Email", "Mobile Number", "Room Type", "Services", "Method", "Total", "Down Payment", "Balance",
				"Services Cost", "Rate Per Night" };

		// data
		Object[][] row = {};

		// creating the table
		table = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}

		};

		// table listener
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display_info(table, 0, true);

				if (table.getRowCount() > 0) {
					bookingInfoPanel.setVisible(true);
				} else {
					bookingInfoPanel.setVisible(false);
				}
			}
		});

		table.setModel(new DefaultTableModel(row, column));
		table.setRowHeight(40);
		table.setFillsViewportHeight(true);
		table.removeColumn(table.getColumn("Date"));
		table.removeColumn(table.getColumn("Address"));
		table.removeColumn(table.getColumn("Email"));
		table.removeColumn(table.getColumn("Mobile Number"));
		table.removeColumn(table.getColumn("Room Type"));
		table.removeColumn(table.getColumn("Services"));
		table.removeColumn(table.getColumn("Method"));
		table.removeColumn(table.getColumn("Total"));
		table.removeColumn(table.getColumn("Down Payment"));
		table.removeColumn(table.getColumn("Balance"));
		table.removeColumn(table.getColumn("Services Cost"));
		table.removeColumn(table.getColumn("Rate Per Night"));
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.setFont(robotoPlain14);
		table.setShowVerticalLines(false);
		table.getTableHeader().setFont(robotoBold14);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		// adding rows to the table
		try {
			display_table(data_list(), table);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(380, 150, 1000, 250);
		this.add(scrollPane);

	}

	// panel
	private JPanel bookingInfoPanel = new JPanel();
	private JPanel searchAndFilterPanel = new JPanel();

	void Panel() {

		bookingInfoPanel.setBounds(380, 425, 1000, 390);
		bookingInfoPanel.setLayout(null);

		searchAndFilterPanel.setBounds(380, 125, 1000, 25);
		searchAndFilterPanel.setBackground(errorRed);
		searchAndFilterPanel.setLayout(null);

		this.add(searchAndFilterPanel);
		this.add(bookingInfoPanel);
		// bookingInfoPanel.setBackground(errorRed);

	}

	public TransactionPanel() {

		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);
		Labels();
		Buttons();
		Table();
		Panel();

		if (table.getRowCount() != 0) {
			bookingInfoPanel.setVisible(true);
			display_info(table, 0, false);
		} else {
			bookingInfoPanel.setVisible(false);
		}
	}

	private ArrayList<Transactions> data_list(String query) {
		ArrayList<Transactions> transactionList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Transactions transactions;
			while (rs.next()) {

				int id = rs.getInt("id");
				int ref_id = rs.getInt("ref_id");
				int room_id = rs.getInt("room_id");
				int guest_ref = rs.getInt("guest_ref");
				int staff_id = rs.getInt("staff_id");
				String transaction_date = rs.getString("transaction_date");
				String staff = rs.getString("staff");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String nationality = rs.getString("nationality");
				String method = rs.getString("method");
				String type = rs.getString("type");
				String services = rs.getString("services");
				String check_in = rs.getString("check_in");
				String check_out = rs.getString("check_out");
				String number = rs.getString("number");
				String payment_status = rs.getString("payment_status");
				String confirmed = rs.getString("confirmed");
				String company = rs.getString("company");
				String status = rs.getString("status");
				double total = rs.getDouble("total");
				double paid = rs.getDouble("paid");
				double balance = rs.getDouble("balance");
				double services_cost = rs.getDouble("services_cost");
				double rate_per_night = rs.getDouble("rate_per_night");

				transactions = new Transactions(id, ref_id, room_id, guest_ref, staff_id, transaction_date, staff, name,
						address, email, age, mobile, nationality, method, type, services, check_in, check_out, number,
						payment_status, confirmed, company, status, total, paid, balance, services_cost,
						rate_per_night);

				transactionList.add(transactions);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionList;

	}

	private ArrayList<Transactions> data_list() {
		ArrayList<Transactions> transactionList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			String query = "select * from transaction where ref_id = " + Login.getCompanyId()
					+ " && confirmed = 'Yes' && status != 'Cancelling'";
			ResultSet rs = statement.executeQuery(query);

			Transactions transactions;
			while (rs.next()) {

				int id = rs.getInt("id");
				int ref_id = rs.getInt("ref_id");
				int room_id = rs.getInt("room_id");
				int guest_ref = rs.getInt("guest_ref");
				int staff_id = rs.getInt("staff_id");
				String transaction_date = rs.getString("transaction_date");
				String staff = rs.getString("staff");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String nationality = rs.getString("nationality");
				String method = rs.getString("method");
				String type = rs.getString("type");
				String services = rs.getString("services");
				String check_in = rs.getString("check_in");
				String check_out = rs.getString("check_out");
				String number = rs.getString("number");
				String payment_status = rs.getString("payment_status");
				String confirmed = rs.getString("confirmed");
				String company = rs.getString("company");
				String status = rs.getString("status");
				double total = rs.getDouble("total");
				double paid = rs.getDouble("paid");
				double balance = rs.getDouble("balance");
				double services_cost = rs.getDouble("services_cost");
				double rate_per_night = rs.getDouble("rate_per_night");

				transactions = new Transactions(id, ref_id, room_id, guest_ref, staff_id, transaction_date, staff, name,
						address, email, age, mobile, nationality, method, type, services, check_in, check_out, number,
						payment_status, confirmed, company, status, total, paid, balance, services_cost,
						rate_per_night);

				transactionList.add(transactions);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionList;

	}

	private void display_table(ArrayList<Transactions> datalist, JTable table) throws SQLException {
		ArrayList<Transactions> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[18];

		try {
			for (int i = 0; i < list.size(); i++) {
				row[0] = list.get(i).getId();
				row[1] = list.get(i).getNumber();
				row[2] = list.get(i).getName();
				row[3] = list.get(i).getCheck_in();
				row[4] = list.get(i).getCheck_out();
				row[5] = list.get(i).getStatus();
				row[6] = list.get(i).getTransaction_date();
				row[7] = list.get(i).getAddress();
				row[8] = list.get(i).getEmail();
				row[9] = list.get(i).getMobile();
				row[10] = list.get(i).getType();
				row[11] = list.get(i).getServices();
				row[12] = list.get(i).getMethod();
				row[13] = list.get(i).getTotal();
				row[14] = list.get(i).getPaid();
				row[15] = list.get(i).getBalance();
				row[16] = list.get(i).getServices_cost();
				row[17] = list.get(i).getRate_per_night();

				model.addRow(row);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	void searchById(String value, JTable table) {
		int id = 0;

		try {
			id = Integer.parseInt(value);
			String query = "select * from transaction where id =" + id;
			try {
				display_table(data_list(query), table);

				if (table.getRowCount() == 0) {

					JOptionPane.showMessageDialog(null, "No Transaction Found");
					display_table(data_list(), table);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Search Field is empty");
		}

	}

	private void display_info(JTable table, int row, boolean bol) {
		int i = table.getSelectedRow();
		TableModel model = table.getModel();

		try {

			if (bol == true) {

				transactionId.setText(model.getValueAt(i, 0).toString());
				transactionDate.setText(model.getValueAt(i, 6).toString());
				name.setText(model.getValueAt(i, 2).toString());
				address.setText(model.getValueAt(i, 7).toString());
				email.setText(model.getValueAt(i, 8).toString());
				mobile.setText(model.getValueAt(i, 9).toString());
				roomNo.setText(model.getValueAt(i, 1).toString());
				roomType.setText(model.getValueAt(i, 10).toString());
				services.setText(model.getValueAt(i, 11).toString());
				checkIn.setText(model.getValueAt(i, 3).toString());
				checkOut.setText(model.getValueAt(i, 4).toString());
				method.setText(model.getValueAt(i, 12).toString());
				total.setText(model.getValueAt(i, 13).toString());
				downPayment.setText(model.getValueAt(i, 14).toString());
				balance.setText(model.getValueAt(i, 15).toString());
				status.setText(model.getValueAt(i, 5).toString());
				services_cost.setText(model.getValueAt(i, 16).toString());
				rate_per_night.setText(model.getValueAt(i, 17).toString());

				if (model.getValueAt(i, 5).toString().equals("Cancelled")
						|| model.getValueAt(i, 5).toString().equals("Cancelled by Staff")
						|| model.getValueAt(i, 5).toString().equals("Completed")) {
					leftButton.setVisible(false);
					centerButton.setVisible(false);
					rightButton.setVisible(false);
				} else if (model.getValueAt(i, 5).toString().equals("Checked In")) {
					leftButton.setVisible(true);
					leftButton.setText("CHECK OUT");
					centerButton.setVisible(false);
					rightButton.setVisible(false);
				} else if (model.getValueAt(i, 5).toString().equals("Confirmed")) {
					leftButton.setVisible(true);
					centerButton.setVisible(true);
					rightButton.setVisible(true);
					leftButton.setText("CHECK IN");
					centerButton.setText("RE-SCHED");
					rightButton.setText("CANCEL");
				}

			} else {
				transactionId.setText(model.getValueAt(row, 0).toString());
				transactionDate.setText(model.getValueAt(row, 6).toString());
				name.setText(model.getValueAt(row, 2).toString());
				address.setText(model.getValueAt(row, 7).toString());
				email.setText(model.getValueAt(row, 8).toString());
				mobile.setText(model.getValueAt(row, 9).toString());
				roomNo.setText(model.getValueAt(row, 1).toString());
				roomType.setText(model.getValueAt(row, 10).toString());
				services.setText(model.getValueAt(row, 11).toString());
				checkIn.setText(model.getValueAt(row, 3).toString());
				checkOut.setText(model.getValueAt(row, 4).toString());
				method.setText(model.getValueAt(row, 12).toString());
				total.setText(model.getValueAt(row, 13).toString());
				downPayment.setText(model.getValueAt(row, 14).toString());
				balance.setText(model.getValueAt(row, 15).toString());
				status.setText(model.getValueAt(row, 5).toString());
				services_cost.setText(model.getValueAt(row, 16).toString());
				rate_per_night.setText(model.getValueAt(row, 17).toString());

				if (model.getValueAt(row, 5).toString().equals("Cancelled")
						|| model.getValueAt(row, 5).toString().equals("Cancelled by Staff")
						|| model.getValueAt(row, 5).toString().equals("Completed")) {
					leftButton.setVisible(false);
					centerButton.setVisible(false);
					rightButton.setVisible(false);
				} else if (model.getValueAt(row, 5).toString().equals("Checked In")) {
					leftButton.setVisible(true);
					leftButton.setText("CHECK OUT");
					centerButton.setVisible(false);
					rightButton.setVisible(false);
				} else if (model.getValueAt(row, 5).toString().equals("Confirmed")) {
					leftButton.setVisible(true);
					centerButton.setVisible(true);
					rightButton.setVisible(true);
					leftButton.setText("CHECK IN");
					centerButton.setText("RE-SCHED");
					rightButton.setText("CANCEL");
				}

			}

		} catch (Exception e) {

		}
	}

	void check_out() {
		int transaction_id = Integer.parseInt(transactionId.getText());
		String room_number = roomNo.getText();
		String room_type = roomType.getText();
		String guest_name = name.getText();
		String check_in = checkIn.getText();
		String check_out = checkOut.getText();
		String packages = services.getText();
		double initial_total = Double.parseDouble(total.getText());
		double rate = Double.parseDouble(rate_per_night.getText());
		double servicesCost = Double.parseDouble(services_cost.getText());
		double initial_payment = Double.parseDouble(downPayment.getText());

		CheckOut checkOut = new CheckOut(transaction_id, room_number, room_type, guest_name, check_in, check_out,
				packages, initial_total, rate, servicesCost, initial_payment);

		checkOut.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_table(data_list(), table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void cancel_booking(int transaction_id) {
		Dialog dialog = new Dialog("Reason for Booking Cancellation", "Booking Cancellation", transaction_id);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_table(data_list(), table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	void check_in() {
		int transaction_id = Integer.parseInt(transactionId.getText());
		String room_number = roomNo.getText();
		String room_type = roomType.getText();
		String guest_name = name.getText();
		String services_val = services.getText();
		String check_out = checkOut.getText();
		double rate = Double.parseDouble(rate_per_night.getText());
		double servicesCost = Double.parseDouble(services_cost.getText());
		double initial_payment = Double.parseDouble(downPayment.getText());

		CheckIn checkIn = new CheckIn(transaction_id, room_number, room_type, guest_name, services_val, check_out, rate,
				servicesCost, initial_payment);

		checkIn.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_table(data_list(), table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	void delete_transaction(int id) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			String query = "delete from transaction where id = " + id + " && ref_id = " + Login.getCompanyId();

			statement.execute(query);

			JOptionPane.showMessageDialog(null, "Transaction is deleted successfully");
			display_table(data_list(), table);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Transaction is not deleted due to error");
		}
	}

}
