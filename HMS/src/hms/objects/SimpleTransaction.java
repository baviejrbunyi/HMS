package hms.objects;

public class SimpleTransaction {

	private int id, ref_id;
	private String transaction_date, type, status, services;
	private Double services_cost, total, paid, balance, refund;

	public SimpleTransaction(int id, int ref_id, String transaction_date, String type, String status, String services,
			Double services_cost, Double total, Double paid, Double balance, Double refund) {
		super();
		this.id = id;
		this.ref_id = ref_id;
		this.transaction_date = transaction_date;
		this.type = type;
		this.status = status;
		this.services = services;
		this.services_cost = services_cost;
		this.total = total;
		this.paid = paid;
		this.balance = balance;
		this.refund = refund;
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
	 * @return the services_cost
	 */
	public Double getServices_cost() {
		return services_cost;
	}

	/**
	 * @param services_cost the services_cost to set
	 */
	public void setServices_cost(Double services_cost) {
		this.services_cost = services_cost;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the paid
	 */
	public Double getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(Double paid) {
		this.paid = paid;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the refund
	 */
	public Double getRefund() {
		return refund;
	}

	/**
	 * @param refund the refund to set
	 */
	public void setRefund(Double refund) {
		this.refund = refund;
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

}
