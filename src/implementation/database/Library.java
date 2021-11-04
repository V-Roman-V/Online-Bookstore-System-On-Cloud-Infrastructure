package implementation.database;

import implementation.database.entity.Author;
import implementation.database.entity.Book;
import implementation.database.entity.Genre;

import abstraction.database.*;
import implementation.database.entity.Reader;

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
     * A Hashmap which stores a databases
     */
    final HashMap<String, Book> book_table;
    final HashMap<String, Author> author_table;
    final HashMap<String, Reader> reader_table;
    final HashMap<String, Genre> genre_table;
    final HashMap<String, Order> order_table;

    /**
     * Creates a database using the genres hashmap
     *
     * @param genres hashmap storing an array of book by genre
     */
    private Library() {
        book_table   = new HashMap<String, Book>();
        author_table = new HashMap<String, Author>();
        reader_table = new HashMap<String, Reader>();
        genre_table  = new HashMap<String, Genre>();
        order_table  = new HashMap<String, Order>();
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