package org.example;

import java.util.List;

public class OrderWithSingleThread extends BaseOrderProcessing {
    public long process(List<Order> orders) {
        long count = 0;
        for (Order o : orders) {
            var v = validate(o);
            var tax = computeTax(v.amount());
            var r = persist(v, tax);
            if (r != null) count++;
        }
        return count;
    }
}
