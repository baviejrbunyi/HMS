package hms.main;

import java.awt.*;
import javax.swing.*;

import hms.frames.ForgotPassword;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends Resources {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private static int companyId;
	private static int loginId;
	private static String usernameValue;
	private static int attendanceId;
	private static String name;
	private static Blob imageBlob;

	ImageIcon loginImage = new ImageIcon("src/images/login.png");
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");

	// labels
	JLabel loginPicture = new JLabel(loginImage);
	JLabel header = new JLabel(logo);
	JLabel error = new JLabel();
	JLabel passToggle = new JLabel("SHOW");
	JLabel forgotPassword = new JLabel("Forgot Password");

	// text fields
	static JTextField company = new JTextField("Hotel Name");
	static JTextField username = new JTextField("Username");
	JPasswordField password = new JPasswordField("Password");

	// fonts
	Font tahoma = new Font("Tahoma", Font.PLAIN, 15);
	Font tahomaBold = new Font("Tahoma", Font.BOLD, 15);
	Font tahomaError = new Font("Tahoma", Font.PLAIN, 10);

	// buttons
	JButton loginButton = new JButton("LOG IN");

	public Login() {

		this.setSize(600, 425);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0x05445E));
		this.setTitle("HMS - User Log In");
		this.setIconImage(icon);
		getContentPane().setLayout(null);

		loginPicture.setBounds(300, 0, 300, 400);
		loginPicture.setFocusable(true);
		header.setBounds(48, 30, 87, 49);

		company.setBounds(48, 126, 200, 20);
		company.setOpaque(false);
		company.setForeground(new Color(0xD4F1F4));
		company.setFont(tahoma);
		company.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xD4F1F4)));
		company.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (company.getText().equals("Hotel Name")) {
					company.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (company.getText().equals("")) {
					company.setText("Hotel Name");
				}
			}
		});
		company.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					username.requestFocus();
				}
			}
		});

		username.setBounds(48, 171, 200, 20);
		username.setOpaque(false);
		username.setForeground(new Color(0xD4F1F4));
		username.setFont(tahoma);
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xD4F1F4)));
		username.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (username.getText().equals("Username")) {
					username.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (username.getText().equals("")) {
					username.setText("Username");
				}
			}
		});
		username.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					password.requestFocus();
				}
			}
		});

		password.setBounds(48, 216, 180, 20);
		password.setOpaque(false);
		password.setForeground(new Color(0xD4F1F4));
		password.setFont(tahoma);
		password.setEchoChar((char) 0);
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xD4F1F4)));
		password.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			public void focusGained(FocusEvent e) {
				if (password.getText().equals("Password")) {
					password.setText("");
					password.setEchoChar('●');
				}
			}

			@SuppressWarnings("deprecation")
			public void focusLost(FocusEvent e) {
				if (password.getText().equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
				}
			}
		});
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					password.requestFocus();
				}
			}
		});
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});

		passToggle.setBounds(216, 216, 32, 20);
		passToggle.setFont(robotoBold10);
		passToggle.setForeground(babyBlue);
		passToggle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, babyBlue));
		passToggle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				show_password(passToggle, password);
			}
		});
		passToggle.setHorizontalAlignment(SwingConstants.RIGHT);

		loginButton.setBounds(48, 300, 100, 40);
		loginButton.setOpaque(true);
		loginButton.setFont(tahomaBold);
		loginButton.setForeground(new Color(0xD4F1F4));
		loginButton.setBackground(new Color(0x189AB4));
		loginButton.setFocusable(false);

		forgotPassword.setBounds(48, 345, 150, 20);
		forgotPassword.setFont(robotoBold10);
		forgotPassword.setForeground(orangeAccent);
		forgotPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				new ForgotPassword();
			}
		});

		error.setBounds(48, 250, 300, 20);
		error.setFont(tahomaError);
		error.setForeground(new Color(0xFCDFA6));

		loginPicture.requestFocus();

		getContentPane().add(loginPicture);
		getContentPane().add(header);
		getContentPane().add(company);
		getContentPane().add(username);
		this.add(password);
		this.add(forgotPassword);
		getContentPane().add(loginButton);
		getContentPane().add(error);
		getContentPane().add(passToggle);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		revalidate();
		repaint();
	}

	public void show_password(JLabel label, JPasswordField passField) {
		if (label.getText().equals("SHOW")) {
			label.setText("HIDE");
			passField.setEchoChar((char) 0);
		} else {
			label.setText("SHOW");
			passField.setEchoChar('●');
		}
	}

	/**
	 * Connects to the database and checks if the company name that is given by the
	 * potential user exists in the database If the company exists, then it will
	 * proceed on executing the check_user() method If the company doesn't exist,
	 * the errorText label will prompt that the company doesn't exist
	 */

	public void login() {

		String companyName = company.getText();

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String companyCheck = "select * from company where name = '" + companyName + "'";

			Statement first = connection.createStatement();
			ResultSet rs = first.executeQuery(companyCheck);

			if (rs.next()) {
				check_user();
			} else {
				error.setText("Company doesn't exist");
			}

		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}

	/**
	 * Fetch input from the username and password textfields Using the companyId of,
	 * rthis method will connect to the database and will check if the username and
	 * password given by the user match an account in the database, it will create
	 * new window, dispose this window, and will fetch the account id from the
	 * database
	 */

	public void check_user() {
		String user = username.getText();
		String pass = new String(password.getPassword());
		int company_id = getCompanyId();

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String adminCheck = "select * from administrator where ref_id = '" + company_id + "' and username = '"
					+ user + "' and password = '" + pass + "'";
			String employeeCheck = "select * from employee where ref_id = '" + company_id + "' and username = '" + user
					+ "' and password = '" + pass + "'";

			Statement second = connection.createStatement();
			Statement third = connection.createStatement();
			ResultSet rs1 = second.executeQuery(adminCheck);
			ResultSet rs2 = third.executeQuery(employeeCheck);

			if (rs1.next()) {
				new Administrator();
				dispose();
			} else if (rs2.next()) {
				login_dateAndTime("employee");
				new FrontDesk();
				dispose();
			} else {
				error.setText("Incorrect Username or Password. Try Again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches the company id by checking the database if the given companyValue
	 * match to an account or company
	 * 
	 * @param company
	 */
	public static void setCompanyId(String company) {
		String companyValue = company;

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from company where name ='" + companyValue + "'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			Login.companyId = rs.getInt("id");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the company Id and returns the company Id
	 * 
	 * @return companyId
	 */

	public static int getCompanyId() {
		setCompanyId(company.getText());
		return companyId;
	}

	// getting the user id

	/**
	 * sets the loginId
	 */
	public static void setLoginId(String role, int companyId, String username) {
		String userRole = role;
		int company = companyId;
		String user = username;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from " + userRole + " where ref_id = " + company + " and username = '" + user
					+ "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			try {
				Login.loginId = rs.getInt("id");
			} catch (Exception e) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return loginId
	 */

	public static int getLoginId(String role, int companyId, String username) {
		try {
			setLoginId(role, companyId, username);
		} catch (Exception e) {

		}

		return loginId;
	}

	/**
	 * @return username
	 */

	public static String getUsernameValue() {
		Login.usernameValue = username.getText();

		return usernameValue;

	}

	/**
	 * Fetches the name of the logged account
	 * 
	 * @return name
	 */

	public static String getNameValue(String role, int companyId, String username) {

		String userRole = role;
		int company = companyId;
		String user = username;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from " + userRole + " where ref_id = " + company + " and username = '" + user
					+ "'";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			Login.name = rs.getString("name");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * Fetches the date and time of login. These data will be stored in the
	 * database.
	 */

	public void login_dateAndTime(String role) {

		int ref_id = Login.getLoginId(role, Login.getCompanyId(), Login.getUsernameValue());
		int company_id = Login.getCompanyId();
		String name = Login.getNameValue(role, Login.getCompanyId(), Login.getUsernameValue());
		String username = Login.getUsernameValue();

		// checks the date and time of login
		LocalDate last_loginDate = LocalDate.now();
		Date last_loginTime = new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String last_login_time = timeFormat.format(last_loginTime);
		String last_login_date = last_loginDate.toString();

		// stores the data in the database
		try {

			// checks if the user already logged in on the current date

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from attendance where last_login_date = current_date() and ref_id = " + ref_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs.next() == false) {
				query = "insert into attendance (ref_id, company_id, name, username, last_login_date, last_login_time) values (?,?,?,?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setInt(1, ref_id);
				pst.setInt(2, company_id);
				pst.setString(3, name);
				pst.setString(4, username);
				pst.setString(5, last_login_date);
				pst.setString(6, last_login_time);
				pst.execute();

				query = "select * from attendance where last_login_date = current_date() and ref_id = " + ref_id;
				rs = statement.executeQuery(query);
				rs.next();
				attendanceId = rs.getInt("id");

			} else {
				attendanceId = rs.getInt("id");
				query = "update attendance set last_logout_time = null where id = " + getAttendanceId();
				statement.execute(query);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int getAttendanceId() {
		return attendanceId;
	}

	/**
	 * Returns the imageblob of the user
	 * 
	 * @param role
	 * @return imageBlob
	 */

	public static Blob setImageBlob(String role) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from " + role + " where id = "
					+ getLoginId(role, getCompanyId(), getUsernameValue());
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();

			Login.imageBlob = rs.getBlob("profile");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Login.imageBlob;
	}

}
