package hms.employee.panels;

import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hms.main.Login;
import hms.objects.Room;

public class RoomsPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4890599297163231104L;

	// labels
	private JLabel panelTitle = new JLabel("ROOM");

	private JLabel roomInfoLabel = new JLabel("ROOM INFORMATION");

	private JLabel roomImage = new JLabel();
	private JTextArea roomDetails = new JTextArea();
	private JScrollPane roomDtlsScroll = new JScrollPane(roomDetails);

	private JLabel roomNumber = new JLabel();

	private JLabel roomNumberLabel = new JLabel("Room Number:");
	private JLabel roomIdLabel = new JLabel("Room Id:");
	private JLabel roomCompanyLabel = new JLabel("Hotel Name:");
	private JLabel roomTypeLabel = new JLabel("Room Type:");
	private JLabel roomRatesLabel = new JLabel("Room Rate:");
	private JLabel roomRatingLabel = new JLabel("Rating:");
	private JLabel occupiedFromLabel = new JLabel("Occupied From::");
	private JLabel occupiedToLabel = new JLabel("Occupied To:");
	private JLabel roomStatusLabel = new JLabel("Status:");
	private JLabel roomDetailsLabel = new JLabel("Details");

	private JLabel roomId = new JLabel();
	private JLabel roomCompany = new JLabel();
	private JLabel roomType = new JLabel();
	private JLabel roomRates = new JLabel();
	private JLabel roomRating = new JLabel();
	private JLabel occupiedFrom = new JLabel();
	private JLabel occupiedTo = new JLabel();
	private JLabel roomStatus = new JLabel();

	private final String[] filterOptions = { "All", "Available", "Not Available" };
	private JComboBox<String> filter = new JComboBox<String>(filterOptions);

	private JTextField searchField = new JTextField("Search transactions by Room Id, Type, or Number");

	void Labels() {

		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		roomInfoLabel.setBounds(0, 0, 200, 20);
		roomInfoLabel.setFont(robotoBold14);

		filter.setBounds(850, 0, 150, 25);
		filter.setFont(robotoPlain14);
		filter.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String availableQuery = "select * from room where status = 'Available' and ref_id ="
						+ Login.getCompanyId();
				String notAvailableQuery = "select * from room where status = 'Not Available' and ref_id ="
						+ Login.getCompanyId();

				switch (filter.getSelectedIndex()) {
				case 0:
					try {
						display_table(data_list());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 1:
					try {
						display_table(data_list(availableQuery));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case 2:
					try {
						display_table(data_list(notAvailableQuery));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		searchField.setBounds(100, 0, 750, 25);
		searchField.setFont(robotoPlain14);
		searchField.setBorder(BorderFactory.createCompoundBorder(searchField.getBorder(),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		searchField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (searchField.getText().equals("Search transactions by Room Id, Type, or Number")) {
					searchField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (searchField.getText().equals("")) {
					searchField.setText("Search transactions by Room Id, Type, or Number");
				}
			}
		});
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (searchField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Search field is empty");
						try {
							display_table(data_list());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						search_room(searchField.getText(), filter.getSelectedItem().toString());
					}

				}
			}
		});

		roomImage.setBounds(0, 40, 400, 330);
		roomImage.setIcon(new ImageIcon(
				image.getImage().getScaledInstance(roomImage.getWidth(), roomImage.getHeight(), Image.SCALE_SMOOTH)));

		roomNumberLabel.setBounds(420, 40, 150, 25);
		roomNumberLabel.setFont(robotoBold20);

		roomIdLabel.setBounds(420, 90, 150, 20);
		roomIdLabel.setFont(robotoBold14);

		roomCompanyLabel.setBounds(420, 120, 150, 20);
		roomCompanyLabel.setFont(robotoBold14);

		roomTypeLabel.setBounds(420, 150, 150, 20);
		roomTypeLabel.setFont(robotoBold14);

		occupiedFromLabel.setBounds(420, 180, 150, 20);
		occupiedFromLabel.setFont(robotoBold14);

		occupiedToLabel.setBounds(420, 210, 150, 20);
		occupiedToLabel.setFont(robotoBold14);

		roomStatusLabel.setBounds(420, 240, 150, 20);
		roomStatusLabel.setFont(robotoBold14);

		roomRatesLabel.setBounds(420, 270, 150, 20);
		roomRatesLabel.setFont(robotoBold14);

		roomRatingLabel.setBounds(420, 300, 150, 20);
		roomRatingLabel.setFont(robotoBold14);

		roomNumber.setBounds(570, 40, 150, 25);
		roomNumber.setFont(robotoBold20);

		roomId.setBounds(570, 90, 150, 20);
		roomId.setFont(robotoPlain14);

		roomCompany.setBounds(570, 120, 150, 20);
		roomCompany.setFont(robotoPlain14);

		roomType.setBounds(570, 150, 150, 20);
		roomType.setFont(robotoPlain14);

		occupiedFrom.setBounds(570, 180, 150, 20);
		occupiedFrom.setFont(robotoPlain14);

		occupiedTo.setBounds(570, 210, 150, 20);
		occupiedTo.setFont(robotoPlain14);

		roomStatus.setBounds(570, 240, 150, 20);
		roomStatus.setFont(robotoPlain14);

		roomRates.setBounds(570, 270, 150, 20);
		roomRates.setFont(robotoPlain14);

		roomRating.setBounds(570, 300, 150, 20);
		roomRating.setFont(robotoPlain14);

		roomDetailsLabel.setBounds(750, 90, 150, 20);
		roomDetailsLabel.setFont(robotoBold14);

		roomDtlsScroll.setBounds(750, 120, 200, 210);
		roomDtlsScroll.setBorder(null);
		roomDetails.setBackground(null);
		roomDetails.setWrapStyleWord(true);
		roomDetails.setLineWrap(true);
		roomDetails.setBorder(null);
		roomDetails.setEditable(false);
		roomDetails.setFocusable(false);
		roomDetails.setFont(robotoPlain14);

		panel.add(roomInfoLabel);
		panel.add(roomImage);
		panel.add(roomDtlsScroll);
		panel.add(roomNumberLabel);
		panel.add(roomNumber);
		panel.add(roomIdLabel);
		panel.add(roomCompanyLabel);
		panel.add(roomTypeLabel);
		panel.add(roomRatesLabel);
		panel.add(roomRatingLabel);
		panel.add(roomId);
		panel.add(roomCompany);
		panel.add(roomType);
		panel.add(roomRates);
		panel.add(roomRating);
		panel.add(occupiedFromLabel);
		panel.add(occupiedToLabel);
		panel.add(roomStatusLabel);
		panel.add(roomDetailsLabel);
		panel.add(occupiedFrom);
		panel.add(occupiedTo);
		panel.add(roomStatus);

		// adding to the panel
		this.add(panelTitle);
		searchAndFilterPanel.add(filter);
		searchAndFilterPanel.add(searchField);

	}

	// button
	private JButton searchButton = new JButton("SEARCH");

	void Buttons() {

		searchButton.setBounds(0, 0, 100, 25);
		searchButton.setForeground(babyBlue);
		searchButton.setBackground(navyBlue);
		searchButton.setFocusable(false);
		searchButton.setFont(robotoBold10);

		searchButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (searchField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Search field is empty");
				} else {
					search_room(searchField.getText(), filter.getSelectedItem().toString());
					try {
						display_table(data_list());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// adding to bookingInfoPanel
		searchAndFilterPanel.add(searchButton);

	}

	// table
	private JTable table;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Room Type", "Room Number", "Occupied From", "Occupied To", "Status", "Rates",
				"Rating", "Details", "Image" };

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
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.removeColumn(table.getColumn("Rates"));
		table.removeColumn(table.getColumn("Rating"));
		table.removeColumn(table.getColumn("Details"));
		table.removeColumn(table.getColumn("Image"));
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
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
		scrollPane.setBounds(380, 150, 1000, 250);
		this.add(scrollPane);

	}

	// panel
	private JPanel panel = new JPanel();
	private JPanel searchAndFilterPanel = new JPanel();

	void Panel() {

		panel.setBounds(380, 425, 1000, 390);
		panel.setLayout(null);

		searchAndFilterPanel.setBounds(380, 125, 1000, 25);
		searchAndFilterPanel.setBackground(errorRed);
		searchAndFilterPanel.setLayout(null);

		this.add(searchAndFilterPanel);
		this.add(panel);
		// bookingInfoPanel.setBackground(errorRed);

	}

	public RoomsPanel() {

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

	public ArrayList<Room> data_list() throws SQLException {
		ArrayList<Room> roomList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from room where ref_id = " + Login.getCompanyId() + " ";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Room room;
			while (rs.next()) {

				room = new Room(rs.getInt("id"), rs.getString("number"), rs.getString("type"), rs.getString("address"),
						rs.getString("occupied_from"), rs.getString("occupied_to"), rs.getString("status"),
						rs.getString("description"), rs.getBlob("image"), rs.getDouble("rates"),
						rs.getString("rating"));
				roomList.add(room);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}

	/**
	 * Connects to the database and creates an arraylist based on the query given
	 * 
	 * @return roomList
	 * @throws SQLException
	 */

	public ArrayList<Room> data_list(String query) throws SQLException {
		ArrayList<Room> roomList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Room room;
			while (rs.next()) {

				room = new Room(rs.getInt("id"), rs.getString("number"), rs.getString("type"), rs.getString("address"),
						rs.getString("occupied_from"), rs.getString("occupied_to"), rs.getString("status"),
						rs.getString("description"), rs.getBlob("image"), rs.getDouble("rates"),
						rs.getString("rating"));
				roomList.add(room);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}

	/**
	 * Adds data to a table it includes id, name, and role
	 * 
	 * @throws SQLException
	 */

	void display_table(ArrayList<Room> datalist) throws SQLException {
		ArrayList<Room> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[10];

		try {
			for (int i = 0; i < list.size(); i++) {

				String occupied_from;
				String occupied_to;

				if (list.get(i).getoccupied_from() == null) {
					occupied_from = "Not Set";
				} else {
					occupied_from = list.get(i).getoccupied_from();
				}

				if (list.get(i).getoccupied_to() == null) {
					occupied_to = "Not Set";
				} else {
					occupied_to = list.get(i).getoccupied_to();
				}

				row[0] = list.get(i).getId();
				row[1] = list.get(i).getType();
				row[2] = list.get(i).getNumber();
				row[3] = occupied_from;
				row[4] = occupied_to;
				row[5] = list.get(i).getStatus();
				row[6] = list.get(i).getRates();
				row[7] = list.get(i).getRating();
				row[8] = list.get(i).getDescription();
				row[9] = list.get(i).getImage();

				model.addRow(row);

			}
		} catch (Exception e) {

		}
	}

	void search_room(String search, String filter) {

		String filterVal = "(status = 'Available' or status = 'Not Available')";

		if (!(filter.equals("All"))) {
			filterVal = "status = '" + filter + "'";
		}

		String idQuery = "select * from room where id = " + search + " and " + filterVal + " and ref_id = "
				+ Login.getCompanyId();
		String typeQuery = "select * from room where type = '" + search + "'and " + filterVal + " and ref_id = "
				+ Login.getCompanyId();
		String numberQuery = "select * from room where number = '" + search + "' and " + filterVal + " and ref_id = "
				+ Login.getCompanyId();

		try {
			try {
				display_table(data_list(idQuery));
			} catch (Exception e) {

			}

			if (table.getRowCount() == 0) {
				try {
					display_table(data_list(typeQuery));
				} catch (Exception e) {

				}

				if (table.getRowCount() == 0) {
					try {
						display_table(data_list(numberQuery));
					} catch (Exception e) {

					}

					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "No room found");
						display_table(data_list());
					}
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No room found");
			try {
				display_table(data_list());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ImageIcon display_image(TableModel model, int selectedRow) {

		BufferedImage buffImg = null;
		ImageIcon imgIcon = null;
		Blob blob = (Blob) model.getValueAt(selectedRow, 9);
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
				buffImg.getScaledInstance(roomImage.getWidth(), roomImage.getHeight(), Image.SCALE_SMOOTH));

		return imgIcon;
	}

	void display_info(JTable table, int row, boolean bol) {
		int i = table.getSelectedRow();
		TableModel model = table.getModel();

		if (bol == true) {

			roomId.setText(model.getValueAt(i, 0).toString());
			roomType.setText(model.getValueAt(i, 1).toString());
			roomNumber.setText(model.getValueAt(i, 2).toString());
			occupiedFrom.setText(model.getValueAt(i, 3).toString());
			occupiedTo.setText(model.getValueAt(i, 4).toString());
			roomStatus.setText(model.getValueAt(i, 5).toString());
			roomRates.setText(model.getValueAt(i, 6).toString());
			roomRating.setText(model.getValueAt(i, 7).toString());
			roomDetails.setText(model.getValueAt(i, 8).toString());
			roomImage.setIcon(display_image(model, i));

		} else {

			roomId.setText(model.getValueAt(row, 0).toString());
			roomType.setText(model.getValueAt(row, 1).toString());
			roomNumber.setText(model.getValueAt(row, 2).toString());
			occupiedFrom.setText(model.getValueAt(row, 3).toString());
			occupiedTo.setText(model.getValueAt(row, 4).toString());
			roomStatus.setText(model.getValueAt(row, 5).toString());
			roomRates.setText(model.getValueAt(row, 6).toString());
			roomRating.setText(model.getValueAt(row, 7).toString());
			roomDetails.setText(model.getValueAt(row, 8).toString());
			roomImage.setIcon(display_image(model, row));

		}
	}
}
