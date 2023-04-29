package com.rujal.drones.utils;

import java.text.MessageFormat;
import java.util.Locale;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Handles Message Bundles
 */
public class MessageUtils {

  private MessageUtils() {}
  private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
  static ReloadableResourceBundleMessageSource messageSource;

  /**
   * Initialize MessageSource Resource Bundle
   */
  static {
    messageSource = new ReloadableResourceBundleMessageSource();
    String[] basenames = {"classpath:/ValidationMessages"};
    messageSource.setBasenames(basenames);
    messageSource.setDefaultEncoding("UTF-8");
  }

  /**
   * Fetch Message with Params
   */
  public static String getMessage(String code, Object... params) {
    return MessageFormat.format(getMessage(code), params);
  }

  /**
   * Fetch message with LOCALE
   */
  public static String getMessage(String code) {
    return messageSource.getMessage(code, null, DEFAULT_LOCALE);
  }
}
