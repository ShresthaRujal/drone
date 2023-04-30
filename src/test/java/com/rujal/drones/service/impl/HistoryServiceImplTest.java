package com.rujal.drones.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.rujal.drones.domain.History;
import com.rujal.drones.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest{
  @InjectMocks
  private HistoryServiceImpl historyService;
  @Mock
  private HistoryRepository historyRepository;

  @Test
  void testAddChangeHistoryShouldAddHistory() {
    // GIVEN
    History history = mock(History.class);
    // WHEN
    historyService.addChangeHistory(history);
    // THEN
    verify(historyRepository).save(history);
  }
}
