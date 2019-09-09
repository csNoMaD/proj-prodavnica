package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Cart;
import model.Product;
import model.User;
import repository.CartRepo;
import repository.ProductRepo;
import repository.UserRepo;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductRepo pr;
	
	@Autowired
	CartRepo cr;
	
	@Autowired
	UserRepo ur;
	
	@RequestMapping(value="/showAll" , method = RequestMethod.GET)
	public String allProduct(Model m) {
		List<Product> products = pr.findAll();
		
		m.addAttribute("products", products);
		return "index.jsp";
	}
	
	@RequestMapping(value="/showCategory" , method = RequestMethod.GET)
	public String allCategory(Model m) {
		List<Product> products = pr.findAll();
		List<String> categories = new ArrayList<String>();
		for(Product p : products) {
			if(!categories.contains(p.getCategory()))
				categories.add(p.getCategory());
		}
		m.addAttribute("categories", categories);
		return "index.jsp";
	}
	
	@RequestMapping(value="/sortName" , method = RequestMethod.GET)
	public String sortedByName(Model m) {
		List<Product> products = pr.findAll();
		products.sort((Product p1, Product p2)->p1.getName().compareTo(p2.getName()));
		m.addAttribute("products", products);
		return "index.jsp";
	}
	
	@RequestMapping(value="/productOfCategory/{category}" , method = RequestMethod.GET)
	public String productOfCategory(Model m, @PathVariable String category) {
		List<Product> products = pr.findAll();
		List<Product> productsOfCategory = new ArrayList<Product>();
		for(Product p:products) {
			if(p.getCategory().equals(category)) {
				productsOfCategory.add(p);
			}
				
		}
		m.addAttribute("products", productsOfCategory);
		return "index.jsp";
	}
	

	
	@RequestMapping(value="/addProduct" , method = RequestMethod.POST)
	public String addProduct(String name, String description, double price, int quantity, String category) {
		Product p =new Product( name,  description,  price,  quantity, category);
		pr.insert(p);
		return "index.jsp";
	}
	

	@RequestMapping(value = "/removeProduct/{productId}", method = RequestMethod.GET)
	public String removeProduct(Model m, @PathVariable String productId) {

		Product p = pr.findById(productId).get();
		pr.delete(p);
		m.addAttribute("message", "Product deleted!");

		return "index.jsp";
	}
	
	@RequestMapping(value = "/editProduct/{productId}", method = RequestMethod.POST)
	public String editProduct(Model m, @PathVariable String productId, String name, String description, double price,
			int quantity, String category) {

		Product p = pr.findById(productId).get();

		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setCategory(category);

		pr.save(p);

		m.addAttribute("message", "Product changed!");
		return "index.jsp";
	}
	
	@RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
	public String editProduct(Model m, @PathVariable String productId) {

		Product p = pr.findById(productId).get();

	
		m.addAttribute("p", p);
		return "index.jsp";
	}
	
	// Create report
		@RequestMapping(value="/createReportForMonth" , method = RequestMethod.GET)
		public String reportForMonth(Model m) throws ParseException {

			Date date;
			List<Cart> deliveredCarts= cr.findDeliveredCarts();
			List<Cart> canceledCarts = cr.findCanceledCarts();
			List<String> namesCustomer= new ArrayList<String>();
			double profit = 0;
			List<Cart> cartsForLastMonth = new ArrayList<Cart>();
			for(Cart c:deliveredCarts) {
				//preuzmemo string date i vratimo ga u java.util.Date format
				String s=c.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				// oduzmemo od trenutnog datuma 1 mesec
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date oneMonthBeforeToday = cal.getTime();
				if(date.after(oneMonthBeforeToday)) {
					cartsForLastMonth.add(c);
					for(User u : ur.findAll()) {
						if(c.getCustomerId().equals(u.getId())) {
							namesCustomer.add(u.getName());
						}
					}
					profit +=c.getPrice();
				}	
			}
			
			for(Cart c1:canceledCarts) {
				String s=c1.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date oneMonthBeforeToday = cal.getTime();
				
				if(date.after(oneMonthBeforeToday)) {
					cartsForLastMonth.add(c1);
					for(User u : ur.findAll()) {
						if(c1.getCustomerId().equals(u.getId())){
							namesCustomer.add(u.getName());
						}
					}
				}	
				
			}
			
			m.addAttribute("cartsForReport", cartsForLastMonth);
			m.addAttribute("namess", namesCustomer);
			m.addAttribute("profit", profit);
			
			return "index.jsp";
		}
		
		@RequestMapping(value="/createReportForWeek" , method = RequestMethod.GET)
		public String reportForWeek(Model m) throws ParseException {
			Date date;
			List<Cart> deliveredCarts= cr.findDeliveredCarts();
			List<Cart> canceledCarts = cr.findCanceledCarts();
			List<String> namesCustomer= new ArrayList<String>();
			double profit = 0;
			List<Cart> cartsForLastWeek = new ArrayList<Cart>();
			for(Cart c:deliveredCarts) {
				String s=c.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date oneWeekBeforeToday = cal.getTime();
				
				if(date.after(oneWeekBeforeToday)) {
					cartsForLastWeek.add(c);
					for(User u : ur.findAll()) {
						if(c.getCustomerId().equals(u.getId())) {
							namesCustomer.add(u.getName());
						}
					}
					profit +=c.getPrice();
				}	
			}
			
			for(Cart c1:canceledCarts) {
				String s=c1.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date oneWeekBeforeToday = cal.getTime();
				
				if(date.after(oneWeekBeforeToday)) {
					cartsForLastWeek.add(c1);
					for(User u : ur.findAll()) {
						if(c1.getCustomerId().equals(u.getId())) {
							namesCustomer.add(u.getName());
						}
					}
				}	
				
			}
			
			m.addAttribute("cartsForReport", cartsForLastWeek);
			m.addAttribute("namess", namesCustomer);
			m.addAttribute("profit", profit);			
			return "index.jsp";
		}
		
		@RequestMapping(value="/createReportForToday" , method = RequestMethod.GET)
		public String reportForToday(Model m) throws ParseException {
			Date date;
			List<Cart> deliveredCarts= cr.findDeliveredCarts();
			List<Cart> canceledCarts = cr.findCanceledCarts();
			List<String> namesCustomer= new ArrayList<String>();
			double profit = 0;
			List<Cart> cartsForToday = new ArrayList<Cart>();
			for(Cart c:deliveredCarts) {
				String s=c.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				Date yesterday = cal.getTime();
				
				if(date.after(yesterday)) {
					cartsForToday.add(c);

					for(User u : ur.findAll()) {
						if(c.getCustomerId().equals(u.getId())) {
							namesCustomer.add(u.getName());
						}
					}
					profit +=c.getPrice();
				}	
			}
			
			for(Cart c1:canceledCarts) {
				String s=c1.getDate();
				date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(s);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				Date yesterday = cal.getTime();
				
				if(date.after(yesterday)) {
					cartsForToday.add(c1);
					for(User u : ur.findAll()) {
						if(c1.getCustomerId().equals(u.getId())) {
							namesCustomer.add(u.getName());
						}
					}
				}	
				
			}
			
			m.addAttribute("cartsForReport", cartsForToday);
			m.addAttribute("namess", namesCustomer);
			m.addAttribute("profit", profit);
			return "index.jsp";
		}
		
		//NA POPUSTU
		@RequestMapping(value = "/productsOnSale", method = RequestMethod.GET)
		public String productsOnSale(Model m) {

			List<Product> products= pr.findProductsOnSale();
			m.addAttribute("products", products);

			return "index.jsp";
		}
		
}
