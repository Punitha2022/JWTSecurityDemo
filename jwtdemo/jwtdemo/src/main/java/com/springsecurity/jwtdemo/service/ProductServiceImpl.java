package com.springsecurity.jwtdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.jwtdemo.models.Product;
import com.springsecurity.jwtdemo.repository.ProductDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	//Field Injection
    @Autowired
	private ProductDao productDao;
	
	//Constructor Injection
    @Autowired
	public ProductServiceImpl(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAllProducts() {
	
		return productDao.findAll();
	}

	@Override
	public Product getProduct(int id) {
	Optional<Product> productOptional=	productDao.findById(id);
	
	if(productOptional.isPresent())
		return productOptional.get();
	else
		return null;
	}

	@Override
	public Product getProductByName(String name) {
	 return	productDao.findByName(name);
	}

	@Override
	public void saveProduct(Product product) {
		productDao.save(product);
		
	}

	@Override
	public void deleteProduct(int id) {
		productDao.deleteById(id);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDao.save(product);
	}

	@Override
	public double findPriceByName(String name) {
		
		Optional<Double> priceOpt= productDao.findPriceByName(name);
		if(priceOpt.isPresent())
			return priceOpt.get();
		else
			return 0.0;
	}

}
