package abstraction.database;

import implementation.database.entity.Author;
import implementation.database.entity.Book;
import implementation.database.entity.Genre;

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

// Admin requests
//    ArrayList<Book> getListOfAvailableBook();
//    ArrayList<Book> getListOfReservedBook();
//    Requester getCurrentBookRequester();
}
