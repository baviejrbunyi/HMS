package hms.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import hms.main.Login;

public class Dialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6030619862479380304L;

	Color navyBlue = new Color(0x05445E);
	Color babyBlue = new Color(0xD4F1F4);
	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoPlain14 = new Font("Roboto", Font.PLAIN, 14);
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");

	public Dialog(String header, String title, int transaction_id) {
		this.setSize(300, 300);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle(title);
		this.setIconImage(icon);

		JLabel headerLabel = new JLabel(header);
		JTextArea field = new JTextArea();
		JButton submitButton = new JButton("SUBMIT");
		JButton cancelButton = new JButton("CANCEL");

		headerLabel.setBounds(10, 10, 300, 20);
		headerLabel.setFont(robotoBold14);
		headerLabel.setForeground(babyBlue);

		field.setFont(robotoPlain14);
		field.setBackground(babyBlue);
		field.setForeground(navyBlue);
		field.setWrapStyleWord(true);
		field.setLineWrap(true);
		field.setBorder(
				BorderFactory.createCompoundBorder(field.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scroll = new JScrollPane(field);
		scroll.setBounds(10, 50, 265, 150);
		scroll.setBorder(null);

		submitButton.setBackground(babyBlue);
		submitButton.setBounds(10, 220, 125, 20);
		submitButton.setFont(robotoBold10);
		submitButton.setFocusable(false);
		submitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (headerLabel.getText().equals("Reason for Denying your Cancellation")) {
					deny_cancellation(transaction_id, field);
				} else if (headerLabel.getText().equals("Reason for Booking Cancellation")) {
					cancel_booking(transaction_id, field);
				}
			}
		});

		cancelButton.setBackground(babyBlue);
		cancelButton.setBounds(150, 220, 125, 20);
		cancelButton.setFont(robotoBold10);
		cancelButton.setFocusable(false);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (headerLabel.getText().equals("Reason for Denying your Cancellation")) {
					JOptionPane.showMessageDialog(null, "Denying cancellation terminated");
				} else if (headerLabel.getText().equals("Reason for Booking Cancellation")) {
					JOptionPane.showMessageDialog(null, "Booking cancellation terminated");
				}
			}
		});

		this.add(headerLabel);
		this.add(scroll);
		this.add(submitButton);
		this.add(cancelButton);
	}

	void cancel_booking(int transaction_id, JTextArea field) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set confirmed = 'Yes', status = 'Cancelled By Staff', staff = '"
					+ Login.getNameValue("employee", Login.getCompanyId(), Login.getUsernameValue()) + "', notes = '"
					+ field.getText() + "' 							where id = " + transaction_id;

			Statement statement = connection.createStatement();
			statement.execute(query);
			dispose();
			JOptionPane.showMessageDialog(null, "Booking cancellation successful");

		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Booking cancellation not successful");
		}
	}

	void deny_cancellation(int transaction_id, JTextArea field) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update transaction set  status = 'Confirmed', staff = '"
					+ Login.getNameValue("employee", Login.getCompanyId(), Login.getUsernameValue()) + "', notes = '"
					+ field.getText() + "' 							where id = " + transaction_id;

			Statement statement = connection.createStatement();
			statement.execute(query);
			dispose();
			JOptionPane.showMessageDialog(null, "Cancellation denied successfully");

		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cancellation denied unsucessfully");
		}
	}
}
