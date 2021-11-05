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
    ArrayList<ReadOnlyBook> getBooksByAuthor(ReadOnlyAuthor author);

    /**
     * Request for list of books by a specific genre
     *
     * @param genre specific genre
     * @return list of books
     */
    ArrayList<ReadOnlyBook> getBooksByGenre(ReadOnlyGenre genre);

    /**
     * Request for list of books by first letter
     *
     * @param letter specific first letter
     * @return list of books
     */
    ArrayList<ReadOnlyBook> getBooksByFirstLetter(Character letter);

    /**
     * Request for list of genres
     *
     * @return list of genres
     */
    ArrayList<ReadOnlyGenre> getListOfGenres();

    /**
     * Request for list of authors
     *
     * @return list of authors
     */
    ArrayList<ReadOnlyAuthor> getListOfAuthors();

    /**
     * Request for list of all books
     *
     * @return list of books
     */
    ArrayList<ReadOnlyBook> getListOfBooks();

    /**
     * Request for information about book reservations
     *
     * @return Is the book available
     */
    Boolean getIsBookAvailable(ReadOnlyBook book);

    /**
     * Request for book by ID
     *
     * @return book
     */
    ReadOnlyBook getBookByID(int id);

    /**
     * Request for author by full name
     *
     * @return ReadOnlyAuthor
     */
    ReadOnlyAuthor getAuthorByFullName(String fullname);

    /**
     * Request for genre by name
     *
     * @return genre
     */
    ReadOnlyGenre getGenreByName(String name);

    /**
     * Request for order of the book
     *
     * @return reader
     */
    ReadOnlyOrder getCurrentBookOrder(ReadOnlyBook book);

    /**
     * Request for book Reservation
     */
    void reqReserveBook(ReadOnlyBook book, String first_name, String last_name);

    /**
     * Request for book releasing
     */
    void reqReleaseBook(ReadOnlyBook book);

    // Admin requests
    // ArrayList<ReadOnlyBook> getListOfAvailableBook();
    // ArrayList<ReadOnlyBook> getListOfReservedBook();
}
