import java.util.*;

/**
 * A class that provides methods for the user to interact with the site
 */
public class Site {
    /**
     * Constants for user responses
     */
    public static final int INCORRECT = -2;
    public static final int EXIT = -1;
    public static final int RETURN_BOOK = 0;
    public static final int SEE_BOOKS = 1;
    public static final int CHOOSE_GENRE = 2;
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     * Database storing books
     */
    private final DataBase listOfBooks;

    /**
     * Creates a site using the genres database
     *
     * @param genres database storing an array of books by genre
     */
    public Site(HashMap<Genre, ArrayList<Book>> genres) {
        listOfBooks = DataBase.getInstance(genres);
    }

    /**
     * Prints the welcome text
     */
    public void enterSite() {
        System.out.println("Welcome to the \"Booka\" Bookstore website. \nWhat do you want to do?");
    }

    /**
     * Prints the goodbye text
     */
    public void exitSite() {
        System.out.println("We will be glad to meet you again in our bookstore  \"Booka\"");
    }

    /**
     * Asks the user for further action
     *
     * @return user response {INCORRECT, EXIT, RETURN_BOOK, SEE_BOOKS}
     */
    public int startMenu() {
        String[] variants = {"return_book", "see_books", "exit"};
        return AskUser(variants);
    }

    /**
     * Asks the user to select books
     *
     * @return the user's response as a pair( first: genre; second: user response {id, INCORRECT, EXIT} )
     */
    public Pair<Genre, Integer> chooseABook() {
        System.out.print("Choose Genre or book by ID ");
        String[] exit = {"exit"};
        printVariantsList(exit);

        String str = getInput();
        if (isExit(str)) return new Pair<>(null, EXIT);
        for (Genre genre : Genre.values())
            if (str.equalsIgnoreCase(genre.toString()))
                return new Pair<>(genre, CHOOSE_GENRE);
        if (!isNumber(str)) return new Pair<>(null, INCORRECT);
        int id = Integer.parseInt(str);
        if (listOfBooks.getBook(id) == null) return new Pair<>(null, INCORRECT);
        return new Pair<>(null, id);
    }

    /**
     * Prints complete information about the book
     *
     * @param bookID ID of the book to print
     */
    public void printBookInfo(int bookID) {
        Book book = listOfBooks.getBook(bookID);
        if (book == null) return;
        System.out.println("Book: " + book.getTitle());
        System.out.println("genre: " + book.getGenre());
        System.out.println("author: " + book.getAuthor());
        System.out.println("price: " + book.getPrice() + "$");
    }

    /**
     * Print short information about the book
     *
     * @param book Book to print
     */
    public void printSmallBookInfo(Book book) {
        if (book == null) return;
        System.out.println("\t" + book.getTitle() + " {" + book.getAuthor() + "} id=" + book.getID());
    }

    /**
     * Asks the user if he wants to reserve the book
     *
     * @param bookID ID of selected book
     * @return user response {NO, YES, EXIT}
     */
    public int askAboutBooking(int bookID) {
        if (listOfBooks.getBook(bookID).isBooked()) {
            System.out.println("This book is already reserved.");
            String[] variants = {"exit"};
            return AskUser(variants);
        }
        System.out.println("Want to reserve a book?");
        String[] variants = {"no", "yes", "exit"};
        return AskUser(variants);
    }

    /**
     * Provides a form for book reservations
     *
     * @param bookID ID of selected book
     * @return was the book reserved
     */
    public boolean bookABook(int bookID) {
        System.out.print("Enter your name ");
        String[] exit = {"exit"};
        printVariantsList(exit);
        String name = getInput();
        if (isExit(name)) return false;

        System.out.println("To book a book, you will need to pay money, do you agree?");
        String[] variants = {"no", "yes", "exit"};
        if (AskUser(variants) != YES) return false;

        listOfBooks.getBook(bookID).setBooker(name);
        System.out.println("The book is successfully reserved (Please remember book ID = " + bookID + ").");
        waitEnter();
        return true;
    }

    /**
     * Provides a form for book returning
     *
     * @return was the book returned
     */
    public boolean returnABook() {
        while (true) {
            System.out.print("Enter your name ");
            String[] exit = {"exit"};
            printVariantsList(exit);
            String name = getInput();
            if (isExit(name)) return false;

            System.out.print("Enter the book ID you want to return or");
            printVariantsList(exit);
            String id = getInput();
            if (isExit(id)) return false;
            if (!isNumber(id)) break;
            Book book = listOfBooks.getBook(Integer.parseInt(id));
            if (book == null) break;
            if (!book.isBooked()) break;
            if (!book.getBookerName().equals(name)) break;

            book.releaseBook();
            System.out.println("You successfully returned the book.");
            waitEnter();
            return true;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
        waitEnter();
        return false;
    }

    /**
     * Prints a list of books
     *
     * @param gen particular genre to print
     */
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

    /**
     * Waiting for the Enter key to be pressed
     */
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
        if (isExit(str)) return EXIT;
        if ((isNumber(str))) {
            int hasExit = (Arrays.asList(variants).contains("exit")) ? 1 : 0;
            int i = Integer.parseInt(str);
            if (i < 0 || i >= variants.length - hasExit) return INCORRECT;
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