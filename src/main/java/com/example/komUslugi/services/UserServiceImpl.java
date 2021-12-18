package com.example.komUslugi.services;

import com.example.komUslugi.data.dao.UserDao;
import com.example.komUslugi.data.domain.Role;
import com.example.komUslugi.data.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        Set<Role> roles = new HashSet<>();

        user.setRoles(roles);
        log.info(user.getPassword());
     //   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info(user.getLogin());
        // userDao.saveAndFlush(user);

    }

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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByLogin(s).get();
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        System.out.println("User is " + user.getLogin());
        return user;
    }
}
