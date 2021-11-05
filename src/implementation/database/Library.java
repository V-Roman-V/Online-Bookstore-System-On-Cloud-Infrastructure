package implementation.database;

import implementation.database.entity.*;

import abstraction.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A class representing Library which stores books
 */
public final class Library implements DataBaseInterface{

    /** Singleton object */
    private static Library instance;

    /** Hashmaps which stores a databases */
    final HashMap<String, Book> book_table;
    final HashMap<String, Author> author_table;
    final HashMap<String, Reader> reader_table;
    final HashMap<String, Genre> genre_table;
    final HashMap<String, Order> current_order_table;
    final HashMap<String, Order> archived_order_table;

    /** Creates a library */
    private Library() {
        book_table   = new HashMap<String, Book>();
        author_table = new HashMap<String, Author>();
        reader_table = new HashMap<String, Reader>();
        genre_table  = new HashMap<String, Genre>();
        current_order_table  = new HashMap<String, Order>();
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
    public ArrayList<Book> getBooksByAuthor(Author author){
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getAuthor() == author).toList());
    }

    @Override
    public ArrayList<Book> getBooksByGenre(Genre genre){
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getGenre() == genre).toList());
    }

    @Override
    public ArrayList<Genre> getListOfGenres(){
        return new ArrayList<>(genre_table.values());
    }

    @Override
    public ArrayList<Author> getListOfAuthors(){
        return new ArrayList<>(author_table.values());
    }

    @Override
    public ArrayList<Book> getListOfBooks(){
        return new ArrayList<>(book_table.values());
    }

    @Override
    public Boolean getIsBookAvailable(Book book){
        return current_order_table.values().stream().anyMatch(order -> order.book == book);
    }
    
    /**
     * This class is access point for creating, modifying, and deleting an entity.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    public class BookForm {
        private final Book _book;
        private final String _book_key;

        public BookForm(String book_title, Author author) {
            _book_key = Book.getKey(book_title, author);

            if (null == book_table.get(_book_key))
                book_table.put(_book_key, new Book(book_title, author));

            _book = book_table.get(_book_key);

        }

        public BookForm genre(Genre genre) {
            _book.setGenre(genre);
            return this;
        }

        public BookForm note(String note) {
            _book.setNote(note);
            return this;
        }

        public BookForm price(Double price) {
            _book.setPrice(price);
            return this;
        }

        public void delete() {
            book_table.remove(_book_key);
        }
    }
}