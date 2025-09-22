package org.example;

import java.math.BigDecimal;

record Order(long id, long customerId, BigDecimal amount) {}
record Receipt(long orderId, BigDecimal total) {}
