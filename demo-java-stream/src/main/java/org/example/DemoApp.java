package org.example;

import org.example.orders.Order;
import org.example.orders.OrderItem;
import org.example.orders.OrderProcessing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        // Generate sample orders
        List<Order> orders = generateSampleOrders();

        // Start processing
        OrderProcessing processing = new OrderProcessing();
        int count = processing.countAllOrders(orders);
        System.out.println("All orders= " + count);

        // Start workshop !!
       


    }

    // Data for testing
    static List<Order> generateSampleOrders() {
        List<Order> list = new ArrayList<>();
        LocalDate base = LocalDate.now().minusDays(20);
        String[] customers = {"CUST-001","CUST-002","CUST-003","CUST-004"};
        OrderProcessing.Status[] statuses = {OrderProcessing.Status.NEW, OrderProcessing.Status.PAID, OrderProcessing.Status.CANCELLED};
        for (int i = 1; i <= 20; i++) {
            String id = "ORD-%03d".formatted(i);
            String customer = customers[i % customers.length];
            LocalDate date = base.plusDays(i);
            OrderProcessing.Status status = OrderProcessing.Status.PAID;
            List<OrderItem> items = List.of(
                    new OrderItem("SKU-%02d".formatted(i%7 + 1), 1 + (i % 3), 100 + (i % 5) * 25.0),
                    new OrderItem("SKU-%02d".formatted((i+3)%7 + 1), 1, 50 + (i % 4) * 10.0)
            );
            list.add(new Order(id, customer, date, status, items));
        }
        return list;
    }
}
