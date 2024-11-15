package com.devsuperior.dslist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/lists")
public class GameListController {

  @Autowired
  private GameListService listService;

  @Autowired
  private GameService gameService;

  @GetMapping
  public ResponseEntity<List<GameListDTO>> findAll() {
    List<GameListDTO> list = listService.findAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{listId}/games")
  public ResponseEntity<List<GameMinDTO>> findByList(@PathVariable Long listId) {
    List<GameMinDTO> list = gameService.findByList(listId);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GameListDTO> findById(@PathVariable Long id) {
    GameListDTO dto = listService.findById(id);
    return ResponseEntity.ok(dto);
  }

}
