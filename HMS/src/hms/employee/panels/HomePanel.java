package hms.employee.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import hms.frames.CheckIn;
import hms.frames.CheckOut;
import hms.frames.Dialog;
import hms.frames.ReSched;
import hms.main.FrontDesk;
import hms.main.Login;
import hms.objects.Room;
import hms.objects.Transactions;

public class HomePanel extends Resources{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2994532125364639162L;
	
	//labels
	JLabel panelLabel = new JLabel("HOME");
	JLabel guestInfo = new JLabel("GUEST INFORMATION");
	JLabel availInfo = new JLabel("AVAILABILITY INFORMATION");
	
	JLabel transactionIdLabel = new JLabel("Transaction Id:");
	JLabel guestNameLabel = new JLabel("Guest Name:");
	JLabel roomNumberLabel = new JLabel("Room Number:");
	JLabel checkInLabel = new JLabel("Check In:");
	JLabel checkOutLabel = new JLabel("Check Out:");
	JLabel balanceLabel = new JLabel("Balance:");
	JLabel statusLabel = new JLabel("Status:");
	
	JLabel transactionId= new JLabel();
	JLabel guestName = new JLabel();
	JLabel roomNumber = new JLabel();
	JLabel checkIn = new JLabel();
	JLabel checkOut = new JLabel();
	JLabel balance = new JLabel();
	JLabel status = new JLabel();
	
	void Labels() {
		panelLabel.setBounds(380,60,400,30);
		panelLabel.setFont(robotoBold25);
		panelLabel.setForeground(navyBlue);
		
		guestInfo.setBounds(380, 125, 300, 16);
		guestInfo.setFont(robotoHead);
		
		availInfo.setBounds(380, 463, 300, 16);
		availInfo.setFont(robotoHead);
		
		transactionIdLabel.setBounds(20,0,150,20);
		transactionIdLabel.setFont(robotoBold14);
		
		guestNameLabel.setBounds(20,30,150,20);
		guestNameLabel.setFont(robotoBold14);
		
		roomNumberLabel.setBounds(20,60,150,20);
		roomNumberLabel.setFont(robotoBold14);
		
		checkInLabel.setBounds(20,90,150,20);
		checkInLabel.setFont(robotoBold14);
		
		checkOutLabel.setBounds(20,120,150,20);
		checkOutLabel.setFont(robotoBold14);
		
		balanceLabel.setBounds(20,150,150,20);
		balanceLabel.setFont(robotoBold14);
		
		statusLabel.setBounds(20,180,150,20);
		statusLabel.setFont(robotoBold14);
		
		transactionId.setBounds(170,0,150,20);
		transactionId.setFont(robotoPlain14);
		
		guestName.setBounds(170,30,150,20);
		guestName.setFont(robotoPlain14);
		
		roomNumber.setBounds(170,60,150,20);
		roomNumber.setFont(robotoPlain14);
		
		checkIn.setBounds(170,90,150,20);
		checkIn.setFont(robotoPlain14);
		
		checkOut.setBounds(170,120,150,20);
		checkOut.setFont(robotoPlain14);
		
		balance.setBounds(170,150,150,20);
		balance.setFont(robotoPlain14);
		
		status.setBounds(170,180,150,20);
		status.setFont(robotoPlain14);
		
		this.add(panelLabel);
		this.add(guestInfo);
		this.add(availInfo);
		
		guestPanel.add(transactionIdLabel);
		guestPanel.add(guestNameLabel);
		guestPanel.add(roomNumberLabel);
		guestPanel.add(checkInLabel);
		guestPanel.add(checkOutLabel);
		guestPanel.add(balanceLabel);
		guestPanel.add(statusLabel);
		
		guestPanel.add(transactionId);
		guestPanel.add(guestName);
		guestPanel.add(roomNumber);
		guestPanel.add(checkIn);
		guestPanel.add(checkOut);
		guestPanel.add(balance);
		guestPanel.add(status);
	}
	
