package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookStore {

    public static void main(String[] args) {
        BookStore bookStore = new BookStore();
        Optional<List<Book>> results = bookStore.inquiryAllBook();
        if (results.isEmpty()) {
            System.out.println("Book not found");
        }
        // show all book
        List<Book> books = results.get();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString());
        }

        for (Book b : books) {
            System.out.println(b.getId());
            System.out.println(b.getName());
        }

    }

    // NPE preventer
    // <> => Generic
    public Optional<List<Book>> inquiryAllBook() {
        // Dummy books
        List<Book> books = new ArrayList();
        books.add(new Book(1, "Book 01", 100));
        books.add(new Book(2, "Book 02", 100));
        books.add(new Book(3, "Book 03", 100));

        return Optional.of(books);
    }

}
