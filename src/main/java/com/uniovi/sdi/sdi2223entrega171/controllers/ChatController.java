package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.entities.Message;
import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ChatsService chatService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MessageService messageService;



    @RequestMapping("/chat/list")
    public String getMyChats(Pageable pageable, Model model) {
        Page<Offer> myOffers = offersService.getMyOffers(pageable, userDetailsService.getActiveUser());
        List<Offer> offers = myOffers.getContent();
        List<Chat> myChats = chatService.findChatBySenderOrReceiver(userDetailsService.getActiveUser());

        //for (Offer o: offers) {
        //    if (o.getCreator().getId() == userDetailsService.getActiveUser().getId()) { //si esa oferta es mia
        //        if (chatService.findChatByOffer(o) != null){
        //            myChats.add(chatService.findChatByOffer(o));
        //        }
        //    }
        //    myChats.add(chatService.findChatBySender(userDetailsService.getActiveUser()));  //si yo cree el chat
       // }

        //if(!myChats.isEmpty()){
        //    System.out.println(myChats.size());
        //    for (Chat c: myChats) {
        //        System.out.println(c.getOffer().getCreator().getEmail() + "   " + c.getSender().getEmail());
        //    }
            model.addAttribute("chatsList", myChats);
        //}

        model.addAttribute("user", userDetailsService.getActiveUser());
        return "chat/list";
    }

    @RequestMapping(value = "/chat/{offerId}/{username}")
    public String createChat(@PathVariable Long offerId, @PathVariable String username, Model model) {
        model.addAttribute("user", userDetailsService.getActiveUser());
        User myUser = usersService.getUserByEmail(username);
        //User userCreator = usersService.getUser(creatorId);
        Offer offer = offersService.getOffer(offerId);
        Chat chat = chatService.findChatByOfferAndSender(offer, myUser);
        List<Message> messages = new ArrayList<Message>();
        if (chat != null){
            if(!userDetailsService.isUserInChat(chat.getId(), username, offerId)){
                throw new AccessDeniedException("No tienes acceso a este recurso");
            }
            messages = messageService.getMessagesByChat(chat);
        }
        model.addAttribute("chatActual", chat);
        model.addAttribute("messagesFromChat", messages);
        model.addAttribute("myUserChat", myUser);   //usuario que crea el chat
        //model.addAttribute("userCreator", userCreator); //usiario propietario de la oferta
        model.addAttribute("offerChat", offer); //oferta del chat
        return "chat/chat";
    }

    @RequestMapping(value = "/chat/{offerId}/{username}/add", method = RequestMethod.POST)
    public String addMessage(@PathVariable Long offerId, @PathVariable String username, @RequestParam("message") String message){
        User myUser = usersService.getUserByEmail(username);
        //User userCreator = usersService.getUser(creatorId);
        Offer offer = offersService.getOffer(offerId);
        Chat chat = chatService.findChatByOfferAndSender(offer, myUser);     //mover al servicio
        if (chat == null){
            chat = chatService.findChatByOfferAndSender(offer, myUser);
        };
        if (chat == null){                                                                          //mover al servicio
            chat = new Chat(offer.getBuyer(), offer.getCreator(), offer);                           //mover al servicio
        }
        if(chat.getSender() == null){
            chat.setSender(userDetailsService.getActiveUser());
        }
        chatService.addChat(chat, message);
        if(!userDetailsService.isUserInChat(chat.getId(), username, offerId)){
            throw new AccessDeniedException("No tienes acceso a este recurso");
        }
                                                //mover al servicio



        return "redirect:/chat/" + offerId + '/' + myUser.getEmail();
    }

}
