package com.example.demo02;

public record NewOrderRequest(
	double total_price,
	int customer_id
) {
}
