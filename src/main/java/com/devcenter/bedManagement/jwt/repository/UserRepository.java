package com.devcenter.bedManagement.jwt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.AppUser;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	//To find the user using username
	AppUser findByUsername(String username);
	
	Boolean existsByUsername(String username);

}