	//tables
	private JTable guestTable;
	private JTable roomTable;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Num", "Guest Name", "Status", "Check In", "Check Out", "Date", "Address", "Email",
				"Mobile Number", "Room Type", "Services", "Method", "Total", "Down Payment", "Balance","Rates", "Services Cost" };
		
		String[] col = { "ID", "Type", "Room Number", "Status", "Rate" };

		// data
		Object[][] row = {};
		
		

		// creating the table
		guestTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}

		};
		roomTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}

		};

		// table listener
		guestTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					show_guest_info(guestTable,0,true);
					
					if(guestTable.getRowCount() > 0) {
						guestPanel.setVisible(true);
					}else {
						guestPanel.setVisible(false);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		roomTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		guestTable.setModel(new DefaultTableModel(row, column));
		guestTable.setRowHeight(40);
		guestTable.setFillsViewportHeight(true);
		guestTable.removeColumn(guestTable.getColumn("Date"));
		guestTable.removeColumn(guestTable.getColumn("Address"));
		guestTable.removeColumn(guestTable.getColumn("Email"));
		guestTable.removeColumn(guestTable.getColumn("Mobile Number"));
		guestTable.removeColumn(guestTable.getColumn("Room Type"));
		guestTable.removeColumn(guestTable.getColumn("Services"));
		guestTable.removeColumn(guestTable.getColumn("Method"));
		guestTable.removeColumn(guestTable.getColumn("Total"));
		guestTable.removeColumn(guestTable.getColumn("Down Payment"));
		guestTable.removeColumn(guestTable.getColumn("Balance"));
		guestTable.removeColumn(guestTable.getColumn("Check Out"));
		guestTable.removeColumn(guestTable.getColumn("Check In"));
		guestTable.removeColumn(guestTable.getColumn("Rates"));
		guestTable.removeColumn(guestTable.getColumn("Services Cost"));
		guestTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		guestTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		guestTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		guestTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		guestTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		guestTable.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		guestTable.setFont(robotoPlain14);
		guestTable.setShowVerticalLines(false);
		guestTable.getTableHeader().setFont(robotoBold14);
		guestTable.getTableHeader().setResizingAllowed(false);
		guestTable.getTableHeader().setReorderingAllowed(false);
		guestTable.setCellSelectionEnabled(false);
		guestTable.setRowSelectionAllowed(true);
		guestTable.setFocusable(false);
		
		roomTable.setModel(new DefaultTableModel(row, col));
		roomTable.setRowHeight(40);
		roomTable.setFillsViewportHeight(true);
		roomTable.removeColumn(roomTable.getColumn("Rate"));
		roomTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		roomTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		roomTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		roomTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		roomTable.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		roomTable.setFont(robotoPlain14);
		roomTable.setShowVerticalLines(false);
		roomTable.getTableHeader().setFont(robotoBold14);
		roomTable.getTableHeader().setResizingAllowed(false);
		roomTable.getTableHeader().setReorderingAllowed(false);
		roomTable.setCellSelectionEnabled(false);
		roomTable.setRowSelectionAllowed(true);
		roomTable.setFocusable(false);

		// adding rows to the table

		try {
			display_guest_table(transaction_list(), guestTable);
			display_table(room_list(), roomTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(guestTable);
		scrollPane.setBounds(380, 150, 400, 225);
		
		JScrollPane roomScroll = new JScrollPane(roomTable);
		roomScroll.setBounds(780, 488, 600, 300);
		this.add(scrollPane);
		this.add(roomScroll);

	}
	
	//buttons
	private JButton topButton = new JButton();
	private JButton midButton = new JButton();
	private JButton bottomButton = new JButton();
	private JButton findButton = new JButton("FIND ROOM");
	private JButton refreshButton = new JButton("REFRESH");
	
	void Buttons() {
		
		topButton.setBounds(400,0,200,30);
		topButton.setBackground(navyBlue);
		topButton.setForeground(babyBlue);
		topButton.setFocusable(false);
		topButton.setFont(robotoBold14);
		topButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (topButton.getText().equals("CHECK OUT")) {
						check_out();
					} else {
						check_in();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"No selected Transaction\nMake sure that the transaction you want to change\nis selected from the table");
				}
			}
		});
		midButton.setBounds(400, 40, 200, 30);
		midButton.setBackground(navyBlue);
		midButton.setForeground(babyBlue);
		midButton.setFocusable(false);
		midButton.setFont(robotoBold14);
		midButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					new ReSched(Integer.parseInt(transactionId.getText()), checkIn.getText(), checkOut.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"No selected Transaction\nMake sure that the transaction you want to change\nis selected from the table");
				}

			}
		});
		bottomButton.setBounds(400, 80, 200, 30);
		bottomButton.setBackground(navyBlue);
		bottomButton.setForeground(babyBlue);
		bottomButton.setFocusable(false);
		bottomButton.setFont(robotoBold14);
		bottomButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					cancel_booking(Integer.parseInt(transactionId.getText()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"No selected Transaction\nMake sure that the transaction you want to change\nis selected from the table");
				}

			}
		});
		findButton.setBounds(0,270,150,30);
		findButton.setBackground(navyBlue);
		findButton.setForeground(babyBlue);
		findButton.setFocusable(false);
		findButton.setFont(robotoBold14);
		findButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(checkInField.getDate() == null && checkOutField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Check in and Check out fields are empty");
				}else if(checkInField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Check in field is empty");
				}else if(checkOutField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Check out field is empty");
				}else if(typeField.getSelectedItem() == "Room Type") {
					JOptionPane.showMessageDialog(null, "No selected room type");
				}else {
					find_room();
				}
			}
		});
		
		refreshButton.setBounds(680,125,100,23);
		refreshButton.setBackground(navyBlue);
		refreshButton.setFocusable(false);
		refreshButton.setForeground(babyBlue);
		refreshButton.setFont(robotoBold10);
		refreshButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					display_guest_table(transaction_list(), guestTable);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		this.add(refreshButton);
		guestPanel.add(topButton);
		guestPanel.add(midButton);
		guestPanel.add(bottomButton);
		roomQueryPanel.add(findButton);
	}
	
	//panel
	private JPanel guestPanel = new JPanel();
	private JPanel roomQueryPanel = new JPanel();
	void Panel() {
		
		guestPanel.setBounds(780, 150, 600, 225);
		guestPanel.setLayout(null);
		
		roomQueryPanel.setBounds(380, 488, 400, 300);
		roomQueryPanel.setLayout(null);
		
		this.add(guestPanel);
		this.add(roomQueryPanel);
	}
	
	//input fields
	private JLabel panelTitle = new JLabel("ROOM FINDER");
	private JLabel typeFieldLabel = new JLabel("Room Type:");
	private JLabel checkInFieldLabel = new JLabel("Check In:");
	private JLabel checkOutFieldLabel = new JLabel("Check Out:");
	
	private final JComboBox<String> typeField = new JComboBox<String>(TransactionPanel.room_types());
	private JDateChooser checkInField = new JDateChooser();
	private JDateChooser checkOutField = new JDateChooser();
	
	void Fields() {
		
		panelTitle.setBounds(0,0,150,30);
		panelTitle.setFont(robotoBold14);
		
		typeFieldLabel.setBounds(0,50,150,20);
		typeFieldLabel.setFont(robotoBold14);
		
		checkInFieldLabel.setBounds(0,80,150,20);
		checkInFieldLabel.setFont(robotoBold14);
		
		
		checkOutFieldLabel.setBounds(0,110,150,20);
		checkOutFieldLabel.setFont(robotoBold14);
		
		typeField.setBounds(150,50,200,20);
		typeField.setFont(robotoPlain14);
		typeField.setFocusable(false);
		
		checkInField.setBounds(150,80,200,20);
		checkInField.setFont(robotoPlain14);
		checkInField.getJCalendar().setMinSelectableDate(new Date());
		checkInField.getComponent(1).setFocusable(true);
		checkInField.getComponent(1).addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				checkOutField.getJCalendar().setMinSelectableDate(checkInField.getDate());
			}
		});
		
		checkOutField.setBounds(150,110,200,20);
		checkOutField.setFont(robotoPlain14);
		checkOutField.getComponent(1).setFocusable(true);
		checkOutField.getJCalendar().setMinSelectableDate(new Date());
		
		
		roomQueryPanel.add(panelTitle);
		roomQueryPanel.add(typeFieldLabel);
		roomQueryPanel.add(checkInFieldLabel);
		roomQueryPanel.add(checkOutFieldLabel);
		roomQueryPanel.add(typeField);
		roomQueryPanel.add(checkInField);
		roomQueryPanel.add(checkOutField);
	}

	
	public HomePanel() {
		this.setBounds(0,0,1420,1080);
		this.setLayout(null);
		
		Labels();
		Table();
		Fields();
		Buttons();
		Panel();
		
		if(guestTable.getRowCount() != 0) {
			guestPanel.setVisible(true);
			show_guest_info(guestTable, 0, false);
		}else {
			guestPanel.setVisible(false);
		}
		
		
	}
	
	
	/**
	 * Connects to the database and creates an arraylist
	 * 
	 * @return roomList
	 * @throws SQLException
	 */

	public ArrayList<Transactions> transaction_list() throws SQLException {
		ArrayList<Transactions> transactionList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from transaction where (check_in = current_date or check_out = current_date or current_date between check_in and check_out) \r\n"
					+ "and not (status = 'Cancelled' || status = 'Cancelled by Staff' || status = 'Pending' || status = 'Completed')\r\n"
					+ "and ref_id = " + Login.getCompanyId() ;
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
	
	public ArrayList<Room> room_list() throws SQLException{
		ArrayList<Room> roomList = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system","root", "465a7dhn");
			String query = "select * from room where status = 'Available' and ref_id = " + Login.getCompanyId() + " ";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			
			Room room;
			while(rs.next()) {
				
				room = new Room(rs.getInt("id"), rs.getString("number"), rs.getString("type"), rs.getString("address"), 
						rs.getString("occupied_from"), rs.getString("occupied_to"), rs.getString("status"), 
						rs.getString("description"), rs.getBlob("image"), rs.getDouble("rates"), rs.getString("rating"));                                        
				roomList.add(room);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	void display_table(ArrayList<Room> datalist, JTable table) throws SQLException {
		ArrayList<Room> list = datalist;
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[5];
		
		try {
			for(int i=0; i<list.size(); i++) {

				row[0] = list.get(i).getId();
				row[1] = list.get(i).getType();
				row[2] = list.get(i).getNumber();
				row[3] = list.get(i).getStatus();
				row[4] = list.get(i).getRates();
				
				model.addRow(row);
				
				
			}
		} catch (Exception e) {
			
		}
	}
	
	void display_guest_table(ArrayList<Transactions> datalist, JTable table) throws SQLException {
		ArrayList<Transactions> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[18];

		try {
			
			for (int i = 0; i < list.size(); i++) {
				row[0] = list.get(i).getId();
				row[1] = list.get(i).getNumber();
				row[2] = list.get(i).getName();
				row[3] = list.get(i).getStatus();
				row[4] = list.get(i).getCheck_in();
				row[5] = list.get(i).getCheck_out();
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
				row[16] = list.get(i).getRate_per_night();
				row[17] = list.get(i).getServices_cost();

				model.addRow(row);

			}
		} catch (Exception e) {

		}
		
		if(table.getRowCount() <= 0) {
			guestPanel.setVisible(false);
		}
	}

	void show_guest_info(JTable table, int row, boolean bol) {
		try {
			int i = table.getSelectedRow();
			TableModel model = table.getModel();

			if (bol == true) {
				transactionId.setText(model.getValueAt(i, 0).toString());
				roomNumber.setText(model.getValueAt(i, 1).toString());
				guestName.setText(model.getValueAt(i, 2).toString());
				status.setText(model.getValueAt(i, 3).toString());
				checkIn.setText(model.getValueAt(i, 4).toString());
				checkOut.setText(model.getValueAt(i, 5).toString());
				balance.setText(model.getValueAt(i, 15).toString());

			} else {
				transactionId.setText(model.getValueAt(row, 0).toString());
				roomNumber.setText(model.getValueAt(row, 1).toString());
				guestName.setText(model.getValueAt(row, 2).toString());
				status.setText(model.getValueAt(row, 3).toString());
				checkIn.setText(model.getValueAt(row, 4).toString());
				checkOut.setText(model.getValueAt(row, 5).toString());
				balance.setText(model.getValueAt(row, 15).toString());
			}

			if (status.getText().equals("Confirmed")) {
				midButton.setVisible(true);
				bottomButton.setVisible(true);
				topButton.setText("CHECK IN");
				midButton.setText("RE-SCHEDULE");
				bottomButton.setText("CANCEL");
			} else if (status.getText().equals("Checked In")) {
				topButton.setText("CHECK OUT");
				midButton.setVisible(false);
				bottomButton.setVisible(false);
			}
		} catch (Exception e) {
		}
	}
	
	void find_room() {
		String room_number = TransactionPanel.find_room(typeField.getSelectedItem().toString(), 
				checkInField.getDate(), checkOutField.getDate()).toString();
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String check_in = formatter.format(checkInField.getDate());
		String check_out = formatter.format(checkOutField.getDate());
		
		if(!(room_number.equals("No Room"))){
			int response = JOptionPane.showConfirmDialog(null, 
					"Room "+ room_number +" is available.\nDo you want to proceed "
							+ "on making a transaction?", "Room Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION) {
				FrontDesk.Switch_Screen(FrontDesk.transactionPanel);
				
				try {
					TransactionPanel.checkInField.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_in));
					TransactionPanel.checkOutField.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_out));
					TransactionPanel.typeField.setSelectedItem(typeField.getSelectedItem());
					TransactionPanel.numberField.setText(room_number);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}else {
			JOptionPane.showMessageDialog(null, "No room found");
		}
	}
	
	void check_out() {
		int i = guestTable.getSelectedRow();
		TableModel model = guestTable.getModel();
		
		int transaction_id = Integer.parseInt(transactionId.getText());
		String room_number = roomNumber.getText();
		String room_type = model.getValueAt(i, 10).toString();
		String guest_name = guestName.getText();
		String check_in = checkIn.getText();
		String check_out = checkOut.getText();
		String packages = model.getValueAt(i, 11).toString();
		double initial_total = Double.parseDouble(model.getValueAt(i, 13).toString());
		double rate = Double.parseDouble(model.getValueAt(i, 16).toString());
		double servicesCost = Double.parseDouble(model.getValueAt(i, 17).toString());
		double initial_payment = Double.parseDouble(model.getValueAt(i, 14).toString());

		CheckOut checkOut = new CheckOut(transaction_id, room_number, room_type, guest_name, check_in, check_out, packages, initial_total,
				rate, servicesCost, initial_payment);
		
		checkOut.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_guest_table(transaction_list(), guestTable);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	 void cancel_booking(int transaction_id) {
		Dialog dialog = new Dialog("Reason for Booking Cancellation", "Booking Cancellation", transaction_id);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_guest_table(transaction_list(), guestTable);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	void check_in() {
		int i = guestTable.getSelectedRow();
		TableModel model = guestTable.getModel();
		
		int transaction_id = Integer.parseInt(transactionId.getText());
		String room_number = roomNumber.getText();
		String room_type =  model.getValueAt(i, 10).toString();
		String guest_name = guestName.getText();
		String services_val =  model.getValueAt(i, 11).toString();
		String check_out = checkOut.getText();
		double rate = Double.parseDouble( model.getValueAt(i, 16).toString());
		double servicesCost = Double.parseDouble( model.getValueAt(i, 17).toString());
		double initial_payment = Double.parseDouble( model.getValueAt(i, 14).toString());
		
		CheckIn checkIn = new CheckIn(transaction_id, room_number, room_type, guest_name, services_val, check_out, 
				rate, servicesCost, initial_payment);
		
		checkIn.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				try {
					display_guest_table(transaction_list(), guestTable);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	

}






































