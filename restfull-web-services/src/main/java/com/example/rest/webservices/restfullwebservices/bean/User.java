package com.example.rest.webservices.restfullwebservices.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @JsonProperty("user_name")
    @Size(min=2, message="name must be more than 2 characters")
    private String userName;
    @Past(message = "birthday must be past time")
    @JsonProperty("birth_day")
    private LocalDate birthDay;

    @OneToMany (mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
}
