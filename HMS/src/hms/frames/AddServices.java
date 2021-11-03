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
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hms.admin.panels.ServicesPanel;
import hms.main.Login;

public class AddServices extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String imageAddress = "";

	JLabel frameTitle = new JLabel("ADD SERVICES");

	JLabel servicesImage = new JLabel();

	JLabel servicesNameLabel = new JLabel("Services Name:");
	JLabel servicesRateLabel = new JLabel("Services Rate:");

	JTextField servicesNameField = new JTextField();
	JTextField servicesRateField = new JTextField();
	JTextArea servicesDetailsField = new JTextArea("Details");
	JScrollPane scroll = new JScrollPane(servicesDetailsField);

	JButton uploadButton = new JButton("UPLOAD PICTURE");
	JButton newButton = new JButton("ADD SERVICES");
	JButton cancelButton = new JButton("CANCEL");

	public AddServices() {
		imageAddress = "src/images/profile.jpg";

		this.setSize(600, 315);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - ADD services");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(servicesImage);
		servicesImage.setBounds(20, 60, 150, 150);
		servicesImage.setIcon(new ImageIcon(profile.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

		this.add(servicesNameLabel);
		servicesNameLabel.setBounds(190, 60, 150, 20);
		servicesNameLabel.setFont(robotoBold14);
		servicesNameLabel.setForeground(babyBlue);

		this.add(servicesRateLabel);
		servicesRateLabel.setBounds(190, 90, 150, 20);
		servicesRateLabel.setFont(robotoBold14);
		servicesRateLabel.setForeground(babyBlue);

		this.add(servicesNameField);
		servicesNameField.setBounds(300, 60, 140, 20);
		servicesNameField.setFont(robotoPlain14);
		servicesNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					servicesRateField.requestFocus();
				}
			}
		});

		this.add(servicesRateField);
		servicesRateField.setBounds(300, 90, 140, 20);
		servicesRateField.setFont(robotoPlain14);
		servicesRateField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					servicesDetailsField.requestFocus();
				}
			}
		});
		servicesRateField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!servicesRateField.getText().equals("")) {
					Double servicesRate = Double.parseDouble(servicesRateField.getText());
					servicesRateField.setText(servicesRate.toString());
				}
			}
		});

		this.add(scroll);
		scroll.setBounds(190, 120, 250, 130);
		servicesDetailsField.setWrapStyleWord(true);
		servicesDetailsField.setLineWrap(true);
		servicesDetailsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (servicesDetailsField.getText().equals("Details")) {
					servicesDetailsField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (servicesDetailsField.getText().equals("")) {
					servicesDetailsField.setText("Details");
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
				imageAddress = upload_image(servicesImage);
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
				if (servicesNameField.equals("")) {
					JOptionPane.showMessageDialog(null, "Services name field is empty");
				} else if (servicesRateField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Services rate field is empty");
				} else if (servicesDetailsField.getText().equals("")
						|| servicesDetailsField.getText().equals("Details")) {
					JOptionPane.showMessageDialog(null, "Services details field is empty");
				} else {
					String servicesName = servicesNameField.getText();
					String servicesDescription = servicesDetailsField.getText();
					int companyId = Login.getCompanyId();
					String imageAd = imageAddress;
					Double servicesRate = Double.parseDouble(servicesRateField.getText());

					try {
						confirm_add(servicesName, servicesDescription, companyId, imageAd, servicesRate);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
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
				JOptionPane.showMessageDialog(null, "Adding services cancelled");
			}
		});

	}

	public AddServices(int id, String serviceName, String details, Double rate, Blob image) {
		this.setSize(600, 315);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(navyBlue);
		this.setLocationRelativeTo(null);
		this.setTitle("HMS - EDIT SERVICES");
		this.setIconImage(icon);

		this.add(frameTitle);
		frameTitle.setBounds(20, 20, 300, 20);
		frameTitle.setText("EDIT SERVICES");
		frameTitle.setFont(robotoBold20);
		frameTitle.setForeground(babyBlue);

		this.add(servicesImage);
		servicesImage.setBounds(20, 60, 150, 150);
		servicesImage.setIcon(display_image(image, servicesImage));

		this.add(servicesNameLabel);
		servicesNameLabel.setBounds(190, 60, 150, 20);
		servicesNameLabel.setFont(robotoBold14);
		servicesNameLabel.setForeground(babyBlue);

		this.add(servicesRateLabel);
		servicesRateLabel.setBounds(190, 90, 150, 20);
		servicesRateLabel.setFont(robotoBold14);
		servicesRateLabel.setForeground(babyBlue);

		this.add(servicesNameField);
		servicesNameField.setBounds(300, 60, 140, 20);
		servicesNameField.setFont(robotoPlain14);
		servicesNameField.setText(serviceName);
		servicesNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					servicesRateField.requestFocus();
				}
			}
		});

		this.add(servicesRateField);
		servicesRateField.setBounds(300, 90, 140, 20);
		servicesRateField.setFont(robotoPlain14);
		servicesRateField.setText(rate.toString());
		servicesRateField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					servicesDetailsField.requestFocus();
				}
			}
		});
		servicesRateField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (!servicesRateField.getText().equals("")) {
					Double servicesRate = Double.parseDouble(servicesRateField.getText());
					servicesRateField.setText(servicesRate.toString());
				}
			}
		});

		this.add(scroll);
		scroll.setBounds(190, 120, 250, 130);
		servicesDetailsField.setWrapStyleWord(true);
		servicesDetailsField.setLineWrap(true);
		servicesDetailsField.setText(details);
		servicesDetailsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (servicesDetailsField.getText().equals("Details")) {
					servicesDetailsField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (servicesDetailsField.getText().equals("")) {
					servicesDetailsField.setText("Details");
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
				imageAddress = upload_image(servicesImage);
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
				if (servicesNameField.getText().equals(serviceName)
						&& servicesRateField.getText().equals(rate.toString())
						&& servicesDetailsField.getText().equals(details) && imageAddress.equals("")) {
					JOptionPane.showMessageDialog(null, "No changes in the services");
				} else {
					int iD = id;
					String servicesName = servicesNameField.getText();
					String servicesDescription = servicesDetailsField.getText();
					String imageAd = imageAddress;
					Double rates = Double.parseDouble(servicesRateField.getText());
					Blob im = image;

					try {
						update(iD, servicesName, servicesDescription, imageAd, rates, im);
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
				JOptionPane.showMessageDialog(null, "Adding services cancelled");
			}
		});

	}

	/**
	 * Fetches the data in the text fields. These data will be used in adding a new
	 * entity in the database
	 * 
	 * @throws FileNotFoundException
	 */

	public void confirm_add(String servicesName, String servicesDescription, int companyId, String imageAddress,
			Double rates) throws FileNotFoundException {

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
			String query = "insert into services (ref_id, name, details, rates, image) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, companyId);
			statement.setString(2, servicesName);
			statement.setString(3, servicesDescription);
			statement.setDouble(4, rates);
			statement.setBlob(5, image);

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Successfull");
			ServicesPanel.display_table(ServicesPanel.data_list());

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, "Unsuccessful");
		}

	}

	public void update(int id, String servicesName, String serviceDescription, String imageAddress, Double rates,
			Blob im) throws FileNotFoundException {

		// changes the image address into a file uploadable to the database
		InputStream image = null;

		// connects to the database to fetch the address of the company where the hotel
		// room will be added
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "update services set name = ?, details = ?, rates = ?, image = ? where id = " + id
					+ " and ref_id = " + Login.getCompanyId();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, servicesName);
			statement.setString(2, serviceDescription);
			statement.setDouble(3, rates);

			try {
				image = new FileInputStream(imageAddress);
				statement.setBlob(4, image);
			} catch (FileNotFoundException ex) {
				statement.setBlob(4, im);
			} catch (Exception e) {
				statement.setBlob(4, im);
			}

			statement.execute();
			statement.close();

			dispose();
			JOptionPane.showMessageDialog(null, "Updated successfully");
			ServicesPanel.display_table(ServicesPanel.data_list());

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
