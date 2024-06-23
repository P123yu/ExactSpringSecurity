
package com.pracSecurity.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@CrossOrigin
public class ManagerController {

    @GetMapping("/create")
    public String createAdminResource() {
        return "create";
    }

    @GetMapping("/read")
    public String readAdminResource() {
        return "read";
    }
}




