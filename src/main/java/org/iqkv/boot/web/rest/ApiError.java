package org.iqkv.boot.web.rest;

import java.time.LocalDateTime;

public record ApiError(
    String path,
    String message,
    int statusCode,
    LocalDateTime localDateTime
) {
}