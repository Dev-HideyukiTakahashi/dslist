package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  @Transactional(readOnly = true)
  public List<GameMinDTO> findAll() {
    List<Game> list = gameRepository.findAll();

    return list.stream()
        .map(game -> new GameMinDTO(game))
        .toList();
  }

  @Transactional(readOnly = true)
  public GameDTO findById(Long id) {
    Game game = gameRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    return new GameDTO(game);
  }

  @Transactional
  public List<GameMinDTO> findByList(Long idList) {
    List<GameMinProjection> list = gameRepository.searchByList(idList);
    return list.stream()
        .map(game -> new GameMinDTO(game))
        .toList();
  }
}
