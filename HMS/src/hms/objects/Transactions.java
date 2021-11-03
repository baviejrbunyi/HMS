package hms.objects;

public class Transactions {

	// Properties of every Transactions
	private int id, ref_id, room_id, guest_ref, staff_id;
	private String transaction_date, staff, name, address, email, age, mobile, nationality, method, type, services,
			check_in, check_out, number, payment_status, confirmed, company, status;;
	private double total, paid, balance, services_cost, rate_per_night;

	public Transactions(int id, int ref_id, int room_id, int guest_ref, int staff_id, String transaction_date,
			String staff, String name, String address, String email, String age, String mobile, String nationality,
			String method, String type, String services, String check_in, String check_out, String number,
			String payment_status, String confirmed, String company, String status, double total, double paid,
			double balance, double services_cost, double rate_per_night) {
		this.id = id;
		this.ref_id = ref_id;
		this.room_id = room_id;
		this.guest_ref = guest_ref;
		this.staff_id = staff_id;
		this.transaction_date = transaction_date;
		this.staff = staff;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
		this.mobile = mobile;
		this.nationality = nationality;
		this.method = method;
		this.type = type;
		this.services = services;
		this.check_in = check_in;
		this.check_out = check_out;
		this.number = number;
		this.payment_status = payment_status;
		this.confirmed = confirmed;
		this.company = company;
		this.status = status;
		this.total = total;
		this.paid = paid;
		this.balance = balance;
		this.services_cost = services_cost;
		this.rate_per_night = rate_per_night;
	}

	/**
	 * @return the services_cost
	 */
	public double getServices_cost() {
		return services_cost;
	}

	/**
	 * @param services_cost the services_cost to set
	 */
	public void setServices_cost(double services_cost) {
		this.services_cost = services_cost;
	}

	/**
	 * @return the rate_per_night
	 */
	public double getRate_per_night() {
		return rate_per_night;
	}

	/**
	 * @param rate_per_night the rate_per_night to set
	 */
	public void setRate_per_night(double rate_per_night) {
		this.rate_per_night = rate_per_night;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ref_id
	 */
	public int getRef_id() {
		return ref_id;
	}

	/**
	 * @param ref_id the ref_id to set
	 */
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}

	/**
	 * @return the room_id
	 */
	public int getRoom_id() {
		return room_id;
	}

	/**
	 * @param room_id the room_id to set
	 */
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	/**
	 * @return the guest_ref
	 */
	public int getGuest_ref() {
		return guest_ref;
	}

	/**
	 * @param guest_ref the guest_ref to set
	 */
	public void setGuest_ref(int guest_ref) {
		this.guest_ref = guest_ref;
	}

	/**
	 * @return the staff_id
	 */
	public int getStaff_id() {
		return staff_id;
	}

	/**
	 * @param staff_id the staff_id to set
	 */
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	/**
	 * @return the transaction_date
	 */
	public String getTransaction_date() {
		return transaction_date;
	}

	/**
	 * @param transaction_date the transaction_date to set
	 */
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	/**
	 * @return the staff
	 */
	public String getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(String staff) {
		this.staff = staff;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the services
	 */
	public String getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * @return the check_in
	 */
	public String getCheck_in() {
		return check_in;
	}

	/**
	 * @param check_in the check_in to set
	 */
	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}

	/**
	 * @return the check_out
	 */
	public String getCheck_out() {
		return check_out;
	}

	/**
	 * @param check_out the check_out to set
	 */
	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the payment_status
	 */
	public String getPayment_status() {
		return payment_status;
	}

	/**
	 * @param payment_status the payment_status to set
	 */
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	/**
	 * @return the confirmed
	 */
	public String getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the paid
	 */
	public double getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(double paid) {
		this.paid = paid;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

}
