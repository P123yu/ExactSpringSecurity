package com.pracSecurity.serviceimpl;

import com.pracSecurity.Model.LoginModel;
import com.pracSecurity.Model.RegisterModel;
import com.pracSecurity.Repository.SpringRepository;
import com.pracSecurity.Service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpringServiceImpl implements SpringService {

    @Autowired
    private SpringRepository springRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterModel register(RegisterModel registerModel) {
        registerModel.setUserPassword(passwordEncoder.encode(registerModel.getUserPassword()));
        return springRepository.save(registerModel);
    }


    @Autowired
    AuthenticationManager authenticationManager;


    //LoginModel

    // step 3
    @Override
    public Authentication login(LoginModel loginModel) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginModel.getUserName(), loginModel.getUserPassword());
        Authentication authentication = authenticationManager.authenticate(authToken); // from this line it will throw to step 4

        // step 8
        return authentication;
    }


}
