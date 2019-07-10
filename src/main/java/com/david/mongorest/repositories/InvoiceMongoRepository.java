package com.david.mongorest.repositories;

import com.david.mongorest.models.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
//@CrossOrigin(origins = "http://mongorestfrontend.herokuapp.com")//https://mongorestfrontend.azurewebsites.net/
public interface InvoiceMongoRepository extends MongoRepository<Invoice, String>{
	Invoice findByid(String id);
	List<Invoice> findByCustomerID(@Param("customerID") int customerID);
	List<Invoice> findByTotalSaleLessThan(@Param("totalSale") int totalSale);
	List<Invoice> findByTotalSaleGreaterThan(@Param("totalSale") int totalSale);
}
