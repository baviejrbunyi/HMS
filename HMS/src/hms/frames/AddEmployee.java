package hms.frames;

import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.mysql.cj.jdbc.Blob;

import hms.admin.panels.EmployeePanel;
import hms.main.Login;

public class AddEmployee extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4184357565472474126L;
	String imageAddress = "";

	JLabel frameTitle = new JLabel("ADD EMPLOYEE");

	JLabel employeeImage = new JLabel();
	JLabel employeeNameLabel = new JLabel("Name:");
	JLabel employeeUserNameLabel = new JLabel("Username:");
	JLabel employeeRoleLabel = new JLabel("Role:");
	JLabel employeeEmailLabel = new JLabel("Email");

	JTextField employeeNameField = new JTextField();
	JTextField employeeUserNameField = new JTextField();
	JComboBox<String> employeeRoleField = new JComboBox<String>(roles());
	JTextField employeeEmailField = new JTextField();

	JButton uploadButton = new JButton("UPLOAD PICTURE");
	JButton newButton = new JButton("ADD EMPLOYEE");
	JButton cancelButton = new JButton("CANCEL");

	public AddEmployee() {
		imageAddress = "src/images/profile.jpg";

		this.setSize(350, 400);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - ADD EMPLOYEE");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(employeeImage);
		employeeImage.setBounds(20, 60, 150, 150);
		employeeImage.setIcon(new ImageIcon(profile.getImage().getScaledInstance(employeeImage.getWidth(),
				employeeImage.getHeight(), Image.SCALE_SMOOTH)));

		this.add(employeeNameLabel);
		employeeNameLabel.setBounds(20, 230, 100, 20);
		employeeNameLabel.setFont(robotoBold14);
		employeeNameLabel.setForeground(babyBlue);

		this.add(employeeUserNameLabel);
		employeeUserNameLabel.setBounds(20, 260, 100, 20);
		employeeUserNameLabel.setFont(robotoBold14);
		employeeUserNameLabel.setForeground(babyBlue);

		this.add(employeeRoleLabel);
		employeeRoleLabel.setBounds(20, 290, 100, 20);
		employeeRoleLabel.setFont(robotoBold14);
		employeeRoleLabel.setForeground(babyBlue);

		this.add(employeeEmailLabel);
		employeeEmailLabel.setBounds(20, 320, 100, 20);
		employeeEmailLabel.setFont(robotoBold14);
		employeeEmailLabel.setForeground(babyBlue);

		this.add(employeeNameField);
		employeeNameField.setBounds(120, 230, 195, 20);
		employeeNameField.setFont(robotoPlain14);
		employeeNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '.'
						|| e.getKeyChar() == ',' || e.getKeyChar() == '-')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					employeeUserNameField.requestFocus();
				}
			}
		});

		employeeNameField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!employeeNameField.getText().equals("")) {
					employeeNameField
							.setText(capitalized_word_string(capitalized_word_array(employeeNameField.getText())));
				}

			}
		});

		this.add(employeeUserNameField);
		employeeUserNameField.setBounds(120, 260, 195, 20);
		employeeUserNameField.setFont(robotoPlain14);
		employeeUserNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					employeeRoleField.showPopup();
					employeeRoleField.setPopupVisible(true);
				}
			}
		});

		this.add(employeeRoleField);
		employeeRoleField.setBounds(120, 290, 195, 20);
		employeeRoleField.setFont(robotoPlain14);

		this.add(employeeEmailField);
		employeeEmailField.setBounds(120, 320, 195, 20);
		employeeEmailField.setFont(robotoPlain14);
		employeeEmailField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					boolean isEmailValid = false;
					final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

					if (!employeeEmailField.getText().matches(EMAIL_PATTERN)) {
						isEmailValid = false;
					} else {
						isEmailValid = true;
					}

					if (employeeNameField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Employee name field is empty");
					} else if (employeeEmailField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Employee email field is empty");
					} else if (employeeUserNameField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Employee username field is empty");
					} else if (isEmailValid == false) {
						JOptionPane.showMessageDialog(null, "Email is Invalid");
						employeeEmailField.requestFocus();
					} else {
						if (employeeRoleField.getSelectedItem() == "Administrator") {
							confirm_add(Login.getCompanyId(), employeeNameField.getText(),
									employeeUserNameField.getText(), employeeEmailField.getText(), imageAddress);

						} else {
							confirm_add(Login.getCompanyId(), employeeNameField.getText(),
									employeeUserNameField.getText(), employeeRoleField.getSelectedItem().toString(),
									employeeEmailField.getText(), imageAddress);
						}
					}
				}
			}
		});

		this.add(uploadButton);
		uploadButton.setBounds(190, 60, 125, 30);
		uploadButton.setForeground(navyBlue);
		uploadButton.setBackground(babyBlue);
		uploadButton.setFocusable(false);
		uploadButton.setFont(robotoBold10);
		uploadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				imageAddress = upload_image(employeeImage);

			}
		});

		this.add(newButton);
		newButton.setBounds(190, 100, 125, 30);
		newButton.setForeground(navyBlue);
		newButton.setBackground(babyBlue);
		newButton.setFocusable(false);
		newButton.setFont(robotoBold10);
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				boolean isEmailValid = false;
				final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				if (!employeeEmailField.getText().matches(EMAIL_PATTERN)) {
					isEmailValid = false;
				} else {
					isEmailValid = true;
				}

				if (employeeNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Employee name field is empty");
				} else if (employeeEmailField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Employee email field is empty");
				} else if (employeeUserNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Employee username field is empty");
				} else if (isEmailValid == false) {
					JOptionPane.showMessageDialog(null, "Email is Invalid");
					employeeEmailField.requestFocus();
				} else {
					if (employeeRoleField.getSelectedItem() == "Administrator") {
						confirm_add(Login.getCompanyId(), employeeNameField.getText(), employeeUserNameField.getText(),
								employeeEmailField.getText(), imageAddress);

					} else {
						confirm_add(Login.getCompanyId(), employeeNameField.getText(), employeeUserNameField.getText(),
								employeeRoleField.getSelectedItem().toString(), employeeEmailField.getText(),
								imageAddress);
					}
				}
			}
		});

		this.add(cancelButton);
		cancelButton.setBounds(190, 140, 125, 30);
		cancelButton.setForeground(navyBlue);
		cancelButton.setBackground(babyBlue);
		cancelButton.setFocusable(false);
		cancelButton.setFont(robotoBold10);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Employee not added");
			}
		});

	}

	public AddEmployee(int id, Blob image, String name, String username, String role, String email) {
		this.setSize(350, 400);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - EDIT EMPLOYEE");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);
		frameTitle.setText("EDIT EMPLOYEE");

		this.add(employeeImage);
		employeeImage.setBounds(20, 60, 150, 150);
		employeeImage.setIcon(display_image(image, employeeImage));

		this.add(employeeNameLabel);
		employeeNameLabel.setBounds(20, 230, 100, 20);
		employeeNameLabel.setFont(robotoBold14);
		employeeNameLabel.setForeground(babyBlue);

		this.add(employeeUserNameLabel);
		employeeUserNameLabel.setBounds(20, 260, 100, 20);
		employeeUserNameLabel.setFont(robotoBold14);
		employeeUserNameLabel.setForeground(babyBlue);

		this.add(employeeRoleLabel);
		employeeRoleLabel.setBounds(20, 290, 100, 20);
		employeeRoleLabel.setFont(robotoBold14);
		employeeRoleLabel.setForeground(babyBlue);

		this.add(employeeEmailLabel);
		employeeEmailLabel.setBounds(20, 320, 100, 20);
		employeeEmailLabel.setFont(robotoBold14);
		employeeEmailLabel.setForeground(babyBlue);

		this.add(employeeNameField);
		employeeNameField.setBounds(120, 230, 195, 20);
		employeeNameField.setFont(robotoPlain14);
		employeeNameField.setText(name);
		employeeNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '.'
						|| e.getKeyChar() == ',' || e.getKeyChar() == '-')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					employeeUserNameField.requestFocus();
				}
			}
		});

		employeeNameField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!employeeNameField.getText().equals("")) {
					employeeNameField
							.setText(capitalized_word_string(capitalized_word_array(employeeNameField.getText())));
				}

			}
		});

		this.add(employeeUserNameField);
		employeeUserNameField.setBounds(120, 260, 195, 20);
		employeeUserNameField.setFont(robotoPlain14);
		employeeUserNameField.setText(username);
		employeeUserNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					employeeRoleField.showPopup();
					employeeRoleField.setPopupVisible(true);
				}
			}
		});

		this.add(employeeRoleField);
		employeeRoleField.setBounds(120, 290, 195, 20);
		employeeRoleField.setFont(robotoPlain14);
		employeeRoleField.setSelectedItem(role);

		this.add(employeeEmailField);
		employeeEmailField.setBounds(120, 320, 195, 20);
		employeeEmailField.setFont(robotoPlain14);
		employeeEmailField.setText(email);
		employeeEmailField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (employeeNameField.getText().equals(name) && employeeUserNameField.getText().equals(username)
							&& employeeRoleField.getSelectedItem().toString().equals(role)
							&& employeeEmailField.getText().equals(email) && imageAddress.equals("")) {
						JOptionPane.showMessageDialog(null, "Information is still the same. Nothing changed");
					} else {
						boolean isEmailValid = false;
						final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

						if (!employeeEmailField.getText().matches(EMAIL_PATTERN)) {
							isEmailValid = false;
						} else {
							isEmailValid = true;
						}

						if (isEmailValid == false) {
							JOptionPane.showMessageDialog(null, "Email is Invalid");
							employeeEmailField.requestFocus();
						} else {
							if (employeeRoleField.getSelectedItem() == "Administrator") {
								update_admin(id, employeeNameField.getText(), employeeUserNameField.getText(),
										employeeEmailField.getText(), imageAddress, image);
							} else {
								update(id, employeeNameField.getText(), employeeUserNameField.getText(),
										employeeRoleField.getSelectedItem().toString(), employeeEmailField.getText(),
										imageAddress, image);
							}
						}

					}
				}
			}
		});

		this.add(uploadButton);
		uploadButton.setBounds(190, 60, 125, 30);
		uploadButton.setForeground(navyBlue);
		uploadButton.setBackground(babyBlue);
		uploadButton.setFocusable(false);
		uploadButton.setFont(robotoBold10);
		uploadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				imageAddress = upload_image(employeeImage);

			}
		});

		this.add(newButton);
		newButton.setBounds(190, 100, 125, 30);
		newButton.setForeground(navyBlue);
		newButton.setBackground(babyBlue);
		newButton.setFocusable(false);
		newButton.setFont(robotoBold10);
		newButton.setText("UPDATE");
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (employeeNameField.getText().equals(name) && employeeUserNameField.getText().equals(username)
						&& employeeRoleField.getSelectedItem().toString().equals(role)
						&& employeeEmailField.getText().equals(email) && imageAddress.equals("")) {
					JOptionPane.showMessageDialog(null, "Information is still the same. Nothing changed");
				} else {
					boolean isEmailValid = false;
					final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

					if (!employeeEmailField.getText().matches(EMAIL_PATTERN)) {
						isEmailValid = false;
					} else {
						isEmailValid = true;
					}

					if (isEmailValid == false) {
						JOptionPane.showMessageDialog(null, "Email is Invalid");
						employeeEmailField.requestFocus();
					} else {
						if (employeeRoleField.getSelectedItem() == "Administrator") {
							update_admin(id, employeeNameField.getText(), employeeUserNameField.getText(),
									employeeEmailField.getText(), imageAddress, image);
						} else {
							update(id, employeeNameField.getText(), employeeUserNameField.getText(),
									employeeRoleField.getSelectedItem().toString(), employeeEmailField.getText(),
									imageAddress, image);
						}
					}

				}

			}
		});

		this.add(cancelButton);
		cancelButton.setBounds(190, 140, 125, 30);
		cancelButton.setForeground(navyBlue);
		cancelButton.setBackground(babyBlue);
		cancelButton.setFocusable(false);
		cancelButton.setFont(robotoBold10);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Employee not added");
			}
		});
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

	String[] roles() {
		String[] roles = null;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "SELECT  * FROM (SELECT  DISTINCT role FROM    employee where ref_id = "
					+ Login.getCompanyId() + " ) roles";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int size = 1 + size(query);

			roles = new String[size];
			roles[0] = "Administrator";
			int i = 1;

			while (rs.next()) {
				roles[i] = rs.getString("role");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
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

	public String upload_image(JLabel image) {

		JFileChooser choose = new JFileChooser();
		String imageAddress = null;
		choose.showOpenDialog(null);

		try {

			File file = choose.getSelectedFile();
			imageAddress = file.getAbsolutePath();

			ImageIcon icon = new ImageIcon(imageAddress);
			image.setIcon(new ImageIcon(
					icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH)));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No Image selected");
			imageAddress = "src/images/profile.jpg";
		}

		return imageAddress;
	}

	/**
	 * Confirms the addition of account to the database. If successful, updates
	 * table. If unsuccessful, shows a message
	 */

	public void confirm_add(int companyId, String name, String username, String role, String email, String imgAddress) {

		// changes the image address into a file uploadable to the database
		InputStream image = null;
		try {

			image = new FileInputStream(imgAddress);

		} catch (FileNotFoundException ex) {

			System.out.println("File Not Found");
		}

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "INSERT INTO `hotel_management_system`.`employee` (`ref_id`,`name`, `role`, `username`,`profile`, `email`) VALUES (?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, companyId);
			statement.setString(2, name);
			statement.setString(3, role);
			statement.setString(4, username);
			statement.setBlob(5, image);
			statement.setString(6, email);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Employee is added Successfully");
			EmployeePanel.display_table(EmployeePanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Employee is added Unuccessfully! \n Try Again Later");
		}

	}

	public void confirm_add(int companyId, String name, String username, String email, String imgAddress) {

		// changes the image address into a file uploadable to the database
		InputStream image = null;
		try {

			image = new FileInputStream(imgAddress);

		} catch (FileNotFoundException ex) {

			System.out.println("File Not Found");
		}

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "INSERT INTO `hotel_management_system`.`administrator` (`ref_id`,`name`, `username`,`profile`, `email`) VALUES (?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, companyId);
			statement.setString(2, name);
			statement.setString(3, username);
			statement.setBlob(4, image);
			statement.setString(5, email);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Employee is added Successfully");
			EmployeePanel.display_table(EmployeePanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Employee is added Unuccessfully! \n Try Again Later");
		}

	}

	/**
	 * Converts blob into image
	 */

	public ImageIcon display_image(Blob image, JLabel label) {

		BufferedImage buffImg = null;
		ImageIcon imgIcon = null;
		InputStream is = null;

		try {
			is = image.getBinaryStream();
		} catch (SQLException e) {
			System.out.println("No Image");
		}
		try {
			buffImg = ImageIO.read(is);
		} catch (IOException e) {
			System.out.println("No Image");
		}

		imgIcon = new ImageIcon(buffImg.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));

		return imgIcon;
	}

	public void update_admin(int id, String name, String username, String email, String imgAddress, Blob im) {

		// changes the image address into a file uploadable to the database
		InputStream image = null;

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update administrator set name = ?, username = ?,  profile = ?, email = ? where id = " + id;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, username);

			try {
				image = new FileInputStream(imageAddress);
				statement.setBlob(3, image);
			} catch (FileNotFoundException ex) {
				statement.setBlob(3, im);
			} catch (Exception e) {
				statement.setBlob(3, im);
			}

			statement.setString(4, email);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Employee is updated Successfully");
			EmployeePanel.display_table(EmployeePanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Employee is update Unuccessfully! \n Try Again Later");
		}

	}

	public void update(int id, String name, String username, String role, String email, String imgAddress, Blob im) {

		// changes the image address into a file uploadable to the database
		InputStream image = null;

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update employee set name = ?, username = ?, role = ?,  profile = ?, email = ? where id = "
					+ id;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, username);
			statement.setString(3, role);

			try {
				image = new FileInputStream(imageAddress);
				statement.setBlob(4, image);
			} catch (FileNotFoundException ex) {
				statement.setBlob(4, im);
			} catch (Exception e) {
				statement.setBlob(4, im);
			}

			statement.setString(5, email);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Employee is updated Successfully");
			EmployeePanel.display_table(EmployeePanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Employee is update Unuccessfully! \n Try Again Later");
		}

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

}
