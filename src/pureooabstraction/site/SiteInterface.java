package pureooabstraction.site;

import implementation.database.entity.Book;
import implementation.database.entity.Genre;
import implementation.site.Site;

public interface SiteInterface {
    /**
     * Constants for user responses
     */
    int INCORRECT = -2;
    int EXIT = -1;
    int RETURN_BOOK = 0;
    int SEE_BOOKS = 1;
    int CHOOSE_GENRE = 2;
    int YES = 1;
    int NO = 0;

    /** Prints the welcome text */
    public void enterSite();

    /** Prints the goodbye text*/
    public void exitSite();

    /**
     * Asks the user for further action
     *
     * @return user response {INCORRECT, EXIT, RETURN_BOOK, SEE_BOOKS}
     */
    public int startMenu();

    /**
     * Asks the user to select books
     *
     * @return the user's response as a pair( first: genre; second: user response
     *         {id, INCORRECT, EXIT} )
     */
    public Site.Pair<Genre, Integer> chooseABook();

    /**
     * Prints complete information about the book
     *
     * @param bookID ID of the book to print
     */
    public void printBookInfo(int bookID);

    /**
     * Print short information about the book
     *
     * @param book Implementation.database.object.Book to print
     */
    public void printSmallBookInfo(Book book);
    /**
     * Asks the user if he wants to reserve the book
     *
     * @param bookID ID of selected book
     * @return user response {NO, YES, EXIT}
     */
    public int askAboutBooking(int bookID);

    /**
     * Provides a form for book reservations
     *
     * @param bookID ID of selected book
     * @return was the book reserved
     */
    public boolean bookABook(int bookID);

    /**
     * Provides a form for book returning
     *
     * @return was the book returned
     */
    public boolean returnABook();

    /**
     * Prints a list of books
     *
     * @param gen particular genre to print
     */
    public void printBookList(Genre gen);

    /**
     * Waiting for the Enter key to be pressed
     */
    public void waitEnter();
}
