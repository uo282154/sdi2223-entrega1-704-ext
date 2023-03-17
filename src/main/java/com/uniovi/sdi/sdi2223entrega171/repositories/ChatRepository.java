package com.uniovi.sdi.sdi2223entrega171.repositories;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

    @Query("SELECT c from Chat c WHERE c.sender = ?1 and c.receiver = ?2 and c.offer = ?3")
    Chat findChatBySenderAndReceiverAndOffer(User sender, User receiver, Offer offer);

    @Query("SELECT c from Chat c WHERE c.offer = ?1 and c.sender = ?2")
    Chat findChatByOfferAndSender(Offer offer, User user);

    @Query("SELECT c from Chat c WHERE c.sender = ?1 or c.receiver = ?1")
    List<Chat> findChatBySenderOrReceiver(User user);
}
