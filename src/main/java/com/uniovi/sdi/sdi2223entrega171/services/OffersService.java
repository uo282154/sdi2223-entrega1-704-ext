package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OffersService {

    @Autowired
    private OfferRepository offerRepository;

    public Page<Offer> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    public Page<Offer> getMyOffersByTitle(Pageable pageable, String searchText, User user) {
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());
        searchText = "%" + searchText + "%";
        System.out.println(user);
        offerPage = offerRepository.searchByTitleAndUser(pageable, searchText, user);
        return offerPage;
    }

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public Page<Offer> getAllOffersByTitle(Pageable pageable, String searchText) {
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());
        searchText = "%" + searchText + "%";
        offerPage = offerRepository.searchByTitle(pageable, searchText);
        return offerPage;
    }

    public Page<Offer> getMyOffers(Pageable pageable, User user) {
        return offerRepository.findMyOffers(pageable, user);
    }

    public List<Offer> getSoldOffers(User user) {
        return offerRepository.findSoldOffers(user);
    }

    public Offer getOfferByTitle(String title) {
        return offerRepository.findOfferByTitle(title);
    }

    public void deleteOffer(Long id) {

        offerRepository.deleteById(id);
    }

    public Page<Offer> getAllExceptUserOnes(Pageable pageable, User user) {
        return offerRepository.findAllExceptUserOnes(pageable,user);
    }

    public Page<Offer> getAllExceptUserOnesByTitle(Pageable pageable, User user, String searchText) {
        return offerRepository.searchAllExceptUserOnesByTitle(pageable, user,"%" + searchText + "%");
    }

    public boolean ableToDelete(Long id, User activeUser) {
        Offer o= offerRepository.findById(id).get();
        return o.getCreator().getId()==activeUser.getId();
    }

    public Offer getOffer(Long id){
        return offerRepository.findById(id).get();
    }
}
