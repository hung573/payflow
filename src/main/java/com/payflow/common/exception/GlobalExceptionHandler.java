/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payflow.common.exception;

import com.payflow.common.dto.ErrorResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author mac
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log =
        LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(
            DuplicateResourceException exception) {

        Map<String, String> errors = new LinkedHashMap<>();
        errors.put(exception.getField(), exception.getMessage());

        ErrorResponse response = new ErrorResponse(
                409,
                "Dữ liệu đã tồn tại",
                errors
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException exception) {

        ConstraintViolationException violation =
                findConstraintViolation(exception);

        Map<String, String> errors = new LinkedHashMap<>();

        if (violation != null
                && "23505".equals(violation.getSQLState())) {

            String constraintName = violation.getConstraintName();

            if ("users_email_key".equals(constraintName)) {
                errors.put("email", "Email đã được đăng ký");
            } else if ("users_phone_key".equals(constraintName)) {
                errors.put("phone", "Số điện thoại đã được đăng ký");
            }
        }

        if (!errors.isEmpty()) {
            ErrorResponse response = new ErrorResponse(
                    409,
                    "Dữ liệu đã tồn tại",
                    errors
            );

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(response);
        }

        log.error("Lỗi ràng buộc database chưa được phân loại", exception);

        ErrorResponse response = new ErrorResponse(
                500,
                "Đã xảy ra lỗi hệ thống",
                new LinkedHashMap<>()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    private ConstraintViolationException findConstraintViolation(
            Throwable exception) {

        Throwable current = exception;

        while (current != null) {
            if (current instanceof ConstraintViolationException) {
                return (ConstraintViolationException) current;
            }

            current = current.getCause();
        }

        return null;
    }
}
