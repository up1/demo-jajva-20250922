package org.example;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Calculator calculator = new Calculator();
        int result = calculator.add(1, 3);
        System.out.println("Result=" + result);

        // Create instance 1
        Main o1 = new Main();
        o1.doNothing();
        // Create instance 2
        Main o2 = new Main();
        o2.doNothing();

        // GC = Garbage collector
        o1 = null;
        o1.returnInt(); // NPE
    }

    void doNothing() {
    }

    int returnInt() {
        return 1;
    }
}