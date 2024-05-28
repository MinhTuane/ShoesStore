package shoesStore.model;

import java.sql.Date;


public class Product {
	private int id;
	private String name;
	private double cost;
	private byte[] image;
	private Date date;
	private long sold;
	private double rate;
	
	
	public Product(int id, String name, double cost, byte[] image, Date date,long sold,double rate) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.image = image;
		this.date = date;
		this.sold = sold;
		this.rate =rate;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public long getSold() {
		return sold;
	}
	public void setSold(long sold) {
		this.sold = sold;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
