package implementation.site;

import implementation.database.entity.Book;
import implementation.database.Library;
import implementation.database.entity.Genre;
import pureooabstraction.site.SiteInterface;

import java.util.*;

/**
 * A class that provides methods for the user to interact with the site
 */
public class Site implements SiteInterface {
    /**
     * Database storing books
     */
    private final Library listOfBooks;

    /**
     * Creates a site using the genres Implementation.database
     *
     * @param genres Implementation.database storing an array of books by genre
     */
    public Site(HashMap<Genre, ArrayList<Book>> genres) {
        listOfBooks = Library.getInstance(genres);
    }

    public void enterSite() {
        System.out.println("Welcome to the \"Booka\" Bookstore website. \nWhat do you want to do?");
    }

    public void exitSite() {
        System.out.println("We will be glad to meet you again in our bookstore  \"Booka\"");
    }

    public int startMenu() {
        String[] variants = { "return book", "see books", "exit" };
        return AskUser(variants);
    }

    public Pair<Genre, Integer> chooseABook() {
        System.out.print("Choose Implementation.database.object.Genre or book by ID ");
        String[] exit = { "exit" };
        printVariantsList(exit);

        String str = getInput();
        if (isExit(str))
            return new Pair<>(null, EXIT);
        for (Genre genre : Genre.values())
            if (str.equalsIgnoreCase(genre.toString()))
                return new Pair<>(genre, CHOOSE_GENRE);
        if (!isNumber(str))
            return new Pair<>(null, INCORRECT);
        int id = Integer.parseInt(str);
        if (listOfBooks.getBook(id) == null)
            return new Pair<>(null, INCORRECT);
        return new Pair<>(null, id);
    }

    public void printBookInfo(int bookID) {
        Book book = listOfBooks.getBook(bookID);
        if (book == null)
            return;
        System.out.println("Implementation.database.object.Book: " + book.getTitle());
        System.out.println("genre: " + book.getGenre());
        System.out.println("author: " + book.getAuthor());
        System.out.println("price: " + book.getPrice() + "$");
    }

    public void printSmallBookInfo(Book book) {
        if (book == null)
            return;
        System.out.println("\t" + book.getTitle() + " {" + book.getAuthor() + "} id=" + book.getID());
    }

    public int askAboutBooking(int bookID) {
        if (listOfBooks.getBook(bookID).isBooked()) {
            System.out.println("This book is already reserved.");
            String[] variants = { "exit" };
            return AskUser(variants);
        }
        System.out.println("Want to reserve a book?");
        String[] variants = { "no", "yes", "exit" };
        return AskUser(variants);
    }

    public boolean bookABook(int bookID) {
        System.out.print("Enter your name ");
        String[] exit = { "exit" };
        printVariantsList(exit);
        String name = getInput();
        if (isExit(name))
            return false;

        System.out.println("To book a book, you will need to pay money, do you agree?");
        String[] variants = { "no", "yes", "exit" };
        if (AskUser(variants) != YES)
            return false;

        listOfBooks.getBook(bookID).setBooker(name);
        System.out.println("The book is successfully reserved (Please remember book ID = " + bookID + ").");
        waitEnter();
        return true;
    }

    public boolean returnABook() {
        while (true) {
            System.out.print("Enter your name ");
            String[] exit = { "exit" };
            printVariantsList(exit);
            String name = getInput();
            if (isExit(name))
                return false;

            System.out.print("Enter the book ID you want to return or");
            printVariantsList(exit);
            String id = getInput();
            if (isExit(id))
                return false;
            if (!isNumber(id))
                break;
            Book book = listOfBooks.getBook(Integer.parseInt(id));
            if (book == null)
                break;
            if (!book.isBooked())
                break;
            if (!book.getBookerName().equals(name))
                break;

            book.releaseBook();
            System.out.println("You successfully returned the book.");
            waitEnter();
            return true;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
        waitEnter();
        return false;
    }

    public void printBookList(Genre gen) {
        if (gen != null) {
            printGenre(gen);
            return;
        }
        for (Genre genre : Genre.values())
            printGenre(genre);
    }

    /**
     * Prints a list of books of one genre
     *
     * @param genre particular genre to print
     */
    private void printGenre(Genre genre) {
        System.out.println(genre.toString() + ":");
        for (Book book : listOfBooks.getBooksGenre().get(genre))
            printSmallBookInfo(book);
        System.out.println();
    }

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