package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.OffersService;
import com.uniovi.sdi.sdi2223entrega171.services.UserDetailsServiceImpl;
import com.uniovi.sdi.sdi2223entrega171.services.UsersService;
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
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class OfferController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private OfferAddFormValidator offerAddFormValidator;


    /**
     * Devuelve una lista con todas las ofertas
     * @param model
     * @param pageable
     * @param principal
     * @return
     */
    @RequestMapping("offer/my")
    public String getList(Model model, Pageable pageable, Principal principal){
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());

        offerPage = offersService.getMyOffers(pageable, userDetailsService.getActiveUser());
        model.addAttribute("myOffersList", offerPage.getContent());
        model.addAttribute("page", offerPage);

        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/my";
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
            model.addAttribute("user", userDetailsService.getActiveUser());
            return "offer/add";
        }
        offer.setCreator( userDetailsService.getActiveUser());
        offer.setCreateAt(LocalDate.now());
        offersService.addOffer(offer);
        //model.addAttribute("user", userDetailsService.getActiveUser());
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
        model.addAttribute("offer", new Offer());
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

        if(offersService.ableToDelete(id, userDetailsService.getActiveUser())){
            offersService.deleteOffer(id);
        }

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
    User user=userDetailsService.getActiveUser();
        if (searchText != null && !searchText.isEmpty()){
            //offerPage = offersService.getAllOffersByTitle(pageable, searchText);
            offerPage=offersService.getAllExceptUserOnesByTitle(pageable,user,searchText);
        }
        else{
            //offerPage = offersService.getAllOffers(pageable);
            offerPage=offersService.getAllExceptUserOnes(pageable,user);
        }
        model.addAttribute("offersList", offerPage.getContent());
        model.addAttribute("page", offerPage);

        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/listAll";
    }


    @RequestMapping("/offer/buy/{id}")
    public String buyOffer(@PathVariable Long id,Model model){
        model.addAttribute("user", userDetailsService.getActiveUser());


        User activeUser=userDetailsService.getActiveUser();
        //Boolean[] errors=new Boolean[3];
        Boolean[] errors=new Boolean[] {false,false,false};
        errors[0]=offersService.notEnoughMoney(activeUser,id);
        errors[1]=offersService.isBuyed(id);
        errors[2]=offersService.isMine(id,activeUser);
        model.addAttribute("offer",offersService.getOfferById(id));

        if(errors[0] || errors[1] || errors[2]){
            model.addAttribute("errors",errors);
            return "offer/notBought";
        }

        offersService.buyOffer(id,activeUser);

        return "offer/bought";
    }

    @RequestMapping("/offer/myBoughts")
    public String getListBoughts(Model model, Pageable pageable, Principal principal){
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());
        offerPage = offersService.getBoughtsOfBuyer(pageable, userDetailsService.getActiveUser());


        for(Offer o: offerPage)
            o.setCreatorEmail(usersService.getUser(o.getBuyer().getId()).getEmail());


        model.addAttribute("myBoughtsList", offerPage.getContent());
        model.addAttribute("page", offerPage);
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/myBoughts";
    }

    @RequestMapping("offer/listFavs")
    public String getListFav(Model model){
        User user=userDetailsService.getActiveUser();
        model.addAttribute("offersList", user.getFavs());
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/listFavs";
    }

    @RequestMapping("offers/addFav/{id}")
    public String getAddFav(Model model,@PathVariable Long id){

        User user=userDetailsService.getActiveUser();
        Offer o=offersService.getOfferById(id);
        if(!o.getCreator().getEmail().equals(user.getEmail()) && o.getStatus().equals(Offer.STATUS_ACTIVE) ){
            Set<Offer> favs=user.getFavs();
            favs.add(o);
            user.setFavs(favs);
            usersService.updateUser(user);
            model.addAttribute("offersList", user.getFavs());
            model.addAttribute("user", userDetailsService.getActiveUser());
            return "offer/listFavs";
        }
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "redirect:/offer/listAll";
    }

    @RequestMapping("offers/deleteFav/{id}")
    public String getDeleteFav(Model model,@PathVariable Long id){

        User user=userDetailsService.getActiveUser();
        Set<Offer> favs=user.getFavs();
        favs.remove(offersService.getOfferById(id));
        user.setFavs(favs);
        usersService.updateUser(user);

        model.addAttribute("offersList", user.getFavs());
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "offer/listFavs";
    }


}
