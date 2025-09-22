package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookStore {

    // List of book
    public List<Book> inquiryAllBook2() {
        return null;
    }

    // NPE preventer
    public Optional<List<Book>> inquiryAllBook() {
        // TODO :: Implement detail
        return Optional.empty();
    }

    public static void main(String[] args) {
        BookStore bookStore = new BookStore();
        Optional<List<Book>> a = bookStore.inquiryAllBook();
        // Style 1
        if(a.isPresent()) {
            // Book
        } else {
            // no book
        }

        // Style 2 :: Fail fast / Fail early
        if(a.isEmpty()) {
            // no book
            throw new RuntimeException("No book");
        }

        // Book

    }

}
