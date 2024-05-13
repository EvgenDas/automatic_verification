package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String login(){
    return "index";
  }

  @GetMapping("/main")
  public String main() {
    return "main";
  }
}
