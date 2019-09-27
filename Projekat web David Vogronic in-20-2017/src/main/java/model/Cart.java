package model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {

	
	@Id
	private String id;
	
	private String customerId;
	
	private String delivererId;
	
	private String date;
	
	private double price;
	
	private List<String> items;
	
	
	
	private Status status;
	
	
	
	
	public Cart(){}

	public Cart(String id, List<String> items, String date, String customerId, String delivererId, Status status) {
		super();
		this.id = id;
		
		this.customerId = customerId;
		
		this.delivererId = delivererId;
		
		
		this.items = items;
		
		
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	public String getDelivererId() {
		return delivererId;
	}

	public void setDelivererId(String delivererId) {
		this.delivererId = delivererId;
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public Status getStatus() {
		return status;
	}
	
	
	
	

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
}
