package org.example.orders;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class OrderProcessing {

    public enum Status {NEW, PAID, SHIPPED, CANCELLED}

    public int countAllOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
        return orders.size();
    }

    // 1. Total revenue from PAID orders in the current month
    public double totalRevenueByPaidStatus(List<Order> orders) {
        // Get current month
        LocalDate today = LocalDate.now();
        YearMonth targetMonth = YearMonth.from(today);

        // Start
        double revenue = 0.0;
        // TODO with for loop and Java Stream + Lambda

        return revenue;
    }

    // 2. Top 3 customers by spend (PAID orders)
    // customer-id, sum(total)


    // 3. Find the earliest overdue NEW order

}
