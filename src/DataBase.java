import java.util.ArrayList;
import java.util.HashMap;

final class DataBase {
    private static DataBase instance;
    HashMap<Genre, ArrayList<Book>> genre_of_books;

    private DataBase(HashMap<Genre, ArrayList<Book>> genres) {
        this.genre_of_books = genres;
    }

    public static DataBase getInstance(HashMap<Genre, ArrayList<Book>> genres) {
        if (instance == null)
            instance = new DataBase(genres);
        return instance;
    }

    public HashMap<Genre, ArrayList<Book>> getBooksGenre() {
        return genre_of_books;
    }

    public Book getBook(int BookID) {
        for (ArrayList<Book> v : genre_of_books.values())
            for (Book book : v)
                if (book.getID() == BookID)
                    return book;
        return null;
    }
}