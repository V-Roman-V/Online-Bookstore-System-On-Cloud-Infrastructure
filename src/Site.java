import java.util.*;

/**
 *
 */
public class Site {
    public static final int INCORRECT = -2;
    public static final int EXIT = -1;
    public static final int RETURN_BOOK = 0;
    public static final int SEE_BOOKS = 1;
    public static final int CHOOSE_GENRE = 2;
    public static final int YES = 1;
    public static final int NO = 0;

    private final DataBase listOfBooks;

    /**
     * @param genres
     */
    public Site(HashMap<Genre, ArrayList<Book>> genres) {
        listOfBooks = DataBase.getInstance(genres);
    }

    /**
     *
     */
    public void enterSite() {
        System.out.println("Welcome to the \"Booka\" Bookstore website. \nWhat do you want to do?");
    }

    /**
     *
     */
    public void exitSite() {
        System.out.println("We will be glad to meet you again in our bookstore  \"Booka\"");
    }

    /**
     * @return
     */
    public int startMenu() {
        String[] variants = {"return_book", "see_books", "exit"};
        return AskUser(variants);
    }

    /**
     * @return
     */
    public Pair<Genre, Integer> chooseABook(Genre gen) {
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
     * @param bookID
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
     * @param book
     */
    public void printSmallBookInfo(Book book) {
        if (book == null) return;
        System.out.println("\t" + book.getTitle() + " {" + book.getAuthor() + "} id=" + book.getID());
    }

    /**
     * @param bookID
     * @return
     */
    public int askAboutBooking(int bookID) {
        if (listOfBooks.getBook(bookID).isBooked()) {
            System.out.println("This book is already reserved.");
            String[] variants = {"exit"};
            return AskUser(variants);
        } else {
            System.out.println("Want to reserve a book?");
            String[] variants = {"no", "yes", "exit"};
            return AskUser(variants);
        }
    }

    /**
     * @param bookID
     * @return
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
        return true;
    }

    /**
     * @return
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
            return true;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
        return true;
    }

    /**
     *
     */
    public void printBookList(Genre gen) {
        if (gen != null) {
            printGenre(gen);
            return;
        }
        for (Genre genre : Genre.values())
            printGenre(genre);
    }

    private void printGenre(Genre genre) {
        System.out.println(genre.toString() + ":");
        for (Book book : listOfBooks.getBooksGenre().get(genre))
            printSmallBookInfo(book);
        System.out.println();
    }

    /**
     *
     */
    public void waitEnter() {
        System.out.println("{Press enter to continue}");
        getInput();
    }

    private int AskUser(String[] var) {
        printVariantsList(var);
        return calculateInput(getInput(), var);
    }

    private boolean isExit(String str) {
        return (str.equals("-") || str.equals("exit"));
    }

    private String getInput() {
        return new Scanner(System.in).nextLine();
    }

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

    private int calculateInput(String str, String[] variants) {
        if (isExit(str)) return EXIT;
        if ((isNumber(str))) {
            int hasExit = (Arrays.asList(variants).contains("exit")) ? 1 : 0;
            int i = Integer.parseInt(str);
            if (i < 0 || i >= variants.length - hasExit) return INCORRECT;
            return i;
        }
        for (int i = 0; i < variants.length; i++)
            if (str.equals(variants[i]))
                return i;
        return INCORRECT;
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public class Pair<T, T1> {
        public T first;
        public T1 second;

        public Pair(T f, T1 s) {
            first = f;
            second = s;
        }
    }
}