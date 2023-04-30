package com.rujal.drones.service.impl;

import com.rujal.drones.domain.History;
import com.rujal.drones.repository.HistoryRepository;
import com.rujal.drones.service.HistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

  private final HistoryRepository historyRepository;

  HistoryServiceImpl(HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }

  @Override
  public void addChangeHistory(History history) {
    historyRepository.save(history);
  }
}
