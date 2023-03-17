package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostConstruct
    public void init() {
    }
    private final HttpSession httpSession;
    @Autowired
    public UsersService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }


    public User getUser(Long id) {

        return usersRepository.findById(id).get();
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void updateMoney(User user){
        User user2 = getUser(user.getId());
        user2.setMoney(user.getMoney());
        usersRepository.save(user2);
    }

    public void updateUser(User user) {
        User user2 = getUser(user.getId());
        user2.setName(user.getName());
        user2.setSurname(user.getSurname());
        user2.setEmail(user.getEmail());
        usersRepository.save(user2);
    }

    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }



    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
