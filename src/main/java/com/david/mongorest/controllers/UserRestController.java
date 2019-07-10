package com.david.mongorest.controllers;

  import com.david.mongorest.models.User;
  import com.david.mongorest.repositories.UserMongoRepository;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.core.userdetails.UserDetailsService;
  import org.springframework.security.core.userdetails.UsernameNotFoundException;
  import org.springframework.web.bind.annotation.*;

  import java.util.List;

@RestController
@RequestMapping(value = "/user/")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController implements UserDetailsService

{
    @Autowired
    private UserMongoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Here add user data layer fetching from the MongoDB.
          I have used userRepository*/
        User user = repository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }else{
            UserDetails details = new SecUserDetails().loadUserByUsername(user);
            return details;
        }
    }

    //return list of all users
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = repository.findAll();
        if (userList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    //get specific user by _id
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        User user = repository.findByid(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    //get specific user by _id
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    //create new user
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User saveInv) {
        return new ResponseEntity<>(repository.save(saveInv), HttpStatus.CREATED);
    }

    //delete user
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        repository.delete(repository.findByid(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
