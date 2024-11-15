package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameListService {

  @Autowired
  private GameListRepository listRepository;

  @Transactional(readOnly = true)
  public List<GameListDTO> findAll() {
    List<GameList> list = listRepository.findAll();

    return list.stream()
        .map(gameList -> new GameListDTO(gameList))
        .toList();
  }

  @Transactional(readOnly = true)
  public GameListDTO findById(Long id) {
    GameList list = listRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    return new GameListDTO(list);
  }
}
