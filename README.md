## mongorest

RESTful API that interacts with a MongoDB database. Implemented in Java with Spring Boot and Maven.  The server assumes it is accessing a database named `invoiceTest` on `localhost:27017` without login credentials required (MongoDB defaults).  The collection of invoice documents inside `invoiceTest` must be named `invoice`.  Note the structure of the POJO's `Invoice.java` and `Item.java` for the expected DB schema. Sorry, there might be some IntelliJ IDEA specific stuff left out of .gitignore.
  
 # Example HTTP requests via cURL
 
 ## List All Invoices
`curl -i -s -X -k GET http://localhost:8080/invoice/`
 ## Get Invoice By MongoDB _id
`curl -i -s -X -k GET http://localhost:8080/invoice/{id}` 
 ## Create Invoice
 `curl -i -s -H -k "Content-Type: application/json" -X POST -d '{Invoice JSON payload}' http://localhost:8080/invoice/`
 ## Modify Invoice By MongoDB _id
 `curl -i -s -H -k "Content-Type: application/json" -X PUT -d '{Invoice JSON payload}' http://localhost:8080/invoice/{id}`
 ## Delete Invoice
 `curl -i -s -X -k DELETE http://localhost:8080/invoice/{id}`
 ## Search by customer-id
 `curl -i -s -X -k GET http://localhost:8080/invoice/searchByCustomerID?customerID={desired id}`
 ## Filter by total sale price (less than target)
 `curl -i -s -X -k GET http://localhost:8080/invoice/totalSaleLessThan?totalSale={sale target}`
 ## Filter by total sale price (greater than target)
 `curl -i -s -X -k GET http://localhost:8080/invoice/totalSaleGreaterThan?totalSale={sale target}`
