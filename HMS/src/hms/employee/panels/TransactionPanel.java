package hms.employee.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import hms.frames.CreditCard;
import hms.main.Login;

public class TransactionPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3106841280100466396L;
	private static Integer roomId;

	// Labels
	private JLabel panelLabel = new JLabel("TRANSACTION FORM");

	// Transaction Information
	private JLabel transactionLabel = new JLabel("TRANSACTION INFORMATION");
	private JLabel idLabel = new JLabel("Transaction Id:");
	private JLabel dateLabel = new JLabel("Date:");
	private JLabel staffLabel = new JLabel("Staff:");

	// Guest Information
	private JLabel guestLabel = new JLabel("GUEST INFORMATION");
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel addressLabel = new JLabel("Address:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel ageLabel = new JLabel("Age:");
	private JLabel mobileLabel = new JLabel("Mobile Number:");
	private JLabel nationalityLabel = new JLabel("Nationality:");

	// Payment Information
	private JLabel paymentLabel = new JLabel("PAYMENT INFORMATION");
	private JLabel methodLabel = new JLabel("Payment Method:");
	private JLabel noOfNightsLabel = new JLabel("No. of Nights");
	private JLabel roomCostLabel = new JLabel("Room Cost:");
	private JLabel servicesCostLabel = new JLabel("Services Cost:");
	private JLabel totalLabel = new JLabel("Total:");
	private JLabel paidLabel = new JLabel("Amount Paid:");
	private JLabel balanceLabel = new JLabel("Balance");

	// Room Information
	private JLabel roomLabel = new JLabel("ROOM INFORMATION");
	private JLabel typeLabel = new JLabel("Room Type:");
	private JLabel servicesLabel = new JLabel("Services");
	private JLabel checkInLabel = new JLabel("Check in by:");
	private JLabel checkOutLabel = new JLabel("Check out by:");
	private JLabel numberLabel = new JLabel("Room Number:");

	void Labels() {

		panelLabel.setBounds(380, 60, 400, 30);
		panelLabel.setFont(robotoBold25);
		panelLabel.setForeground(navyBlue);

		// transaction info
		transactionLabel.setBounds(380, 140, 250, 16);
		transactionLabel.setFont(robotoBold14);

		idLabel.setBounds(380, 170, 100, 16);
		idLabel.setFont(robotoBold12);

		dateLabel.setBounds(380, 200, 80, 16);
		dateLabel.setFont(robotoBold12);

		staffLabel.setBounds(380, 230, 80, 16);
		staffLabel.setFont(robotoBold12);

		// guest info
		guestLabel.setBounds(380, 340, 200, 16);
		guestLabel.setFont(robotoBold14);

		nameLabel.setBounds(380, 370, 80, 16);
		nameLabel.setFont(robotoBold12);

		addressLabel.setBounds(380, 400, 80, 16);
		addressLabel.setFont(robotoBold12);

		emailLabel.setBounds(380, 430, 80, 16);
		emailLabel.setFont(robotoBold12);

		ageLabel.setBounds(380, 460, 80, 16);
		ageLabel.setFont(robotoBold12);

		mobileLabel.setBounds(380, 490, 100, 16);
		mobileLabel.setFont(robotoBold12);

		nationalityLabel.setBounds(380, 520, 80, 16);
		nationalityLabel.setFont(robotoBold12);

		// payment info
		paymentLabel.setBounds(900, 400, 200, 16);
		paymentLabel.setFont(robotoBold12);

		methodLabel.setBounds(900, 430, 100, 16);
		methodLabel.setFont(robotoBold12);

		noOfNightsLabel.setBounds(900, 460, 200, 16);
		noOfNightsLabel.setFont(robotoBold12);

		roomCostLabel.setBounds(900, 490, 200, 16);
		roomCostLabel.setFont(robotoBold12);

		servicesCostLabel.setBounds(900, 520, 200, 16);
		servicesCostLabel.setFont(robotoBold12);

		totalLabel.setBounds(900, 550, 80, 16);
		totalLabel.setFont(robotoBold12);

		paidLabel.setBounds(900, 580, 80, 16);
		paidLabel.setFont(robotoBold12);

		balanceLabel.setBounds(900, 610, 80, 16);
		balanceLabel.setFont(robotoBold12);

		// room info
		roomLabel.setBounds(900, 140, 200, 16);
		roomLabel.setFont(robotoBold14);

		typeLabel.setBounds(900, 170, 80, 16);
		typeLabel.setFont(robotoBold12);

		servicesLabel.setBounds(900, 200, 80, 16);
		servicesLabel.setFont(robotoBold12);

		checkInLabel.setBounds(900, 230, 80, 16);
		checkInLabel.setFont(robotoBold12);

		checkOutLabel.setBounds(900, 260, 80, 16);
		checkOutLabel.setFont(robotoBold12);

		numberLabel.setBounds(900, 354, 100, 16);
		numberLabel.setFont(robotoBold12);

		// adding labels to the panel
		this.add(panelLabel);
		this.add(transactionLabel);
		this.add(idLabel);
		this.add(dateLabel);
		this.add(staffLabel);
		this.add(guestLabel);
		this.add(nameLabel);
		this.add(addressLabel);
		this.add(emailLabel);
		this.add(ageLabel);
		this.add(mobileLabel);
		this.add(nationalityLabel);
		this.add(paymentLabel);
		this.add(methodLabel);
		this.add(totalLabel);
		this.add(paidLabel);
		this.add(balanceLabel);
		this.add(roomLabel);
		this.add(typeLabel);
		this.add(servicesLabel);
		this.add(checkInLabel);
		this.add(checkOutLabel);
		this.add(numberLabel);
		this.add(servicesCostLabel);
		this.add(roomCostLabel);
		this.add(noOfNightsLabel);
	}

	// textfields
	// Transaction Information
	private JTextField idField = new JTextField();
	private JDateChooser dateField = new JDateChooser();
	private JTextField staffField = new JTextField();

	// Guest Information
	private JTextField nameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField ageField = new JTextField();
	private JTextField mobileField = new JTextField();
	private JTextField nationalityField = new JTextField();

	// Payment Information
	private static String[] methods = { "Choose Payment Method", "Card", "Cash" };
	public static final JComboBox<String> methodField = new JComboBox<String>(methods);
	private JTextField noOfNightsField = new JTextField();
	private JTextField roomCostField = new JTextField();
	private JTextField servicesCostField = new JTextField();
	private JTextField totalField = new JTextField();
	private JTextField paidField = new JTextField();
	private JTextField balanceField = new JTextField();
	static JTextField card = new JTextField();

	// Room Information
	public static final JComboBox<String> typeField = new JComboBox<String>(room_types());
	private JComboBox<String> servicesField = new JComboBox<String>(services());
	public static JDateChooser checkInField = new JDateChooser();
	public static JDateChooser checkOutField = new JDateChooser();

	public static JTextField numberField = new JTextField();

	void TextFields() {

		// transaction info
		idField.setBounds(490, 170, 200, 20);
		idField.setFont(robotoPlain12);
		idField.setBackground(null);
		idField.setFocusable(false);
		idField.setEditable(false);
		numberField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0xD4F1F4)));

		dateField.setBounds(490, 195, 200, 20);
		dateField.setFont(robotoPlain12);
		((JTextComponent) dateField.getComponent(1)).setEditable(false);
		((JTextComponent) dateField.getComponent(1)).setFocusable(false);
		dateField.getComponent(0).setEnabled(false);

		staffField.setBounds(490, 230, 200, 20);
		staffField.setFont(robotoPlain12);
		staffField.setBackground(null);
		staffField.setEditable(false);
		staffField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0xD4F1F4)));

		// guest info
		nameField.setBounds(490, 370, 200, 20);
		nameField.setFont(robotoPlain12);
		nameField.setBackground(null);
		nameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '.'
						|| e.getKeyChar() == ',' || e.getKeyChar() == '-')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					addressField.requestFocus();
				}
			}
		});

		nameField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!nameField.getText().equals("")) {
					nameField.setText(capitalized_word_string(capitalized_word_array(nameField.getText())));
				}

			}
		});

		addressField.setBounds(490, 400, 200, 20);
		addressField.setFont(robotoPlain12);
		addressField.setBackground(null);
		addressField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					emailField.requestFocus();
				}
			}
		});

		addressField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!addressField.getText().equals("")) {
					addressField.setText(capitalized_word_string(capitalized_word_array(addressField.getText())));
				}
			}
		});

		emailField.setBounds(490, 430, 200, 20);
		emailField.setFont(robotoPlain12);
		emailField.setBackground(null);
		emailField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					ageField.requestFocus();
				}
			}
		});

		emailField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {

				final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				if (!emailField.getText().matches(EMAIL_PATTERN)) {
					JOptionPane.showMessageDialog(null, "Invalid email address");

					emailField.requestFocus();
				}

			}
		});

		ageField.setBounds(490, 460, 200, 20);
		ageField.setFont(robotoPlain12);
		ageField.setBackground(null);
		ageField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()))) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					mobileField.requestFocus();
				}
			}
		});
		ageField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!ageField.getText().equals("")) {
					if (Integer.parseInt(ageField.getText()) < 18) {
						JOptionPane.showMessageDialog(null, "Customer is a minor");
						ageField.requestFocus();
					}
				}
			}
		});

		mobileField.setBounds(490, 490, 200, 20);
		mobileField.setFont(robotoPlain12);
		mobileField.setBackground(null);
		mobileField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()))) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					nationalityField.requestFocus();
				}
			}
		});
		mobileField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!mobileField.getText().equals("")) {
					if (mobileField.getText().length() > 11 || mobileField.getText().length() < 11) {
						JOptionPane.showMessageDialog(null,
								"Only local numbers are permitted at the moment.\nIf there is none, leave this part for now.\nThank you.");
						mobileField.requestFocus();
					}
				}
			}
		});

		nationalityField.setBounds(490, 520, 200, 20);
		nationalityField.setFont(robotoPlain12);
		nationalityField.setBackground(null);
		nationalityField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					methodField.requestFocus();
				}
			}
		});
		nationalityField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!nationalityField.getText().equals("")) {
					nationalityField
							.setText(capitalized_word_string(capitalized_word_array(nationalityField.getText())));
				}
			}
		});

		// payment info
		methodField.setBounds(1020, 430, 200, 20);
		methodField.setFont(robotoPlain12);
		methodField.setBackground(null);
		methodField.setFocusable(false);
		methodField.setEditable(true);
		methodField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (methodField.getSelectedItem() == "Card") {
					new CreditCard();
				}
			}
		});

		noOfNightsField.setBounds(1020, 460, 200, 20);
		noOfNightsField.setFont(robotoPlain12);
		noOfNightsField.setBackground(null);
		noOfNightsField.setEditable(false);
		noOfNightsField.setFocusable(false);

		roomCostField.setBounds(1020, 490, 200, 20);
		roomCostField.setFont(robotoPlain12);
		roomCostField.setBackground(null);
		roomCostField.setEditable(false);
		roomCostField.setFocusable(false);

		servicesCostField.setBounds(1020, 520, 200, 20);
		servicesCostField.setFont(robotoPlain12);
		servicesCostField.setBackground(null);
		servicesCostField.setEditable(false);
		servicesCostField.setFocusable(false);

		totalField.setBounds(1020, 550, 200, 20);
		totalField.setFont(robotoPlain12);
		totalField.setBackground(null);
		totalField.setEditable(false);
		totalField.setFocusable(false);

		paidField.setBounds(1020, 580, 200, 20);
		paidField.setFont(robotoPlain12);
		paidField.setBackground(null);
		paidField.setEditable(false);
		paidField.setFocusable(false);
		paidField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() >= 48 && e.getKeyChar() <= 57) || e.getKeyCode() == 8 || e.getKeyChar() == '.') {

				} else if (e.getKeyCode() == 10) {
					if (paidField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "The field for the amount paid is empty");
					} else {
						balanceField.setText(balance().toString());
					}
				} else {
					paidField.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter numerical values only");
					paidField.setEditable(true);
					paidField.setBackground(null);
				}
			}
		});

		balanceField.setBounds(1020, 610, 200, 20);
		balanceField.setFont(robotoPlain12);
		balanceField.setBackground(null);
		balanceField.setEditable(false);
		balanceField.setFocusable(false);
		balanceField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (paidField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The field for the amount paid is empty");
				} else {
					balanceField.setText(balance().toString());
				}

			}
		});

		// room info
		typeField.setBounds(1020, 170, 200, 20);
		typeField.setFont(robotoPlain12);
		typeField.setBackground(null);
		typeField.setFocusable(false);

		servicesField.setBounds(1020, 200, 200, 20);
		servicesField.setFont(robotoPlain12);
		servicesField.setBackground(null);
		servicesField.setFocusable(false);

		checkInField.setBounds(1020, 230, 200, 20);
		checkInField.setFont(robotoPlain12);
		checkInField.getJCalendar().setMinSelectableDate(new java.util.Date());
		checkInField.getComponent(1).setFocusable(true);
		checkInField.getComponent(1).addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				checkOutField.getJCalendar().setMinSelectableDate(checkInField.getDate());
			}
		});

		checkOutField.setBounds(1020, 260, 200, 20);
		checkOutField.setFont(robotoPlain12);
		checkOutField.getComponent(1).setFocusable(true);
		checkOutField.getJCalendar().setMinSelectableDate(new java.util.Date());

		numberField.setBounds(1020, 354, 80, 20);
		numberField.setFont(robotoPlain12);
		numberField.setBackground(null);
		numberField.setFocusable(false);
		numberField.setEditable(false);
		numberField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0xD4F1F4)));

		// adding tot the panel
		this.add(idField);
		this.add(dateField);
		this.add(staffField);
		this.add(nameField);
		this.add(addressField);
		this.add(emailField);
		this.add(ageField);
		this.add(mobileField);
		this.add(mobileField);
		this.add(nationalityField);
		this.add(methodField);
		this.add(totalField);
		this.add(paidField);
		this.add(balanceField);
		this.add(typeField);
		this.add(servicesField);
		this.add(checkInField);
		this.add(checkOutField);
		this.add(numberField);
		this.add(roomCostField);
		this.add(servicesCostField);
		this.add(noOfNightsField);
	}

	// buttons
	private JButton findButton = new JButton("FIND ROOM");
	private JButton confirmButton = new JButton("CONFIRM");
	private JButton cancelButton = new JButton("CANCEL");

	void Buttons() {
		// findButton
		findButton.setBounds(1120, 340, 100, 30);
		findButton.setFont(robotoBold10);
		findButton.setBackground(navyBlue);
		findButton.setForeground(babyBlue);
		findButton.setFocusable(false);
		findButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (typeField.getSelectedIndex() == 0 && servicesField.getSelectedIndex() == 0
						&& checkInField.getDate() == null && checkOutField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Fields under room information are empty");
				} else if (typeField.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "No selected room type");
				} else if (servicesField.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "No selected service");
				} else if (checkInField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Check in field is empty");
				} else if (checkOutField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Check out field is empty");
				} else {
					numberField.setText(find_room(typeField.getSelectedItem().toString(), checkInField.getDate(),
							checkOutField.getDate()).toString());
					try {

						noOfNightsField.setText(days(checkInField.getDate(), checkOutField.getDate()).toString());
						roomCostField.setText(getRoomRates(numberField.getText()).toString());
						servicesCostField
								.setText(getServicesFee(servicesField.getSelectedItem().toString()).toString());

						totalField.setText(
								total(servicesCostField.getText(), noOfNightsField.getText(), roomCostField.getText())
										.toString());

						paidField.setFocusable(true);
						paidField.setEditable(true);
						paidField.setBackground(null);
					} catch (Exception e2) {
						totalField.setText("No Room");
					}
				}

			}
		});

		// confirmButton
		confirmButton.setBounds(380, 735, 150, 60);
		confirmButton.setBackground(navyBlue);
		confirmButton.setForeground(babyBlue);
		confirmButton.setFocusable(false);
		confirmButton.setFont(robotoBold14);
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nameField.getText().equals("") || addressField.getText().equals("")
						|| emailField.getText().equals("") || ageField.getText().equals("")
						|| mobileField.getText().equals("") || nationalityField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"One of the fields under guest information is blank.\nPlease chack"
									+ " again before you proceed.");
				} else if (typeField.getSelectedIndex() == 0 || servicesField.getSelectedIndex() == 0
						|| checkInField.getDate() == null || checkOutField.getDate() == null) {
					JOptionPane.showMessageDialog(null,
							"One of the fields under room information is blank.\nPlease chack"
									+ " again before you proceed.");
				} else if (methodField.getSelectedItem().equals("Choose Payment Method")
						|| totalField.getText().equals("") || totalField.getText().equals("")
						|| paidField.getText().equals("") || balanceField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"One of the fields under payment information is blank.\nPlease chack"
									+ " again before you proceed.");
				} else {
					add_transaction();
				}
			}
		});

		// cancelButton
		cancelButton.setBounds(550, 735, 150, 60);
		cancelButton.setBackground(navyBlue);
		cancelButton.setForeground(babyBlue);
		cancelButton.setFocusable(false);
		cancelButton.setFont(robotoBold14);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancel_transaction();
			}
		});

		// adding to the panel
		this.add(findButton);
		this.add(confirmButton);
		this.add(cancelButton);
	}

	public TransactionPanel() {

		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Labels();
		TextFields();
		Buttons();
		default_values();

		repaint();
	}

	String[] capitalized_word_array(String value) {
		String string = value;

		String[] words = string.split(" ");
		String[] finalWords = new String[words.length];

		for (int i = 0; i < words.length; i++) {
			String str = words[i];
			String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
			finalWords[i] = cap;
		}

		return finalWords;
	}

	String capitalized_word_string(String[] words) {
		StringBuilder query = new StringBuilder("");

		for (int i = 0; i < words.length; i++) {
			if (i > 0) {
				query.append(" ");
			}
			query.append(words[i]);
		}

		return query.toString();
	}

	static String[] room_types() {
		String[] roomTypes = null;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select type from room_types where ref_id = " + Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int size = 1 + size(query);

			roomTypes = new String[size];
			roomTypes[0] = "Room Type";
			int i = 1;

			while (rs.next()) {
				roomTypes[i] = rs.getString("type");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomTypes;
	}

	/**
	 * Provides value to the transaction id
	 * 
	 * @return transactionId
	 */

	Integer transaction_id() {
		Integer transactionId = 0;

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from transaction order by id desc";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs.next() == false) {
				transactionId = 1;
			} else {
				transactionId = rs.getInt("id") + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactionId;
	}

	/**
	 * Gets the name of the current staff logged in
	 * 
	 * @return name
	 */

	String staff_name() {
		String name = null;
		int loggedId = Login.getLoginId("employee", Login.getCompanyId(), Login.getUsernameValue());
		int companyId = Login.getCompanyId();

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from employee where ref_id = " + companyId + " and id = " + loggedId + " ";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();

			name = rs.getString("name");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}

	/**
	 * Returns the id of the staff that is currently logged in
	 * 
	 * @return id
	 */

	int staff_id() {
		int id = 0;
		int loggedId = Login.getLoginId("employee", Login.getCompanyId(), Login.getUsernameValue());
		int companyId = Login.getCompanyId();

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from employee where ref_id = " + companyId + " and id = " + loggedId + " ";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();

			id = rs.getInt("id");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	/**
	 * Sets default values in the textfields
	 */

	void default_values() {
		idField.setText(transaction_id().toString());
		dateField.setDate(Date.valueOf(LocalDate.now()));
		staffField.setText(staff_name());
	}

	/**
	 * Returns an array of possible packages that a customer may choose
	 * 
	 * @return services
	 */

	String[] services() {
		String[] services = null;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from services where ref_id = " + Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int size = 1 + size(query);

			services = new String[size];
			services[0] = "Choose a Package";
			int i = 1;

			while (rs.next()) {
				services[i] = rs.getString("name");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
	}

	/**
	 * Returns the size of the result set
	 * 
	 * @return size
	 */

	static int size(String query) {
		int size = 0;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				size++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return size;
	}

	/**
	 * Returns the balance of the customer
	 * 
	 * @return balance
	 */

	Double balance() {
		Double total = Double.parseDouble(totalField.getText());
		Double paid = Double.parseDouble(paidField.getText());

		Double balance = total - paid;

		return balance;
	}

	/**
	 * Resets the value of all text fields
	 */

	void reset_fields() {

		default_values();
		nameField.setText(null);
		addressField.setText(null);
		emailField.setText(null);
		ageField.setText(null);
		mobileField.setText(null);
		nationalityField.setText(null);
		methodField.setSelectedIndex(0);
		totalField.setText(null);
		paidField.setText(null);
		balanceField.setText(null);
		typeField.setSelectedIndex(0);
		servicesField.setSelectedIndex(0);
		checkInField.setDate(null);
		checkOutField.setDate(null);
		numberField.setText(null);
		servicesCostField.setText(null);
		noOfNightsField.setText(null);
		roomCostField.setText(null);
	}

	/**
	 * Cancels the transaction and sets all of the fields into their previous statw
	 */

	void cancel_transaction() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			reset_fields();
		}
	}

	/**
	 * Add transaction to the database
	 */

	void add_transaction() {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String id = idField.getText();
		String date = formatter.format(dateField.getDate()).toString();
		String staff = staffField.getText();
		String name = nameField.getText();
		String address = addressField.getText();
		String email = emailField.getText();
		String age = ageField.getText();
		String mobile = mobileField.getText();
		String nationality = nationalityField.getText();
		String method = methodField.getSelectedItem().toString();
		String total = totalField.getText();
		String paid = paidField.getText();
		String balance = balanceField.getText();
		String type = typeField.getSelectedItem().toString();
		String services = servicesField.getSelectedItem().toString();
		String checkIn = formatter.format(checkInField.getDate()).toString();
		String checkOut = formatter.format(checkOutField.getDate()).toString();
		String number = numberField.getText();
		String status = "Confirmed";

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "insert into `hotel_management_system`.`transaction` (`id`, `ref_id`, `transaction_date`, `staff`, `staff_id`, `name`, `address`, `email`, `age`, `mobile`, `nationality`, `method`, `total`, `paid`, `balance`, `type`, `services`, `check_in`, `check_out`, `number`, `status`,`confirmed`, `room_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, Integer.parseInt(id));
			statement.setInt(2, Login.getCompanyId());
			statement.setString(3, date);
			statement.setString(4, staff);
			statement.setInt(5, staff_id());
			statement.setString(6, name);
			statement.setString(7, address);
			statement.setString(8, email);
			statement.setString(9, age);
			statement.setString(10, mobile);
			statement.setString(11, nationality);
			statement.setString(12, method);
			statement.setDouble(13, Double.parseDouble(total));
			statement.setDouble(14, Double.parseDouble(paid));
			statement.setDouble(15, Double.parseDouble(balance));
			statement.setString(16, type);
			statement.setString(17, services);
			statement.setString(18, checkIn);
			statement.setString(19, checkOut);
			statement.setString(20, number);
			statement.setString(21, status);
			statement.setString(22, "Yes");
			statement.setInt(23, getRoomId());

			statement.execute();
			statement.close();

			update_availability(getRoomId(), checkIn, checkOut);

			JOptionPane.showMessageDialog(null, "Transaction Successfull");
			reset_fields();
			default_values();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Transaction Unsuccessfull");
		}

	}

	/**
	 * Returns the array of room id where the dates of checking in and out are in
	 * between
	 * 
	 * @return idArray
	 */

	static String[] id_array(String type, String checkIn, String checkOut) {
		String[] idArray = null;

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from transaction where ref_id = " + Login.getCompanyId() + " and type = '" + type
					+ "' and (('" + checkIn + "' between check_in and check_out) or ('" + checkOut
					+ "' between check_in and check_out))";
			int size = size(query);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			idArray = new String[size + 1];
			idArray[0] = "0";
			int i = 1;

			while (rs.next()) {
				idArray[i] = rs.getString("room_id");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idArray;
	}

	/**
	 * Finds room that sattisfy the requirements given by the customer
	 * 
	 * @return roomNumber
	 */

	static String find_room(String roomType, java.util.Date checkInDate, java.util.Date checkOutDate) {
		String roomNumber = "No Room";

		try {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String checkIn = formatter.format(checkInDate).toString();
			String checkOut = formatter.format(checkOutDate).toString();
			StringBuilder query = new StringBuilder("Select * from room where ref_id = " + Login.getCompanyId()
					+ " and type = '" + roomType + "' and id not in (");
			String[] id = id_array(roomType, checkIn, checkOut);

			for (int i = 0; i < id.length; i++) {
				if (i > 0) {
					query.append(",");
				}
				query.append("?");
			}
			query.append(")");

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			PreparedStatement pstmt = connection.prepareStatement(query.toString());

			for (int i = 0; i < id.length; i++) {
				pstmt.setString(i + 1, id[i]);
			}
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			roomNumber = rs.getString("number");
			setRoomId(rs.getInt("id"));

		} catch (Exception e) {
			e.printStackTrace();
			roomNumber = "No Room";
		}
		return roomNumber;
	}

	/**
	 * Returns the number of days the guest stayed in the room
	 * 
	 * @return days
	 */

	Integer days(java.util.Date checkIn, java.util.Date checkOut) {

		long timeDifference = checkOut.getTime() - checkIn.getTime();
		Long dayDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
		String day = dayDifference.toString();
		int days = Integer.parseInt(day);

		return days;
	}

	/**
	 * Returns the rates of the room
	 * 
	 * @return roomRates
	 */

	Double getRoomRates(String roomNumber) {
		double roomRates = 0;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from room where number = '" + roomNumber + "' and ref_id = "
					+ Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			roomRates = rs.getDouble("rates");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roomRates;
	}

	/**
	 * Returns the rates of services purchased
	 * 
	 * @return servicesFee
	 */

	Double getServicesFee(String services) {
		double servicesFee = 0;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from services where name = '" + services + "' and ref_id = "
					+ Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			servicesFee = rs.getDouble("rates");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicesFee;
	}

	/**
	 * Returns the total amount based on selected room type, services chosen and
	 * days that the guest will occupy the room
	 * 
	 * @return total
	 */

	Double total(String servicesFee, String noOfNights, String roomCost) {
		double services_fee = Double.parseDouble(servicesFee);
		int no_of_nights = Integer.parseInt(noOfNights);
		double room_cost = Double.parseDouble(roomCost);
		double total = services_fee + (room_cost * no_of_nights);

		return total;
	}

	/**
	 * Getter for roomId
	 * 
	 * @return roomId
	 */

	public static Integer getRoomId() {
		return roomId;
	}

	/**
	 * Setter for roomId
	 */

	public static void setRoomId(Integer roomId) {
		TransactionPanel.roomId = roomId;
	}

	/**
	 * Changes the availability date of the hotel room booked by a guest
	 * 
	 * @throws ParseException
	 */

	void update_availability(Integer roomId, String checkIn, String checkOut) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date currentIn = null;
		java.util.Date currentOut = null;

		java.util.Date in = formatter.parse(checkIn);
		java.util.Date out = formatter.parse(checkOut);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select occupied_from, occupied_to from room where id = " + roomId;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();

			try {
				currentIn = formatter.parse(rs.getString("occupied_from"));
				currentOut = formatter.parse(rs.getString("occupied_to"));
			} catch (Exception e) {

			}

			if (currentIn == null || in.compareTo(currentIn) < 0) {

				String q1 = "update `hotel_management_system`.`room` set `occupied_from` = '" + checkIn
						+ "' where (`id` = '" + roomId + "');";
				Statement ps = connection.createStatement();

				ps.execute(q1);
				// UPDATE `hotel_management_system`.`room` SET `occupied_from` = ? WHERE (`id` =
				// ? );
			}

			if (currentOut == null || out.compareTo(currentOut) > 0) {

				String q1 = "update `hotel_management_system`.`room` set `occupied_to` = '" + checkOut
						+ "' where (`id` = '" + roomId + "');";
				Statement ps = connection.createStatement();

				ps.execute(q1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
