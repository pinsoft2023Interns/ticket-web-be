package com.pinsoft.ticketwebbe.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e){

        logger.error(e.getMessage(),e);
        return new ApiException(  e.getMessage(), HttpStatus.BAD_REQUEST  ).toResponse();
    }

    @ExceptionHandler(value = {InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<ApiException> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){

        logger.error(e.getMessage(),e);
        return new ApiException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR).toResponse();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e){

        logger.error(e.getMessage(),e);
        return new ApiException(e.getMessage(),HttpStatus.BAD_REQUEST).toResponse();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiException> handleException(Exception e){

        logger.error(e.getMessage(),e);
        return new ApiException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR).toResponse();
    }
}
