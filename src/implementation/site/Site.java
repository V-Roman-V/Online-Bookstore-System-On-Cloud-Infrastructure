package implementation.site;

import implementation.database.entity.Book;
import implementation.database.Library;
import implementation.database.entity.Genre;
import abstraction.site.SiteInterface;
import implementation.database.entity.Order;
import implementation.database.entity.Reader;

import java.util.*;

/**
 * A class that provides methods for the user to interact with the site
 */
public class Site implements SiteInterface {
    /**
     * Database storing books
     */
    private final Library library;

    /**
     * Creates a site
     */
    public Site() {
        library = Library.getInstance();
    }

    @Override
    public void enterSite() {
        System.out.println("Welcome to the \"Booka\" Bookstore website. \nWhat do you want to do?");
    }

    @Override
    public void exitSite() {
        System.out.println("We will be glad to meet you again in our bookstore  \"Booka\"");
    }

    @Override
    public int startMenu() {
        String[] variants = { "return book", "see books", "exit" };
        return AskUser(variants);
    }

    @Override
    public Pair<Genre, Integer> chooseABook() {
        System.out.print("Choose genre or book by ID ");
        String[] exit = { "exit" };
        printVariantsList(exit);

        String str = getInput();
        if (isExit(str))
            return new Pair<>(null, EXIT);
        for (Genre genre : library.getListOfGenres())
            if (str.equalsIgnoreCase(genre.name))
                return new Pair<>(genre, CHOOSE_GENRE);
        if (!isNumber(str))
            return new Pair<>(null, INCORRECT);
        int id = Integer.parseInt(str);
        if (library.getBookByID(id) == null)
            return new Pair<>(null, INCORRECT);
        return new Pair<>(null, id);
    }

    @Override
    public void printBookInfo(int bookID) {
        Book book = library.getBookByID(bookID);
        if (book == null)
            return;
        System.out.println("Book: " + book.title);
        System.out.println("genre: " + book.genre.name);
        System.out.println("author: " + book.author.fullName());
        System.out.println("price: " + book.price + "$");
    }

    @Override
    public void printSmallBookInfo(Book book) {
        if (book == null)
            return;
        System.out.println("\t" + book.title + " {" + book.author.fullName() + "} id=" + book.ID);
    }

    @Override
    public int askAboutBooking(int bookID) {
        Book book = library.getBookByID(bookID);

        if (!library.getIsBookAvailable(book)) {
            System.out.println("This book is already reserved.");
            String[] variants = { "exit" };
            return AskUser(variants);
        }
        System.out.println("Want to reserve a book?");
        String[] variants = { "no", "yes", "exit" };
        return AskUser(variants);
    }

    @Override
    public boolean bookABook(int bookID) {
        System.out.print("Enter your first name ");
        String[] exit = { "exit" };
        printVariantsList(exit);
        String first_name = getInput();
        if (isExit(first_name))
            return false;

        System.out.print("Enter your last name ");
        printVariantsList(exit);
        String last_name = getInput();
        if (isExit(last_name))
            return false;

        System.out.println("To book a book, you will need to pay money, do you agree?");
        String[] variants = { "no", "yes", "exit" };
        if (AskUser(variants) != YES)
            return false;

        Book book = library.getBookByID(bookID);
        library.reqReserveBook(book, first_name, last_name);
        System.out.println("The book is successfully reserved (Please remember book ID = " + bookID + ").");
        waitEnter();
        return true;
    }

    @Override
    public boolean returnABook() {
        while (true) {
            System.out.print("Enter your first name ");
            String[] exit = { "exit" };
            printVariantsList(exit);
            String first_name = getInput();
            if (isExit(first_name))
                return false;

            System.out.print("Enter your last name ");
            printVariantsList(exit);
            String last_name = getInput();
            if (isExit(last_name))
                return false;

            System.out.print("Enter the book ID you want to return or");
            printVariantsList(exit);
            String id = getInput();
            if (isExit(id))
                return false;
            if (!isNumber(id))
                break;
            Book book = library.getBookByID(Integer.parseInt(id));
            if (book == null)
                break;
            if (library.getIsBookAvailable(book))
                break;
            Order order = library.getCurrentBookOrder(book);
            if (order == null)
                break;
            if (order.reader.first_name.equals(first_name) && order.reader.last_name.equals(last_name))
                break;

            library.reqReleaseBook(book);
            System.out.println("You successfully returned the book.");
            waitEnter();
            return true;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
        waitEnter();
        return false;
    }

    @Override
    public void printBookList(Genre gen) {
        if (gen != null) {
            printGenre(gen);
            return;
        }
        for (Genre genre : library.getListOfGenres())
            printGenre(genre);
    }

    /**
     * Prints a list of books of one genre
     *
     * @param genre particular genre to print
     */
    private void printGenre(Genre genre) {
        System.out.println(genre.toString() + ":");
        for (Book book : library.getBooksByGenre(genre))
            printSmallBookInfo(book);
        System.out.println();
    }

    @Override
    public void waitEnter() {
        System.out.println("{Press enter to continue}");
        getInput();
    }

    /**
     * Prints out a list of variants to the user and returns the answer
     *
     * @param var list of variants
     * @return user response
     */
    private int AskUser(String[] var) {
        printVariantsList(var);
        return calculateInput(getInput(), var);
    }

    /**
     * @return the text printed by the user
     */
    private String getInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Prints out a list of variants to the user
     */
    private void printVariantsList(String[] variants) {
        StringBuilder out = new StringBuilder("{");
        for (int i = 0; i < variants.length; i++) {
            out.append("(");
            out.append(variants[i].equals("exit") ? "-" : Integer.toString(i));
            out.append(")");
            out.append(variants[i]);
            out.append((i + 1 == variants.length) ? "}" : "; ");
        }
        System.out.println(out);
    }

    /**
     * Calculates the response received from the user
     *
     * @param str      user text
     * @param variants list of variants
     * @return user response
     */
    private int calculateInput(String str, String[] variants) {
        if (isExit(str))
            return EXIT;
        if ((isNumber(str))) {
            int hasExit = (Arrays.asList(variants).contains("exit")) ? 1 : 0;
            int i = Integer.parseInt(str);
            if (i < 0 || i >= variants.length - hasExit)
                return INCORRECT;
            return i;
        }
        for (int i = 0; i < variants.length; i++)
            if (str.equalsIgnoreCase(variants[i]))
                return i;
        return INCORRECT;
    }

    /**
     * Checks if the string is the word 'exit' or '-'
     *
     * @param str string for checking
     */
    private boolean isExit(String str) {
        return (str.equals("-") || str.equalsIgnoreCase("exit"));
    }

    /**
     * Checks if the string is a number
     *
     * @param str string for checking
     */
    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

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