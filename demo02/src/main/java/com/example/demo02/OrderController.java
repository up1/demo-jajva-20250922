package com.example.demo02;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createNewOrder(
            @Valid @RequestBody NewOrderRequest request){
        OrderResponse newOrder = orderService.createNewOrder(request);
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
