import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Main {

    static void fillArrayList(ArrayList<Book> BookList, ArrayList<Book> arrayList, String KeyWord) {
        for (Book book : BookList) {
            if (Objects.equals(book.getGenre(), KeyWord)) {
                arrayList.add(book);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Book> BookList = new ArrayList<>();
        BookList.add(new Book("Max", "Detective", "Pushkin", 22.2));
        BookList.add(new Book("Nastya", "Horror", "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", "Comedy", "Thinker", 10.9));
        BookList.add(new Book("Rust", "Fiction", "krug", 85.4));
        BookList.add(new Book("Bong", "Thriller", "King", 24.4));
        BookList.add(new Book("ProbStat", "Erotic", "Gorodos", 9999.9));
        BookList.add(new Book("Center", "Horror", "Osborn", 9.2));

        HashMap<String, ArrayList<Book>> BookGenre = new HashMap<>();

        ArrayList<Book> Horrors = new ArrayList<>();
        ArrayList<Book> Erotics = new ArrayList<>();
        ArrayList<Book> Thrillers = new ArrayList<>();
        ArrayList<Book> Fictions = new ArrayList<>();
        ArrayList<Book> Comedies = new ArrayList<>();
        ArrayList<Book> Detectives = new ArrayList<>();

        fillArrayList(BookList, Horrors, "Horror");
        fillArrayList(BookList, Erotics, "Erotic");
        fillArrayList(BookList, Thrillers, "Thriller");
        fillArrayList(BookList, Fictions, "Fiction");
        fillArrayList(BookList, Comedies, "Comedy");
        fillArrayList(BookList, Detectives, "Detective");

        BookGenre.put("Horror", Horrors);
        BookGenre.put("Detective", Detectives);
        BookGenre.put("Comedy", Comedies);
        BookGenre.put("Fiction", Fictions);
        BookGenre.put("Thriller", Thrillers);
        BookGenre.put("Erotic", Erotics);
        System.out.println(BookGenre);

        DataBase db = DataBase.getInstance(BookList, BookGenre);
    }
}