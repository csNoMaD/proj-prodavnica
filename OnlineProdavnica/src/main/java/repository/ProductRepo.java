package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import model.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

	@Query("{'category' : 'SALE'}")
	List<Product> findProductsOnSale();

}
