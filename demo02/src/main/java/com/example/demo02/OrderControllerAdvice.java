package com.example.demo02;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(exception = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessageResponse> badRequest(Exception ex){
        ErrorMessageResponse myException = new ErrorMessageResponse("Bad request with "+ ex.getMessage());
        ResponseEntity<ErrorMessageResponse> response =
                new ResponseEntity<>(myException, HttpStatusCode.valueOf(400));
        return response;
    }

    @ExceptionHandler(exception = OrderNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> orderNotFound(Exception ex){
        ErrorMessageResponse myException = new ErrorMessageResponse(ex.getMessage());
        ResponseEntity<ErrorMessageResponse> response =
                new ResponseEntity<>(myException, HttpStatusCode.valueOf(404));
        return response;
    }

}
