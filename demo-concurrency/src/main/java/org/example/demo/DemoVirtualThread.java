package org.example.demo;

import java.util.ArrayList;

import static java.lang.Thread.startVirtualThread;

public class DemoVirtualThread {
    public static final int THREAD_SIZE = 10_000_000;

    public static void main(String[] args) throws Exception {
        var start = System.nanoTime();
        var threads = new ArrayList<Thread>(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE; i++) {
            var thread = startVirtualThread(() -> {});
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        var end = System.nanoTime();
        var duration = java.time.Duration.ofNanos(end - start);
        System.out.println("Elapsed time: " + duration.toMillis() + " ms");
    }
}
