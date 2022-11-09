package com.example.rest.webservices.restfullwebservices.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
