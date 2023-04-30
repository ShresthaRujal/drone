package com.rujal.drones.utils;

import static com.rujal.drones.utils.Constants.Field.CREATED_DATE;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import com.rujal.drones.domain.EventDetail;
import com.rujal.drones.domain.EventType;
import com.rujal.drones.domain.History;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ReflectionDiffBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.collection.spi.PersistentBag;

public class HistoryUtils {

  private static final List<String> FIELDS_TO_IGNORE = asList(CREATED_DATE);

  private HistoryUtils() {}

  /**
   * Create {@link History} from old value, new value and {@link EventType}
   */
  public static History createHistory(Object oldValue, Object newValue, EventType eventType) {
    History history = new History();
    history.setCreateDate(LocalDateTime.now());
    history.setEventType(eventType);
    history.setNewValue(newValue);
    history.setOldValue(oldValue);
    history.setEventDetails(getDiffInEvenDetails(history));
    return history;
  }

  private static List<EventDetail> getDiffInEvenDetails(History history) {
    List<EventDetail> listOfChanges = new ArrayList<>();
    DiffResult<?> diffResult = new ReflectionDiffBuilder<>(history.getOldValue(),
        history.getNewValue(),
        ToStringStyle.DEFAULT_STYLE).build();
    if (isNotEmpty(diffResult.getDiffs())) {
      diffResult.getDiffs().stream()
          .filter(diff -> !FIELDS_TO_IGNORE.contains(diff.getFieldName())
              && diffIsNotNull(diff))
          .map(HistoryUtils::setChangeSetInEventDetail)
          .forEach(listOfChanges::add);
      return listOfChanges;
    }
    return emptyList();
  }

  /**
   * Check is Old value and New vale are null
   */
  private static boolean diffIsNotNull(Diff<?> diff) {
    if (diff.getValue() instanceof PersistentBag) {
      return isNotEmpty((PersistentBag<?>) diff.getValue());
    }
    return !isNull(diff.getLeft()) || !isNull(diff.getRight());
  }

  /**
   * create EventDetail from difference
   */
  private static EventDetail setChangeSetInEventDetail(Diff<?> diff) {
    return new EventDetail(diff.getFieldName(), diff.getLeft(), diff.getRight());
  }

}
