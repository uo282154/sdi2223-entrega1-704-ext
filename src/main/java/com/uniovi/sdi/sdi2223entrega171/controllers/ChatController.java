package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.services.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    @Autowired
    ChatsService chatService;

    @RequestMapping("/chat/list")
    private String getChats(Model model){
        model.addAttribute("chatsList", chatService.getChats());
        return "chat/list";
    }

}
