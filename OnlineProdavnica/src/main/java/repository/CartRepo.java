package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import model.Cart;

public interface CartRepo extends MongoRepository<Cart, String> {

	@Query("{'status' : 'DELIVERED'}")
	List<Cart> findDeliveredCarts();
	
	@Query("{'status' : 'CANCELED'}")
	List<Cart> findCanceledCarts();
}
