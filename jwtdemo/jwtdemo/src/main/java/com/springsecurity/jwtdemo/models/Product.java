package com.springsecurity.jwtdemo.models;

import java.util.Objects;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private int quantity;
private double price;

public Product() {
	super();
	// TODO Auto-generated constructor stub
}
public Product(String name, int quantity, double price) {
	super();
	this.name = name;
	this.quantity = quantity;
	this.price = price;
}
public Product(int id, String name, int quantity, double price) {
	super();
	this.id = id;
	this.name = name;
	this.quantity = quantity;
	this.price = price;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
@Override
public int hashCode() {
	return Objects.hash(id, name, price, quantity);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	return id == other.id && Objects.equals(name, other.name)
			&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
}

}
