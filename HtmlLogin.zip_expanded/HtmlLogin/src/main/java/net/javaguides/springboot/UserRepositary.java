package net.javaguides.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepositary extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	
//	@Modifying
//    @Query("UPDATE User u SET u.lastName = :lastName,u.firstName = :firstName. u.email = :email WHERE u.id = ?1")
//    User editPage(String email, String firstName, String lastName);
	
}
