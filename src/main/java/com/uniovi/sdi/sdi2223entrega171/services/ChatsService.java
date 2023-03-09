package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatsService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getChats(){
        List<Chat> chats = new ArrayList<>();
        chatRepository.findAll().forEach(chats::add);
        return chats;
    }

}
