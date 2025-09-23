package com.example.demo02;

import jakarta.validation.constraints.Min;

public record NewOrderRequest(
        @Min(1) double total_price,
        @Min(1) int customer_id
) {
}
