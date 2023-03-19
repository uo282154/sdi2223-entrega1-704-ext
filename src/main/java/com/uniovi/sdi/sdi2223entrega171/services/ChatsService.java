package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.entities.Message;
import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.repositories.ChatRepository;
import com.uniovi.sdi.sdi2223entrega171.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatsService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public List<Chat> getChats(){
        List<Chat> chats = new ArrayList<>();
        chatRepository.findAll().forEach(chats::add);
        return chats;
    }

    public Chat getChatBySenderAndReceiverAndOffer(User sender, User receiver, Offer offer){
        return chatRepository.findChatBySenderAndReceiverAndOffer(sender, receiver, offer);
    }

    public Chat findChatByOfferAndSender(Offer offer, User user){
        return chatRepository.findChatByOfferAndSender(offer, user);
    }

    public List<Chat> findChatBySenderOrReceiver(User user){
        return chatRepository.findChatBySenderOrReceiver(user);
    }

    public void addChat(Chat chat, String text) {
        Message msg = new Message(text, LocalDateTime.now(), chat, userDetailsService.getActiveUser());
        chat.addMessage(msg);
        messageRepository.save(msg);
        if(chatRepository.findById(chat.getId()).isEmpty()){
            chatRepository.save(chat);
        }
    }

}
