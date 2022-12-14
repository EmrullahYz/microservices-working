package com.example.rest.webservices.restfullwebservices.controller;

import com.example.rest.webservices.restfullwebservices.bean.User;
import com.example.rest.webservices.restfullwebservices.dao.UserDaoService;
import com.example.rest.webservices.restfullwebservices.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;


    @GetMapping(path = "/users")
    public List<User> getUsers(){
        return service.findAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id){
        User selectedUser = service.findOne(id);
        if(selectedUser == null)
            throw new UserNotFoundException("id :" + id);
        return selectedUser;
    }
    /* For Hateoas example*/
    @GetMapping(path = "/user/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        User selectedUser = service.findOne(id);
        if(selectedUser == null)
            throw new UserNotFoundException("id :" + id);
        EntityModel<User> entityModel = EntityModel.of(selectedUser);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable int id){
        service.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
