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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hms.admin.panels.RoomInformationPanel;
import hms.main.Login;

public class AddRoom extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String imageAddress = "";

	JLabel frameTitle = new JLabel("ADD ROOM");

	JLabel roomImage = new JLabel();

	JLabel roomNumberLabel = new JLabel("Room Number:");
	JLabel roomTypeLabel = new JLabel("Room Type:");
	JLabel roomRateLabel = new JLabel("Room Rate:");

	JTextField roomNumberField = new JTextField();
	JComboBox<String> roomTypeField = new JComboBox<String>(room_types());
	JTextField roomRateField = new JTextField();
	JTextArea roomDetailsField = new JTextArea("Details");
	JScrollPane scroll = new JScrollPane(roomDetailsField);

	JButton uploadButton = new JButton("UPLOAD PICTURE");
	JButton newButton = new JButton("ADD ROOM");
	JButton cancelButton = new JButton("CANCEL");

	public AddRoom() {
		imageAddress = "src/images/profile.jpg";

		this.setSize(600, 315);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - ADD ROOM");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(roomImage);
		roomImage.setBounds(20, 60, 150, 150);
		roomImage.setIcon(new ImageIcon(profile.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

		this.add(roomNumberLabel);
		roomNumberLabel.setBounds(190, 60, 150, 20);
		roomNumberLabel.setFont(robotoBold14);
		roomNumberLabel.setForeground(babyBlue);

		this.add(roomTypeLabel);
		roomTypeLabel.setBounds(190, 90, 150, 20);
		roomTypeLabel.setFont(robotoBold14);
		roomTypeLabel.setForeground(babyBlue);

		this.add(roomRateLabel);
		roomRateLabel.setBounds(190, 120, 150, 20);
		roomRateLabel.setFont(robotoBold14);
		roomRateLabel.setForeground(babyBlue);

		this.add(roomNumberField);
		roomNumberField.setBounds(300, 60, 140, 20);
		roomNumberField.setFont(robotoPlain14);
		roomNumberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()))) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					roomTypeField.showPopup();
					roomTypeField.setPopupVisible(true);
				}
			}
		});

		this.add(roomTypeField);
		roomTypeField.setBounds(300, 90, 140, 20);
		roomTypeField.setFont(robotoPlain14);
		roomTypeField.setEditable(true);

		this.add(roomRateField);
		roomRateField.setBounds(300, 120, 140, 20);
		roomRateField.setFont(robotoPlain14);
		roomRateField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					roomDetailsField.requestFocus();
				}
			}
		});
		roomRateField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!roomRateField.getText().equals("")) {
					Double roomRate = Double.parseDouble(roomRateField.getText());
					roomRateField.setText(roomRate.toString());
				}
			}
		});

		this.add(scroll);
		scroll.setBounds(190, 150, 250, 100);
		roomDetailsField.setWrapStyleWord(true);
		roomDetailsField.setLineWrap(true);
		roomDetailsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (roomDetailsField.getText().equals("Details")) {
					roomDetailsField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (roomDetailsField.getText().equals("")) {
					roomDetailsField.setText("Details");
				}
			}
		});

		this.add(uploadButton);
		uploadButton.setBounds(20, 220, 150, 30);
		uploadButton.setForeground(navyBlue);
		uploadButton.setBackground(babyBlue);
		uploadButton.setFont(robotoBold10);
		uploadButton.setFocusable(false);
		uploadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				imageAddress = upload_image(roomImage);
			}
		});

		this.add(newButton);
		newButton.setBounds(450, 60, 125, 30);
		newButton.setForeground(navyBlue);
		newButton.setBackground(babyBlue);
		newButton.setFont(robotoBold10);
		newButton.setFocusable(false);
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (roomNumberField.equals("")) {
					JOptionPane.showMessageDialog(null, "Room number field is empty");
				} else if (roomTypeField.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Room type fireld is not changed");
				} else if (roomRateField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Room rate field is empty");
				} else if (roomDetailsField.getText().equals("") || roomDetailsField.getText().equals("Details")) {
					JOptionPane.showMessageDialog(null, "Room details field is empty");
				} else {
					String roomNumber = roomNumberField.getText();
					String roomType = roomTypeField.getSelectedItem().toString();
					String roomDescription = roomDetailsField.getText();
					int companyId = Login.getCompanyId();
					String imageAd = imageAddress;
					Double roomRate = Double.parseDouble(roomRateField.getText());

					try {
						confirm_add(roomNumber, roomType, roomDescription, companyId, imageAd, roomRate);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		this.add(cancelButton);
		cancelButton.setBounds(450, 110, 125, 30);
		cancelButton.setForeground(navyBlue);
		cancelButton.setBackground(babyBlue);
		cancelButton.setFont(robotoBold10);
		cancelButton.setFocusable(false);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Adding room cancelled");
			}
		});

	}

	public AddRoom(int id, String roomNumber, String type, String details, Double rate, Blob image) {
		this.setSize(600, 315);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - EDIT ROOM");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setText("EDIT ROOM");
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(roomImage);
		roomImage.setBounds(20, 60, 150, 150);
		roomImage.setIcon(display_image(image, roomImage));

		this.add(roomNumberLabel);
		roomNumberLabel.setBounds(190, 60, 150, 20);
		roomNumberLabel.setFont(robotoBold14);
		roomNumberLabel.setForeground(babyBlue);

		this.add(roomTypeLabel);
		roomTypeLabel.setBounds(190, 90, 150, 20);
		roomTypeLabel.setFont(robotoBold14);
		roomTypeLabel.setForeground(babyBlue);

		this.add(roomRateLabel);
		roomRateLabel.setBounds(190, 120, 150, 20);
		roomRateLabel.setFont(robotoBold14);
		roomRateLabel.setForeground(babyBlue);

		this.add(roomNumberField);
		roomNumberField.setBounds(300, 60, 140, 20);
		roomNumberField.setFont(robotoPlain14);
		roomNumberField.setText(roomNumber);
		roomNumberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()))) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					roomTypeField.showPopup();
					roomTypeField.setPopupVisible(true);
				}
			}
		});

		this.add(roomTypeField);
		roomTypeField.setBounds(300, 90, 140, 20);
		roomTypeField.setFont(robotoPlain14);
		roomTypeField.setEditable(true);
		roomTypeField.setSelectedItem(type);

		this.add(roomRateField);
		roomRateField.setBounds(300, 120, 140, 20);
		roomRateField.setFont(robotoPlain14);
		roomRateField.setText(rate.toString());
		roomRateField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					roomDetailsField.requestFocus();
				}
			}
		});
		roomRateField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!roomRateField.getText().equals("")) {
					Double roomRate = Double.parseDouble(roomRateField.getText());
					roomRateField.setText(roomRate.toString());
				}
			}
		});

		this.add(scroll);
		scroll.setBounds(190, 150, 250, 100);
		roomDetailsField.setWrapStyleWord(true);
		roomDetailsField.setLineWrap(true);
		roomDetailsField.setText(details);
		roomDetailsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (roomDetailsField.getText().equals("Details")) {
					roomDetailsField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (roomDetailsField.getText().equals("")) {
					roomDetailsField.setText("Details");
				}
			}
		});

		this.add(uploadButton);
		uploadButton.setBounds(20, 220, 150, 30);
		uploadButton.setForeground(navyBlue);
		uploadButton.setBackground(babyBlue);
		uploadButton.setFont(robotoBold10);
		uploadButton.setFocusable(false);
		uploadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				imageAddress = upload_image(roomImage);
			}
		});

		this.add(newButton);
		newButton.setBounds(450, 60, 125, 30);
		newButton.setForeground(navyBlue);
		newButton.setText("UPDATE");
		newButton.setBackground(babyBlue);
		newButton.setFont(robotoBold10);
		newButton.setFocusable(false);
		newButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (roomNumberField.getText().equals(roomNumber)
						&& roomTypeField.getSelectedItem().toString().equals(type)
						&& roomRateField.getText().equals(rate.toString()) && roomDetailsField.getText().equals(details)
						&& imageAddress.equals("")) {
					JOptionPane.showMessageDialog(null, "No changes in the room");
				} else {
					int iD = id;
					String roomNumber = roomNumberField.getText();
					String roomType = roomTypeField.getSelectedItem().toString();
					String roomDescription = roomDetailsField.getText();
					String imageAd = imageAddress;
					Double rates = Double.parseDouble(roomRateField.getText());
					Blob im = image;

					try {
						update(iD, roomNumber, roomType, roomDescription, imageAd, rates, im);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		this.add(cancelButton);
		cancelButton.setBounds(450, 110, 125, 30);
		cancelButton.setForeground(navyBlue);
		cancelButton.setBackground(babyBlue);
		cancelButton.setFont(robotoBold10);
		cancelButton.setFocusable(false);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "Adding room cancelled");
			}
		});

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
	 * Fetches the data in the text fields. These data will be used in adding a new
	 * entity in the database
	 * 
	 * @throws FileNotFoundException
	 */

	public void confirm_add(String roomNumber, String roomType, String roomDescription, int companyId,
			String imageAddress, Double rates) throws FileNotFoundException {

		// changes the image address into a file uploadable to the database
		InputStream image = null;
		try {

			image = new FileInputStream(imageAddress);

		} catch (FileNotFoundException ex) {

			System.out.println("File Not Found");
		}

		// connects to the database to fetch the address of the company where the hotel
		// room will be added
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "insert into room (ref_id, number, type, image, description, rates, rating) values (?,?,?,?,?,?, '4.1')";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, companyId);
			statement.setString(2, roomNumber);
			statement.setString(3, roomType);
			statement.setBlob(4, image);
			statement.setString(5, roomDescription);
			statement.setDouble(6, rates);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Successfull");
			RoomInformationPanel.display_table(RoomInformationPanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Unsuccessful");
		}

	}

	public void update(int id, String roomNumber, String roomType, String roomDescription, String imageAddress,
			Double rates, Blob im) throws FileNotFoundException {

		// changes the image address into a file uploadable to the database
		InputStream image = null;

		// connects to the database to fetch the address of the company where the hotel
		// room will be added
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update room set number = ?, type = ?, description = ?, rates = ?, image = ? where id = "
					+ id + " and ref_id = " + Login.getCompanyId();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, roomNumber);
			statement.setString(2, roomType);
			statement.setString(3, roomDescription);
			statement.setDouble(4, rates);

			try {
				image = new FileInputStream(imageAddress);
				statement.setBlob(5, image);
			} catch (FileNotFoundException ex) {
				statement.setBlob(5, im);
			} catch (Exception e) {
				statement.setBlob(5, im);
			}

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Updated successfully");
			RoomInformationPanel.display_table(RoomInformationPanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Update unsuccessfull");
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

}
