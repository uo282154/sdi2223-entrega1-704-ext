package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
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

    @Autowired
    private  LogService logService;

    @PostConstruct
    public void init() {
        User user1 = new User("admin@gmail.com", "Admin", "Admin");
        user1.setPassword("admin");
        user1.setRole(rolesService.getRoles()[1]);
        User user2 = new User("user01@gmail.com", "user01", "user01");
        user2.setPassword("user01");
        user2.setRole(rolesService.getRoles()[0]);
        User user3 = new User("user02@gmail.com", "user02", "user02");
        user3.setPassword("user02");
        user3.setRole(rolesService.getRoles()[0]);
        User user4 = new User("user03@gmail.com", "user03", "user03");
        user4.setPassword("user03");
        user4.setRole(rolesService.getRoles()[0]);
        User user5 = new User("user04@gmail.com", "user04", "user04");
        user5.setPassword("user04");
        user5.setRole(rolesService.getRoles()[0]);
        User user6 = new User("user05@gmail.com", "user05", "user05");
        user6.setPassword("user05");
        user6.setRole(rolesService.getRoles()[0]);
        User user7 = new User("user06@gmail.com", "user06", "user06");
        user7.setPassword("user06");
        user7.setRole(rolesService.getRoles()[0]);
        User user8 = new User("user07@gmail.com", "user07", "user07");
        user8.setPassword("user07");
        user8.setRole(rolesService.getRoles()[0]);
        User user9 = new User("user08@gmail.com", "user08", "user08");
        user9.setPassword("user08");
        user9.setRole(rolesService.getRoles()[0]);
        User user10 = new User("user09@gmail.com", "user09", "user09");
        user10.setPassword("user09");
        user10.setRole(rolesService.getRoles()[0]);
        User user11 = new User("user10@gmail.com", "user10", "user10");
        user11.setPassword("user10");
        user11.setRole(rolesService.getRoles()[0]);
        User user12 = new User("user11@gmail.com", "user11", "user11");
        user12.setPassword("user11");
        user12.setRole(rolesService.getRoles()[0]);
        User user13 = new User("user12@gmail.com", "user12", "user12");
        user13.setPassword("user12");
        user13.setRole(rolesService.getRoles()[0]);
        User user14 = new User("user13@gmail.com", "user13", "user13");
        user14.setPassword("user13");
        user14.setRole(rolesService.getRoles()[0]);
        User user15 = new User("user14@gmail.com", "user14", "user14");
        user15.setPassword("user14");
        user15.setRole(rolesService.getRoles()[0]);

        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
        usersService.addUser(user7);
        usersService.addUser(user8);
        usersService.addUser(user9);
        usersService.addUser(user10);
        usersService.addUser(user11);
        usersService.addUser(user12);
        usersService.addUser(user13);
        usersService.addUser(user14);
        usersService.addUser(user15);


        Offer offer1 = new Offer("Oferta 1", "Oferta para los más entusiastas", 40, user1);
        offer1.setStatus(Offer.STATUS_SOLD);
        Offer offer4_a = new Offer("Ofertaza", "Ofertaza increible", 100, user1);
        offer1.setStatus(Offer.STATUS_ACTIVE);
        offer1.setBuyer(user2);
        Offer offer2 = new Offer("Oferta 2", "Oferta para los más locos", 50, user2);
        offer2.setStatus(rolesService.getOfferStatus()[0]);
        Offer offer3 = new Offer("Oferta 3", "Oferta tímida", 22, user3);
        offer3.setStatus(rolesService.getOfferStatus()[0]);
        //Offer offer4 = new Offer("Oferta 4", "Ofertón, sin más", 10);
        //offer4.setStatus(rolesService.getOfferStatus()[0]);
        Offer offer5_a = new Offer("Oferta 5a", "Oferta cinco a", 150, user3);
        offer3.setStatus(rolesService.getOfferStatus()[0]);

        offersService.addOffer(offer1);
        offersService.addOffer(offer2);
        offersService.addOffer(offer3);

        logService.addLog(Log.LogItemType.PET, "Log n1");
        logService.addLog(Log.LogItemType.LOGIN_ERR, "Log n2");
        logService.addLog(Log.LogItemType.LOGOUT, "Log n3");
        offersService.addOffer(offer4_a);
        offersService.addOffer(offer5_a);
    }

}