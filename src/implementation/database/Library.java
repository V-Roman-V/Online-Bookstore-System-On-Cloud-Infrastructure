package implementation.database;

import implementation.database.entity.*;

import abstraction.database.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing Library which stores books
 */
public final class Library implements DataBaseInterface {

    /** Singleton object */
    private static Library instance;

    /** Hashmaps which stores a databases */
    static HashMap<String, Book> book_table;
    static HashMap<String, Author> author_table;
    static HashMap<String, Reader> reader_table;
    static HashMap<String, Genre> genre_table;
    static HashMap<String, Order> current_order_table;
    static HashMap<String, Order> archived_order_table;

    /** Creates a library */
    private Library() {
        book_table = new HashMap<String, Book>();
        author_table = new HashMap<String, Author>();
        reader_table = new HashMap<String, Reader>();
        genre_table = new HashMap<String, Genre>();
        current_order_table = new HashMap<String, Order>();
        archived_order_table = new HashMap<String, Order>();
    }

    /**
     * An alternative to the constructor and is the access point to an instance of
     * Library class.
     */
    public static Library getInstance() {
        if (instance == null)
            instance = new Library();
        return instance;
    }

    @Override
    public ArrayList<Book> getBooksByAuthor(Author author) {
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getAuthor() == author).toList());
    }

    @Override
    public ArrayList<Book> getBooksByGenre(Genre genre) {
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getGenre() == genre).toList());
    }

    @Override
    public ArrayList<Genre> getListOfGenres() {
        return new ArrayList<>(genre_table.values());
    }

    @Override
    public ArrayList<Author> getListOfAuthors() {
        return new ArrayList<>(author_table.values());
    }

    @Override
    public ArrayList<Book> getListOfBooks() {
        return new ArrayList<>(book_table.values());
    }

    @Override
    public Boolean getIsBookAvailable(Book book) {
        return current_order_table.values().stream().anyMatch(order -> order.getBook() == book);
    }

    /**
     * This class is access point for creating, modifying, and deleting a Book.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    public class BookForm {
        public static Book getInstance(String book_title, Author author) {
            String _book_key = Book.getKey(book_title, author);
            if (null == book_table.get(_book_key))
                book_table.put(_book_key, new Book(book_title, author));

            return book_table.get(_book_key);
        }

        public static Book getInstance(String book_title, String first_name, String last_name) {
            Author _author = Library.AuthorForm.getInstance(first_name, last_name);
            return Library.BookForm.getInstance(book_title, _author);
        }

        public static void delete(String book_title, Author author) {
            String _book_key = Book.getKey(book_title, author);
            book_table.remove(_book_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting an Author.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    public class AuthorForm {
        public static Author getInstance(String first_name, String last_name) {
            String _author_key = Author.getKey(first_name, last_name);
            if (null == author_table.get(_author_key))
                author_table.put(_author_key, new Author(first_name, last_name));

            return author_table.get(_author_key);
        }

        public static void delete(String first_name, String last_name) {
            String _author_key = Author.getKey(first_name, last_name);
            author_table.remove(_author_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting a Genre.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    public class GenreForm {
        public static Genre getInstance(String name) {
            String _genre_key = Genre.getKey(name);
            if (null == genre_table.get(_genre_key))
                genre_table.put(_genre_key, new Genre(name));

            return genre_table.get(_genre_key);
        }

        public static void delete(String name) {
            String _genre_key = Genre.getKey(name);
            genre_table.remove(_genre_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting a Reader.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    public class ReaderForm {
        public static Reader getInstance(String first_name, String last_name) {
            String _reader_key = Reader.getKey(first_name, last_name);
            if (null == reader_table.get(_reader_key))
                reader_table.put(_reader_key, new Reader(first_name, last_name));

            return reader_table.get(_reader_key);
        }

        public static void delete(String first_name, String last_name) {
            String _reader_key = Reader.getKey(first_name, last_name);
            reader_table.remove(_reader_key);
        }
    }

}