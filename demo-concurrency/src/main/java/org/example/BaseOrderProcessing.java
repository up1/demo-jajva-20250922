package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public abstract class BaseOrderProcessing {

    public abstract long process(List<Order> orders);

    public Order validate(Order o) {
        Objects.requireNonNull(o);
        if (o.amount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("bad amount");
        return o;
    }

    public  BigDecimal computeTax(BigDecimal base) {
        // simulate some CPU work
        double x = base.doubleValue();
        for (int i = 0; i < 30; i++) x = Math.sin(x) * Math.cos(x) + x;
        return BigDecimal.valueOf(x * 0.07).abs();
    }


    public Receipt persist(Order o, BigDecimal tax) {
        // Simulate slow for write data
        try { Thread.sleep(100); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
        return new Receipt(o.id(), o.amount().add(tax));
    }
}

