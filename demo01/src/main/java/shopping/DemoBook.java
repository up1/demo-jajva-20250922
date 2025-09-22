package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DemoBook {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        // Step 1 : xxx
        for (Integer number : numbers) {
            // Step 2 : yyy
            if(number%2 == 0) {
                System.out.println(number);
            }
        }

        Stream<Integer> numbers1 = numbers.stream();
        long c = numbers1.filter(n -> n % 2 == 0).count();
        System.out.println("Result = "+ c);

        numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .count();
        numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .count();
    }
}
