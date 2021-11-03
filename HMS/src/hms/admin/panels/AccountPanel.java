package hms.admin.panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hms.main.Login;

public class AccountPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// labels
	private JLabel panelTitle = new JLabel("EDIT ACCOUNT INFORMATION");
	private static JLabel userImage = new JLabel();
	private JLabel userNameLabel = new JLabel("Name:");
	private JLabel userUsernameLabel = new JLabel("Username:");
	private JLabel userRoleLabel = new JLabel("User Role:");
	private JLabel userTelephoneLabel = new JLabel("Telephone No.:");
	private JLabel userEmailLabel = new JLabel("Email Address:");
	private JLabel userBirthdayLabel = new JLabel("Birthday:");
	private JLabel userPasswordLabel = new JLabel("Password:");
	private JLabel editPasswordLabel = new JLabel("Edit");
	private JLabel newPasswordLabel = new JLabel("New Password:");
	private JLabel oldPasswordLabel = new JLabel("Old Password:");
	private JLabel confirmPasswordLabel = new JLabel("Retype New Password:");
	private JLabel errorLabel = new JLabel();

	private JLabel showPassword = new JLabel("SHOW");
	private JLabel showNewPassword = new JLabel("SHOW");
	private JLabel showConfirmPassword = new JLabel("SHOW");

	private void Labels() {
		// panelTitle
		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		// userImage
		userImage.setBounds(380, 160, 225, 225);
		userImage.setIcon(new ImageIcon(
				image.getImage().getScaledInstance(userImage.getWidth(), userImage.getHeight(), Image.SCALE_SMOOTH)));

		// userNameLabel
		userNameLabel.setBounds(620, 160, 100, 16);
		userNameLabel.setFont(robotoBold12);

		// userUsernameLabel
		userUsernameLabel.setBounds(620, 190, 100, 16);
		userUsernameLabel.setFont(robotoBold12);

		// userRoleLabel
		userRoleLabel.setBounds(620, 220, 100, 16);
		userRoleLabel.setFont(robotoBold12);

		// userTelephoneLabel
		userTelephoneLabel.setBounds(620, 250, 100, 16);
		userTelephoneLabel.setFont(robotoBold12);

		// userEmailLabel
		userEmailLabel.setBounds(620, 280, 100, 16);
		userEmailLabel.setFont(robotoBold12);

		// userBirthdayLabel
		userBirthdayLabel.setBounds(620, 320, 100, 16);
		userBirthdayLabel.setFont(robotoBold12);

		// userPasswordLabel
		userPasswordLabel.setBounds(620, 350, 100, 16);
		userPasswordLabel.setFont(robotoBold12);
		editPasswordLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				change_password(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				editPasswordLabel.setForeground(errorRed);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				editPasswordLabel.setForeground(navyBlue);
			}
		});

		// editPasswordLabel
		editPasswordLabel.setBounds(910, 350, 100, 16);
		editPasswordLabel.setFont(robotoPlain12);
		editPasswordLabel.setForeground(navyBlue);

		showPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_password(showPassword, oldPasswordField);
			}
		});

		showNewPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_password(showNewPassword, newPasswordField);
			}
		});

		showConfirmPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_password(showConfirmPassword, confirmPasswordField);
			}
		});

		// adding to the frame
		this.add(panelTitle);
		this.add(userImage);
		this.add(userNameLabel);
		this.add(userUsernameLabel);
		this.add(userRoleLabel);
		this.add(userTelephoneLabel);
		this.add(userEmailLabel);
		this.add(userBirthdayLabel);
		this.add(userPasswordLabel);
		this.add(editPasswordLabel);
		this.add(newPasswordLabel);
		this.add(oldPasswordLabel);
		this.add(confirmPasswordLabel);
		this.add(errorLabel);
		this.add(showPassword);
		this.add(showNewPassword);
		this.add(showConfirmPassword);
	}

	// buttons
	private String img;

	public String getImage() {
		return img;
	}

	public void setImage(String image) {
		this.img = image;
	}

	private JButton uploadPictureBtn = new JButton("UPLOAD PROFILE PICTURE");
	private JButton updatePasswordBtn = new JButton("UPDATE PASSWORD");
	private JButton saveChangesBtn = new JButton("SAVE CHANGES");
	private JButton cancelPasswordBtn = new JButton("CANCEL");

	private void Buttons() {
		// uploadPictureBtn
		uploadPictureBtn.setBounds(380, 400, 225, 60);
		uploadPictureBtn.setBackground(navyBlue);
		uploadPictureBtn.setForeground(babyBlue);
		uploadPictureBtn.setFocusable(false);
		uploadPictureBtn.setFont(robotoBold14);
		uploadPictureBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setImage(set_image());

				if (getImage() == null) {
					display_image(Login.setImageBlob("administrator"));
				}
			}
		});

		// saveChangesBtn
		saveChangesBtn.setBounds(380, 760, 225, 60);
		saveChangesBtn.setBackground(navyBlue);
		saveChangesBtn.setForeground(babyBlue);
		saveChangesBtn.setFocusable(false);
		saveChangesBtn.setFont(robotoBold14);
		saveChangesBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				update_profile(getImage(), userNameField.getText(), userUsernameField.getText(),
						userTelephoneField.getText(), userEmailField.getText(), userBirthdayField.getText());
			}
		});

		cancelPasswordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				change_password(false);
			}
		});

		updatePasswordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				update_password(new String(newPasswordField.getPassword()), new String(oldPasswordField.getPassword()),
						new String(confirmPasswordField.getPassword()));
			}
		});

		// adding to the panel
		this.add(uploadPictureBtn);
		this.add(updatePasswordBtn);
		this.add(cancelPasswordBtn);
		this.add(saveChangesBtn);
	}

	// textfields

	private static Blob imageBlob;
	private static JTextField userNameField = new JTextField();
	private static JTextField userUsernameField = new JTextField();
	private static JTextField userRoleField = new JTextField("Manager");
	private static JTextField userTelephoneField = new JTextField();
	private static JTextField userEmailField = new JTextField();
	private static JTextField userBirthdayField = new JTextField();
	private static JPasswordField userPasswordField = new JPasswordField();
	private JPasswordField newPasswordField = new JPasswordField();
	private JPasswordField oldPasswordField = new JPasswordField();
	private JPasswordField confirmPasswordField = new JPasswordField();

	private void TextFields() {
		// userNameField
		userNameField.setBounds(730, 160, 200, 16);
		userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userNameField.setBackground(null);

		// userUsernameField
		userUsernameField.setBounds(730, 190, 200, 16);
		userUsernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userUsernameField.setBackground(null);

		// userRoleField
		userRoleField.setBounds(730, 220, 200, 16);
		userRoleField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		userRoleField.setBackground(null);
		userRoleField.setEditable(false);

		// userTelephoneField
		userTelephoneField.setBounds(730, 250, 200, 16);
		userTelephoneField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userTelephoneField.setBackground(null);

		// userEmailField
		userEmailField.setBounds(730, 280, 200, 16);
		userEmailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userEmailField.setBackground(null);

		// userBirthdayField
		userBirthdayField.setBounds(730, 320, 200, 16);
		userBirthdayField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userBirthdayField.setBackground(null);

		// userPasswordField
		userPasswordField.setBounds(730, 350, 200, 16);
		userPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		userPasswordField.setBackground(null);
		userPasswordField.setEditable(false);
		userPasswordField.setFocusable(false);

		// adding to the panel]
		this.add(userNameField);
		this.add(userUsernameField);
		this.add(userRoleField);
		this.add(userTelephoneField);
		this.add(userEmailField);
		this.add(userBirthdayField);
		this.add(userPasswordField);
		this.add(newPasswordField);
		this.add(oldPasswordField);
		this.add(confirmPasswordField);

		show_account_info();

	}

	public AccountPanel() {
		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Labels();
		TextFields();
		Buttons();
	}

	/**
	 * Show options and textfields for changing the user password
	 */

	private void change_password(boolean bol) {
		// newPasswordLabel
		newPasswordLabel.setBounds(730, 400, 150, 16);
		newPasswordLabel.setFont(robotoPlain14);
		newPasswordLabel.setForeground(navyBlue);
		newPasswordLabel.setVisible(bol);

		// oldPasswordLabel
		oldPasswordLabel.setBounds(730, 440, 150, 16);
		oldPasswordLabel.setFont(robotoPlain14);
		oldPasswordLabel.setForeground(navyBlue);
		oldPasswordLabel.setVisible(bol);

		// confirmPasswordLabel
		confirmPasswordLabel.setBounds(730, 480, 150, 16);
		confirmPasswordLabel.setFont(robotoPlain14);
		confirmPasswordLabel.setForeground(navyBlue);
		confirmPasswordLabel.setVisible(bol);

		// errorLabel
		errorLabel.setBounds(730, 580, 500, 16);
		errorLabel.setFont(robotoPlain14);
		errorLabel.setForeground(errorRed);
		errorLabel.setVisible(bol);

		// updatePasswordsBtn
		updatePasswordBtn.setBounds(730, 520, 150, 40);
		updatePasswordBtn.setBackground(navyBlue);
		updatePasswordBtn.setForeground(babyBlue);
		updatePasswordBtn.setFocusable(false);
		updatePasswordBtn.setFont(robotoBold10);
		updatePasswordBtn.setVisible(bol);

		// cancelPasswordBtn
		cancelPasswordBtn.setBounds(900, 520, 150, 40);
		cancelPasswordBtn.setBackground(navyBlue);
		cancelPasswordBtn.setForeground(babyBlue);
		cancelPasswordBtn.setFocusable(false);
		cancelPasswordBtn.setFont(robotoBold10);
		cancelPasswordBtn.setVisible(bol);

		// newPasswordField
		newPasswordField.setBounds(900, 400, 200, 16);
		newPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		newPasswordField.setBackground(null);
		newPasswordField.setVisible(bol);

		// oldPasswordField
		oldPasswordField.setBounds(900, 440, 200, 16);
		oldPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		oldPasswordField.setBackground(null);
		oldPasswordField.setVisible(bol);

		// confirmPasswordField
		confirmPasswordField.setBounds(900, 480, 200, 16);
		confirmPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		confirmPasswordField.setBackground(null);
		confirmPasswordField.setVisible(bol);

		// showNewPassword
		showNewPassword.setBounds(1110, 400, 50, 16);
		showNewPassword.setFont(robotoBold10);
		showNewPassword.setVisible(bol);

		// showPassword
		showPassword.setBounds(1110, 440, 50, 16);
		showPassword.setFont(robotoBold10);
		showPassword.setVisible(bol);

		// showConfirmPassword
		showConfirmPassword.setBounds(1110, 480, 50, 16);
		showConfirmPassword.setFont(robotoBold10);
		showConfirmPassword.setVisible(bol);

		oldPasswordField.setText(null);
		newPasswordField.setText(null);
		confirmPasswordField.setText(null);

	}

	/**
	 * Shows information of the account that is currently logged in
	 */

	public static void show_account_info() {

		int loggedId = Login.getLoginId("administrator", Login.getCompanyId(), Login.getUsernameValue());
		int companyId = Login.getCompanyId();

		// connects to the database
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from administrator where ref_id = " + companyId + " and id = " + loggedId + " ";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();

			userNameField.setText(rs.getString("name"));
			userTelephoneField.setText(rs.getString("telephone"));
			userRoleField.setText(rs.getString("role"));
			userEmailField.setText(rs.getString("email"));
			userBirthdayField.setText(rs.getString("birthday"));
			userPasswordField.setText(rs.getString("password"));
			userUsernameField.setText(rs.getString("username"));
			display_image(rs.getBlob("profile"));

			setImageBlob(rs.getBlob("profile"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the file manager so that the user can select an image that will be
	 * uploaded in the database.
	 * 
	 * The image will be displayed in the placeholder of the new room that will be
	 * added
	 * 
	 * This also returns the address of the image
	 * 
	 * @return imageAddress
	 * 
	 */

	public String set_image() {

		JFileChooser choose = new JFileChooser();
		String imageAddress = null;
		choose.showOpenDialog(null);

		try {

			File file = choose.getSelectedFile();
			imageAddress = file.getAbsolutePath();

		} catch (Exception e) {
		}

		ImageIcon icon = new ImageIcon(imageAddress);

		userImage.setIcon(new ImageIcon(
				icon.getImage().getScaledInstance(userImage.getWidth(), userImage.getHeight(), Image.SCALE_SMOOTH)));

		return imageAddress;
	}

	/**
	 * Displays the image in the databse
	 */

	public static void display_image(Blob imageBlob) {

		BufferedImage buffImg = null;
		ImageIcon imgIcon = null;
		Blob blob = imageBlob;
		InputStream is = null;

		try {
			is = blob.getBinaryStream();
		} catch (SQLException e) {
			System.out.println("No Image");
		}
		try {
			buffImg = ImageIO.read(is);
		} catch (IOException e) {
			System.out.println("No Image");
		}

		imgIcon = new ImageIcon(
				buffImg.getScaledInstance(userImage.getWidth(), userImage.getHeight(), Image.SCALE_SMOOTH));
		userImage.setIcon(imgIcon);
	}

	/**
	 * Gathers all the information in the textfields and uploads it in the database.
	 */

	public void update_profile(String imageAddress, String name, String username, String telephone, String email,
			String birthday) {

		InputStream image = null;

		// connects to the database to fetch the address of the company where the hotel
		// room will be added
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");

			// add the information from the extfields to the database
			String queryTwo = "update `hotel_management_system`.`administrator` set `name` = ?, `username` = ?, `profile` = ?, `birthday` = ?, `telephone` = ?, `email` = ? where (`id` = ? and `ref_id` = ?);";
			PreparedStatement statementTwo = connection.prepareStatement(queryTwo);

			statementTwo.setString(1, name);
			statementTwo.setString(2, username);

			try {
				image = new FileInputStream(imageAddress);
				statementTwo.setBlob(3, image);
			} catch (FileNotFoundException ex) {
				statementTwo.setBlob(3, getImageBlob());
			} catch (Exception e) {
				statementTwo.setBlob(3, getImageBlob());
			}

			statementTwo.setString(4, birthday);
			statementTwo.setString(5, telephone);
			statementTwo.setString(6, email);
			statementTwo.setInt(7, Login.getLoginId("administrator", Login.getCompanyId(), Login.getUsernameValue()));
			statementTwo.setInt(8, Login.getCompanyId());

			statementTwo.execute();
			statementTwo.close();

			show_account_info();
			JOptionPane.showMessageDialog(null, "Successful");

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Unsuccessful");
		}

		// update `hotel_management_system`.`administrator` set `name` = ?, `username` =
		// ?, `password` = ?, `profile` = ?, `birthday` = ?, `telephone` = ?, `email` =
		// ? where (`id` = ? and ref_id = ?);
	}

	/**
	 * Password Validation
	 */

	public void validate_password(JPasswordField field) {

	}

	/**
	 * Use to mask or show the password typed in the field
	 */

	public void show_password(JLabel label, JPasswordField passField) {
		if (label.getText().equals("SHOW")) {
			label.setText("HIDE");
			passField.setEchoChar((char) 0);
		} else {
			label.setText("SHOW");
			passField.setEchoChar('*');
		}
	}

	/**
	 * Updates password
	 */

	public void update_password(String newPassword, String oldPassword, String confirmPassword) {

		String password = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");

			String query = "select * from administrator where id = "
					+ Login.getLoginId("administrator", Login.getCompanyId(), Login.getUsernameValue());
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			rs.next();
			password = rs.getString("password");

			if (oldPassword.equals(password)) {
				if (newPassword.equals(confirmPassword)) {

					String q = "update `hotel_management_system`.`administrator` set `password` = '" + newPassword
							+ "' where (`id` = '"
							+ Login.getLoginId("administrator", Login.getCompanyId(), Login.getUsernameValue()) + "');";
					Statement st = connection.createStatement();

					st.execute(q);
					st.close();

					JOptionPane.showMessageDialog(null, "Password is changed successfully");
					change_password(false);

					show_account_info();

					errorLabel.setText(null);
				} else {
					errorLabel.setText("Your new password doesn't match to your retyped one");
				}
			} else {
				errorLabel.setText("Your old password doesn't match your current password");
				System.out.println(password);
				System.out.println(oldPassword);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static Blob getImageBlob() {
		return imageBlob;
	}

	public static void setImageBlob(Blob imageBlob) {
		AccountPanel.imageBlob = imageBlob;
	}

}
