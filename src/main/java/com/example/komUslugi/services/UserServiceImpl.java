package com.example.komUslugi.services;

import com.example.komUslugi.data.dao.UserDao;
import com.example.komUslugi.data.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findQAll();
    }

    @Override
    public User findByLogin(String login) {
        List<User> list = findAll();
        for (User user : list) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        System.out.println("User is " + user.getLogin());
        return user;
    }
}
