package com.david.mongorest.controllers;

import com.david.mongorest.models.Invoice;
import com.david.mongorest.models.Item;
import com.david.mongorest.repositories.InvoiceMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
//@RequestMapping(value = "/invoice/")
//@CrossOrigin(origins = "http://mongorestfrontend.herokuapp.com")//https://mongorestfrontend.azurewebsites.net/
@Validated
public class InvoiceRestController {
	@Autowired
    private InvoiceMongoRepository repository;

	//return list of all invoices
	@RequestMapping(value = "/invoice/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllInvoices()
	{
		List<Invoice> inv = repository.findAll();
		if(inv == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(inv,HttpStatus.OK);
		}
	}

	//get specific invoice by _id
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInvoiceById(@PathVariable("id") String id) {
		Invoice inv = repository.findByid(id);
		if(inv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(inv,HttpStatus.OK);
		}
	}
	
	//create new invoice
	@RequestMapping(value = "/invoice/", method = RequestMethod.POST)
	public ResponseEntity<?> saveInvoice(@Valid @RequestBody Invoice saveInv) {
		int totalSale = 0;
		saveInv.setTotalSale(0);
		for (Item item : saveInv.getItems()) {
			totalSale += (item.getSellPrice() * item.getQuantity());
		}
		saveInv.setTotalSale(totalSale);
		
		return new ResponseEntity<>(repository.save(saveInv), HttpStatus.CREATED);
	}

	//modify invoice
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> modifyInvoiceByID(@PathVariable("id") String id, @RequestBody Invoice saveInv) {
		Invoice orgInv = repository.findByid(id);
		if(orgInv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		int totalSale = 0;
		saveInv.setTotalSale(0);
		for (Item item : saveInv.getItems()) {
			totalSale += (item.getSellPrice() * item.getQuantity());
		}
		saveInv.setTotalSale(totalSale);
		saveInv.setid(id);
		
		return new ResponseEntity<>(repository.save(saveInv), HttpStatus.OK);
	}

	//delete invoice
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInvoice(@PathVariable("id") String id) {
		repository.delete(repository.findByid(id));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}




	//search by customer ID
	@RequestMapping(value = "/invoice/searchByCustomerID", method = RequestMethod.GET)
	public ResponseEntity<?> searchByCustomerID(@RequestParam(name = "customerID") int customerID) {
		List<Invoice> inv = repository.findByCustomerID(customerID);
		if(inv == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(inv,HttpStatus.OK);
		}
	}

	//return list of invoices with sales less than a number
	@RequestMapping(value = "/invoice/totalSaleLessThan", method = RequestMethod.GET)
	public ResponseEntity<?> getSalesLessThan(@RequestParam(name = "totalSale") int totalSale) {
		List<Invoice> inv = repository.findByTotalSaleLessThan(totalSale);
		
		if(inv == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(inv,HttpStatus.OK);
		}
		
	}

	//return list of invoices with sales greater than a number
	@RequestMapping(value = "/invoice/totalSaleGreaterThan", method = RequestMethod.GET)
	public ResponseEntity<?> getSalesGreaterThan(@RequestParam(name = "totalSale") int totalSale) {
		
		List<Invoice> inv = repository.findByTotalSaleGreaterThan(totalSale);
		
		if(inv == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(inv,HttpStatus.OK);
		}
	}

	//
	//Security / Login stuff
	//
//	@RequestMapping(value = "/login")
//	public boolean login(@RequestBody User user ) {
//		return user.getUsername().equals("Username") && user.getPassword().equals("Password");
//	}
//
//	@RequestMapping("/user")
//	public Principal user(HttpServletRequest request) {
//		String authToken = request.getHeader("Authorization")
//				.substring("Basic".length()).trim();
//		return () ->  new String(Base64.getDecoder()
//				.decode(authToken)).split(":")[0];
//	}
}