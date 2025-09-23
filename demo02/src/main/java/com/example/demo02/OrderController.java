package com.example.demo02;

import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @GetMapping("/orders/{id}")
    public OrderResponse getOrderById(@PathVariable int id) {
        return new OrderResponse(id, 123, 1000.50);
    }

}
