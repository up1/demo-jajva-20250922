package com.example.demo02;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public OrderResponse createNewOrder(NewOrderRequest request){
        // Insert to database
        // Return result to controller
        OrderResponse newOrder = new OrderResponse(111, 111, 1111.0);
        return newOrder;
    }

}
