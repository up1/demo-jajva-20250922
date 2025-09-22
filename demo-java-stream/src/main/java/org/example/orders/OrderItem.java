package org.example.orders;

public record OrderItem(String sku, int qty, double unitPrice) {
    double lineTotal() { return qty * unitPrice; }
}
