package com.codefellowship.lab16.configs;

import com.codefellowship.lab16.repositories.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AppRepo appRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return (UserDetails) appRepo.findByUsername(username);
    }
}
