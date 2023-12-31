package com.uniovi.sdi.sdi2223entrega171.repositories;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1))")
    Page<Offer> searchByTitle(Pageable pageable, String searchText);

    @Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1) and LOWER(o.creator) LIKE LOWER(?2))")
    Page<Offer> searchByTitleAndUser(Pageable pageable, String searchText, User user);
    Page<Offer> findAll(Pageable pageable);

    @Query("SELECT o FROM Offer o WHERE ( LOWER(o.status) = LOWER(?1) AND o.buyer = ?2)")
    Page<Offer> searchByBuyer(Pageable pageable, String status, User buyer);

    @Query("SELECT o FROM Offer o WHERE o.creator = ?1")
    Page<Offer> findMyOffers(Pageable pageable, User user);

    @Query("SELECT o FROM Offer o WHERE o.buyer = ?1")
    List<Offer> findSoldOffers(User user);

    @Query("SELECT o FROM Offer o WHERE o.title = ?1")
    Offer findOfferByTitle(String title);

    @Query("SELECT o FROM Offer o WHERE o.creator != ?1")
    Page<Offer> findAllExceptUserOnes(Pageable pageable,User user);

    @Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?2)) and o.creator!=?1")
    Page<Offer> searchAllExceptUserOnesByTitle(Pageable pageable, User user,String searchText);

    @Query("UPDATE Offer set buyer=?2, status='SOLD' WHERE id=?1")
    void setOfferBuyed(Long id, User activeUser);
}
