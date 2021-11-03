package hms.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hms.employee.panels.TransactionPanel;

public class CreditCard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8623199688603434747L;

	Color navyBlue = new Color(0x05445E);
	Color babyBlue = new Color(0xD4F1F4);
	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);
	Font robotoBold20 = new Font("Roboto", Font.BOLD, 20);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoPlain14 = new Font("Roboto", Font.PLAIN, 14);
	Font robotoPlain12 = new Font("Roboto", Font.PLAIN, 12);
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Color errorRed = new Color(0xCB6565);
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");

	public CreditCard() {
		this.setSize(350, 350);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - Credit Card");
		this.setIconImage(icon);

		JLabel frameTitle = new JLabel("CREDIT CARD");

		JLabel cardTypeLabel = new JLabel("Card Type:");
		JLabel cardNumberLabel = new JLabel("Card Number:");

		JTextArea error = new JTextArea();

		String[] cardTypes = { "Visa", "MasterCard", "AmEx" };
		JComboBox<String> cardType = new JComboBox<String>(cardTypes);
		JTextField cardNumber = new JTextField();

		JButton confirmButton = new JButton("CONFIRM");
		JButton cancelButton = new JButton("CANCEL");

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 150, 25);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(cardTypeLabel);
		cardTypeLabel.setBounds(20, 70, 150, 20);
		cardTypeLabel.setFont(robotoBold14);
		cardTypeLabel.setForeground(babyBlue);

		this.add(cardNumberLabel);
		cardNumberLabel.setBounds(20, 100, 150, 20);
		cardNumberLabel.setFont(robotoBold14);
		cardNumberLabel.setForeground(babyBlue);

		this.add(cardType);
		cardType.setBounds(170, 70, 150, 20);
		cardType.setFocusable(false);
		cardType.setFont(robotoPlain14);

		this.add(cardNumber);
		cardNumber.setBounds(170, 100, 150, 20);
		cardNumber.setFont(robotoPlain14);
		cardNumber.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (cardNumber.getText().equals("")) {
						error.setText("Card number field is empty");
					} else {
						check_credit_card(cardType.getSelectedItem(), cardNumber.getText(), error);
					}
				}
			}
		});

		this.add(error);
		error.setBounds(20, 135, 300, 100);
		error.setBackground(null);
		error.setForeground(errorRed);
		error.setLineWrap(true);
		error.setWrapStyleWord(true);

		this.add(confirmButton);
		confirmButton.setBounds(20, 240, 140, 30);
		confirmButton.setForeground(navyBlue);
		confirmButton.setBackground(babyBlue);
		confirmButton.setFocusable(false);
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cardNumber.getText().equals("")) {
					error.setText("Card number field is empty");
				} else {
					check_credit_card(cardType.getSelectedItem(), cardNumber.getText(), error);

					if (error.getText().equals("")) {
						String[] card = splitToNChar(cardNumber.getText(), 4);

						TransactionPanel.methodField
								.setSelectedItem(cardType.getSelectedItem().toString() + " " + card[3]);
						dispose();
					}
				}
			}
		});

		this.add(cancelButton);
		cancelButton.setBounds(170, 240, 150, 30);
		cancelButton.setForeground(navyBlue);
		cancelButton.setBackground(babyBlue);
		cancelButton.setFocusable(false);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		revalidate();
		repaint();

	}

	void check_credit_card(Object creditCardType, String creditCardNumber, JTextArea area) {
		String visa = "^4[0-9]{12}(?:[0-9]{3})?$";
		String mastercard = "^?5[1-5][0-9]{14}?$";
		String americanExpress = "^?3[47][0-9]{13}?$";
		Pattern p;

		if (creditCardType == "Visa") {
			p = Pattern.compile(visa);
			Matcher m = p.matcher(creditCardNumber);

			if (m.matches() == false) {
				area.setText("Credit card is invalid");
			} else {
				area.setText("");
			}

		} else if (creditCardType == "MasterCard") {
			p = Pattern.compile(mastercard);
			Matcher m = p.matcher(creditCardNumber);

			if (m.matches() == false) {
				area.setText("Credit card is invalid");
			} else {
				area.setText("");
			}

		} else if (creditCardType == "AmEx") {
			p = Pattern.compile(americanExpress);
			Matcher m = p.matcher(creditCardNumber);

			if (m.matches() == false) {
				area.setText("Credit card is invalid");
			} else {
				area.setText("");
			}

		}
	}

	private static String[] splitToNChar(String text, int size) {
		List<String> parts = new ArrayList<>();

		int length = text.length();
		for (int i = 0; i < length; i += size) {
			parts.add(text.substring(i, Math.min(length, i + size)));
		}
		return parts.toArray(new String[0]);
	}

}
