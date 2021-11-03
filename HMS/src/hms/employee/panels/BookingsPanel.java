package hms.employee.panels;

import java.awt.Component;
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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hms.frames.Dialog;
import hms.main.Login;
import hms.objects.Transactions;

public class BookingsPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4442703842380407779L;

	// labels
	private JLabel panelTitle = new JLabel("VERIFY BOOKINGS");

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

		// adding to the panel
		this.add(panelTitle);

	}

	// button
	private JButton cancelButton = new JButton("CANCEL");
	private JButton confirmButton = new JButton("VERIFY");

	void Buttons() {

		cancelButton.setBounds(0, 350, 100, 40);
		cancelButton.setFont(robotoBold10);
		cancelButton.setBackground(navyBlue);
		cancelButton.setForeground(babyBlue);
		cancelButton.setFocusable(false);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cancelButton.getText().equals("DENY")) {
					deny_cancellation(Integer.parseInt(transactionId.getText()));
				} else {
					cancel_booking(Integer.parseInt(transactionId.getText()));
				}
			}
		});

		confirmButton.setBounds(150, 350, 100, 40);
		confirmButton.setFont(robotoBold10);
		confirmButton.setBackground(navyBlue);
		confirmButton.setForeground(babyBlue);
		confirmButton.setFocusable(false);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (confirmButton.getText().equals("VERIFY")) {
					verify_booking(Integer.parseInt(transactionId.getText()));
				} else {
					confirm_cancellation(Integer.parseInt(transactionId.getText()));
				}
			}
		});

		// adding to bookingInfoPanel
		bookingInfoPanel.add(cancelButton);
		bookingInfoPanel.add(confirmButton);

	}

	// table
	private JTable table;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Room Number", "Guest Name", "Check In", "Check Out", "Date", "Address", "Email",
				"Mobile Number", "Room Type", "Services", "Method", "Total", "Down Payment", "Balance", "Status" };

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
				try {

					if (table.getRowCount() != 0) {
						bookingInfoPanel.setVisible(true);
						display_info(table, 0, true);
					} else {
						bookingInfoPanel.setVisible(false);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
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
		table.removeColumn(table.getColumn("Status"));
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(2).setPreferredWidth(500);
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
		table.setFocusable(false);

		// adding rows to the table

		try {
			display_table(data_list(), table);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(380, 150, 1000, 250);
		this.add(scrollPane);

	}

	// panel
	private JPanel bookingInfoPanel = new JPanel();

	void Panel() {

		bookingInfoPanel.setBounds(380, 425, 1000, 390);
		bookingInfoPanel.setLayout(null);
		this.add(bookingInfoPanel);
		// bookingInfoPanel.setBackground(errorRed);

	}

	public BookingsPanel() {

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

	/**
	 * Connects to the database and creates an arraylist
	 * 
	 * @return roomList
	 * @throws SQLException
	 */

	public ArrayList<Transactions> data_list() throws SQLException {
		ArrayList<Transactions> toBeConfirmedList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from transaction where ref_id = " + Login.getCompanyId()
					+ " && (confirmed = 'No' || status = 'Cancelling');";
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

				toBeConfirmedList.add(transactions);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return toBeConfirmedList;
	}

	/**
	 * Adds data to a table
	 * 
	 * @throws SQLException
	 */

	private void display_table(ArrayList<Transactions> datalist, JTable table) throws SQLException {
		ArrayList<Transactions> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[16];

		try {
			for (int i = 0; i < list.size(); i++) {
				row[0] = list.get(i).getId();
				row[1] = list.get(i).getNumber();
				row[2] = list.get(i).getName();
				row[3] = list.get(i).getCheck_in();
				row[4] = list.get(i).getCheck_out();
				row[5] = list.get(i).getTransaction_date();
				row[6] = list.get(i).getAddress();
				row[7] = list.get(i).getEmail();
				row[8] = list.get(i).getMobile();
				row[9] = list.get(i).getType();
				row[10] = list.get(i).getServices();
				row[11] = list.get(i).getMethod();
				row[12] = list.get(i).getTotal();
				row[13] = list.get(i).getPaid();
				row[14] = list.get(i).getBalance();
				row[15] = list.get(i).getStatus();

				model.addRow(row);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Shows the details of the unconfirmed transaction
	 */

	private void display_info(JTable table, int row, boolean bol) {
		int i = table.getSelectedRow();
		TableModel model = table.getModel();

		try {

			if (bol == true) {
				transactionId.setText(model.getValueAt(i, 0).toString());
				transactionDate.setText(model.getValueAt(i, 5).toString());
				name.setText(model.getValueAt(i, 2).toString());
				address.setText(model.getValueAt(i, 6).toString());
				email.setText(model.getValueAt(i, 7).toString());
				mobile.setText(model.getValueAt(i, 8).toString());
				roomNo.setText(model.getValueAt(i, 1).toString());
				roomType.setText(model.getValueAt(i, 9).toString());
				services.setText(model.getValueAt(i, 10).toString());
				checkIn.setText(model.getValueAt(i, 3).toString());
				checkOut.setText(model.getValueAt(i, 4).toString());
				method.setText(model.getValueAt(i, 11).toString());
				total.setText(model.getValueAt(i, 12).toString());
				downPayment.setText(model.getValueAt(i, 13).toString());
				balance.setText(model.getValueAt(i, 14).toString());
				status.setText(model.getValueAt(i, 15).toString());

				if (model.getValueAt(i, 15).toString().equals("Cancelling")) {
					cancelButton.setText("DENY");
					confirmButton.setText("CONFIRM");
				} else {
					cancelButton.setText("CANCEL");
					confirmButton.setText("VERIFY");
				}

			} else {
				transactionId.setText(model.getValueAt(row, 0).toString());
				transactionDate.setText(model.getValueAt(row, 5).toString());
				name.setText(model.getValueAt(row, 2).toString());
				address.setText(model.getValueAt(row, 6).toString());
				email.setText(model.getValueAt(row, 7).toString());
				mobile.setText(model.getValueAt(row, 8).toString());
				roomNo.setText(model.getValueAt(row, 1).toString());
				roomType.setText(model.getValueAt(row, 9).toString());
				services.setText(model.getValueAt(row, 10).toString());
				checkIn.setText(model.getValueAt(row, 3).toString());
				checkOut.setText(model.getValueAt(row, 4).toString());
				method.setText(model.getValueAt(row, 11).toString());
				total.setText(model.getValueAt(row, 12).toString());
				downPayment.setText(model.getValueAt(row, 13).toString());
				balance.setText(model.getValueAt(row, 14).toString());
				status.setText(model.getValueAt(row, 15).toString());

				if (model.getValueAt(row, 15).toString().equals("Cancelling")) {
					cancelButton.setText("DENY");
					confirmButton.setText("CONFIRM");
				} else {
					cancelButton.setText("CANCEL");
					confirmButton.setText("VERIFY");
				}

			}

		} catch (Exception e) {

		}
	}

	/**
	 * Verify booking
	 */

	private void verify_booking(int transaction_id) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set confirmed = 'Yes', status = 'Confirmed', staff = '"
					+ Login.getNameValue("employee", Login.getCompanyId(), Login.getUsernameValue()) + "' where id = "
					+ transaction_id;
			Statement statement = connection.createStatement();
			statement.execute(query);

			JOptionPane.showMessageDialog(null, "Transaction Verified");
			display_table(data_list(), table);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Transaction Verification Unsuccessful");
		}
	}

	private void confirm_cancellation(int transaction_id) {
		try {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {

				Connection connection = DriverManager
						.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system", "root", "465a7dhn");
				String query = "update transaction set status = 'Cancelled', staff = '"
						+ Login.getNameValue("employee", Login.getCompanyId(), Login.getUsernameValue())
						+ "' where id = " + transaction_id;

				Statement statement = connection.createStatement();
				statement.execute(query);

				JOptionPane.showMessageDialog(null, "Booking Cancellation Confirmed");
				display_table(data_list(), table);

			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Confirmation of Booking Cancellation Unsuccessful");
		}

	}

	private void deny_cancellation(int transaction_id) {
		Dialog dialog = new Dialog("Reason for Denying your Cancellation", "Deny Cancellation", transaction_id);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_table(data_list(), table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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

}
