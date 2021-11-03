package hms.objects;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class Room {

	// Properties of Room Object
	private int id, ref_id;
	private String number, type, address, occupied_from, occupied_to, status, description, rating;
	private Blob image;
	private ImageIcon hotelImg;
	private double rates;

	public Room(int id, String number, String type, String address, String occupied_from, String occupied_to,
			String status, String description, Blob image, Double rates, String rating) {

		this.id = id;
		this.number = number;
		this.type = type;
		this.address = address;
		this.occupied_from = occupied_from;
		this.occupied_to = occupied_to;
		this.status = status;
		this.description = description;
		this.image = image;
		this.rating = rating;
		this.rates = rates;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getoccupied_from() {
		return occupied_from;
	}

	public void setoccupied_from(String occupied_from) {
		this.occupied_from = occupied_from;
	}

	public String getoccupied_to() {
		return occupied_to;
	}

	public void setoccupied_to(String occupied_to) {
		this.occupied_to = occupied_to;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public ImageIcon getHotelImg() {
		return hotelImg;
	}

	public void setHotelImg(ImageIcon hotelImg) throws IOException, SQLException {
		this.hotelImg = hotelImg;
	}

	/**
	 * @return the rates
	 */
	public double getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(double rates) {
		this.rates = rates;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

}
