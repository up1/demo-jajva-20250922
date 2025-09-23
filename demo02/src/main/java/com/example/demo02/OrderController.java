package com.example.demo02;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createNewOrder(@RequestBody NewOrderRequest request){
        System.out.println(request);
        OrderResponse newOrder = new OrderResponse(111, 111, 1111.0);
        ResponseEntity<OrderResponse> response = new ResponseEntity<>(newOrder, HttpStatusCode.valueOf(201));
        return response;
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrderById(@PathVariable int id) {

        if(id == 2) {
            throw new OrderNotFoundException("Order id="+ id +" not found in system");
        }

        return new OrderResponse(id, 123, 1000.50);
    }



}
