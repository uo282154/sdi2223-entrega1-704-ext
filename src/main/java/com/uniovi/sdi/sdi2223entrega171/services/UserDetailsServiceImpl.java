package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.repositories.ChatRepository;
import com.uniovi.sdi.sdi2223entrega171.repositories.OfferRepository;
import com.uniovi.sdi.sdi2223entrega171.repositories.UsersRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;


import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    /**
     * Mñetodo que devuelve el usuario que esta activo en el sistema
     * @return, usuario acitvo
     */
    public User getActiveUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = usersRepository.findByEmail(email);
        return user;
    }

    public boolean isUserInChat(Long chatId, String username, Long offerId) {
        Chat chat = chatRepository.findById(chatId).get();
        Offer offer = offerRepository.findById(offerId).get();
        String emailCreator = offer.getCreator().getEmail();
        String myEmail = getActiveUser().getEmail();
        return chat != null && ((myEmail.equals(username)
            || emailCreator.equals(myEmail))
            && !(emailCreator.equals(username) && myEmail.equals(username)));
    }

}
