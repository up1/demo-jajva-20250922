package com.example.demo02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Order not found in system")
    void getOrderById(){
        ResponseEntity<ErrorMessageResponse> response = restTemplate.getForEntity("/orders/2", ErrorMessageResponse.class);
        assertEquals(404, response.getStatusCode().value());
    }


    @Test
    @DisplayName("Success with create a new order")
    void createNewOrder() {
        NewOrderRequest request = new NewOrderRequest(1, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertEquals(111, result.getBody().id());
    }

    @Test
    @DisplayName("Failure with create a new order")
    void createNewOrder2() {
        NewOrderRequest request = new NewOrderRequest(0, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }
}