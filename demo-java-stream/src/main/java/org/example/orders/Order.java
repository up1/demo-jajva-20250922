package org.example.orders;

import java.time.LocalDate;
import java.util.List;

public record Order(
        String id,
        String customerId,
        LocalDate createdAt,
        OrderProcessing.Status status,
        List<OrderItem> items
) {
    double total() { return items.stream().mapToDouble(OrderItem::lineTotal).sum(); }
    boolean isOverdue(LocalDate today) {
        return status == OrderProcessing.Status.NEW && createdAt.plusDays(7).isBefore(today);
    }
}


