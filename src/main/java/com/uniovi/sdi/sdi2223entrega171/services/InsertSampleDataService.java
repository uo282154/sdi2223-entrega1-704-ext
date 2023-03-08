package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class InsertSampleDataService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;
    @PostConstruct
    public void init() {
        User user1 = new User("admin@gmail.com", "Admin", "Admin");
        user1.setPassword("admin");
        user1.setRole(rolesService.getRoles()[1]);
        User user2 = new User("test1@gmail.com", "1", "1");
        user2.setPassword("11111");
        user2.setRole(rolesService.getRoles()[0]);
        User user3 = new User("test2@gmail.com", "2", "2");
        user3.setPassword("22222");
        user3.setRole(rolesService.getRoles()[0]);
        User user4 = new User("test3@gmail.com", "3", "3");
        user4.setPassword("33333");
        user4.setRole(rolesService.getRoles()[0]);
        User user5 = new User("test4@gmail.com", "4", "4");
        user5.setPassword("44444");
        user5.setRole(rolesService.getRoles()[0]);

        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);

    }

}