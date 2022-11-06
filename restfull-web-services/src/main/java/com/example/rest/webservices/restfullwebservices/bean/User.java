package com.example.rest.webservices.restfullwebservices.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    @JsonProperty("user_name")
    @Size(min=2, message="name must be more than 2 characters")
    private String name;
    @Past(message = "birthday must be past time")
    @JsonProperty("birth_day")
    private LocalDate birthDay;
}
