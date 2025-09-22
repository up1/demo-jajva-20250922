package shopping;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Annotation , Decorator
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookWithLombok {
    private int id;
    private String name;
    private int price;
}

class Demo2 {
    public static void main(String[] args) {
        BookWithLombok b1 = new BookWithLombok();
        BookWithLombok b2 = new BookWithLombok(1, "Book 01", 100);

        System.out.println(b1.getId());
        System.out.println(b2);
    }
}
