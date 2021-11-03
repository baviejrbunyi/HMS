package hms.objects;

import java.sql.Blob;

public class Services {
	private int id, ref_id;
	private String name, details;
	private Double rates;
	private Blob image;

	public Services(int id, int ref_id, String name, String details, Double rates, Blob blob) {
		super();
		this.id = id;
		this.ref_id = ref_id;
		this.name = name;
		this.details = details;
		this.rates = rates;
		this.image = blob;
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the rates
	 */
	public Double getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(Double rates) {
		this.rates = rates;
	}

	/**
	 * @return the image
	 */
	public Blob getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

}
