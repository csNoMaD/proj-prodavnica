package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Cart;
import model.Product;
import model.Role;
import model.Status;
import model.User;
import repository.CartRepo;
import repository.ProductRepo;
import repository.UserRepo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserRepo ur;

	@Autowired
	CartRepo cr;

	@Autowired
	ProductRepo pr;

	@RequestMapping(value = "/insert", method = RequestMethod.POST) //registracija
	public String saveUser(String userName, String password, String name, String surname, String phoneNumber,
			String email, String adress) {

		User user = new User();

		user.setAdress(adress);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setPhoneNumber(phoneNumber);
		user.setRole(Role.CUSTOMER);

		ur.insert(user);

		return "index.jsp";
	}

	

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String getUserByUsername(Model m, String userName, String password, HttpSession session) {
		User u = null;
		List<User> users = ur.findUserByPasswordAndUserName(password, userName);
		try {
			u = users.get(0);

		} catch (Exception e) {
			System.out.println("Wrong username or password!");
		}

		if (u == null) {
			m.addAttribute("message", "Wrong username or password!");
		} else {
			session.setAttribute("loggedUser", u);
			m.addAttribute("loggedUser", u);
		}

		return "index.jsp";
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.setAttribute("loggedUser", null);
		return "index.jsp";
	}
	
	@RequestMapping(value = "/addDeliverer", method = RequestMethod.POST)
	public String addDeliverer(String userName, String password, String name, String surname, String phoneNumber,
			String email, String adress) {

		User user = new User();

		user.setAdress(adress);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setPhoneNumber(phoneNumber);
		user.setRole(Role.DELIVERER);

		ur.insert(user);

		return "index.jsp";
	}
	
	@RequestMapping(value = "/addToFavourites/{userId}/{productId}", method = RequestMethod.GET)
	public String addToFavourites(Model m, @PathVariable String userId, @PathVariable String productId) {

		User u = ur.findById(userId).get();
		u.getFavourites().add(productId);
		ur.save(u);

		return "index.jsp";
	}
	
	
	@RequestMapping(value = "/showFavourites/{userId}", method = RequestMethod.GET)
	public String favourites(Model m, @PathVariable String userId) {

		List<String> itemsIds = ur.findById(userId).get().getFavourites();
		List<Product> favouriteProducts = new ArrayList<Product>();

		for (String id : itemsIds) {
			Product p = pr.findById(id).get();
			favouriteProducts.add(p);

		}

		m.addAttribute("products", favouriteProducts);

		return "index.jsp";
	}

	@RequestMapping(value = "/buy/{userId}", method = RequestMethod.GET)
	public String crateCart(@PathVariable String userId) {
		User user = ur.findById(userId).get();
		List<String> selectedItems = user.getSelectedItems();
		user.setSelectedItems(new ArrayList<String>());
		double price = 0;

		for (String id : selectedItems) {
			Product p = pr.findById(id).get();
			price += p.getPrice();
		}
		Cart c = new Cart();
		c.setCustomerId(userId);
		c.setItems(selectedItems);
		c.setDate(new Date().toString());
		c.setDelivererId(null); 					// ne zna se jos ko ce biti dostavljac
		c.setStatus(Status.PURCHASED);
		c.setPrice(price);

		List<String> newPurchasedItems = user.getPurchasedItems();
		for (String item : selectedItems) {
			newPurchasedItems.add(item);
		}
		user.setPurchasedItems(newPurchasedItems);

		cr.insert(c);
		ur.save(user);

		return "index.jsp";
	}

	
	

	@RequestMapping(value = "/showPurechesedProducts/{userId}", method = RequestMethod.GET)
	public String showPurchasedProducts(Model m, @PathVariable String userId) {

		List<String> itemsIds = ur.findById(userId).get().getPurchasedItems();
		List<Product> products = new ArrayList<Product>();
		for (String id : itemsIds) {
			try {
				Product p = pr.findById(id).get();
				products.add(p);

			} catch (Exception e) {
				
			}
		}
		m.addAttribute("purchasedItems", products);

		return "index.jsp";
	}

	
	

	@RequestMapping(value = "/showUser/{userId}", method = RequestMethod.GET)
	public String getAllUsers(Model m, @PathVariable String userId) {

		User user = ur.findById(userId).get();
		m.addAttribute("user", user);

		if (user.getRole() == Role.CUSTOMER) {

			List<String> purchasedItemsiDs = user.getPurchasedItems();
			List<Product> purchasedItems = new ArrayList<Product>();
			for (String id : purchasedItemsiDs) {
				purchasedItems.add(pr.findById(id).get());
			}
			m.addAttribute("purchasedItems", purchasedItems);

		} else if (user.getRole() == Role.DELIVERER) {

			List<String> productsIds = user.getOrderList();
			List<Product> orderedList = new ArrayList<Product>();
			for (String id : productsIds) {
				orderedList.add(pr.findById(id).get());
			}
			m.addAttribute("orderedList", orderedList);
		}

		return "";
	}
	
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String getAllUsers(Model m) {

		List<User> users = ur.findAll();
		m.addAttribute("users", users);

		return "index.jsp";
	}
	
	@RequestMapping(value = "/myOrders/{userId}", method = RequestMethod.GET)
	public String myOrders(Model m, @PathVariable String userId) {

		User user = ur.findById(userId).get();
		List<Cart> carts = new ArrayList<Cart>();

		for (String id : user.getOrderList()) {
			Cart c = cr.findById(id).get();
			if (c.getStatus() == Status.DELIVERI_IN_PROGERSS)
				carts.add(c);

		}

		List<String> namesCustomer = new ArrayList<String>();

		for (Cart c : carts) {

			namesCustomer.add(ur.findById(c.getCustomerId()).get().getName());

		}

	@RequestMapping(value = "/getSelectedItems/{userId}", method = RequestMethod.GET)
	public String getSelected(Model m, @PathVariable String userId) {

		User user = ur.findById(userId).get();
		m.addAttribute("user", user);

		List<String> selectedItemsIds = user.getSelectedItems();
		List<Product> selectedItems = new ArrayList<Product>();
		for (String id : selectedItemsIds) {
			selectedItems.add(pr.findById(id).get());
		}
		m.addAttribute("selectedItems", selectedItems);

		return "";
	}

	@RequestMapping(value = "/getFavourites/{userId}", method = RequestMethod.GET)
	public String getFavourites(Model m, @PathVariable String userId) {

		User user = ur.findById(userId).get();
		m.addAttribute("user", user);

		List<String> favouritesIds = user.getFavourites();
		List<Product> favourites = new ArrayList<Product>();
		for (String id : favouritesIds) {
			favourites.add(pr.findById(id).get());
		}
		m.addAttribute("favourites", favourites);

		return "";
	}

	@RequestMapping(value = "/getCarts", method = RequestMethod.GET)
	public String getCarts(Model m) {

		List<Cart> carts = cr.findAll();
		List<Cart> retCarts = new ArrayList<Cart>();
		List<String> namesCustomer = new ArrayList<String>();

		for (Cart c : carts) {
			if (c.getStatus() == Status.PURCHASED) {
				retCarts.add(c);
				namesCustomer.add(ur.findById(c.getCustomerId()).get().getName());
			}

		}

		m.addAttribute("cartss", retCarts);
		m.addAttribute("names", namesCustomer);

		return "index.jsp";
	}

	

	@RequestMapping(value = "/selectProduct/{productId}/{userId}", method = RequestMethod.GET)
	public String addProductToSelectedList(@PathVariable String userId, @PathVariable String productId) {

		User u = ur.findById(userId).get();
		u.getSelectedItems().add(productId);
		ur.save(u);

		return "index.jsp";
	}

	
	@RequestMapping(value = "/selectAsDelivered/{cartId}", method = RequestMethod.GET)
	public String deliveredProduct(@PathVariable String cartId) {

		Cart cart = cr.findById(cartId).get();

		cart.setStatus(Status.DELIVERED);
		cr.save(cart);

		return "index.jsp";
	}
	
	// preuzmi porudzbinu
		@RequestMapping(value = "/takeOrder/{cartId}/{userId}", method = RequestMethod.GET)
		public String takeOrder(@PathVariable String cartId, @PathVariable String userId) {

			Cart cart = cr.findById(cartId).get();
			User user = ur.findById(userId).get();

			user.getOrderList().add(cart.getId());
			cart.setDelivererId(userId);
			cart.setStatus(Status.DELIVERI_IN_PROGERSS);

			cr.save(cart);
			ur.save(user);

			return "index.jsp";
		}

	// zameni ulogu
	@RequestMapping(value = "/changeRole/{userId}/{role}", method = RequestMethod.GET)
	public String changeRole(@PathVariable String userId, @PathVariable String role) {

		User user = ur.findById(userId).get();

		if (role.equals("ADMIN"))
			user.setRole(Role.ADMIN);
		if (role.equals("CUSTOMER"))
			user.setRole(Role.CUSTOMER);
		if (role.equals("DELIVERER"))
			user.setRole(Role.DELIVERER);

		ur.save(user);

		return "index.jsp";
	}

	

	

		m.addAttribute("myOrder", carts);
		m.addAttribute("names", namesCustomer);
		return "index.jsp";
	}

	@RequestMapping(value = "/deliveredOrder/{userId}", method = RequestMethod.GET)
	public String deliveredOrder(Model m, @PathVariable String userId) {

		User user = ur.findById(userId).get();
		List<Cart> carts = new ArrayList<Cart>();

		for (String id : user.getOrderList()) {
			Cart c = cr.findById(id).get();
			if (c.getStatus() == Status.DELIVERED)
				carts.add(c);

		}

		List<String> namesCustomer = new ArrayList<String>();

		for (Cart c : carts) {

			namesCustomer.add(ur.findById(c.getCustomerId()).get().getName());

		}

		m.addAttribute("deliveredOrder", carts);
		m.addAttribute("names", namesCustomer);
		return "index.jsp";
	}

	// otkazi porudzbinu
	@RequestMapping(value = "/cancelOrder/{cartId}/{userId}", method = RequestMethod.GET)
	public String cancelOrder(@PathVariable String cartId, @PathVariable String userId) {

		Cart cart = cr.findById(cartId).get();
		User user = ur.findById(userId).get();

		user.getOrderList().remove(cart.getId());
		cart.setStatus(Status.CANCELED);

		cr.save(cart);
		ur.save(user);

		return "index.jsp";
	}

	// obrisi korisnika ---> koristi se i za brisanje dostavljaca
	@RequestMapping(value = "/removeUser/{userId}", method = RequestMethod.GET)
	public String removeUser(Model m, @PathVariable String userId) {

		User u = ur.findById(userId).get();
		ur.delete(u);
		m.addAttribute("message", "User deleted!");

		return "index.jsp";
	}

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public String getUser(Model m, @PathVariable String userId) {

		User u = ur.findById(userId).get();

		m.addAttribute("u", u);

		return "index.jsp";
	}

	// Sluzi za izmenu dostavljaca
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.POST)
	public String editUser(@PathVariable String userId, String userName, String password, String name, String surname,
			String phoneNumber, String email, String adress) {

		User user = ur.findById(userId).get();

		user.setAdress(adress);
		user.setEmail(email);
		user.setName(name);
		user.setSurname(surname);
		user.setPhoneNumber(phoneNumber);
		user.setRole(Role.DELIVERER);

		ur.save(user);

		return "index.jsp";
	}

	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	public String searchProduct(Model m, String name, String description, String price) {

		List<Product> products = pr.findAll();
		List<Product> searchProducts = new ArrayList<Product>();
		String s = "";

		for (Product p : products) {
			if (!p.getName().toLowerCase().trim().contains(name.toLowerCase().trim()) && !name.equals(""))
				continue;
			if (!p.getDescription().toLowerCase().trim().contains(description.toLowerCase().trim())
					&& !description.equals(""))
				continue;

			try {
				if(p.getPrice() != Double.parseDouble(price)) {
					continue;
				}
			} catch (Exception e) {
				
							}
			

			searchProducts.add(p);

		}

		m.addAttribute("searchProducts", searchProducts);
		System.out.println(s);
		return "index.jsp";
	}

}
