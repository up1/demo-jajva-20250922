package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final Random random = new Random(42);

    public static void main(String[] args) {
        System.out.println("Start process");
        Instant startTime = Instant.now();

        // 1. Generate data for testing
        List<Order> orders = generateOrders(100_000);
        System.out.printf("Size of order=%d\n", orders.size());

        // 2. Process
        BaseOrderProcessing p1 = new OrderWithSingleThread();
        long count = p1.process(orders);

        Duration totalTime = Duration.between(startTime, Instant.now());
        System.out.printf("Process=%d orders in time = %s", count, human(totalTime));
    }

    static String human(Duration d) {
        long ms = d.toMillis();
        long s = ms / 1000; ms %= 1000;
        long m = s / 60; s %= 60;
        long h = m / 60; m %= 60;
        return (h>0? h+"h ":"") + (m>0? m+"m ":"") + s+"."+String.format("%03d", ms)+"s";
    }

    static List<Order> generateOrders(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new Order(i, random.nextInt(100_000), BigDecimal.valueOf(10 + random.nextInt(10_000) / 100.0)))
                .toList();
    }
}