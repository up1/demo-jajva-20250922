package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;

public class OrderWithVirtualThread extends BaseOrderProcessing {
    public long process(List<Order> orders) {
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Callable<Receipt>> tasks = orders.stream()
                    .<Callable<Receipt>>map(o -> () -> {
                        var v = validate(o);
                        var tax = computeTax(v.amount());
                        return persist(v, tax);
                    })
                    .toList();
            List<Future<Receipt>> futures = exec.invokeAll(tasks);
            long ok = 0;
            for (Future<Receipt> f : futures) if (f.get() != null) ok++;
            return ok;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
