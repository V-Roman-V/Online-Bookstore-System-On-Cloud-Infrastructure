import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Book> BookList = new ArrayList<>();
        BookList.add(new Book("Max", "Detective", "Pushkin", 22.2));
        BookList.add(new Book("Nastya", "Horror", "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", "Comedy", "Thinker", 10.9));
        BookList.add(new Book("Rust", "Fiction", "krug", 85.4));
        BookList.add(new Book("Bong", "Thriller", "King", 24.4));
        BookList.add(new Book("Probstat", "Erotic", "Gorodos", 9999.9));

//        System.out.println(BookList.get(0).getAuthor());
//        System.out.println(BookList.get(0).getID());
//        System.out.println(BookList.get(1).getID());
//        System.out.println(BookList.get(2).getID());

        DataBase db = DataBase.getInstance(BookList);

    }
}
