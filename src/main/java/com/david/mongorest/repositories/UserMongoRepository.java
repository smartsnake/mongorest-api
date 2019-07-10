package com.david.mongorest.repositories;

  import com.david.mongorest.models.User;
  import org.springframework.data.mongodb.repository.MongoRepository;
  import org.springframework.data.rest.core.annotation.RepositoryRestResource;
  import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
//@CrossOrigin(origins = "https://mongorestfrontend.azurewebsites.net/")
public interface UserMongoRepository extends MongoRepository<User, String>{
    User findByid(String id);
    User findByUsername(String username);
}
