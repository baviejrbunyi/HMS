package hms.objects;

public class AttendanceCombined {
	String name, last_login_date, last_logout_time;

	public AttendanceCombined(String name, String last_login_date, String last_logout_time) {
		super();
		this.name = name;
		this.last_login_date = last_login_date;
		this.last_logout_time = last_logout_time;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the last_login_date
	 */
	public String getLast_login_date() {
		return last_login_date;
	}

	/**
	 * @param last_login_date the last_login_date to set
	 */
	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	/**
	 * @return the last_logout_time
	 */
	public String getLast_logout_time() {
		return last_logout_time;
	}

	/**
	 * @param last_logout_time the last_logout_time to set
	 */
	public void setLast_logout_time(String last_logout_time) {
		this.last_logout_time = last_logout_time;
	}

}
