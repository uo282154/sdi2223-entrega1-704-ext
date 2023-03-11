package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.OffersService;
import com.uniovi.sdi.sdi2223entrega171.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;

    /**
     * Devuelve una lista con todas las ofertas
     * @param model
     * @param pageable
     * @param principal
     * @param searchText
     * @return
     */
    @RequestMapping("offer/list")
    public String getList(Model model, Pageable pageable, Principal principal,
                          @RequestParam(value ="", required = false) String searchText){
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());

        if (searchText != null && !searchText.isEmpty()){
            offerPage = offersService.getAllOffersByTitle(pageable, searchText);
        }
        else{
            offerPage = offersService.getAllOffers(pageable);
        }
        model.addAttribute("offersList", offerPage.getContent());
        model.addAttribute("page", offerPage);

        //UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        //User activeUser = userDetailsService.getActiveUser(model);

        //List<Offer> offerListSelled = offersService.getSoldOffers(activeUser);
        //model.addAttribute("offersSelled", offerListSelled);

        //List<Offer> offersMyList = offersService.getMyOffers(activeUser);
        //model.addAttribute("myOffers", offersMyList);

        return "offer/list";
    }

}