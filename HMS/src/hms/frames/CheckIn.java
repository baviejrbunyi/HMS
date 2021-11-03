package hms.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import hms.main.Login;

public class CheckIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7727098759319372392L;

	Color navyBlue = new Color(0x05445E);
	Color babyBlue = new Color(0xD4F1F4);
	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);
	Font robotoBold20 = new Font("Roboto", Font.BOLD, 20);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoPlain14 = new Font("Roboto", Font.PLAIN, 14);
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");

	public CheckIn(Integer transaction_id, String room_number, String room_type, String guest_name, String services_val,
			String check_out, Double rate, Double service_cost, Double initial_payment) {

		this.setSize(700, 500);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - Check In");
		this.setIconImage(icon);

		JLabel frameTitle = new JLabel("CHECK IN");

		JLabel transactionIdLabel = new JLabel("Transaction Id:");
		JLabel roomNumberLabel = new JLabel("Room Number:");
		JLabel roomTypeLabel = new JLabel("Room Type:");
		JLabel guestNameLabel = new JLabel("Guest Name:");
		JLabel servicesLabel = new JLabel("Services:");
		JLabel noOfAdultsLabel = new JLabel("No. Of Adults:");
		JLabel noOfChildrenLabel = new JLabel("No. Of Children:");
		JLabel noOfGuestsLabel = new JLabel("No. Of Guests:");
		JLabel checkInLabel = new JLabel("Check In:");
		JLabel inCheckOutLabel = new JLabel("In. Check Out:");
		JLabel noOfNightsLabel = new JLabel("No. Of Nights:");
		JLabel roomRateLabel = new JLabel("Rate per Night:");
		JLabel servicesCostLabel = new JLabel("Services Cost:");
		JLabel subTotalLabel = new JLabel("Sub Total:");
		JLabel inPaymentLabel = new JLabel("- In. Payment:");
		JLabel balanceLabel = new JLabel("Balance:");

		JLabel transactionId = new JLabel(transaction_id.toString());
		JLabel roomNumber = new JLabel(room_number);
		JLabel roomType = new JLabel(room_type);
		JLabel guestName = new JLabel(guest_name);
		JLabel services = new JLabel(services_val);
		JTextField noOfAdults = new JTextField("0");
		JTextField noOfChildren = new JTextField("0");
		JLabel noOfGuests = new JLabel();
		JDateChooser checkIn = new JDateChooser();
		JDateChooser inCheckOut = new JDateChooser();
		JLabel noOfNights = new JLabel();
		JLabel roomRate = new JLabel(rate.toString());
		JLabel servicesCost = new JLabel(service_cost.toString());
		JLabel subTotal = new JLabel();
		JLabel inPayment = new JLabel(initial_payment.toString());
		JLabel balance = new JLabel();

		JButton checkInBtn = new JButton("CHECK IN");
		JButton cancelBtn = new JButton("CANCEL");

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 600, 25);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(transactionIdLabel);
		transactionIdLabel.setBounds(20, 60, 150, 20);
		transactionIdLabel.setFont(robotoBold14);
		transactionIdLabel.setForeground(babyBlue);

		this.add(roomNumberLabel);
		roomNumberLabel.setBounds(20, 90, 150, 20);
		roomNumberLabel.setFont(robotoBold14);
		roomNumberLabel.setForeground(babyBlue);

		this.add(roomTypeLabel);
		roomTypeLabel.setBounds(20, 120, 150, 20);
		roomTypeLabel.setFont(robotoBold14);
		roomTypeLabel.setForeground(babyBlue);

		this.add(guestNameLabel);
		guestNameLabel.setBounds(20, 150, 150, 20);
		guestNameLabel.setFont(robotoBold14);
		guestNameLabel.setForeground(babyBlue);

		this.add(servicesLabel);
		servicesLabel.setBounds(20, 180, 150, 20);
		servicesLabel.setFont(robotoBold14);
		servicesLabel.setForeground(babyBlue);

		this.add(noOfAdultsLabel);
		noOfAdultsLabel.setBounds(20, 230, 150, 20);
		noOfAdultsLabel.setFont(robotoBold14);
		noOfAdultsLabel.setForeground(babyBlue);

		this.add(noOfChildrenLabel);
		noOfChildrenLabel.setBounds(20, 260, 150, 20);
		noOfChildrenLabel.setFont(robotoBold14);
		noOfChildrenLabel.setForeground(babyBlue);

		this.add(noOfGuestsLabel);
		noOfGuestsLabel.setBounds(20, 290, 150, 20);
		noOfGuestsLabel.setFont(robotoBold14);
		noOfGuestsLabel.setForeground(babyBlue);

		this.add(checkInLabel);
		checkInLabel.setBounds(350, 60, 150, 20);
		checkInLabel.setFont(robotoBold14);
		checkInLabel.setForeground(babyBlue);

		this.add(inCheckOutLabel);
		inCheckOutLabel.setBounds(350, 90, 150, 20);
		inCheckOutLabel.setFont(robotoBold14);
		inCheckOutLabel.setForeground(babyBlue);

		this.add(noOfNightsLabel);
		noOfNightsLabel.setBounds(350, 120, 150, 20);
		noOfNightsLabel.setFont(robotoBold14);
		noOfNightsLabel.setForeground(babyBlue);

		this.add(roomRateLabel);
		roomRateLabel.setBounds(350, 150, 150, 20);
		roomRateLabel.setFont(robotoBold14);
		roomRateLabel.setForeground(babyBlue);

		this.add(servicesCostLabel);
		servicesCostLabel.setBounds(350, 180, 150, 20);
		servicesCostLabel.setFont(robotoBold14);
		servicesCostLabel.setForeground(babyBlue);

		this.add(subTotalLabel);
		subTotalLabel.setBounds(350, 210, 150, 20);
		subTotalLabel.setFont(robotoBold14);
		subTotalLabel.setForeground(babyBlue);

		this.add(inPaymentLabel);
		inPaymentLabel.setBounds(350, 240, 150, 20);
		inPaymentLabel.setFont(robotoBold14);
		inPaymentLabel.setForeground(babyBlue);

		this.add(balanceLabel);
		balanceLabel.setBounds(350, 270, 150, 20);
		balanceLabel.setFont(robotoBold14);
		balanceLabel.setForeground(babyBlue);

		this.add(transactionId);
		transactionId.setBounds(150, 60, 150, 20);
		transactionId.setFont(robotoPlain14);
		transactionId.setForeground(babyBlue);

		this.add(roomNumber);
		roomNumber.setBounds(150, 90, 150, 20);
		roomNumber.setFont(robotoPlain14);
		roomNumber.setForeground(babyBlue);

		this.add(roomType);
		roomType.setBounds(150, 120, 150, 20);
		roomType.setFont(robotoPlain14);
		roomType.setForeground(babyBlue);

		this.add(guestName);
		guestName.setBounds(150, 150, 150, 20);
		guestName.setFont(robotoPlain14);
		guestName.setForeground(babyBlue);

		this.add(services);
		services.setBounds(150, 180, 150, 20);
		services.setFont(robotoPlain14);
		services.setForeground(babyBlue);

		this.add(noOfAdults);
		noOfAdults.setBounds(150, 230, 50, 20);
		noOfAdults.setForeground(navyBlue);
		noOfAdults.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() >= 48 && e.getKeyChar() <= 57) || e.getKeyCode() == 8) {

				} else if (e.getKeyCode() == 10) {

					String roomTypeVal = roomType.getText();
					int noOfAdultsVal = Integer.parseInt(noOfAdults.getText());
					int noOfChildrenVal = Integer.parseInt(noOfChildren.getText());

					noOfGuests.setText(no_of_guests(roomTypeVal, noOfAdultsVal, noOfChildrenVal).toString());

				} else {

					noOfAdults.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter numerical values only");
					noOfAdults.setEditable(true);
				}

			}

		});

		this.add(noOfChildren);
		noOfChildren.setBounds(150, 260, 50, 20);
		noOfChildren.setForeground(navyBlue);
		noOfChildren.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() >= 48 && e.getKeyChar() <= 57) || e.getKeyCode() == 8) {

				} else if (e.getKeyCode() == 10) {

					String roomTypeVal = roomType.getText();
					int noOfAdultsVal = Integer.parseInt(noOfAdults.getText());
					int noOfChildrenVal = Integer.parseInt(noOfChildren.getText());

					noOfGuests.setText(no_of_guests(roomTypeVal, noOfAdultsVal, noOfChildrenVal).toString());
				} else {

					noOfChildren.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter numerical values only");
					noOfChildren.setEditable(true);
				}

			}

		});

		this.add(noOfGuests);
		noOfGuests.setBounds(150, 290, 100, 20);
		noOfGuests.setForeground(babyBlue);
		noOfGuests.setFont(robotoPlain14);

		this.add(checkIn);
		checkIn.setBounds(500, 60, 150, 20);
		checkIn.setForeground(navyBlue);
		checkIn.setDate(Date.valueOf(LocalDate.now()));
		((JTextComponent) checkIn.getComponent(1)).setEditable(false);
		((JTextComponent) checkIn.getComponent(1)).setFocusable(false);
		checkIn.getComponent(0).setEnabled(false);

		this.add(inCheckOut);
		inCheckOut.setBounds(500, 90, 150, 20);
		inCheckOut.setForeground(navyBlue);
		((JTextComponent) inCheckOut.getComponent(1)).setEditable(false);
		((JTextComponent) inCheckOut.getComponent(1)).setFocusable(false);
		inCheckOut.getComponent(0).setEnabled(false);
		try {
			inCheckOut.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_out));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.add(noOfNights);
		noOfNights.setBounds(500, 120, 150, 20);
		noOfNights.setForeground(babyBlue);
		noOfNights.setFont(robotoPlain14);
		noOfNights.setText(days(checkIn.getDate(), inCheckOut.getDate()).toString());

		this.add(roomRate);
		roomRate.setBounds(500, 150, 150, 20);
		roomRate.setFont(robotoPlain14);
		roomRate.setForeground(babyBlue);

		this.add(servicesCost);
		servicesCost.setBounds(500, 180, 150, 20);
		servicesCost.setFont(robotoPlain14);
		servicesCost.setForeground(babyBlue);

		this.add(subTotal);
		subTotal.setBounds(500, 210, 150, 20);
		subTotal.setFont(robotoPlain14);
		subTotal.setForeground(babyBlue);
		subTotal.setText(total(Integer.parseInt(noOfNights.getText()), Double.parseDouble(roomRate.getText()),
				Double.parseDouble(servicesCost.getText())).toString());

		this.add(inPayment);
		inPayment.setBounds(500, 240, 150, 20);
		inPayment.setFont(robotoPlain14);
		inPayment.setForeground(babyBlue);

		this.add(balance);
		balance.setBounds(500, 270, 150, 20);
		balance.setFont(robotoPlain14);
		balance.setForeground(babyBlue);
		balance.setText(
				balance(Double.parseDouble(subTotal.getText()), Double.parseDouble(inPayment.getText())).toString());

		this.add(services);
		this.add(noOfAdults);
		this.add(noOfChildren);
		this.add(noOfGuests);
		this.add(checkIn);
		this.add(inCheckOut);
		this.add(noOfNights);
		this.add(roomRate);
		this.add(servicesCost);
		this.add(subTotal);
		this.add(inPayment);
		this.add(balance);

		this.add(checkInBtn);
		checkInBtn.setBounds(20, 400, 150, 30);
		checkInBtn.setFont(robotoBold10);
		checkInBtn.setForeground(navyBlue);
		checkInBtn.setBackground(babyBlue);
		checkInBtn.setFocusable(false);
		checkInBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (noOfGuests.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No of guests is undefined");
				} else {
					if (Double.parseDouble(balance.getText()) < 0) {

						int transactionId = transaction_id;
						;
						int no_of_guests = Integer.parseInt(noOfGuests.getText());
						java.util.Date check_in = checkIn.getDate();
						double total = Double.parseDouble(subTotal.getText());
						double paid = total;
						double balance = 0;
						double refund = Double.parseDouble(inPayment.getText()) - total;

						// System.out.println("Total = " + total + "\nPaid = " + paid + "\nBalance = "+
						// balance+ "\nRefund = "+refund);

						check_in(transactionId, no_of_guests, check_in, total, paid, balance, refund);
					} else {
						check_in(transaction_id, Integer.parseInt(noOfGuests.getText()), checkIn.getDate());
					}

				}

			}
		});

		this.add(cancelBtn);
		cancelBtn.setBounds(200, 400, 150, 30);
		cancelBtn.setFont(robotoBold10);
		cancelBtn.setForeground(navyBlue);
		cancelBtn.setBackground(babyBlue);
		cancelBtn.setFocusable(false);
		cancelBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Checking In cancelled");
			}
		});

	}

	Integer no_of_guests(String room_type, int no_of_adults, int no_of_children) {

		int no_of_guests = 0;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from room_types where type = '" + room_type + "' && ref_id = "
					+ Login.getCompanyId();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();

			no_of_guests = no_of_adults + no_of_children;
			int capacity = rs.getInt("capacity");

			if (capacity >= no_of_guests) {
				no_of_guests = no_of_adults + no_of_children;
			} else {
				no_of_guests = 0;
				JOptionPane.showMessageDialog(null,
						"No of Guests is higer than the capacity of the room selected. \nGuests must be less than or equal to "
								+ capacity + ". \nThank you.");
			}

		} catch (Exception e) {

		}

		return no_of_guests;
	}

	Integer days(java.util.Date checkIn, java.util.Date checkOut) {

		long timeDifference = checkOut.getTime() - checkIn.getTime();
		Long dayDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
		String day = dayDifference.toString();
		int days = Integer.parseInt(day);

		return days;
	}

	Double total(int days, double rate, double service_cost) {
		double total = ((rate * days) + service_cost);

		return total;
	}

	Double balance(double total, double amount_paid) {
		double balance = total - amount_paid;

		return balance;
	}

	void check_in(int transaction_id, int no_of_guests, java.util.Date check_in) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String checkIn = formatter.format(check_in);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set no_of_guests = " + no_of_guests + ", check_in = '" + checkIn
					+ "', status = 'Checked In' where id = " + transaction_id;
			Statement st = connection.createStatement();
			st.execute(query);

			dispose();
			JOptionPane.showMessageDialog(null, "Checked in successfully");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Checking in terminated due to error");
		}
	}

	void check_in(int transaction_id, int no_of_guests, java.util.Date check_in, double total, double paid,
			double balance, double refund) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String checkIn = formatter.format(check_in);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set no_of_guests = " + no_of_guests + ", check_in = '" + checkIn
					+ "', status = 'Checked In', refund = " + refund + " , total = " + total + " , paid = " + paid
					+ " , balance = " + balance + " where id = " + transaction_id;
			Statement st = connection.createStatement();
			st.execute(query);

			dispose();
			JOptionPane.showMessageDialog(null, "Checked in successfully");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Checking in terminated due to error");
		}
	}

}
