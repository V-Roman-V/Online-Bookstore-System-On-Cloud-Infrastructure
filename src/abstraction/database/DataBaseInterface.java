package abstraction.database;

import implementation.database.entity.*;

import java.util.ArrayList;

public interface DataBaseInterface {
    // User requests

    /**
     * Request for list of books by a specific author
     *
     * @param author specific author
     * @return list of books
     */
    ArrayList<Book> getBooksByAuthor(Author author);

    /**
     * Request for list of books by a specific genre
     *
     * @param genre specific genre
     * @return list of books
     */
    ArrayList<Book> getBooksByGenre(Genre genre);

    /**
     * Request for list of books by first letter
     *
     * @param letter specific first letter
     * @return list of books
     */
    ArrayList<Book> getBooksByFirstLetter(Character letter);

    /**
     * Request for list of genres
     *
     * @return list of genres
     */
    ArrayList<Genre> getListOfGenres();

    /**
     * Request for list of authors
     *
     * @return list of authors
     */
    ArrayList<Author> getListOfAuthors();

    /**
     * Request for list of all books
     *
     * @return list of books
     */
    ArrayList<Book> getListOfBooks();

    /**
     * Request for information about book reservations
     *
     * @return Is the book available
     */
    Boolean getIsBookAvailable(Book book);

    /**
     * Request for book by ID
     *
     * @return book
     */
    Book getBookByID(int id);

    /**
     * Request for Author by full name
     *
     * @return Author
     */
    Author getAuthorByFullName(String fullname);

    /**
     * Request for Genre by name
     *
     * @return Genre
     */
    Genre getGenreByName(String name);

    /**
     * Request for Order of the book
     *
     * @return reader
     */
    Order getCurrentBookOrder(Book book);

    /**
     * Request for book Reservation
     */
    void reqReserveBook(Book book, String first_name, String last_name);

    /**
     * Request for book releasing
     */
    void reqReleaseBook(Book book);

    // Admin requests
    // ArrayList<Book> getListOfAvailableBook();
    // ArrayList<Book> getListOfReservedBook();
}
