package hms.frames;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hms.main.Login;

public class ForgotPassword extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6827395616352415902L;

	private String password, role;
	JPanel sendVerificationPanel = new JPanel();
	JPanel verifyAccountPanel = new JPanel();
	JPanel changePasswordPanel = new JPanel();

	public ForgotPassword() {

		this.setSize(400, 400);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - Forgot Password");
		this.setIconImage(icon);

		send_verification();

		this.add(sendVerificationPanel);
		sendVerificationPanel.setBounds(0, 0, 400, 400);
		sendVerificationPanel.setBackground(navyBlue);
		sendVerificationPanel.setLayout(null);

		this.add(verifyAccountPanel);

		this.add(changePasswordPanel);

	}

	private void send_verification() {
		JLabel panelLabel = new JLabel("RESET PASSWORD");
		JTextArea message = new JTextArea("Enter the email associated with your account and we'll send an"
				+ " email with instructions to reset your password");
		JLabel emailLabel = new JLabel("Email Address");
		JTextField emailField = new JTextField();
		JButton sendButton = new JButton("SEND EMAIL");
		JLabel error = new JLabel();
		JLabel cancel = new JLabel("Cancel");

		sendVerificationPanel.add(panelLabel);
		panelLabel.setBounds(20, 20, 300, 30);
		panelLabel.setFont(robotoBold20);
		panelLabel.setForeground(babyBlue);

		sendVerificationPanel.add(message);
		message.setBounds(20, 55, 345, 30);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setForeground(babyBlue);
		message.setFont(robotoPlain12);
		message.setBackground(null);
		message.setFocusable(false);
		message.setEditable(false);

		sendVerificationPanel.add(emailLabel);
		emailLabel.setBounds(20, 125, 300, 20);
		emailLabel.setFont(robotoBold14);
		emailLabel.setForeground(babyBlue);

		sendVerificationPanel.add(emailField);
		emailField.setBounds(20, 155, 345, 35);
		emailField.setFont(robotoPlain14);
		emailField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		emailField.requestFocus();
		emailField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

					if (!emailField.getText().matches(EMAIL_PATTERN)) {
						error.setText("Invalid Email Address");
					} else {
						check_account(emailField.getText(), error);
					}
				}
			}
		});

		sendVerificationPanel.add(sendButton);
		sendButton.setBounds(20, 300, 150, 40);
		sendButton.setBackground(babyBlue);
		sendButton.setForeground(navyBlue);
		sendButton.setFocusable(false);
		sendButton.setFont(robotoBold10);
		sendButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				if (!emailField.getText().matches(EMAIL_PATTERN)) {
					error.setText("Invalid Email Address");
				} else {
					check_account(emailField.getText(), error);
				}

			}
		});

		sendVerificationPanel.add(error);
		error.setBounds(20, 195, 345, 20);
		error.setFont(robotoPlain12);
		error.setForeground(orangeAccent);

		sendVerificationPanel.add(cancel);
		cancel.setBounds(230, 325, 128, 12);
		cancel.setFont(robotoBold12);
		cancel.setForeground(orangeAccent);
		cancel.setHorizontalAlignment(SwingConstants.RIGHT);
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login();
			}
		});

	}

	private void verify_account(String email) {
		verifyAccountPanel.setBounds(0, 0, 400, 400);
		verifyAccountPanel.setBackground(navyBlue);
		verifyAccountPanel.setLayout(null);

		JLabel panelLabel = new JLabel("RESET PASSWORD");
		JTextArea message = new JTextArea(
				"Enter the six digit verification code we just sent you on your email address");

		JLabel verificationLabel = new JLabel("Verification Code");
		JTextField digit1 = new JTextField();
		JTextField digit2 = new JTextField();
		JTextField digit3 = new JTextField();
		JTextField digit4 = new JTextField();
		JTextField digit5 = new JTextField();
		JTextField digit6 = new JTextField();

		JLabel resendLabel = new JLabel("Didn't recieve the code?");
		JLabel resend = new JLabel("Resend");

		JButton verifyButton = new JButton("VERIFY");
		JLabel error = new JLabel();

		verifyAccountPanel.add(panelLabel);
		panelLabel.setBounds(20, 20, 300, 30);
		panelLabel.setFont(robotoBold20);
		panelLabel.setForeground(babyBlue);

		verifyAccountPanel.add(message);
		message.setBounds(20, 55, 345, 30);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setForeground(babyBlue);
		message.setFont(robotoPlain12);
		message.setBackground(null);
		message.setFocusable(false);
		message.setEditable(false);

		verifyAccountPanel.add(verificationLabel);
		verificationLabel.setBounds(20, 125, 300, 20);
		verificationLabel.setFont(robotoBold14);
		verificationLabel.setForeground(babyBlue);

		verifyAccountPanel.add(digit1);
		digit1.setBounds(20, 155, (int) 52.5, 35);
		digit1.setFont(robotoPlain14);
		digit1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit1.setHorizontalAlignment(SwingConstants.CENTER);
		digit1.requestFocus();
		digit1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit1.getText().length() == 1) {
					e.consume();
				} else {
					digit2.setFocusable(true);
					digit2.requestFocus();
				}
			}

		});

		verifyAccountPanel.add(digit2);
		digit2.setBounds((int) 78.5, 155, (int) 52.5, 35);
		digit2.setFont(robotoPlain14);
		digit2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit2.setFocusable(false);
		digit2.setHorizontalAlignment(SwingConstants.CENTER);
		digit2.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit2.getText().length() == 1) {
					e.consume();
				} else {
					digit3.setFocusable(true);
					digit3.requestFocus();
				}
			}

		});

		verifyAccountPanel.add(digit3);
		digit3.setBounds(137, 155, (int) 52.5, 35);
		digit3.setFont(robotoPlain14);
		digit3.setFocusable(false);
		digit3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit3.setHorizontalAlignment(SwingConstants.CENTER);
		digit3.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit3.getText().length() == 1) {
					e.consume();
				} else {
					digit4.setFocusable(true);
					digit4.requestFocus();
				}
			}

		});

		verifyAccountPanel.add(digit4);
		digit4.setBounds((int) 195.5, 155, (int) 52.5, 35);
		digit4.setFont(robotoPlain14);
		digit4.setFocusable(false);
		digit4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit4.setHorizontalAlignment(SwingConstants.CENTER);
		digit4.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit4.getText().length() == 1) {
					e.consume();
				} else {
					digit5.setFocusable(true);
					digit5.requestFocus();
				}
			}

		});

		verifyAccountPanel.add(digit5);
		digit5.setBounds(254, 155, (int) 52.5, 35);
		digit5.setFont(robotoPlain14);
		digit5.setFocusable(false);
		digit5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit5.setHorizontalAlignment(SwingConstants.CENTER);
		digit5.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit5.getText().length() == 1) {
					e.consume();
				} else {
					digit6.setFocusable(true);
					digit6.requestFocus();
				}
			}

		});

		verifyAccountPanel.add(digit6);
		digit6.setBounds((int) 312.5, 155, (int) 52.5, 35);
		digit6.setFont(robotoPlain14);
		digit6.setFocusable(false);
		digit6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		digit6.setHorizontalAlignment(SwingConstants.CENTER);
		digit6.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					verify_code(email, digit1.getText(), digit2.getText(), digit3.getText(), digit4.getText(),
							digit5.getText(), digit6.getText(), error);
				}
			}

			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar())) || digit6.getText().length() == 1) {
					e.consume();
				}
			}

		});

		verifyAccountPanel.add(error);
		error.setBounds(20, 195, 345, 20);
		error.setFont(robotoPlain12);
		error.setForeground(orangeAccent);

		verifyAccountPanel.add(verifyButton);
		verifyButton.setBounds(20, 300, 150, 40);
		verifyButton.setBackground(babyBlue);
		verifyButton.setForeground(navyBlue);
		verifyButton.setFocusable(false);
		verifyButton.setFont(robotoBold10);
		verifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				verify_code(email, digit1.getText(), digit2.getText(), digit3.getText(), digit4.getText(),
						digit5.getText(), digit6.getText(), error);
			}
		});

		verifyAccountPanel.add(resendLabel);
		resendLabel.setBounds(230, 297, 128, 12);
		resendLabel.setFont(robotoPlain12);
		resendLabel.setForeground(babyBlue);
		resendLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		verifyAccountPanel.add(resend);
		resend.setBounds(230, 315, 128, 12);
		resend.setFont(robotoBold12);
		resend.setForeground(orangeAccent);
		resend.setHorizontalAlignment(SwingConstants.RIGHT);
		resend.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Email sent.\nPlease check your inbox");
			}
		});

	}

	private void change_password(String email) {
		changePasswordPanel.setBounds(0, 0, 400, 400);
		changePasswordPanel.setBackground(navyBlue);
		changePasswordPanel.setLayout(null);

		JLabel panelLabel = new JLabel("CHANGE PASSWORD");
		JTextArea message = new JTextArea("Your new password must be different from your previous password.");

		JLabel newPasswordLabel = new JLabel("New Password");
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		JLabel show1 = new JLabel("✔");
		JLabel show2 = new JLabel("✔");

		JPasswordField newPassword = new JPasswordField();
		JPasswordField confirmPassword = new JPasswordField();

		JLabel error = new JLabel();

		JButton resetButton = new JButton("RESET PASSWRD");

		changePasswordPanel.add(panelLabel);
		panelLabel.setBounds(20, 20, 300, 30);
		panelLabel.setFont(robotoBold20);
		panelLabel.setForeground(babyBlue);

		changePasswordPanel.add(message);
		message.setBounds(20, 55, 345, 30);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setForeground(babyBlue);
		message.setFont(robotoPlain12);
		message.setBackground(null);
		message.setFocusable(false);
		message.setEditable(false);

		changePasswordPanel.add(newPasswordLabel);
		newPasswordLabel.setBounds(20, 100, 300, 20);
		newPasswordLabel.setFont(robotoBold14);
		newPasswordLabel.setForeground(babyBlue);

		changePasswordPanel.add(newPassword);
		newPassword.requestFocus();
		newPassword.setBounds(20, 130, 310, 35);
		newPassword.setFont(robotoPlain14);
		newPassword.setEchoChar('*');
		newPassword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		newPassword.requestFocus();
		newPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					confirmPassword.setFocusable(true);
					confirmPassword.requestFocus();

				}
			}

		});
		newPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				if (!newPassword.getPassword().equals(null)) {
					if (newPassword.getPassword().length < 8) {
						error.setText("New password must be at least 8 characters long");
					} else {
						if (newPassword.getText().equals(getPassword())) {
							error.setText("New password cannot be the same with your old password");
						} else {
							error.setText("");
						}
					}
				}
			}
		});

		changePasswordPanel.add(show1);
		show1.setBounds(330, 130, 35, 35);
		show1.setOpaque(true);
		show1.setHorizontalAlignment(SwingConstants.CENTER);
		show1.setBackground(orangeAccent);
		show1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				show_password(show1, newPassword);
			}
		});

		changePasswordPanel.add(confirmPasswordLabel);
		confirmPasswordLabel.setBounds(20, 175, 300, 20);
		confirmPasswordLabel.setFont(robotoBold14);
		confirmPasswordLabel.setForeground(babyBlue);

		changePasswordPanel.add(confirmPassword);
		confirmPassword.setFocusable(false);
		confirmPassword.setBounds(20, 205, 310, 35);
		confirmPassword.setEchoChar('*');
		confirmPassword.setFont(robotoPlain14);
		confirmPassword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		confirmPassword.requestFocus();
		confirmPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				confirmPassword.setFocusable(true);
			}
		});
		confirmPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {

				}
			}

		});
		confirmPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				if (!confirmPassword.getText().equals(newPassword.getText())) {
					error.setText("Password doesn't match");
				} else {
					error.setText("");
				}
			}
		});
		confirmPassword.addPropertyChangeListener(new PropertyChangeListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!confirmPassword.getText().equals(newPassword.getText())) {
					error.setText("Password doesn't match");
				} else {
					error.setText("");
				}

			}

		});

		changePasswordPanel.add(show2);
		show2.setBounds(330, 205, 35, 35);
		show2.setOpaque(true);
		show2.setBackground(orangeAccent);
		show2.setHorizontalAlignment(SwingConstants.CENTER);
		show2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				show_password(show2, confirmPassword);
			}
		});

		changePasswordPanel.add(error);
		error.setBounds(20, 245, 345, 20);
		error.setFont(robotoPlain12);
		error.setForeground(orangeAccent);

		changePasswordPanel.add(resetButton);
		resetButton.setBounds(20, 300, 150, 40);
		resetButton.setBackground(babyBlue);
		resetButton.setForeground(navyBlue);
		resetButton.setFocusable(false);
		resetButton.setFont(robotoBold10);
		resetButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				if (newPassword.getText().equals("") || confirmPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "One of the fields is empty");
				} else if (!error.getText().equals("")) {
					JOptionPane.showMessageDialog(null, error.getText());
				} else if (!confirmPassword.getText().equals(newPassword.getText())) {
					JOptionPane.showMessageDialog(null, "Password doesn't match");
				} else {

					change_password(email, getRole(), newPassword.getText());
				}
			}
		});

	}

	private void check_account(String email, JLabel label) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String companyCheck = "select * from (select email,password,role from employee union select email,password,role from administrator) employees where email ='"
					+ email + "'";
			Statement first = connection.createStatement();
			ResultSet rs = first.executeQuery(companyCheck);

			if (rs.next()) {
				setPassword(rs.getString("password"));
				setRole(rs.getString("role"));
				sendVerificationPanel.setVisible(false);
				verify_account(email);
			} else {
				label.setText("The email given is not associated to any account");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void verify_code(String email, String one, String two, String three, String four, String five, String six,
			JLabel label) {

		String correctCode = "123456";
		String userCode = one + two + three + four + five + six;

		if (userCode.equals(correctCode)) {
			label.setText("");
			verifyAccountPanel.setVisible(false);
			change_password(email);

		} else {
			label.setText("Invalid verification code. Please try again");
		}

	}

	private void change_password(String email, String role, String new_password) {
		String employee_role = "employee";

		if (role.equals("Administrator")) {
			employee_role = "administrator";
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update " + employee_role + " set password = '" + new_password + "' where email = '" + email
					+ "'";
			Statement st = connection.createStatement();

			st.execute(query);

			JOptionPane.showMessageDialog(null, "Password changed.\nYou will be rediriected to the login screen");
			dispose();
			new Login();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void show_password(JLabel label, JPasswordField passField) {
		if (label.getText().equals("✔")) {
			label.setText("❌");
			passField.setEchoChar((char) 0);
		} else {
			label.setText("✔");
			passField.setEchoChar('*');
		}
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
