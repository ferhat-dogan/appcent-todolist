package com.demo.appcenttodolist.service;

import com.demo.appcenttodolist.entities.User;
import com.demo.appcenttodolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User currentUser = repository.findByEmail(email);
        UserDetails user = new org.springframework.security.core.userdetails.User(email, currentUser.getPassword()
        , true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
        return user;
    }
}
