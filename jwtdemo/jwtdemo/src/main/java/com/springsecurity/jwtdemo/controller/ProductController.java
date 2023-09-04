package com.springsecurity.jwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwtdemo.models.ErrorClazz;
import com.springsecurity.jwtdemo.models.Product;
import com.springsecurity.jwtdemo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	// spring-boot-autoconfigure
	// -org.springframework.boot.autoconfigure.security.servlet ->
	// SpringBookWebSecurityConfiguration
	// spring-security-web -> org.springframework.security.web.access.intercept ->
	// AuthorizationFilter
	// spring-security-web->
	// org.springframework.security.web.authentication.ui->DefaultLoginPageGeneratingFilter
	@GetMapping("/allproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id,HttpServletRequest request) {

		Product product = productService.getProduct(id);
		if (product == null) {
			ErrorClazz
			errorClazz = new ErrorClazz(200, "Product doesnt exists");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.OK);
		} else
			return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/search/{name}")
	public Product getProduct(@PathVariable String name) {
		return productService.getProductByName(name);
	}

	@PostMapping("/addproduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product, HttpServletRequest request) {
		try {
					productService.saveProduct(product);
					return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(500, e.getMessage());
			if (e.getMessage().contains("name_unique"))
				errorClazz = new ErrorClazz(500, "Product name already exists..");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}

	@PutMapping("/update")
	public void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
	}
}
