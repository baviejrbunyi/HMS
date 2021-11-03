package hms.admin.panels;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.Blob;

import hms.frames.AddServices;
import hms.main.Login;
import hms.objects.Services;

public class ServicesPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4890599297163231104L;

	// labels
	private JLabel panelTitle = new JLabel("SERVICES");

	private JLabel servicesInfoLabel = new JLabel("PACKAGE INFORMATION");

	private JLabel servicesImage = new JLabel();
	private JTextArea servicesDetails = new JTextArea(
			"Executive servicess, with a luxurious double bed and seating area, are popular with business travellers and families. Modern and contemporary, they may accommodate an extra bed or interconnect. Along with free WiFi, Executive servicess have air conditioning and bathrobes.");
	private JScrollPane servicesDtlsScroll = new JScrollPane(servicesDetails);

	private JLabel servicesName = new JLabel("Package 1");

	private JLabel servicesNameLabel = new JLabel("Service Name:");
	private JLabel servicesIdLabel = new JLabel("Service ID:");
	private JLabel servicesRatesLabel = new JLabel("Service Rate:");
	private JLabel servicesDetailsLabel = new JLabel("Details");

	private JLabel servicesId = new JLabel("Service ID:");
	private JLabel servicesRates = new JLabel("services Rate:");

	void Labels() {

		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		servicesInfoLabel.setBounds(0, 0, 200, 20);
		servicesInfoLabel.setFont(robotoBold14);

		servicesImage.setBounds(0, 40, 400, 330);
		servicesImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(servicesImage.getWidth(),
				servicesImage.getHeight(), Image.SCALE_SMOOTH)));

		servicesNameLabel.setBounds(420, 40, 150, 25);
		servicesNameLabel.setFont(robotoBold20);

		servicesIdLabel.setBounds(420, 90, 150, 20);
		servicesIdLabel.setFont(robotoBold14);

		servicesRatesLabel.setBounds(420, 120, 150, 20);
		servicesRatesLabel.setFont(robotoBold14);

		servicesName.setBounds(570, 40, 150, 25);
		servicesName.setFont(robotoBold20);

		servicesId.setBounds(570, 90, 150, 20);
		servicesId.setFont(robotoPlain14);

		servicesRates.setBounds(570, 120, 150, 20);
		servicesRates.setFont(robotoPlain14);

		servicesDetailsLabel.setBounds(420, 150, 150, 20);
		servicesDetailsLabel.setFont(robotoBold14);

		servicesDtlsScroll.setBounds(420, 180, 500, 192);
		servicesDtlsScroll.setBorder(null);
		servicesDetails.setBackground(null);
		servicesDetails.setWrapStyleWord(true);
		servicesDetails.setLineWrap(true);
		servicesDetails.setBorder(null);
		servicesDetails.setEditable(false);
		servicesDetails.setFocusable(false);
		servicesDetails.setFont(robotoPlain14);

		panel.add(servicesInfoLabel);
		panel.add(servicesImage);
		panel.add(servicesDtlsScroll);
		panel.add(servicesNameLabel);
		panel.add(servicesName);
		panel.add(servicesIdLabel);
		panel.add(servicesRatesLabel);
		panel.add(servicesId);
		panel.add(servicesRates);
		panel.add(servicesDetailsLabel);

		// adding to the panel
		this.add(panelTitle);

	}

	// button
	private JButton deleteButton = new JButton("DELETE");
	private JButton createButton = new JButton("ADD SERVICE");
	private JButton editButton = new JButton("EDIT");

	void Buttons() {

		// createButton
		createButton.setBounds(1120, 125, 270, 40);
		createButton.setFont(robotoBold10);
		createButton.setForeground(babyBlue);
		createButton.setBackground(navyBlue);
		createButton.setFocusable(false);
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddServices();
			}
		});

		// deleteButton
		deleteButton.setBounds(1120, 185, 270, 40);
		deleteButton.setFont(robotoBold10);
		deleteButton.setForeground(babyBlue);
		deleteButton.setBackground(navyBlue);
		deleteButton.setFocusable(false);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() > 0) {
					delete_service(Integer.parseInt(servicesId.getText()));
				} else {
					JOptionPane.showMessageDialog(null, "No selected service");
				}
			}
		});

		// editButton
		editButton.setBounds(1120, 245, 270, 40);
		editButton.setFont(robotoBold10);
		editButton.setForeground(babyBlue);
		editButton.setBackground(navyBlue);
		editButton.setFocusable(false);
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() > 0) {
					int id = Integer.parseInt(servicesId.getText());
					String serviceName = servicesName.getText();
					String details = servicesDetails.getText();
					Double rate = Double.parseDouble(servicesRates.getText());
					Blob image = (Blob) table.getModel().getValueAt(table.getSelectedRow(), 4);

					new AddServices(id, serviceName, details, rate, image);
				} else {
					JOptionPane.showMessageDialog(null, "No selected service");
				}
			}
		});

		// adding to bookingInfoPanel
		this.add(createButton);
		this.add(editButton);
		this.add(deleteButton);

	}

	// table
	private static JTable table;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Package Name", "Price", "Details", "Image" };

		// data
		Object[][] row = {};

		// creating the table
		table = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}

		};

		// table listener
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display_info(table, 0, true);
			}
		});

		table.setModel(new DefaultTableModel(row, column));
		table.setRowHeight(40);
		table.setFillsViewportHeight(true);
		table.removeColumn(table.getColumn("Details"));
		table.removeColumn(table.getColumn("Image"));
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.setFont(robotoPlain14);
		table.setShowVerticalLines(false);
		table.getTableHeader().setFont(robotoBold14);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		// adding rows to the table
		try {
			display_table(data_list());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(380, 125, 720, 275);
		this.add(scrollPane);

	}

	// panel
	private JPanel panel = new JPanel();
	private JPanel searchAndFilterPanel = new JPanel();

	void Panel() {

		panel.setBounds(380, 425, 1000, 390);
		panel.setLayout(null);

		searchAndFilterPanel.setBounds(380, 125, 720, 25);
		searchAndFilterPanel.setBackground(errorRed);
		searchAndFilterPanel.setLayout(null);

		this.add(searchAndFilterPanel);
		this.add(panel);
		// bookingInfoPanel.setBackground(errorRed);

	}

	public ServicesPanel() {

		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Labels();
		Buttons();
		Table();
		Panel();

		if (table.getRowCount() != 0) {
			panel.setVisible(true);
		} else {
			panel.setVisible(false);
		}

		display_info(table, 0, false);
	}

	/**
	 * Connects to the database and creates an arraylist
	 * 
	 * @return roomList
	 * @throws SQLException
	 */

	public static ArrayList<Services> data_list() throws SQLException {
		ArrayList<Services> servicesList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from services where ref_id = " + Login.getCompanyId() + " ";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Services services;
			while (rs.next()) {

				services = new Services(rs.getInt("id"), rs.getInt("ref_id"), rs.getString("name"),
						rs.getString("details"), rs.getDouble("rates"), rs.getBlob("image"));
				servicesList.add(services);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicesList;
	}

	/**
	 * Adds data to a table it includes id, name, and role
	 * 
	 * @throws SQLException
	 */

	public static void display_table(ArrayList<Services> datalist) throws SQLException {
		ArrayList<Services> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[5];

		try {
			for (int i = 0; i < list.size(); i++) {

				row[0] = list.get(i).getId();
				row[1] = list.get(i).getName();
				row[2] = list.get(i).getRates();
				row[3] = list.get(i).getDetails();
				row[4] = list.get(i).getImage();

				model.addRow(row);

			}
		} catch (Exception e) {

		}
	}

	public ImageIcon display_image(TableModel model, int selectedRow, JLabel label) {

		BufferedImage buffImg = null;
		ImageIcon imgIcon = null;
		Blob blob = (Blob) model.getValueAt(selectedRow, 4);
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

		imgIcon = new ImageIcon(buffImg.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));

		return imgIcon;
	}

	void display_info(JTable table, int row, boolean bol) {
		int i = table.getSelectedRow();
		TableModel model = table.getModel();

		if (bol == true) {

			servicesName.setText(model.getValueAt(i, 1).toString());
			servicesId.setText(model.getValueAt(i, 0).toString());
			servicesDetails.setText(model.getValueAt(i, 3).toString());
			servicesRates.setText(model.getValueAt(i, 2).toString());
			servicesImage.setIcon(display_image(model, i, servicesImage));

		} else {

			servicesName.setText(model.getValueAt(row, 1).toString());
			servicesId.setText(model.getValueAt(row, 0).toString());
			servicesDetails.setText(model.getValueAt(row, 3).toString());
			servicesRates.setText(model.getValueAt(row, 2).toString());
			servicesImage.setIcon(display_image(model, row, servicesImage));

		}
	}

	void delete_service(int id) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "delete from services where id = " + id + " and ref_id = " + Login.getCompanyId();
			Statement statement = connection.createStatement();

			statement.execute(query);

			JOptionPane.showMessageDialog(null, "Successfull");
			display_table(data_list());

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unsuccessful");
		}
	}
}
