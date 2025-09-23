package com.example.demo02;

import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @GetMapping("/orders/{id}")
    public OrderResponse getOrderById(@PathVariable int id) {

        if(id == 2) {
            throw new OrderNotFoundException("Order id="+ id +" not found in system");
        }

        return new OrderResponse(id, 123, 1000.50);
    }

}
