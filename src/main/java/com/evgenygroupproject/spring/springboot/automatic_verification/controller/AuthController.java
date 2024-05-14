package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name="AuthController", description="Класс для авторизации")
public class AuthController {

  @GetMapping("/login")
  @Operation(
      summary="страница авторизации",
      description="страница авторизации"
  )
  public String login(){
    return "index";
  }

  @GetMapping("/main")
  public String main() {
    return "main";
  }
}
