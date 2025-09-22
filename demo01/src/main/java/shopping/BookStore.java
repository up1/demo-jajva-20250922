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
            System.out.println(b.toString());
        }

    }

    // NPE preventer
    // <> => Generic
    public Optional<List<Book>> inquiryAllBook() {
        // Dummy books
        List<Book> books = new ArrayList();
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());

        return Optional.of(books);
    }

}
