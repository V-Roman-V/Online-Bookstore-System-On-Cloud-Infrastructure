package implementation.database;

import implementation.database.entity.*;

import abstraction.database.*;

import java.sql.Date;
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
     * An alternative to the constructor and
     * is the access point to an instance of Library class.
     */
    public static Library getInstance() {
        if (instance == null)
            instance = new Library();
        return instance;
    }

    @Override
    public ArrayList<Book> getBooksByAuthor(Author author){
        return new ArrayList<>(book_table.values().stream().filter( book -> book.author == author).toList());
    }

    @Override
    public ArrayList<Book> getBooksByGenre(Genre genre){
        return new ArrayList<>(book_table.values().stream().filter( book -> book.genre == genre).toList());
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
        return current_order_table.values().stream().anyMatch( order -> order.book == book);
    }

    @Override
    public Book getBookByID(int id) {
        for(Book book: book_table.values())
            if(book.ID == id)
                return book;
        return null;
    }

    @Override
    public Order getCurrentBookOrder(Book book) {
        for(Order order: current_order_table.values())
            if(order.book == book)
                return order;
        return null;
    }

    @Override
    public void reqReserveBook(Book book, String first_name, String last_name) {
        Reader reader = new Reader(first_name, last_name);//TODO
        Order order = new Order(book, reader);
        current_order_table.put(order.getKey(), order);
    }

    @Override
    public void reqReleaseBook(Book book) {
        Order order = getCurrentBookOrder(book);
        current_order_table.remove(order.getKey());
        order.date_return = new Date(System.currentTimeMillis());
        archived_order_table.put(order.getKey(), order);
    }
}