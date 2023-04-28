package com.rujal.drones.converters;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.data.domain.Page;

public abstract class BaseConverter<E, D> {

  public abstract E fromDto(D dto);

  public abstract D fromEntity(E entity);

  public List<E> fromDto(List<D> dtos) {
    if (dtos == null) {
      return null;
    }
    return dtos.stream().map(this::fromDto).collect(toList());
  }

  public List<D> fromEntity(List<E> entities) {
    if (entities == null) {
      return null;
    }
    return entities.stream().map(this::fromEntity).collect(toList());
  }

  public Page<E> fromDto(Page<D> dtos) {
    if (dtos == null) {
      return null;
    }
    return dtos.map(this::fromDto);
  }

  public Page<D> fromEntity(Page<E> entities) {
    if (entities == null) {
      return null;
    }
    return entities.map(this::fromEntity);
  }
}