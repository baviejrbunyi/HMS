package hms.admin.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.YearMonth;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import hms.main.Login;
import hms.objects.SimpleTransaction;

public class BusinessStatsPanel extends Resources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel panelTitle = new JLabel("BUSINESS STATISTICS");

	void Label() {

		panelTitle.setBounds(380, 60, 400, 30);
		panelTitle.setFont(robotoBold25);
		panelTitle.setForeground(navyBlue);

		this.add(panelTitle);
	}

	private final String[] choices = { "Income", "Loses", "Monthly Bookings", "All Transactions", "Services" };
	private JComboBox<String> choicesDropDown = new JComboBox<String>(choices);

	private final String[] filters = { "Completed", "Confirmed" };
	private JComboBox<String> filterDropDown = new JComboBox<String>(filters);

	void TextFields() {

		choicesDropDown.setBounds(380, 150, 500, 25);
		choicesDropDown.setFont(robotoBold14);
		choicesDropDown.setFocusable(false);
		choicesDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] filters = { { "Completed", "Confirmed" },

						{ "Refunds" },

						{ "January", "February", "March", "April", "May", "June", "July", "August", "September",
								"October", "November", "December" },

						{ "Completed", "Confirmed", "Cancelled" } };

				if (choicesDropDown.getSelectedItem() == "Income") {
					filterDropDown.setModel(new DefaultComboBoxModel<String>(filters[0]));
					income_pie_chart(income_values());
					repaint();
					// shows default data in the table
					String query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where status = 'Completed' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedItem() == "Loses") {
					filterDropDown.setModel(new DefaultComboBoxModel<String>(filters[1]));
					loses_bar_chart(loses_values());
					repaint();
					// shows default data in the table
					String query = "select id, ref_id, transaction_date, type, services_cost, total , paid, status, balance, services, refund "
							+ "from transaction where refund > 0 and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedItem() == "Monthly Bookings") {
					filterDropDown.setModel(new DefaultComboBoxModel<String>(filters[2]));
					monthly_bookings_bar_chart(monthly_bookings());
					repaint();

					int month = YearMonth.now().getMonthValue();
					filterDropDown.setSelectedIndex(month - 1);

					String query = "select id, ref_id, transaction_date, type, services_cost, total , paid, balance, status, services, refund from transaction\r\n"
							+ "where (month(transaction_date) = " + month
							+ " and year(transaction_date) = 2021) and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedItem() == "All Transactions") {
					filterDropDown.setModel(new DefaultComboBoxModel<String>(filters[3]));
					all_transactions_pie_chart(all_transactions());
					repaint();

					String query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where status = 'Completed' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedItem() == "Services") {
					filterDropDown.setModel(new DefaultComboBoxModel<String>(services()));
					services_pie_chart(services_values(), services());
					repaint();

					String query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where services = '" + filterDropDown.getSelectedItem()
							+ "' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		filterDropDown.setBounds(900, 195, 500, 25);
		filterDropDown.setFont(robotoPlain14);
		filterDropDown.setFocusable(false);
		filterDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query;
				if (choicesDropDown.getSelectedIndex() == 0) {

					query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where status = '" + filterDropDown.getSelectedItem()
							+ "' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedIndex() == 1) {

					query = "select id, ref_id, transaction_date, type, services_cost, total , status, paid, balance, services, refund "
							+ "from transaction where refund > 0 and ref_id = " + Login.getCompanyId();
					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedIndex() == 2) {

					int month = filterDropDown.getSelectedIndex() + 1;

					query = "select id, ref_id, transaction_date, type, services_cost, total , paid, balance, status, services, refund from transaction\r\n"
							+ "where (month(transaction_date) = " + month
							+ " and year(transaction_date) = 2021) and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedIndex() == 3) {

					query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where status  like '%" + filterDropDown.getSelectedItem()
							+ "%' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else if (choicesDropDown.getSelectedIndex() == 4) {
					query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
							+ " from transaction where services = '" + filterDropDown.getSelectedItem()
							+ "' and ref_id = " + Login.getCompanyId();

					try {
						display_table(data_list(query), table, filterDropDown.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		this.add(choicesDropDown);
		this.add(filterDropDown);
	}

	private JTable table;
	private DefaultTableCellRenderer cellRenderer;

	void Table() {
		// column header
		String[] column = { "ID", "Date", "Type", "Services", "Services Cost", "Price", "Refund" };

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

			}
		});

		table.setModel(new DefaultTableModel(row, column));
		table.setRowHeight(40);
		table.setFillsViewportHeight(true);
		cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		table.setFont(robotoPlain12);
		table.setShowVerticalLines(false);
		table.getTableHeader().setFont(robotoBold10);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		// adding rows to the table
		String query = "select ref_id, id, transaction_date, type, services_cost, total , paid, balance, status, services, refund"
				+ " from transaction where status = 'Completed' and ref_id = " + Login.getCompanyId();

		try {
			display_table(data_list(query), table, filterDropDown.getSelectedItem());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// adding to the scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(900, 220, 500, 580);
		this.add(scrollPane);

	}

	JPanel statisticsPanel = new JPanel();

	void Panel() {

		statisticsPanel.setBounds(380, 195, 500, 605);
		statisticsPanel.setLayout(null);
		// statisticsPanel.setBackground(errorRed);

		this.add(statisticsPanel);
	}

	public BusinessStatsPanel() {
		this.setBounds(0, 0, 1420, 1080);
		this.setLayout(null);

		Label();
		TextFields();
		;
		Table();
		Panel();

		income_pie_chart(income_values());

	}

	public static ArrayList<SimpleTransaction> data_list(String query) throws SQLException {
		ArrayList<SimpleTransaction> simpleTransactionList = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			SimpleTransaction simpleTransaction;
			while (rs.next()) {

				simpleTransaction = new SimpleTransaction(rs.getInt("id"), rs.getInt("ref_id"),
						rs.getString("transaction_date"), rs.getString("type"), rs.getString("status"),
						rs.getString("services"), rs.getDouble("services_cost"), rs.getDouble("total"),
						rs.getDouble("paid"), rs.getDouble("balance"), rs.getDouble("refund"));
				simpleTransactionList.add(simpleTransaction);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return simpleTransactionList;
	}

	public static void display_table(ArrayList<SimpleTransaction> datalist, JTable table, Object filter)
			throws SQLException {
		ArrayList<SimpleTransaction> list = datalist;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[7];
		try {
			for (int i = 0; i < list.size(); i++) {

				row[0] = list.get(i).getId();
				row[1] = list.get(i).getTransaction_date();
				row[2] = list.get(i).getType();
				row[3] = list.get(i).getServices();
				row[4] = list.get(i).getServices_cost();
				row[5] = list.get(i).getPaid();
				row[6] = list.get(i).getRefund();

				model.addRow(row);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	Double[] services_values() {
		Double[] servicesValues = {};

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs;
			int id = Login.getCompanyId();
			String[] services = services();
			servicesValues = new Double[services.length];
			String[] query = new String[services.length];

			for (int i = 0; i < services.length; i++) {
				query[i] = "select sum(services_cost) from transaction where services = '" + services[i]
						+ "' and ref_id = " + id;
			}

			for (int i = 0; i < query.length; i++) {
				rs = statement.executeQuery(query[i]);
				rs.next();
				servicesValues[i] = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return servicesValues;
	}

	Double[] loses_values() {
		Double[] losesValues = new Double[12];

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs;
			int id = Login.getCompanyId();
			String[] query = new String[12];

			for (int i = 0; i < query.length; i++) {
				query[i] = "select sum(refund) from transaction where (month(transaction_date) = " + (i + 1)
						+ " and year(transaction_date) = 2021) and ref_id = " + id;
			}

			for (int i = 0; i < query.length; i++) {
				rs = statement.executeQuery(query[i]);
				rs.next();
				losesValues[i] = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return losesValues;
	}

	Integer[] monthly_bookings() {
		Integer[] monthlyBookings = new Integer[12];

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs;
			int id = Login.getCompanyId();
			String[] query = new String[12];

			for (int i = 0; i < query.length; i++) {
				query[i] = "select count(*) from transaction where (month(transaction_date) = " + (i + 1)
						+ " and year(transaction_date) = 2021) and ref_id = " + id;
			}

			for (int i = 0; i < query.length; i++) {
				rs = statement.executeQuery(query[i]);
				rs.next();
				monthlyBookings[i] = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return monthlyBookings;
	}

	Integer[] all_transactions() {
		Integer[] allTransactions = new Integer[2];

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			Statement statement = connection.createStatement();
			ResultSet rs;
			int id = Login.getCompanyId();
			String[] query = {
					"select count(*) from transaction where (status = 'Confirmed' or status = 'Completed' or status = 'Checked In') and ref_id = "
							+ id,
					"select count(*) from transaction where status like '%Cancelled%' and ref_id = " + id };

			for (int i = 0; i < query.length; i++) {
				rs = statement.executeQuery(query[i]);
				rs.next();
				allTransactions[i] = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allTransactions;
	}

	String[] services() {
		String[] services = null;

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel_management_system",
					"root", "465a7dhn");
			String query = "select * from services where ref_id = " + Login.getCompanyId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int size = size(query);

			services = new String[size];
			int i = 0;

			while (rs.next()) {
				services[i] = rs.getString("name");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return services;
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

	void income_pie_chart(Double[] income) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
		dataset.setValue("Completed Transactions", income[0]);
		dataset.setValue("Confirmed Transactions", income[1]);
		JFreeChart chart = ChartFactory.createPieChart("Hotel Income", dataset, true, true, true);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(0, 0, 500, 605);
		statisticsPanel.removeAll();
		statisticsPanel.add(frame);

	}

	void all_transactions_pie_chart(Integer[] transactions) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
		dataset.setValue("Uncancelled Transactions", transactions[0]);
		dataset.setValue("Cancelled Transactions", transactions[1]);
		JFreeChart chart = ChartFactory.createPieChart("All Transactions", dataset, true, true, true);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(0, 0, 500, 605);
		statisticsPanel.removeAll();
		statisticsPanel.add(frame);

	}

	void monthly_bookings_bar_chart(Integer[] month) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(month[0], "Booking", "Jan");
		dataset.setValue(month[1], "Booking", "Feb");
		dataset.setValue(month[2], "Booking", "Mar");
		dataset.setValue(month[3], "Booking", "Apr");
		dataset.setValue(month[4], "Booking", "May");
		dataset.setValue(month[5], "Booking", "Jun");
		dataset.setValue(month[6], "Booking", "Jul");
		dataset.setValue(month[7], "Booking", "Aug");
		dataset.setValue(month[8], "Booking", "Sep");
		dataset.setValue(month[9], "Booking", "Oct");
		dataset.setValue(month[10], "Booking", "Nov");
		dataset.setValue(month[11], "Booking", "Dec");
		JFreeChart chart = ChartFactory.createBarChart("Monthly Bookings", "Months", "No. of Bookings", dataset,
				PlotOrientation.VERTICAL, true, true, true);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(0, 0, 500, 605);
		statisticsPanel.removeAll();
		statisticsPanel.add(frame);

	}

	void loses_bar_chart(Double[] loses) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(loses[0], "Refunds", "Jan");
		dataset.setValue(loses[1], "Refunds", "Feb");
		dataset.setValue(loses[2], "Refunds", "Mar");
		dataset.setValue(loses[3], "Refunds", "Apr");
		dataset.setValue(loses[4], "Refunds", "May");
		dataset.setValue(loses[5], "Refunds", "Jun");
		dataset.setValue(loses[6], "Refunds", "Jul");
		dataset.setValue(loses[7], "Refunds", "Aug");
		dataset.setValue(loses[8], "Refunds", "Sep");
		dataset.setValue(loses[9], "Refunds", "Oct");
		dataset.setValue(loses[10], "Refunds", "Nov");
		dataset.setValue(loses[11], "Refunds", "Dec");
		JFreeChart chart = ChartFactory.createBarChart("Loses through Refunds", "Months", "Amount Refunded", dataset,
				PlotOrientation.VERTICAL, true, true, true);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(0, 0, 500, 605);
		statisticsPanel.removeAll();
		statisticsPanel.add(frame);

	}

	void services_pie_chart(Double[] services, String[] services_name) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();

		for (int i = 0; i < services_name.length; i++) {
			dataset.setValue(services_name[i], services[i]);
		}

		JFreeChart chart = ChartFactory.createPieChart("Services", dataset, true, true, true);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(0, 0, 500, 605);
		statisticsPanel.removeAll();
		statisticsPanel.add(frame);

	}

}
