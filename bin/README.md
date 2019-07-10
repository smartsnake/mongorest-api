## mongorest

RESTful API that interacts with a MongoDB database. Implemented in Java with Spring Boot and Maven.  The server assumes it is accessing a database named `invoiceTest` on `localhost:27017` without login credentials required (MongoDB defaults).  The collections must be named `invoice` and `item` respectively.  Note the structure of the POJO's `Invoice.java` and `Item.java` for the expected DB schema. Sorry, there might be some IntelliJ IDEA specific stuff left out of .gitignore.
  
 # Example HTTP requests via cURL
 ## List All Invoices
`curl -i -s -X GET http://localhost:8080/invoice/`
 ## Get Invoice By MongoDB _id
`curl -i -s -X GET http://localhost:8080/invoice/{id}`  
