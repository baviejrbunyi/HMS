package hms.objects;

public class Attendance {

	// Properties of Attendance
	private int id, ref_id;
	private String username, last_login_date, last_login_time, last_logout_time;

	public Attendance(int id, String username, String last_login_date, String last_login_time,
			String last_logout_time) {

		this.id = id;
		this.username = username;
		this.last_login_date = last_login_date;
		this.last_login_time = last_login_time;
		this.last_logout_time = last_logout_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRef_id() {
		return ref_id;
	}

	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_logout_time() {
		return last_logout_time;
	}

	public void setLast_logout_time(String last_logout_time) {
		this.last_logout_time = last_logout_time;
	}

}
