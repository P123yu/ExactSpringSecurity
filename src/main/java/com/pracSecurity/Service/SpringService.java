package com.pracSecurity.Service;

import com.pracSecurity.Model.LoginModel;
import com.pracSecurity.Model.RegisterModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface SpringService {

    // Register   // save // post
    RegisterModel register(RegisterModel registerModel);


    // Login

    // LoginModel
    Authentication login(LoginModel loginModel);
}
