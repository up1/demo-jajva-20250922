package com.example.demo02;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewOrderRequest(
        @Min(1) double total_price,
        @Min(1) int customer_id
) {
}
