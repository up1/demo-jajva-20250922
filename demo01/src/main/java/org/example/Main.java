package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        System.out.print("Hello and welcome!");
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