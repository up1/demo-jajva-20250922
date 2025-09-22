package shopping;

public class SampleApp {
    public static void main(String[] args) {
        Book2 b1 = new Book2(1, "Book 01", 100);
        Book2 b2 = new Book2(1, "Book 01", 100);

        if (b1.equals(b2)) {
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }

    }
}
