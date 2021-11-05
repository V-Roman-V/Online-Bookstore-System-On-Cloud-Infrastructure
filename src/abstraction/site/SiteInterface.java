package abstraction.site;

import implementation.database.entity.ReadOnlyAuthor;
import implementation.database.entity.ReadOnlyBook;
import implementation.database.entity.ReadOnlyGenre;

public abstract class SiteInterface {
    /** Constants for user responses */
    public enum Response {
        INCORRECT, EXIT, RETURN_BOOK, SEE_BOOKS, ALPHABETIC, GENRE, AUTHOR, YES, NO
    }


    /** Prints the welcome text */
    public abstract void enterSite();

    /** Prints the goodbye text */
    public abstract void exitSite();

    /**
     * Asks the user for further action
     *
     * @return user response {RETURN_BOOK, SEE_BOOKS, EXIT, INCORRECT}
     */
    public abstract Response startMenu();

    /**
     * Requests the user to select a letter
     *
     * @return pair: first=Response{Exit,Incorrect, YES}, second=letter
     */
    public abstract Pair<Response, Character> askLetter();

    /**
     * Requests the user to select an author
     *
     * @return pair: first=Response{Exit,Incorrect, YES}, second=author
     */
    public abstract Pair<Response, ReadOnlyAuthor> askAuthor();

    /**
     * Requests the user to select a genre
     *
     * @return pair: first=Response{Exit,Incorrect, YES}, second=genre
     */
    public abstract Pair<Response, ReadOnlyGenre> askGenre();

    /**
     * Prints a list of books of one author
     *
     * @param author specific author
     */
    public abstract void printBooksByAuthor(ReadOnlyAuthor author);

    /**
     * Prints a list of books of one genre
     *
     * @param genre specific author
     */
    public abstract void printBooksByGenre(ReadOnlyGenre genre);

    /**
     * Prints a list of books with first Letter
     *
     * @param letter specific author
     */
    public abstract void printBooksByLetter(Character letter);

    /**
     * Asks the user to select books
     *
     * @return pair: first=Response{Exit,Incorrect, YES}, second=book
     */
    public abstract Pair<Response, ReadOnlyBook> chooseABook();

    /**
     * Prints complete information about the book
     *
     * @param book book to print
     */
    public abstract void printBookInfo(ReadOnlyBook book);

    /**
     * Print short information about the book
     *
     * @param book book to print
     */
    public abstract void printSmallBookInfo(ReadOnlyBook book);
    /**
     * Asks the user if he wants to reserve the book
     *
     * @param book selected book
     * @return user response {NO, YES, EXIT}
     */
    public abstract Response askAboutBooking(ReadOnlyBook book);

    /**
     * Asks the user if he wants to reserve the book
     *
     * @return user response {alphabetic, genre, author, exit, incorrect}
     */
    public abstract Response askSearchType();

    /**
     * Provides a form for book reservations
     *
     * @param book selected book
     * @return was the book reserved: user response {Yes, No}
     */
    public abstract Response bookABook(ReadOnlyBook book);

    /**
     * Provides a form for book returning
     *
     * @return was the book returned: user response {Yes, No}
     */
    public abstract Response returnABook();

    /**
     * Prints a list of books alphabetically
     */
    public abstract void printAlphabet();

    /**
     * Prints a list of books by genre
     */
    public abstract void printGenres();

    /**
     * Prints a list of books by author
     */
    public abstract void printAuthors();

    /**
     * Waiting for the Enter key to be pressed
     */
    public abstract void waitEnter();

    /**
     * A helper class for returning multiple values from a function
     */
    public static class Pair<T, T1> {
        public T first;
        public T1 second;

        public Pair(T f, T1 s) {
            first = f;
            second = s;
        }
    }
}
