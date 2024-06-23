package com.pracSecurity.serviceimpl;

import com.pracSecurity.Model.LoginModel;
import com.pracSecurity.Model.RegisterModel;
import com.pracSecurity.Repository.RegisterRepository;
import com.pracSecurity.Service.SpringService;
import com.pracSecurity.jwt.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpringServiceImpl implements SpringService {

    @Autowired
    private RegisterRepository springRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private RefreshServiceImpl refreshServiceImpl;

    @Override
    public String register(RegisterModel registerModel) {
        registerModel.setUserPassword(passwordEncoder.encode(registerModel.getUserPassword()));

        RegisterModel register=springRepository.save(registerModel);
        refreshServiceImpl.createRefreshToken(register.getUsername());
        System.out.println("goood morning");


        // this line generate JWT Token
        String jwtToken=jwtTokenGenerator.generateToken(registerModel);
        System.out.println("good night");
        return jwtToken;
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
