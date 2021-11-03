package hms.objects;

import java.sql.Blob;

public class Employee {

	/**
	 * Properties of employee
	 */
	private int id, ref_id;
	private String name, role, username, birthday, telephone, email, last_login_date, last_login_time, last_logout_date,
			last_logout_time;
	private Blob image;

	public Employee(int id, String name, String role, String username, String birthday, String telephone, String email,
			Blob image) {

		this.id = id;
		this.name = name;
		this.role = role;
		this.username = username;
		this.birthday = birthday;
		this.telephone = telephone;
		this.email = email;
		this.image = image;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLast_logout_date() {
		return last_logout_date;
	}

	public void setLast_logout_date(String last_logout_date) {
		this.last_logout_date = last_logout_date;
	}

	public String getLast_logout_time() {
		return last_logout_time;
	}

	public void setLast_logout_time(String last_logout_time) {
		this.last_logout_time = last_logout_time;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
