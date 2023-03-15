package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.OffersService;
import com.uniovi.sdi.sdi2223entrega171.services.UserDetailsServiceImpl;
import com.uniovi.sdi.sdi2223entrega171.validators.OfferAddFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class OfferController {




    @Autowired
    private OffersService offersService;



    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private OfferAddFormValidator offerAddFormValidator;


    /**
     * Devuelve una lista con todas las ofertas
     * @param model
     * @param pageable
     * @param principal
     * @param searchText
     * @return
     */
    @RequestMapping("offer/my")
    public String getList(Model model, Pageable pageable, Principal principal,
                          @RequestParam(value ="", required = false) String searchText){
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());

        if (searchText != null && !searchText.isEmpty()){
            offerPage = offersService.getMyOffersByTitle(pageable, searchText, userDetailsService.getActiveUser());
        }
        else{
            offerPage = offersService.getMyOffers(pageable, userDetailsService.getActiveUser());
        }
        model.addAttribute("offersList", offerPage.getContent());
        model.addAttribute("page", offerPage);

        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/list";
    }

    /**
     * post del add para añadir nuevas ofertas
     * @param offer
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String add(@Validated Offer offer, BindingResult result, Model model) {

        offerAddFormValidator.validate(offer,result);
        if(result.hasErrors()) {
            return "offer/add";
        }

        offersService.addOffer(offer);
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "redirect:/offer/my";
    }

    /**
     *
     * get del add para añadir nuevas ofertas
     * @param model
     * @return
     */
    @RequestMapping(value = "/offer/add", method = RequestMethod.GET)
    public String add(Model model) {
        Offer o=new Offer();
        o.setCreator( userDetailsService.getActiveUser());
        model.addAttribute("offer", o);
        model.addAttribute("user", userDetailsService.getActiveUser());

        return "offer/add";
    }

    /**
     * Elimina la oferta indicada
     * @param id
     * @return
     */
    @RequestMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable Long id){


        offersService.deleteOffer(id);
        return "redirect:/offer/my";
    }

    /**
     * Devuelve una lista con todas las ofertas
     * @param model
     * @param pageable
     * @param principal
     * @param searchText
     * @return
     */
    @RequestMapping("offer/listAll")
    public String getListAll(Model model, Pageable pageable, Principal principal,
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

        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/listAll";
    }


}
