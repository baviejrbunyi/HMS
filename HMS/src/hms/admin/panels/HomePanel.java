package hms.admin.panels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import hms.main.Login;
import hms.objects.AttendanceCombined;
import hms.objects.Room;

public class HomePanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel panelTitle = new JLabel("HOME");

	private JLabel availableRoomsLabel = new JLabel("AVAILABLE ROOMS");
	private JLabel incomeLabel = new JLabel("INCOME INFORMATION");
	private JLabel attendanceLabel = new JLabel("ATTENDANCE");

	private JLabel incomeFromCompletedLabel = new JLabel("Income (Completed):");
	private JLabel incomeFromConfirmedLabel = new JLabel("Income (Confirmed):");
	private JLabel totalIncomeLabel = new JLabel("Total Income:");

	private JLabel incomeFromCompleted = new JLabel("1500.0");
	private JLabel incomeFromConfirmed = new JLabel("1500.0");
	private JLabel totalIncome = new JLabel("3000.0");

	void Label() {

		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		availableRoomsLabel.setBounds(380, 125, 500, 20);
		availableRoomsLabel.setFont(robotoBold14);

		incomeLabel.setBounds(900, 125, 500, 20);
		incomeLabel.setFont(robotoBold14);

		attendanceLabel.setBounds(900, 485, 500, 20);
		attendanceLabel.setFont(robotoBold14);

		incomeFromCompletedLabel.setBounds(0, 228, 250, 20);
		incomeFromCompletedLabel.setFont(robotoBold12);

		incomeFromConfirmedLabel.setBounds(0, 258, 250, 20);
		incomeFromConfirmedLabel.setFont(robotoBold12);

		totalIncomeLabel.setBounds(0, 288, 250, 12);
		totalIncomeLabel.setFont(robotoBold12);

		Double[] incomeValues = income_values();
		Double total = incomeValues[0] + incomeValues[1];

		incomeFromCompleted.setBounds(250, 228, 250, 12);
		incomeFromCompleted.setFont(robotoBold12);
		incomeFromCompleted.setHorizontalAlignment(SwingConstants.RIGHT);
		incomeFromCompleted.setText(incomeValues[0].toString());

		incomeFromConfirmed.setBounds(250, 258, 250, 12);
		incomeFromConfirmed.setFont(robotoBold12);
		incomeFromConfirmed.setHorizontalAlignment(SwingConstants.RIGHT);
		incomeFromConfirmed.setText(incomeValues[1].toString());

		totalIncome.setBounds(250, 288, 250, 12);
		totalIncome.setFont(robotoBold12);
		totalIncome.setHorizontalAlignment(SwingConstants.RIGHT);
		totalIncome.setText(total.toString());

		this.add(panelTitle);
		this.add(availableRoomsLabel);
		this.add(incomeLabel);
		this.add(attendanceLabel);
		statsPanel.add(incomeFromCompletedLabel);
		statsPanel.add(incomeFromConfirmedLabel);
		statsPanel.add(totalIncomeLabel);
		statsPanel.add(incomeFromCompleted);
		statsPanel.add(incomeFromConfirmed);
		statsPanel.add(totalIncome);
	}

	private JTable table;
	private JTable attendanceTable;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[][] column = { { "Room Number", "Room Type", "Status" }, { "Employee Name", "Status" } };

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

		attendanceTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4789327404773408781L;

			public boolean isCellEditable(int data, int columnLabel) {

				return false;
			}

		};

		table.setModel(new DefaultTableModel(row, column[0]));
		table.setRowHeight(40);
		table.setFillsViewportHeight(true);
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

		attendanceTable.setModel(new DefaultTableModel(row, column[1]));
		attendanceTable.setRowHeight(40);
		attendanceTable.setFillsViewportHeight(true);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		attendanceTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		attendanceTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		attendanceTable.setFont(robotoPlain14);
		attendanceTable.setShowVerticalLines(false);
		attendanceTable.getTableHeader().setFont(robotoBold14);
		attendanceTable.getTableHeader().setResizingAllowed(false);
		attendanceTable.getTableHeader().setReorderingAllowed(false);
		attendanceTable.setCellSelectionEnabled(false);
		attendanceTable.setRowSelectionAllowed(true);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// adding rows to the table
				try {
					display_attendance(attendance_list(), attendanceTable);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}, 1000, 1000);

		try {

			display_table(data_list(), table);
		} catch (Exception e) {

		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(380, 165, 500, 635);
		this.add(scrollPane);

		JScrollPane scroll = new JScrollPane(attendanceTable);
		scroll.setBounds(900, 525, 500, 275);
		this.add(scroll);

	}

	private JPanel statsPanel = new JPanel();

	void Panel() {
		statsPanel.setBounds(900, 165, 500, 300);
		statsPanel.setLayout(null);

		ChartPanel panel = income_pie_chart(income_values());
		panel.setBounds(0, 0, 500, 210);

		statsPanel.add(panel);
		this.add(statsPanel);
	}

	public HomePanel() {
		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Label();
		Table();
		Panel();

	}

	Double[] income_values() {
		Double[] incomeValues = new Double[2];

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs;
			int id = Login.getCompanyId();
			String[] query = { "select sum(paid) from transaction where status = 'Completed' and ref_id = " + id,
					"select sum(paid) from transaction where status = 'Confirmed' and ref_id = " + id };

			for (int i = 0; i < query.length; i++) {
				rs = statement.executeQuery(query[i]);
				rs.next();
				incomeValues[i] = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return incomeValues;
	}

	ChartPanel income_pie_chart(Double[] income) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
		dataset.setValue("Completed Transactions", income[0]);
		dataset.setValue("Confirmed Transactions", income[1]);
		JFreeChart chart = ChartFactory.createPieChart("Hotel Income", dataset, true, true, true);
		ChartPanel panel = new ChartPanel(chart);

		return panel;

	}

	/**
	 * Connects to the database and creates an arraylist based on the query given
	 * 
	 * @return roomList
	 * @throws SQLException
	 */

	public ArrayList<Room> data_list() throws SQLException {
		ArrayList<Room> roomList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from room where status = 'Available' and ref_id = " + Login.getCompanyId();
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

	public static void display_table(ArrayList<Room> datalist, JTable table) throws SQLException {
		ArrayList<Room> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[10];

		try {
			for (int i = 0; i < list.size(); i++) {

				row[0] = list.get(i).getNumber();
				row[1] = list.get(i).getType();
				row[2] = list.get(i).getStatus();

				model.addRow(row);

			}
		} catch (Exception e) {

		}
	}

	/**
	 * Creates a list of attendance
	 */
	public ArrayList<AttendanceCombined> attendance_list() {
		ArrayList<AttendanceCombined> attendanceList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "SELECT employee.name, last_login_date, last_logout_time\r\n" + "FROM attendance\r\n"
					+ "right join employee\r\n"
					+ "ON attendance.ref_id = employee.id where  (attendance.last_login_date = current_date() or attendance.last_login_date is null) and "
					+ " employee.ref_id = " + Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			AttendanceCombined attendance;
			while (rs.next()) {

				attendance = new AttendanceCombined(rs.getString("name"), rs.getString("last_login_date"),
						rs.getString("last_logout_time"));

				attendanceList.add(attendance);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceList;
	}

	public static void display_attendance(ArrayList<AttendanceCombined> datalist, JTable table) throws SQLException {
		ArrayList<AttendanceCombined> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[10];

		try {
			for (int i = 0; i < list.size(); i++) {

				row[0] = list.get(i).getName();

				String status = "Not logged in";
				String last_login = list.get(i).getLast_login_date();
				String last_logout = list.get(i).getLast_logout_time();

				if (!(last_login == null)) {
					if (!(last_logout == null)) {
						status = "Logged out";
					} else {
						status = "Logged in";
					}
				}

				row[1] = status;

				model.addRow(row);

			}
		} catch (Exception e) {

		}
	}

}
