package com.example.komUslugi.bootstrap;


import com.example.komUslugi.data.dao.*;
import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.Role;
import com.example.komUslugi.data.domain.User;
import com.example.komUslugi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
@Slf4j
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    ZpDao zpDao;
    @Autowired
    RoleDao roleDao;
    ZhiletsDao zhiletsDao;
    UpravOrgDao upravOrgDao;
    @Autowired
    ServicesDao servicesDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String password;
    public void init(){
        HashSet<Role> roles = new HashSet<>();

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setUsers(new HashSet<>());
        roles.add(role);
        roleDao.saveAndFlush(role);




        User user = new User();
        user.setLogin("admin");
        password = UUID.randomUUID().toString().substring(0,8);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);
        userDao.saveAndFlush(user);

        role = new Role();
        role.setName("ROLE_USER");
        role.setUsers(new HashSet<>());
        roleDao.saveAndFlush(role);


        Komm_uslugi usluga = new Komm_uslugi();
        usluga.setVidUslug("Проверка водосчетчиков");
        usluga.setStoimostUslug(500);

        servicesDao.saveAndFlush(usluga);

        usluga = new Komm_uslugi();
        usluga.setVidUslug("Сантехнические работы");
        usluga.setStoimostUslug(600);

        servicesDao.saveAndFlush(usluga);

        usluga = new Komm_uslugi();
        usluga.setVidUslug("Установка, замена, проверка счетчиков тепла");
        usluga.setStoimostUslug(200);

        servicesDao.saveAndFlush(usluga);

        usluga = new Komm_uslugi();
        usluga.setVidUslug("Установка, замена, проверка счетчиков тепла");
        usluga.setStoimostUslug(200);

        servicesDao.saveAndFlush(usluga);

        usluga = new Komm_uslugi();
        usluga.setVidUslug("Установка, замена, проверка энергообогревателей");
        usluga.setStoimostUslug(200);

        servicesDao.saveAndFlush(usluga);

        usluga = new Komm_uslugi();
        usluga.setVidUslug("Электромонтажные работы");
        usluga.setStoimostUslug(1200);

        servicesDao.saveAndFlush(usluga);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Bootstrap init");
        if(userDao.findQAll().size()==0)
        init();
        log.info("Database is not found. Creating bootstrap data. Creating new admin user. Admin password = "+ password+ " login: admin");
    }
}
