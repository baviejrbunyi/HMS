package hms.admin.panels;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hms.objects.*;
import hms.frames.AddEmployee;
import hms.main.*;

public class EmployeePanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8021809700254127294L;

	// labels
	private JLabel panelTitle = new JLabel("EMPLOYEE DIRECTORY");
	private JLabel employeeImage = new JLabel();
	private JLabel employeeNameLabel = new JLabel("Name:");
	private JLabel employeeRoleLabel = new JLabel("Role:");
	private JLabel employeeEmailLabel = new JLabel("Email:");
	private JLabel employeeTelephoneLabel = new JLabel("Telephone:");
	private JLabel employeeBirthdayLabel = new JLabel("Birthday:");

	private JLabel employeePanelTitle = new JLabel("EMPLOYEE INFORMATION");
	private JLabel employeeName = new JLabel();
	private JLabel employeeRole = new JLabel();
	private JLabel employeeEmail = new JLabel();
	private JLabel employeeTelephone = new JLabel();
	private JLabel employeeBirthday = new JLabel();

	private void Labels() {
		// panelTitle
		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		// employeePnelTitle
		employeePanelTitle.setBounds(0, 0, 300, 20);
		employeePanelTitle.setFont(robotoBold14);

		// employeeImage
		employeeImage.setBounds(0, 40, 300, 300);
		employeeImage.setIcon(image);
		employeeImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(employeeImage.getWidth(),
				employeeImage.getHeight(), Image.SCALE_SMOOTH)));

		// empolyeeNameLabel
		employeeNameLabel.setBounds(320, 40, 100, 20);
		employeeNameLabel.setFont(robotoBold14);

		// employeeRoleLabel
		employeeRoleLabel.setBounds(320, 70, 150, 20);
		employeeRoleLabel.setFont(robotoBold14);

		// employeeEmailLabel
		employeeEmailLabel.setBounds(320, 100, 150, 20);
		employeeEmailLabel.setFont(robotoBold14);

		// employeeTelephoneLabel
		employeeTelephoneLabel.setBounds(320, 130, 150, 20);
		employeeTelephoneLabel.setFont(robotoBold14);

		// employeeBirthdayLabel
		employeeBirthdayLabel.setBounds(320, 160, 150, 20);
		employeeBirthdayLabel.setFont(robotoBold14);

		// employeeName
		employeeName.setBounds(470, 40, 200, 20);
		employeeName.setFont(robotoPlain14);

		// employeeRole
		employeeRole.setBounds(470, 70, 200, 20);
		employeeRole.setFont(robotoPlain14);

		// employeeEmail
		employeeEmail.setBounds(470, 100, 200, 20);
		employeeEmail.setFont(robotoPlain14);

		// employeeTelephone
		employeeTelephone.setBounds(470, 130, 150, 20);
		employeeTelephone.setFont(robotoPlain14);

		// employeeBirthday
		employeeBirthday.setBounds(470, 160, 200, 20);
		employeeBirthday.setFont(robotoPlain14);

		// adding to the panel
		this.add(panelTitle);

		// adding to the employee panel
		employeePanel.add(employeePanelTitle);
		employeePanel.add(employeeImage);
		employeePanel.add(employeeNameLabel);
		employeePanel.add(employeeRoleLabel);
		employeePanel.add(employeeEmailLabel);
		employeePanel.add(employeeTelephoneLabel);
		employeePanel.add(employeeBirthdayLabel);
		employeePanel.add(employeeName);
		employeePanel.add(employeeRole);
		employeePanel.add(employeeEmail);
		employeePanel.add(employeeTelephone);
		employeePanel.add(employeeBirthday);

	}

	// textfields
	private String[] filter = { "ID", "Name", "Role" };
	private JComboBox<String> searchFilter = new JComboBox<String>(filter);
	private JTextField searchField = new JTextField("Search by " + searchFilter.getSelectedItem());

	private void TextFields() {
		searchField.setBounds(480, 125, 520, 25);
		searchField.setFont(robotoPlain12);
		searchField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (searchField.getText().equals("Search by " + searchFilter.getSelectedItem())) {
					searchField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (searchField.getText().equals("")) {
					searchField.setText("Search by " + searchFilter.getSelectedItem());
				}
			}
		});
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (searchField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Search field is empty");
					} else {
						try {
							search_employee(searchFilter.getSelectedItem(), searchField.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

			public void keyTyped(KeyEvent e) {
				if (searchFilter.getSelectedItem() == "ID") {
					if (!(Character.isDigit(e.getKeyChar()))) {
						e.consume();
					}
				} else if (searchFilter.getSelectedItem() == "Name" || searchFilter.getSelectedItem() == "Role") {
					if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '.'
							|| e.getKeyChar() == ',' || e.getKeyChar() == '-')) {
						e.consume();
					}
				}
			}

		});

		searchFilter.setBounds(1000, 125, 100, 25);
		searchFilter.setFont(robotoPlain12);
		searchFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchField.setText("Search by " + searchFilter.getSelectedItem());
				try {
					display_table(data_list());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// adding to the panel
		this.add(searchField);
		this.add(searchFilter);
	}

	// buttons
	private String img;
	private JButton deleteButton = new JButton("DELETE");
	private JButton createButton = new JButton("ADD EMPLOYEE");
	private JButton editButton = new JButton("EDIT");
	private JButton uploadButton = new JButton("UPLOAD PICTURE");
	private JButton addButton = new JButton("SAVE");
	private JButton cancelButton = new JButton("CANCEL");
	private JButton searchButton = new JButton("SEARCH");

	private void Buttons() {
		// searchButton
		searchButton.setBounds(380, 125, 100, 25);
		searchButton.setFont(robotoBold10);
		searchButton.setForeground(babyBlue);
		searchButton.setBackground(navyBlue);
		searchButton.setFocusable(false);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (searchField.getText().equals("")
						|| searchField.getText().equals("Search by " + searchFilter.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "Search field is empty");
				} else {
					try {
						search_employee(searchFilter.getSelectedItem(), searchField.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		// createButton
		createButton.setBounds(1120, 125, 270, 40);
		createButton.setFont(robotoBold10);
		createButton.setForeground(babyBlue);
		createButton.setBackground(navyBlue);
		createButton.setFocusable(false);
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddEmployee();
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
					int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
					String role = employeeRole.getText();
					remove_employee(role, id);
				} else {
					JOptionPane.showMessageDialog(null, "No employee selected");
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

					int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
					String name = employeeName.getText();
					String username = (String) table.getModel().getValueAt(table.getSelectedRow(), 7);
					String role = employeeRole.getText();
					String email = employeeEmail.getText();
					com.mysql.cj.jdbc.Blob image = (com.mysql.cj.jdbc.Blob) table.getModel()
							.getValueAt(table.getSelectedRow(), 6);
					new AddEmployee(id, image, name, username, role, email);
				} else {
					JOptionPane.showMessageDialog(null, "No employee selected");
				}
			}
		});

		// adding buttons to the panel
		this.add(deleteButton);
		this.add(createButton);
		this.add(uploadButton);
		this.add(addButton);
		this.add(cancelButton);
		this.add(editButton);
		this.add(searchButton);
	}

	// Employee table
	private static JTable table;
	private DefaultTableCellRenderer cellRenderer;

	private void EmployeeTable() {

		// column header
		String[] column = { "ID", "Name", "Email", "Role", "Telephone", "Birthday", "Image", "Username" };

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
		table.setRowHeight(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.setModel(new DefaultTableModel(row, column));

		// table listener
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getRowCount() > 0) {
					employeePanel.setVisible(true);
					show_employee_info(table, 0, true);
				} else {
					employeePanel.setVisible(false);
				}
			}
		});

		// table properties
		table.setPreferredScrollableViewportSize(new Dimension(450, 100));
		table.setFillsViewportHeight(true);
		table.removeColumn(table.getColumn("Telephone"));
		table.removeColumn(table.getColumn("Birthday"));
		table.removeColumn(table.getColumn("Image"));
		table.removeColumn(table.getColumn("Username"));
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.setFont(robotoPlain14);
		table.setShowVerticalLines(false);
		table.getTableHeader().setFont(robotoBold14);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		// adds data to the table
		try {

			display_table(data_list());

		} catch (SQLException e) {

			e.printStackTrace();

		}

		// scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(380, 150, 720, 275);
		this.add(scrollPane);
	}

	// Attendance table
	private JTable attendanceTable;

	private void AttendanceTable(String username) {

		String[] column = { "Date", "Login", "Logout" };
		Object[][] rows = {};

		attendanceTable = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}
		};

		attendanceTable.setRowHeight(40);
		attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		attendanceTable.setModel(new DefaultTableModel(rows, column));

		attendanceTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		attendanceTable.setFillsViewportHeight(true);
		attendanceTable.setFont(robotoPlain14);
		attendanceTable.setShowVerticalLines(false);
		attendanceTable.getTableHeader().setFont(robotoBold14);
		attendanceTable.setCellSelectionEnabled(false);
		attendanceTable.setRowSelectionAllowed(true);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		attendanceTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		attendanceTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		attendanceTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

		display_attendance(username);

		JScrollPane scrollPane = new JScrollPane(attendanceTable);
		scrollPane.setBounds(740, 40, 270, 300);
		employeePanel.add(scrollPane);

	}

	// panel
	private JPanel employeePanel = new JPanel();

	void Panel() {
		employeePanel.setBounds(380, 460, 1010, 340);
		employeePanel.setLayout(null);

		this.add(employeePanel);
	}

	public EmployeePanel() {
		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Labels();
		Buttons();
		TextFields();
		EmployeeTable();
		AttendanceTable("rosemary.kemp");
		Panel();

		if (table.getRowCount() > 0) {
			employeePanel.setVisible(true);
			show_employee_info(table, 0, false);
		} else {
			employeePanel.setVisible(false);
		}
	}

	/**
	 * Connects to the database and creates an arraylist
	 * 
	 * @return employeeList
	 * @throws SQLException
	 */

	public static ArrayList<Employee> data_list() throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select *  from employee where ref_id = 1 union all select * from administrator where ref_id = "
					+ Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Employee employee;
			while (rs.next()) {

				employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"),
						rs.getString("username"), rs.getString("birthday"), rs.getString("telephone"),
						rs.getString("email"), rs.getBlob("profile"));
				employeeList.add(employee);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public ArrayList<Employee> data_list(String query) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Employee employee;
			while (rs.next()) {

				employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"),
						rs.getString("username"), rs.getString("birthday"), rs.getString("telephone"),
						rs.getString("email"), rs.getBlob("profile"));
				employeeList.add(employee);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	/**
	 * Adds data to a table it includes id, name, and role
	 * 
	 * @throws SQLException
	 */

	public static void display_table(ArrayList<Employee> data_list) throws SQLException {
		ArrayList<Employee> list = data_list;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[8];

		try {
			for (int i = 0; i < list.size(); i++) {
				row[0] = list.get(i).getId();
				row[1] = list.get(i).getName();
				row[2] = list.get(i).getEmail();
				row[3] = list.get(i).getRole();
				row[4] = list.get(i).getBirthday();
				row[5] = list.get(i).getTelephone();
				row[6] = list.get(i).getImage();
				row[7] = list.get(i).getUsername();

				model.addRow(row);

			}
		} catch (Exception e) {

		}
	}

	/**
	 * Displays information of the employee it includes name, role, email,
	 * telephone, birthday, and image
	 */

	public void show_employee_info(JTable table, int row, boolean bol) {
		int i = table.getSelectedRow();
		TableModel model = table.getModel();

		if (bol == true) {
			try {
				display_image(model, i);

				employeeName.setText(model.getValueAt(i, 1).toString());
				employeeRole.setText(model.getValueAt(i, 3).toString());
				employeeEmail.setText(model.getValueAt(i, 2).toString());
				employeeTelephone.setText(model.getValueAt(i, 4).toString());
				employeeBirthday.setText(model.getValueAt(i, 5).toString());

			} catch (Exception e) {

				employeeName.setText(model.getValueAt(i, 1).toString());
				employeeRole.setText(model.getValueAt(i, 3).toString());
				employeeEmail.setText(model.getValueAt(i, 2).toString());

			}

			try {
				AttendanceTable(model.getValueAt(i, 7).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				display_image(model, row);

				employeeName.setText(model.getValueAt(row, 1).toString());
				employeeRole.setText(model.getValueAt(row, 3).toString());
				employeeEmail.setText(model.getValueAt(row, 2).toString());
				employeeTelephone.setText(model.getValueAt(row, 4).toString());
				employeeBirthday.setText(model.getValueAt(row, 5).toString());

			} catch (Exception e) {

				employeeName.setText(model.getValueAt(row, 1).toString());
				employeeRole.setText(model.getValueAt(row, 2).toString());
				employeeEmail.setText(model.getValueAt(row, 3).toString());

			}

			try {
				AttendanceTable(model.getValueAt(row, 7).toString());
			} catch (Exception e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
			}
		}

	}

	/**
	 * Removes the selected employee from the database. It will shows a confirm
	 * message which also acts as a warning. If YES_Option is chosen, it will
	 * proceed in deleting. if successful, updates the table . if not successful,
	 * shows unsucessful message. If NO_Option is chosen, it will cancel the
	 * deletion process.
	 */

	public void remove_employee(String role, int id) {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");

			String queryOne;
			String query = "select * from attendance;";
			if (role.equals("Administrator")) {
				queryOne = "delete from administrator WHERE id = " + id;
			} else {
				query = "delete from attendance where ref_id = " + id;
				queryOne = "delete from employee WHERE id = " + id;
				
			}

			Statement statement = connection.createStatement();

			int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				statement.execute(query);
				statement.execute(queryOne);
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
			}

			display_table(data_list());

		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Deleted Unsuccessfully");

		}

	}

	/**
	 * Converts blob into image
	 */

	public void display_image(TableModel model, int selectedRow) {

		BufferedImage buffImg = null;
		ImageIcon imgIcon = null;
		Blob blob = (Blob) model.getValueAt(selectedRow, 6);
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
				buffImg.getScaledInstance(employeeImage.getWidth(), employeeImage.getHeight(), Image.SCALE_SMOOTH));
		employeeImage.setIcon(imgIcon);
	}

	public String getImg() {

		return img;

	}

	public void setImg(String img) {

		this.img = img;

	}

	/**
	 * Resets the panel
	 */

	public void show_panel(Boolean bol) {
		employeeImage.setVisible(bol);
		employeeNameLabel.setVisible(bol);
		employeeName.setVisible(bol);
		employeeRoleLabel.setVisible(bol);
		employeeRole.setVisible(bol);
		employeeEmailLabel.setVisible(bol);
		employeeEmail.setVisible(bol);
		employeeTelephoneLabel.setVisible(bol);
		employeeTelephone.setVisible(bol);
		employeeBirthdayLabel.setVisible(bol);
		employeeBirthday.setVisible(bol);

		employeeImage.setIcon(new ImageIcon(new ImageIcon("src/images/harry-potter.jpg").getImage()
				.getScaledInstance(employeeImage.getWidth(), employeeImage.getHeight(), Image.SCALE_DEFAULT)));
		employeeName.setText(null);
		employeeRole.setText(null);
		employeeEmail.setText("Not Set");
		employeeTelephone.setText("Not Set");
		employeeBirthday.setText("Not Set");

	}

	/**
	 * Creates a list of attendance
	 */
	public ArrayList<Attendance> attendance_list(String username) {
		ArrayList<Attendance> attendanceList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from attendance where ref_id = " + Login.getCompanyId() + " and username = '"
					+ username + "' order by id desc";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Attendance attendance;
			while (rs.next()) {

				attendance = new Attendance(rs.getInt("id"), rs.getString("username"), rs.getString("last_login_date"),
						rs.getString("last_login_time"), rs.getString("last_logout_time"));
				attendanceList.add(attendance);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceList;
	}

	/**
	 * Displays the attendance of the employee
	 */

	public void display_attendance(String username) {
		ArrayList<Attendance> list = attendance_list(username);
		DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
		model.setRowCount(0);
		Object[] row = new Object[3];

		try {
			for (int i = 0; i < list.size(); i++) {

				row[0] = list.get(i).getLast_login_date();
				row[1] = list.get(i).getLast_login_time();
				row[2] = list.get(i).getLast_logout_time();
				model.addRow(row);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void search_employee(Object filter, String search) throws SQLException {
		String query;

		if (filter == "ID") {
			query = "select * from employee where id = " + search + " && ref_id = " + Login.getCompanyId();
			display_table(data_list(query));

		} else if (filter == "Name") {
			query = "select * from employee where name like '%" + search + "%'  && ref_id = " + Login.getCompanyId();
			display_table(data_list(query));

		} else if (filter == "Role") {
			query = "select * from employee where role like '%" + search + "%' && ref_id = " + Login.getCompanyId();
			display_table(data_list(query));

		}

		if (table.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "No employee found");
			display_table(data_list());
		}
	}

}
