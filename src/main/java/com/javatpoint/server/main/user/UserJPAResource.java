package com.javatpoint.server.main.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
public class UserJPAResource 
{
@Autowired
private UserDaoService service;
//instance of user repository
@Autowired
private UserRepository userRepository;
//instance of post repository
@Autowired
private PostRepository postRepository;
//Retrieving all user from the userRepository
@GetMapping("/jpa/users")
public List<User> retriveAllUsers()
{
return userRepository.findAll();
}

/*
//retrieves a specific user detail
@GetMapping("/jpa/users/{id}")
public User retriveUser(@PathVariable int id)
{
User user= service.findOne(id);
if(user==null)
//runtime exception
throw new UserNotFoundException("id: "+ id);
return user;
}
*/
@GetMapping("/jpa/users/{id}")
public Resource<User> retriveUser(@PathVariable int id)
{
Optional<User> user= userRepository.findById(id);
if(!user.isPresent())
//runtime exception
throw new UserNotFoundException("id: "+ id);
//"all-users", SERVER_PATH + "/users"
//retrieveAllUsers
Resource<User> resource=new Resource<User>(user.get());	//constructor of Resource class
//add link to retrieve all the users
ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retriveAllUsers());
resource.add(linkTo.withRel("all-users"));
return resource;
}
//method that delete a user resource
@DeleteMapping("/jpa/users/{id}")
public void deleteUser(@PathVariable int id)
{
userRepository.deleteById(id);
}
//method that posts a new user detail and returns the status of the user resource
@PostMapping("/jpa/users")
public ResponseEntity<Object> createUser(@Valid @RequestBody User user)	
{
User sevedUser=service.save(user);	
URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId()).toUri();
return ResponseEntity.created(location).build();
}
//Retrieving all posts of a specific user
@GetMapping("/jpa/users/{id}/posts")
public List<Post> retriveAllUsers(@PathVariable int id)
{
Optional<User> userOptional= userRepository.findById(id);
if(!userOptional.isPresent())
{
throw new UserNotFoundException("id: "+ id);
}
return userOptional.get().getPosts();
}
//creating a post for the specific  user
@PostMapping("/jpa/users/{id}/posts")
public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post)	
{
Optional<User> userOptional= userRepository.findById(id);
if(!userOptional.isPresent())
{
throw new UserNotFoundException("id: "+ id);
}
//getting a user variable
User user=userOptional.get();	
//map the user to the post
post.setUser(user);
//save post to the database
postRepository.save(post);
//getting the path of the post and append id of the post to the URI 
URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
//returns the location of the created post
return ResponseEntity.created(location).build();
}
}