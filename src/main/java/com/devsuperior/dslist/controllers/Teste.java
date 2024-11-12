package com.devsuperior.dslist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/hello")
public class Teste {

  @GetMapping
  public String hello() {
    return "Hello springboot!";
  }

}