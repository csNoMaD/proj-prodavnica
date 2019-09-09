package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private Role role;
	private String phoneNumber;
	private String email;
	private String adress;
	
	//for custemer
	private List<String> purchasedItems = new ArrayList<String>();
	private List<String> selectedItems = new ArrayList<String>();
	private List<String> favourites = new ArrayList<String>();
	
	//for deliverer
	private List<String> orderList = new ArrayList<String>();
	
	public User(){
		
	}

	public User(String id, String userName, String password, String name, String surname, Role role, String phoneNumber,
			String email, String adress, List<String> purchasedItems, List<String> selectedItems,
			List<String> favourites, List<String> orderList) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.adress = adress;
		this.purchasedItems = purchasedItems;
		this.selectedItems = selectedItems;
		this.favourites = favourites;
		this.orderList = orderList;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<String> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(List<String> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	public List<String> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public List<String> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<String> favourites) {
		this.favourites = favourites;
	}

	public List<String> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<String> orderList) {
		this.orderList = orderList;
	}
	
	
	
	
}
