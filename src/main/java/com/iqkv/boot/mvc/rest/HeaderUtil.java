package com.iqkv.boot.mvc.rest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

  private static final Logger LOG = LoggerFactory.getLogger(HeaderUtil.class);

  private HeaderUtil() {
  }

  /**
   * createAlert.
   *
   * @param applicationName a {@link java.lang.String} object.
   * @param message         a {@link java.lang.String} object.
   * @param param           a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createAlert(String applicationName, String message, String param) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + applicationName + "-alert", message);
    headers.add("X-" + applicationName + "-params", URLEncoder.encode(param, StandardCharsets.UTF_8));
    return headers;
  }

  /**
   * createEntityCreationAlert.
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityCreationAlert(
      String applicationName,
      boolean enableTranslation,
      String entityName,
      String param
  ) {
    String message = enableTranslation
        ? applicationName + "." + entityName + ".created"
        : "A new " + entityName + " is created with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * createEntityUpdateAlert.
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityUpdateAlert(String applicationName, boolean enableTranslation, String entityName, String param) {
    String message = enableTranslation
        ? applicationName + "." + entityName + ".updated"
        : "A " + entityName + " is updated with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * createEntityDeletionAlert.
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityDeletionAlert(
      String applicationName,
      boolean enableTranslation,
      String entityName,
      String param
  ) {
    String message = enableTranslation
        ? applicationName + "." + entityName + ".deleted"
        : "A " + entityName + " is deleted with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * createFailureAlert.
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param errorKey          a {@link java.lang.String} object.
   * @param defaultMessage    a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createFailureAlert(
      String applicationName,
      boolean enableTranslation,
      String entityName,
      String errorKey,
      String defaultMessage
  ) {
    LOG.error("Entity processing failed, {}", defaultMessage);

    String message = enableTranslation ? "error." + errorKey : defaultMessage;

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + applicationName + "-error", message);
    headers.add("X-" + applicationName + "-params", entityName);
    return headers;
  }
}