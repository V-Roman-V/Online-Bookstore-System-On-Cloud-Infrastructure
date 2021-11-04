package implementation.database;

import implementation.database.entity.Book;
import implementation.database.entity.Genre;

import pureooabstraction.database.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing Database which stores books
 */
public final class Library implements DataBaseInterface{
    /**
     * Singleton object
     */
    private static Library instance;

    /**
     * A Hashmap which stores an array of books by genre
     */
    final HashMap<Genre, ArrayList<Book>> genre_of_books;

    /**
     * Creates a Implementation.database using the genres hashmap
     *
     * @param genres hashmap storing an array of book by genre
     */
    private Library(HashMap<Genre, ArrayList<Book>> genres) {
        this.genre_of_books = genres;
    }

    /**
     * @param genres hashmap An alternative to the constructor and is the access
     *               point to an instance of Database class.
     */
    public static Library getInstance(HashMap<Genre, ArrayList<Book>> genres) {
        if (instance == null)
            instance = new Library(genres);
        return instance;
    }

    /**
     * @return genre_of_books hashmap
     */
    public HashMap<Genre, ArrayList<Book>> getBooksGenre() {
        return genre_of_books;
    }

    /**
     * @param BookID
     * @return book stored in genre_of_books hashmap and which BookID converges with
     *         the parameter
     */
    public Book getBook(int BookID) {
        for (ArrayList<Book> v : genre_of_books.values())
            for (Book book : v)
                if (book.getID() == BookID)
                    return book;
        return null;
    }
}