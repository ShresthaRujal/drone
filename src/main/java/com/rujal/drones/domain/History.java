package com.rujal.drones.domain;

import static com.rujal.drones.utils.Constants.Database.HISTORY;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity(name = HISTORY)
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime createDate;
  @JdbcTypeCode(SqlTypes.JSON)
  private List<EventDetail> eventDetails = new ArrayList<>();
  @Enumerated(EnumType.STRING)
  private EventType eventType;
  @Transient
  private Object oldValue;
  @Transient
  private Object newValue;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public List<EventDetail> getEventDetails() {
    return eventDetails;
  }

  public void setEventDetails(List<EventDetail> eventDetails) {
    this.eventDetails = eventDetails;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Object getOldValue() {
    return oldValue;
  }

  public void setOldValue(Object oldValue) {
    this.oldValue = oldValue;
  }

  public Object getNewValue() {
    return newValue;
  }

  public void setNewValue(Object newValue) {
    this.newValue = newValue;
  }
}
