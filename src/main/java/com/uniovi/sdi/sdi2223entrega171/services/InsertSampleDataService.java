package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
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

    @Autowired
    private  OffersService offersService;

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


        Offer offer1 = new Offer("Oferta 1", "Oferta para los más entusiastas", 40, user1);
        offer1.setStatus(Offer.STATUS_SOLD);
        offer1.setBuyer(user2);
        Offer offer2 = new Offer("Oferta 2", "Oferta para los más locos", 50, user2);
        offer2.setStatus(rolesService.getOfferStatus()[0]);
        Offer offer3 = new Offer("Oferta 3", "Oferta tímida", 22, user3);
        offer3.setStatus(rolesService.getOfferStatus()[0]);
        //Offer offer4 = new Offer("Oferta 4", "Ofertón, sin más", 10);
        //offer4.setStatus(rolesService.getOfferStatus()[0]);

        offersService.addOffer(offer1);
        offersService.addOffer(offer2);
        offersService.addOffer(offer3);
    }

}