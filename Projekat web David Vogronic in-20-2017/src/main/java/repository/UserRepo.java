package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
	
	@Query("{'password' : ?0 , 'userName' : ?1}")
	List<User> findUserByPasswordAndUserName(String password,String userName);

}
