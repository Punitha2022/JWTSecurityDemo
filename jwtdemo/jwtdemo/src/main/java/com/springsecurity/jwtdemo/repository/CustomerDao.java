package com.springsecurity.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.jwtdemo.models.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	 Customer findByEmail(String email);
}
