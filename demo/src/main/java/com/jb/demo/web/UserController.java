package com.jb.demo.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.demo.domain.Product;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/{userId}")
	public String displayUser(@PathVariable int userId) {
		return "User ID: " + userId;
	}
	
	@RequestMapping("/{id}/invoices")
	public String displayUserInvoices(@PathVariable("id") int userId, 
			@RequestParam(value="d", required=false) Date dateOrNull) {
		return "Invoice found for user: " + userId + " on the date: " + dateOrNull;
	}
	
	@RequestMapping("/{userId}/items")
	public List<String> displayStringJson(){
		return Arrays.asList("Shoes", "Laptop","Button");
	}
	
	@RequestMapping("/{userId}/products_as_json") //javascript object notation
	public List<Product> displayProductsJson(){
		return Arrays.asList(new Product(1,"Shoes", 42.99),
				new Product(2,"Laptop", 1042.99),
				new Product(3,"Buttons", 2.99));
	}
}
