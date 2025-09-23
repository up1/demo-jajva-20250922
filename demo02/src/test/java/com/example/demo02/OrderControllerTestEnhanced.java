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
class OrderControllerTestEnhanced {

    @Autowired
    TestRestTemplate restTemplate;

    // GET /orders/{id} Tests
    @Test
    @DisplayName("Order not found - ID 2 specifically returns 404")
    void getOrderByIdNotFound() {
        ResponseEntity<ErrorMessageResponse> response = restTemplate.getForEntity("/orders/2", ErrorMessageResponse.class);
        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().message().contains("Order id=2 not found in system"));
    }

    @Test
    @DisplayName("Success - Get order by ID that exists")
    void getOrderByExistingId() {
        int orderId = 1;
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/" + orderId, OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().id());
        assertEquals(123, response.getBody().customer_id());
        assertEquals(1000.50, response.getBody().total_price());
    }

    @Test
    @DisplayName("Success - Get order by ID with different valid ID")
    void getOrderByDifferentValidId() {
        int orderId = 5;
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/" + orderId, OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().id());
    }

    @Test
    @DisplayName("Test order with negative ID")
    void getOrderByNegativeId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/-1", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value()); 
        assertNotNull(response.getBody());
        assertEquals(-1, response.getBody().id());
    }

    @Test
    @DisplayName("Test order with very large ID")
    void getOrderByVeryLargeId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/999999", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(999999, response.getBody().id());
    }

    @Test
    @DisplayName("Test order with zero ID")
    void getOrderByZeroId() {
        ResponseEntity<OrderResponse> response = restTemplate.getForEntity("/orders/0", OrderResponse.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().id());
    }

    // POST /order Tests
    @Test
    @DisplayName("Success with create a new order")
    void createNewOrder() {
        NewOrderRequest request = new NewOrderRequest(1, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(111, result.getBody().id());
        assertEquals(111, result.getBody().customer_id());
        assertEquals(1111.0, result.getBody().total_price());
    }

    @Test
    @DisplayName("Success with create a new order - decimal total_price")
    void createNewOrderWithDecimalPrice() {
        NewOrderRequest request = new NewOrderRequest(99.99, 5);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(111, result.getBody().id());
        assertEquals(111, result.getBody().customer_id());
        assertEquals(1111.0, result.getBody().total_price());
    }

    @Test
    @DisplayName("Success with create a new order - large values")
    void createNewOrderWithLargeValues() {
        NewOrderRequest request = new NewOrderRequest(9999.99, 999999);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(111, result.getBody().id());
    }

    @Test
    @DisplayName("Create order with minimum valid values")
    void createNewOrderWithMinimumValues() {
        NewOrderRequest request = new NewOrderRequest(1, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(111, result.getBody().id());
    }

    @Test
    @DisplayName("Create order with floating point precision")
    void createNewOrderWithFloatingPointPrecision() {
        NewOrderRequest request = new NewOrderRequest(123.456789, 42);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(111, result.getBody().id());
    }

    // Validation Error Tests
    @Test
    @DisplayName("Failure with create a new order - invalid total_price")
    void createNewOrderWithInvalidTotalPrice() {
        NewOrderRequest request = new NewOrderRequest(0, 1);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
        // Note: The response body might be null or contain validation error details
    }

    @Test
    @DisplayName("Failure with create a new order - invalid customer_id")
    void createNewOrderWithInvalidCustomerId() {
        NewOrderRequest request = new NewOrderRequest(100.0, 0);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure with create a new order - both fields invalid")
    void createNewOrderWithBothFieldsInvalid() {
        NewOrderRequest request = new NewOrderRequest(0, 0);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure with create a new order - negative total_price")
    void createNewOrderWithNegativeTotalPrice() {
        NewOrderRequest request = new NewOrderRequest(-10.0, 1);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure with create a new order - negative customer_id")
    void createNewOrderWithNegativeCustomerId() {
        NewOrderRequest request = new NewOrderRequest(100.0, -5);
        ResponseEntity<ErrorMessageResponse> result = restTemplate.postForEntity("/order", request, ErrorMessageResponse.class);
        assertEquals(400, result.getStatusCode().value());
    }

    // Edge Case Tests
    @Test
    @DisplayName("Create order with very small valid values")
    void createNewOrderWithVerySmallValues() {
        NewOrderRequest request = new NewOrderRequest(1.01, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("Create order with maximum precision decimal")
    void createNewOrderWithMaxPrecisionDecimal() {
        NewOrderRequest request = new NewOrderRequest(99999999.99, 1);
        ResponseEntity<OrderResponse> result = restTemplate.postForEntity("/order", request, OrderResponse.class);
        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }
}