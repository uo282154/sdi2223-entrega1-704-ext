package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}