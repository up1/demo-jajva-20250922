package org.example;

import java.math.BigDecimal;
import java.util.List;

public class OrderWithStream extends BaseOrderProcessing {
    public long process(List<Order> orders) {
        return orders.parallelStream()
                .map( order -> validate(order))
                .map(order -> new Object[]{order, computeTax(order.amount())})
                .map(arr -> persist((Order) arr[0], (BigDecimal) arr[1]))
                .count();
    }
}
