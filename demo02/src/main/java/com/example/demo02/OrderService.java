package com.example.demo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse createNewOrder(NewOrderRequest request){
        // Insert to database
        Order order = new Order();
        order.setOrderId(111);
        order.setCustomerId(111);
        order.setTotalPrice(1111.0);
        order = orderRepository.save(order);
        // Return result to controller
        OrderResponse newOrder = new OrderResponse(
                order.getOrderId(),
                order.getCustomerId(),
                order.getTotalPrice());
        return newOrder;
    }

}
