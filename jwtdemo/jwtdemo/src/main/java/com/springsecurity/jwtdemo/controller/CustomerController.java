package com.springsecurity.jwtdemo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwtdemo.config.JWTTokenHelper;
import com.springsecurity.jwtdemo.models.Customer;
import com.springsecurity.jwtdemo.models.LoginCredentials;
import com.springsecurity.jwtdemo.responses.UserInfo;
import com.springsecurity.jwtdemo.service.CustomerUserDetailsService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private CustomerUserDetailsService userDetailsService;

	@PostMapping("/public/login")
	public ResponseEntity<?> login(@RequestBody LoginCredentials lc) 
			throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println("Email " + lc.getEmail());
        System.out.println("password " + lc.getPassword());
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(lc.getEmail(),
						lc.getPassword()));
		Collection<? extends GrantedAuthority> authorities=
				authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		String jwtToken=jWTTokenHelper.generateToken(lc.getEmail(),authorities);		
		return ResponseEntity.ok(jwtToken);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		UserDetails userDetails= 
				userDetailsService.loadUserByUsername(user.getName());
		return ResponseEntity.ok(userDetails);
		
		
		
	}
}
