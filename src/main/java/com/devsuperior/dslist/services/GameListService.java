package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameListService {

  @Autowired
  private GameListRepository listRepository;

  @Autowired
  private GameRepository gameRepository;

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

  @Transactional
  public void move(Long listId, int sourceIndex, int destinationIndex) {
    List<GameMinProjection> list = gameRepository.searchByList(listId);
    GameMinProjection obj = list.remove(sourceIndex);
    list.add(destinationIndex, obj);

    int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
    int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

    for (int i = min; i < max; i++) {
      listRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }
  }
}
