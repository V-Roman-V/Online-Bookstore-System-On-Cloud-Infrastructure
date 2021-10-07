import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing Database which stores books
 */
final class DataBase {
    /**
     * Singleton object
     */
    private static DataBase instance;

    /**
     * A Hashmap which stores an array of book class objects by genre
     */
    final HashMap<Genre, ArrayList<Book>> genre_of_books;

    /**
     * Creates a database using the genres hashmap
     *
     * @param genres hashmap storing an array of book class objects by genre
     */
    private DataBase(HashMap<Genre, ArrayList<Book>> genres) {
        this.genre_of_books = genres;
    }

    /**
     * @param genres hashmap
     * An alternative to the constructor and
     * is the access point to an instance of Database class.
     */
    public static DataBase getInstance(HashMap<Genre, ArrayList<Book>> genres) {
        if (instance == null)
            instance = new DataBase(genres);
        return instance;
    }

    /**
     * @return of genre_of_books hashmap
     */
    public HashMap<Genre, ArrayList<Book>> getBooksGenre() {
        return genre_of_books;
    }

    /**
     * @param BookID
     * @return book class object stored in genre_of_books hashmap
     * and which BookID converges with the parameter
     */
    public Book getBook(int BookID) {
        for (ArrayList<Book> v : genre_of_books.values())
            for (Book book : v)
                if (book.getID() == BookID)
                    return book;
        return null;
    }
}