package com.example.demo02;

public record OrderResponse(
	int id,
	int customer_id,
    double total_price
) {
}
