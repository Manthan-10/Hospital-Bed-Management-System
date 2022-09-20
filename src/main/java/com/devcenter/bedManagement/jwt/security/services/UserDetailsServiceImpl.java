package com.devcenter.bedManagement.jwt.security.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.AppUser;
import com.devcenter.bedManagement.jwt.repository.UserRepository;
/**
 * Service class
 * @author MN097789
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	AppUser user;
	
	/**
	 * Function finds the user by username
	 * @param username
	 * @return Returns userdetails
	 */
	@Override 
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		
		try {
			 user = userRepository.findByUsername(username);
		}
		catch(UsernameNotFoundException e) {
			logger.error("User Not Found: {}", e); 
		}
		
		return UserDetailsImpl.build(user);
	}  

}
