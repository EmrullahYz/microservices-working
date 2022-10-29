package com.example.rest.webservices.restfullwebservices.dao;

import com.example.rest.webservices.restfullwebservices.bean.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Eve", LocalDate.now().minusYears(32)));
        users.add(new User(++userCount,"Jhon", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Clark", LocalDate.now().minusYears(18)));
    }

    public List<User> findAllUsers(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public void saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
    }
}
