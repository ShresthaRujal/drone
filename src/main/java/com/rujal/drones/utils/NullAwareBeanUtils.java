package com.rujal.drones.utils;

import com.rujal.drones.exceptions.BackEndException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtils extends BeanUtilsBean {

  @Override
  public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException,
      InvocationTargetException {
    if (value == null) {
      return;
    }
    super.copyProperty(dest, name, value);
  }

  /**
   * Copy attributes from Source to Destination with similar fields
   */
  @Override
  public void copyProperties(Object dest, Object source) {
    try {
      super.copyProperties(dest, source);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new BackEndException(e);
    }
  }
}
