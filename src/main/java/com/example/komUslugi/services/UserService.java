package com.example.komUslugi.services;



import com.example.komUslugi.data.domain.User;

import java.util.List;

public interface UserService {
    User findByLogin(String login) ;
    List<User> findAll();
    void saveUser(User user);
}
