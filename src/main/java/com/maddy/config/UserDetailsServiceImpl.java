package com.maddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.maddy.dao.UserRepository;
import com.maddy.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Fetch User from Data base
		User userByUserName = userRepository.getUserByUserName(username);
		
		if (null == userByUserName || userByUserName.getUserName().isEmpty() ) {
            throw new UsernameNotFoundException("Username not found");
        }
		System.out.println("User Details on UserDetailsServiceImpl"+userByUserName);
		CustomUserDetails customUserDetails =new CustomUserDetails(userByUserName);
		return customUserDetails;
	}

}
