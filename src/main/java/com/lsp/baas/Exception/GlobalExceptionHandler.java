package com.lsp.baas.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "The fields and values provided are invalid."
        );

        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Invalid Request Parameters");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        List<InvalidParam> invalidParams = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        problemDetail.setProperty("invalidParams", invalidParams);

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundExceptions(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        System.out.println(ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
}

//{
//    "type": "https://api.tuempresa.com/errors/bad-request",
//    "title": "Invalid Request Parameters",
//    "status": 400,
//    "detail": "The provided fields do not meet the business validation constraints.",
//    "instance": "/api/baas/v1/customers",
//    "invalidParams": [
//        { "name": "email", "reason": "Must be a valid email address" }
//    ]
//}