package com.example.rest.webservices.restfullwebservices.jpa;

import com.example.rest.webservices.restfullwebservices.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
