package shopping;

import java.util.Objects;

class Demo{
    public static void main(String[] args) {
        Book2 b = new Book2(1, "Book01", 100);
        System.out.println(b.id());
        System.out.println(b.name());
        System.out.println(b.price());
        System.out.println(b);
    }
}

record Book2(int id, String name, int price){
}

public class Book {
    private int id;
    private String name;
    private int price;

    public Book(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && price == book.price && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
