import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Main {

    static void fillArrayList(ArrayList<Book> BookList, ArrayList<Book> arrayList, Genre KeyWord) {
        for (Book book : BookList) {
            if (Objects.equals(book.getGenre(), KeyWord)) {
                arrayList.add(book);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Book> BookList = new ArrayList<>();
        BookList.add(new Book("Max", Genre.DETECTIVE, "Pushkin", 22.2));
        BookList.add(new Book("Nastya", Genre.HORROR, "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", Genre.COMEDY, "Thinker", 10.9));
        BookList.add(new Book("Rust", Genre.FICTION, "krug", 85.4));
        BookList.add(new Book("Bong", Genre.THRILLER, "King", 24.4));
        BookList.add(new Book("ProbStat", Genre.EROTIC, "Gorodos", 9999.9));
        BookList.add(new Book("Center", Genre.HORROR, "Osborn", 9.2));

        HashMap<Genre, ArrayList<Book>> BookGenre = new HashMap<>();

        ArrayList<Book> Horrors = new ArrayList<>();
        ArrayList<Book> Erotics = new ArrayList<>();
        ArrayList<Book> Thrillers = new ArrayList<>();
        ArrayList<Book> Fictions = new ArrayList<>();
        ArrayList<Book> Comedies = new ArrayList<>();
        ArrayList<Book> Detectives = new ArrayList<>();

        fillArrayList(BookList, Horrors, Genre.HORROR);
        fillArrayList(BookList, Erotics, Genre.EROTIC);
        fillArrayList(BookList, Thrillers, Genre.THRILLER);
        fillArrayList(BookList, Fictions, Genre.FICTION);
        fillArrayList(BookList, Comedies, Genre.COMEDY);
        fillArrayList(BookList, Detectives, Genre.DETECTIVE);

        BookGenre.put(Genre.HORROR, Horrors);
        BookGenre.put(Genre.DETECTIVE, Detectives);
        BookGenre.put(Genre.COMEDY, Comedies);
        BookGenre.put(Genre.FICTION, Fictions);
        BookGenre.put(Genre.THRILLER, Thrillers);
        BookGenre.put(Genre.EROTIC, Erotics);
        System.out.println(BookGenre);

        DataBase db = DataBase.getInstance(BookList, BookGenre);
    }
}