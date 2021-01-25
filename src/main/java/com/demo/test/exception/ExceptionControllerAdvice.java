package com.demo.test.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Exception controller advice.
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Create strategy not implemented exception string.
     *
     * @param ex the ex
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(StrategyNotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED )
    public String createStrategyNotImplementedException(StrategyNotImplementedException ex) {
    return ex.getMessage();
    }

    /**
     * Handle all exceptions response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Internal Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
