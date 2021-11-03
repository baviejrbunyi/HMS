package hms.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class ReSched extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -904555299640833175L;

	Color navyBlue = new Color(0x05445E);
	Color babyBlue = new Color(0xD4F1F4);
	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);
	Font robotoBold20 = new Font("Roboto", Font.BOLD, 20);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoPlain14 = new Font("Roboto", Font.PLAIN, 14);
	Font robotoPlain12 = new Font("Roboto", Font.PLAIN, 12);
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");

	public ReSched(int transaction_id, String check_in, String check_out) {
		this.setSize(350, 400);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - Re-schedule Booking");
		this.setIconImage(icon);

		JLabel frameTitle = new JLabel("RE-SCHEDULE BOOKING");

		JLabel checkInLabel = new JLabel("Check In:");
		JLabel checkOutLabel = new JLabel("Check Out:");

		JTextArea message = new JTextArea("Message");
		JScrollPane scroll = new JScrollPane(message);

		JDateChooser checkIn = new JDateChooser();
		JDateChooser checkOut = new JDateChooser();

		JButton confirmBtn = new JButton("CONFIRM");
		JButton cancelBtn = new JButton("CANCEL");

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 30);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(checkInLabel);
		checkInLabel.setBounds(20, 60, 100, 20);
		checkInLabel.setForeground(babyBlue);
		checkInLabel.setFont(robotoBold14);

		this.add(checkOutLabel);
		checkOutLabel.setBounds(20, 90, 100, 20);
		checkOutLabel.setForeground(babyBlue);
		checkOutLabel.setFont(robotoBold14);

		this.add(scroll);
		scroll.setBounds(20, 140, 300, 150);
		message.setForeground(navyBlue);
		message.setBackground(babyBlue);
		message.setWrapStyleWord(true);
		message.setLineWrap(true);
		message.setBorder(
				BorderFactory.createCompoundBorder(message.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		message.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (message.getText().equals("Message")) {
					message.setText("");
				}
			}
		});

		this.add(checkIn);
		checkIn.setBounds(120, 60, 200, 20);
		checkIn.getComponent(1).setFocusable(true);
		checkIn.getJCalendar().setMinSelectableDate(new Date());
		checkIn.getComponent(1).addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				checkOut.getJCalendar().setMinSelectableDate(checkIn.getDate());
			}
		});
		try {
			checkIn.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_in));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		this.add(checkOut);
		checkOut.setBounds(120, 90, 200, 20);
		checkOut.getJCalendar().setMinSelectableDate(new Date());
		try {
			checkOut.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(check_out));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		this.add(confirmBtn);
		confirmBtn.setBounds(20, 310, 140, 25);
		confirmBtn.setForeground(navyBlue);
		confirmBtn.setBackground(babyBlue);
		confirmBtn.setFont(robotoBold10);
		confirmBtn.setFocusable(false);
		confirmBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String checkOutVal = formatter.format(checkOut.getDate());
				String checkInVal = formatter.format(checkIn.getDate());

				try {
					Connection connection = DriverManager
							.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system", "root", "465a7dhn");
					String query = "update transaction set check_in = '" + checkInVal + "', check_out = '" + checkOutVal
							+ "', notes = '" + message.getText() + "' where id = " + transaction_id;

					Statement statement = connection.createStatement();
					statement.execute(query);

					dispose();
					JOptionPane.showMessageDialog(null, "Re-Scheduling Successfull");

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		this.add(cancelBtn);
		cancelBtn.setBounds(180, 310, 140, 25);
		cancelBtn.setForeground(navyBlue);
		cancelBtn.setBackground(babyBlue);
		cancelBtn.setFont(robotoBold10);
		cancelBtn.setFocusable(false);
		cancelBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Re-Scheduling terminated");
			}
		});

	}

}
