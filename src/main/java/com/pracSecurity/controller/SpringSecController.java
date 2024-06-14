package com.pracSecurity.controller;


import com.pracSecurity.Model.LoginModel;
import com.pracSecurity.Model.RegisterModel;
import com.pracSecurity.serviceimpl.SpringServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SpringSecController {

    @Autowired
    private SpringServiceImpl springServiceImpl;

    @GetMapping("/get")
    public String message(){
        return "hello";
    }

    @GetMapping("/get1")
    public String message1(){
        return "hello1";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterModel registerModel) {
        System.out.println("hello");
        RegisterModel register=springServiceImpl.register(registerModel);
        if(register!=null) {
            return ResponseEntity.ok(register);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not registerd");
        }
}


// step 2

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
        Authentication auth=springServiceImpl.login(loginModel);
        if(auth!=null) {
            // step 9
            return ResponseEntity.ok(auth);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not login");
        }
    }
}
