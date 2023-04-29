package com.rujal.drones.converters;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

import java.util.List;
import org.springframework.data.domain.Page;

public interface BaseConverter<E, D> {

  E fromDto(D dto);

  D fromEntity(E entity);

  default List<E> fromDto(List<D> dtos) {
    if (dtos == null) {
      return emptyList();
    }
    return dtos.stream().map(this::fromDto).toList();
  }

  default List<D> fromEntity(List<E> entities) {
    if (isNull(entities)) {
      return emptyList();
    }
    return entities.stream().map(this::fromEntity).toList();
  }

  default Page<E> fromDto(Page<D> dtos) {
    if (isNull(dtos)) {
      return null;
    }
    return dtos.map(this::fromDto);
  }

  default Page<D> fromEntity(Page<E> entities) {
    if (isNull(entities)) {
      return null;
    }
    return entities.map(this::fromEntity);
  }
}