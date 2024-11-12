package com.devsuperior.dslist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/games")
public class GameController {

  @Autowired
  private GameService gameService;

  @GetMapping
  public ResponseEntity<List<GameMinDTO>> findAll() {
    List<GameMinDTO> list = gameService.findAll();
    return ResponseEntity.ok(list);
  }

}
