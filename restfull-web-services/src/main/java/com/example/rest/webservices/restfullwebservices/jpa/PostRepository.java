package com.example.rest.webservices.restfullwebservices.jpa;

import com.example.rest.webservices.restfullwebservices.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
