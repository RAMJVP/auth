package com.secured.auth.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // In a real application, you should load user details from a database
        return new org.springframework.security.core.userdetails.User
        (username, "$2a$10$rDDPB6p10FKvhaeTR5sQ9eSUkJNGSC0HdT7HOprYUWPTcP15m7g.O", new ArrayList<>());
        //(password in encoded form)
    }
}
