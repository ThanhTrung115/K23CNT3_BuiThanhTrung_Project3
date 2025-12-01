package com.treeree.shop.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Trả về view templates/login.html
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}