package com.example.rest.webservices.restfullwebservices.controller;

import com.example.rest.webservices.restfullwebservices.bean.Post;
import com.example.rest.webservices.restfullwebservices.bean.User;
import com.example.rest.webservices.restfullwebservices.dao.UserDaoService;
import com.example.rest.webservices.restfullwebservices.exceptions.UserNotFoundException;
import com.example.rest.webservices.restfullwebservices.jpa.PostRepository;
import com.example.rest.webservices.restfullwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PostRepository postRepository;


    @GetMapping(path = "/jpa/users")
    public List<User> getUsers(){
        return repository.findAll();
    }
    /* For Hateoas example*/
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        Optional<User> selectedUser = repository.findById(id);
        if(selectedUser.isEmpty())
            throw new UserNotFoundException("id :" + id);
        EntityModel<User> entityModel = EntityModel.of(selectedUser.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id){
        repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrievePostsByUserId(@PathVariable int id){
        Optional<User> selectedUser = repository.findById(id);
        if(selectedUser.isEmpty())
            throw new UserNotFoundException("id :" + id);
        return selectedUser.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> selectedUser = repository.findById(id);
        if(selectedUser.isEmpty())
            throw new UserNotFoundException("id :" + id);
        post.setUser(selectedUser.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
