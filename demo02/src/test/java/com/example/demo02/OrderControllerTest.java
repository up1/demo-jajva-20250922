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
    @DisplayName("Success with create a new order")
    void createNewOrder() {
        NewOrderRequest request = new NewOrderRequest(1, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertEquals(111, result.getBody().id());
    }
}