package com.astratech.backend_gstrack.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception ini akan dilempar ketika suatu resource (mis: Reimbursement by ID)
 * tidak ditemukan di database. Anotasi @ResponseStatus akan secara otomatis
 * membuat Spring Boot mengembalikan HTTP Status 404 Not Found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}