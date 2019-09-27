package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	
	@Id
	private String id;
	
	private int quantity;
	
	private double price;
	
	private String category;
	
	private String name;
	
	
	
	
	private String description;
	
	
	
	
	
	public Product(){}

	public Product(String name, String description, double price, int quantity, String category) {
		super();
		
		this.quantity = quantity;
		
		this.price = price;
		
		this.category = category;
		
		
		this.name = name;
		
		this.description = description;
		
		
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	

	

	
	
	
	

}
