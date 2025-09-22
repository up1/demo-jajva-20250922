package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class OrderWithCompletableFutures extends BaseOrderProcessing {
    public long process(List<Order> orders) {
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            List<CompletableFuture<Receipt>> futures = orders.stream()
                    .map(o -> CompletableFuture.supplyAsync(() -> validate(o), exec)
                            .thenApplyAsync(v -> new Object[]{v, computeTax(v.amount())}, exec)
                            .thenApplyAsync(arr -> persist((Order) arr[0], (BigDecimal) arr[1]), exec))
                    .toList();
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            return futures.stream().map(CompletableFuture::join).filter(Objects::nonNull).count();
        }
    }
}
