package com.pracSecurity.serviceimpl;

import com.pracSecurity.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// step 6
@Service
public class SpringServiceConfig implements UserDetailsService {

    @Autowired
    private RegisterRepository springRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return springRepository.findByUserName(username);
    }
}
