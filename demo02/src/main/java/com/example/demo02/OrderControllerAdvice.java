package com.example.demo02;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(exception = MethodArgumentTypeMismatchException.class)
    public ErrorMessageResponse badRequest(MethodArgumentTypeMismatchException ex){
        return new ErrorMessageResponse("Bad request with "+ ex.getMessage());
    }

}
