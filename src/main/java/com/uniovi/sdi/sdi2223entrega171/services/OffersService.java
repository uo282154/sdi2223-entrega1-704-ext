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

    public Page<Offer> getAllOffers(Pageable pageable){
        return offerRepository.findAll(pageable);
    }

    public Page<Offer> getAllOffersByTitle(Pageable pageable, String searchText){
        Page<Offer> offerPage = new PageImpl<Offer>(new LinkedList<Offer>());
        searchText = "%" + searchText + "%";
        offerPage = offerRepository.searchByTitle(pageable, searchText);
        return offerPage;
    }

    public void addOffer(Offer offer){
        offerRepository.save(offer);
    }

    public List<Offer> getMyOffers(User user) {
        return offerRepository.findMyOffers(user);
    }

    public List<Offer> getSoldOffers(User user) {
        return offerRepository.findSoldOffers(user);
    }
}
