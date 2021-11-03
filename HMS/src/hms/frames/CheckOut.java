package hms.frames;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;

public class CheckOut extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458087238306227135L;

	public CheckOut(Integer transaction_id, String room_number, String room_type, String guest_name, String check_in,
			String check_out, String services, Double initial_total, Double rate, Double services_cost,
			Double initial_payment) {
		this.setSize(700, 500);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - Check Out");
		this.setIconImage(icon);

		JLabel frameTitle = new JLabel("CHECK OUT SUMMARY");

		JLabel transactionIdLabel = new JLabel("Transaction Id:");
		JLabel roomNumberLabel = new JLabel("Room Number:");
		JLabel roomTypeLabel = new JLabel("Room Type:");
		JLabel guestNameLabel = new JLabel("Guest Name:");
		JLabel checkInLabel = new JLabel("Check In:");
		JLabel initialCheckOutLabel = new JLabel("In. Check Out:");
		JLabel servicesLabel = new JLabel("Services:");
		JLabel initialTotalLabel = new JLabel("In. Total:");
		JLabel checkOutLabel = new JLabel("Check Out:");
		JLabel noOfNightsLabel = new JLabel("No. of Nights:");
		JLabel ratePerNightLabel = new JLabel("Rate/Night");
		JLabel servicesCostLabel = new JLabel("Services Cost");
		JLabel subTotalLabel = new JLabel("Sub Total:");
		JLabel initialPaymentLabel = new JLabel("- Initial Payment:");
		JLabel totalLabel = new JLabel("Total:");
		JLabel amountPaidLabel = new JLabel("Amount Paid");
		JLabel balanceLabel = new JLabel("Balance:");

		JLabel transactionId = new JLabel(transaction_id.toString());
		JLabel roomNumber = new JLabel(room_number);
		JLabel roomType = new JLabel(room_type);
		JLabel guestName = new JLabel(guest_name);
		JDateChooser checkIn = new JDateChooser();
		JLabel initialCheckOut = new JLabel(check_out);
		JLabel servicesValue = new JLabel(services);
		JLabel initialTotal = new JLabel(initial_total.toString());
		JDateChooser checkOut = new JDateChooser();
		JLabel noOfNights = new JLabel();
		JLabel ratePerNight = new JLabel(rate.toString());
		JLabel servicesCost = new JLabel(services_cost.toString());
		JLabel subTotal = new JLabel();
		JLabel initialPayment = new JLabel(initial_payment.toString());
		JLabel total = new JLabel();
		JTextField amountPaid = new JTextField();
		JLabel balance = new JLabel();

		JButton checkOutBtn = new JButton("CHECK OUT");
		JButton cancelBtn = new JButton("CANCEL");
		JButton computeBtn = new JButton();

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

		this.add(checkInLabel);
		checkInLabel.setBounds(20, 200, 150, 20);
		checkInLabel.setFont(robotoBold14);
		checkInLabel.setForeground(babyBlue);

		this.add(initialCheckOutLabel);
		initialCheckOutLabel.setBounds(20, 230, 150, 20);
		initialCheckOutLabel.setFont(robotoBold14);
		initialCheckOutLabel.setForeground(babyBlue);

		this.add(servicesLabel);
		servicesLabel.setBounds(20, 260, 150, 20);
		servicesLabel.setFont(robotoBold14);
		servicesLabel.setForeground(babyBlue);

		this.add(initialTotalLabel);
		initialTotalLabel.setBounds(20, 290, 150, 20);
		initialTotalLabel.setFont(robotoBold14);
		initialTotalLabel.setForeground(babyBlue);

		this.add(checkOutLabel);
		checkOutLabel.setBounds(350, 60, 150, 20);
		checkOutLabel.setFont(robotoBold14);
		checkOutLabel.setForeground(babyBlue);

		this.add(noOfNightsLabel);
		noOfNightsLabel.setBounds(350, 90, 150, 20);
		noOfNightsLabel.setFont(robotoBold14);
		noOfNightsLabel.setForeground(babyBlue);

		this.add(ratePerNightLabel);
		ratePerNightLabel.setBounds(350, 120, 150, 20);
		ratePerNightLabel.setFont(robotoBold14);
		ratePerNightLabel.setForeground(babyBlue);

		this.add(servicesCostLabel);
		servicesCostLabel.setBounds(350, 150, 150, 20);
		servicesCostLabel.setFont(robotoBold14);
		servicesCostLabel.setForeground(babyBlue);

		this.add(subTotalLabel);
		subTotalLabel.setBounds(350, 180, 150, 20);
		subTotalLabel.setFont(robotoBold14);
		subTotalLabel.setForeground(babyBlue);

		this.add(initialPaymentLabel);
		initialPaymentLabel.setBounds(350, 210, 150, 20);
		initialPaymentLabel.setFont(robotoBold14);
		initialPaymentLabel.setForeground(babyBlue);

		this.add(totalLabel);
		totalLabel.setBounds(350, 260, 150, 20);
		totalLabel.setFont(robotoBold14);
		totalLabel.setForeground(babyBlue);

		this.add(amountPaidLabel);
		amountPaidLabel.setBounds(350, 290, 150, 20);
		amountPaidLabel.setFont(robotoBold14);
		amountPaidLabel.setForeground(babyBlue);

		this.add(balanceLabel);
		balanceLabel.setBounds(350, 320, 150, 20);
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

		this.add(checkIn);
		checkIn.setBounds(150, 200, 150, 20);
		checkIn.setFont(robotoPlain14);
		checkIn.setForeground(babyBlue);
		checkIn.setBackground(navyBlue);
		((JTextComponent) checkIn.getComponent(1)).setEditable(false);
		((JTextComponent) checkIn.getComponent(1)).setFocusable(false);
		checkIn.getComponent(0).setEnabled(false);
		try {
			checkIn.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_in));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.add(initialCheckOut);
		initialCheckOut.setBounds(150, 230, 150, 20);
		initialCheckOut.setFont(robotoPlain14);
		initialCheckOut.setForeground(babyBlue);

		this.add(servicesValue);
		servicesValue.setBounds(150, 260, 150, 20);
		servicesValue.setFont(robotoPlain14);
		servicesValue.setForeground(babyBlue);

		this.add(initialTotal);
		initialTotal.setBounds(150, 290, 150, 20);
		initialTotal.setFont(robotoPlain14);
		initialTotal.setForeground(babyBlue);

		this.add(checkOut);
		checkOut.setBounds(500, 60, 150, 20);
		checkOut.setFont(robotoPlain14);
		checkOut.setForeground(Color.black);
		checkOut.setDate(Date.valueOf(LocalDate.now()));
		((JTextComponent) checkOut.getComponent(1)).setEditable(false);
		((JTextComponent) checkOut.getComponent(1)).setFocusable(false);
		checkOut.getComponent(0).setEnabled(false);

		this.add(noOfNights);
		noOfNights.setBounds(500, 90, 150, 20);
		noOfNights.setFont(robotoPlain14);
		noOfNights.setForeground(babyBlue);
		noOfNights.setText(days(checkIn.getDate(), checkOut.getDate()).toString());

		this.add(ratePerNight);
		ratePerNight.setBounds(500, 120, 150, 20);
		ratePerNight.setFont(robotoPlain14);
		ratePerNight.setForeground(babyBlue);

		this.add(servicesCost);
		servicesCost.setBounds(500, 150, 150, 20);
		servicesCost.setFont(robotoPlain14);
		servicesCost.setForeground(babyBlue);

		this.add(subTotal);
		subTotal.setBounds(500, 180, 150, 20);
		subTotal.setFont(robotoPlain14);
		subTotal.setForeground(babyBlue);
		subTotal.setText(sub_total(Integer.parseInt(noOfNights.getText()), Double.parseDouble(ratePerNight.getText()),
				Double.parseDouble(servicesCost.getText())).toString());

		this.add(initialPayment);
		initialPayment.setBounds(500, 210, 150, 20);
		initialPayment.setFont(robotoPlain14);
		initialPayment.setForeground(babyBlue);

		this.add(total);
		total.setBounds(500, 260, 150, 20);
		total.setFont(robotoPlain14);
		total.setForeground(babyBlue);
		total.setText(
				total(Double.parseDouble(subTotal.getText()), Double.parseDouble(initialPayment.getText())).toString());

		this.add(amountPaid);
		amountPaid.setBounds(500, 290, 130, 20);
		amountPaid.setFont(robotoPlain14);
		amountPaid.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() >= 48 && e.getKeyChar() <= 57) || e.getKeyCode() == 8) {

				} else if (e.getKeyCode() == 10) {
					try {
						balance.setText(
								balance(Double.parseDouble(total.getText()), Double.parseDouble(amountPaid.getText()))
										.toString());
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Field is empty");
					}

				} else {

					amountPaid.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter numerical values only");
					amountPaid.setEditable(true);
				}
			}
		});

		this.add(balance);
		balance.setBounds(500, 320, 150, 20);
		balance.setFont(robotoPlain14);
		balance.setForeground(babyBlue);

		this.add(checkOutBtn);
		checkOutBtn.setBounds(20, 400, 150, 30);
		checkOutBtn.setFont(robotoBold10);
		checkOutBtn.setForeground(navyBlue);
		checkOutBtn.setBackground(babyBlue);
		checkOutBtn.setFocusable(false);
		checkOutBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int transaction_id = Integer.parseInt(transactionId.getText());
				String guest_name = guestName.getText();
				java.util.Date check_out = checkOut.getDate();
				double totalval = Double.parseDouble(total.getText());
				double amount_paid = Double.parseDouble(amountPaid.getText());
				double balanceval = Double.parseDouble(balance.getText());
				double initial_payment = Double.parseDouble(initialPayment.getText());

				// checks if the payment input is greater than 0
				if (amount_paid <= 0) {
					System.out.println("Amount paid is 0");
					amount_paid = Math.abs(totalval);
					totalval = amount_paid;
					balanceval = 0;
					double refund = initial_payment - amount_paid;

					check_out(transaction_id, guest_name, check_out, totalval, amount_paid, balanceval, refund);

				} else {
					System.out.println("Amount paid is not 0");
					System.out.println("Amount paid = " + amount_paid + "\nTotal Value = " + totalval + "\nBalance = "
							+ balanceval);
					check_out(transaction_id, guest_name, check_out, totalval, amount_paid, balanceval);
				}

				// check_out(transaction_id, guest_name, check_out, totalval, amount_paid,
				// balanceval, initial_payment);
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
				JOptionPane.showMessageDialog(null, "Checking Out cancelled");
			}
		});

		this.add(computeBtn);
		computeBtn.setBounds(630, 290, 20, 20);
		computeBtn.setFocusable(false);
		computeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					balance.setText(
							balance(Double.parseDouble(total.getText()), Double.parseDouble(amountPaid.getText()))
									.toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Field is empty");
				}
			}
		});

	}

	/**
	 * Returns the number of days the guest stayed in the room
	 * 
	 * @return days
	 * @throws ParseException
	 */

	Integer days(java.util.Date checkIn, java.util.Date checkOut) {

		long timeDifference = checkOut.getTime() - checkIn.getTime();
		Long dayDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
		String day = dayDifference.toString();
		int days = Integer.parseInt(day);

		return days;
	}

	Double total(double sub_total, double initial_payment) {
		double total = sub_total - initial_payment;

		return total;
	}

	Double balance(double total, double amount_paid) {
		double balance = total - amount_paid;

		return balance;
	}

	void check_out(int transaction_id, String guest_name, java.util.Date check_out, double total, double amount_paid,
			double balance) {

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String checkOut = formatter.format(check_out);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set name = '" + guest_name + "', check_out = '" + checkOut
					+ "', total = " + total + ", paid = " + amount_paid + ", balance = " + balance
					+ ", status = 'Completed' where id = " + transaction_id;

			System.out.println(query);
			Statement st = connection.createStatement();
			st.execute(query);

			this.dispose();
			JOptionPane.showMessageDialog(null, "Checked Out Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Checked Out Unsuccessfully");
		}
	}

	void check_out(int transaction_id, String guest_name, java.util.Date check_out, double total, double amount_paid,
			double balance, double refund) {

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String checkOut = formatter.format(check_out);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set name = '" + guest_name + "', check_out = '" + checkOut
					+ "', total = " + total + ", paid = " + amount_paid + ", balance = " + balance + ", refund = "
					+ refund + ", status = 'Completed' where id = " + transaction_id;

			System.out.println(query);
			Statement st = connection.createStatement();
			st.execute(query);

			this.dispose();
			JOptionPane.showMessageDialog(null, "Checked Out Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Checked Out Unsuccessfully");
		}
	}

	Double sub_total(int days, double rate, double service_cost) {
		double subTotal = (rate * days) + service_cost;

		return subTotal;
	}

}
