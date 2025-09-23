package com.example.demo02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    // === GET /orders/{id} Tests ===
    
    @Test
    @DisplayName("Order not found - ID 2 returns 404")
    void getOrderById(){
        ResponseEntity<ErrorMessageResponse> response = restTemplate.getForEntity("/orders/2", ErrorMessageResponse.class);
        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().message().contains("Order id=2 not found in system"));
    }

    @Test
    @DisplayName("Success - Get order by valid ID")
    void getOrderByValidId() {
        int orderId = 1;
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/" + orderId, OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().id());
        assertEquals(123, response.getBody().customer_id());
        assertEquals(1000.50, response.getBody().total_price());
    }

    @Test
    @DisplayName("Success - Get order by different valid ID")  
    void getOrderByDifferentId() {
        int orderId = 5;
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/" + orderId, OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().id());
    }

    @Test
    @DisplayName("Get order with zero ID")
    void getOrderByZeroId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/0", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().id());
    }

    @Test
    @DisplayName("Get order with negative ID")
    void getOrderByNegativeId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/-1", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(-1, response.getBody().id());
    }

    @Test
    @DisplayName("Get order with large ID")
    void getOrderByLargeId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/999999", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(999999, response.getBody().id());
    }

    // === POST /order Tests - Success Cases ===

    
    @Test
    @DisplayName("Success with create a new order")
    void createNewOrder() {
        NewOrderRequest request = new NewOrderRequest(1, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().id());
        assertEquals(1, result.getBody().customer_id());
        assertEquals(1, result.getBody().total_price());
    }

    @Test
    @DisplayName("Success - Create order with decimal price")
    void createNewOrderWithDecimalPrice() {
        NewOrderRequest request = new NewOrderRequest(99.99, 5);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("Success - Create order with large values")
    void createNewOrderWithLargeValues() {
        NewOrderRequest request = new NewOrderRequest(9999.99, 999999);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("Success - Create order with minimum values")
    void createNewOrderWithMinimumValues() {
        NewOrderRequest request = new NewOrderRequest(1.01, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("Success - Create order with floating point precision")
    void createNewOrderWithFloatingPointPrecision() {
        NewOrderRequest request = new NewOrderRequest(123.456789, 42);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    // === POST /order Tests - Validation Failures ===

    @Test
    @DisplayName("Failure - Create order with invalid total_price (zero)")
    void createNewOrderWithInvalidTotalPrice() {
        NewOrderRequest request = new NewOrderRequest(0, 1);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure - Create order with invalid customer_id (zero)")
    void createNewOrderWithInvalidCustomerId() {
        NewOrderRequest request = new NewOrderRequest(100.0, 0);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure - Create order with both fields invalid")
    void createNewOrderWithBothFieldsInvalid() {
        NewOrderRequest request = new NewOrderRequest(0, 0);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure - Create order with negative total_price")
    void createNewOrderWithNegativeTotalPrice() {
        NewOrderRequest request = new NewOrderRequest(-10.0, 1);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure - Create order with negative customer_id")
    void createNewOrderWithNegativeCustomerId() {
        NewOrderRequest request = new NewOrderRequest(100.0, -5);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }
}