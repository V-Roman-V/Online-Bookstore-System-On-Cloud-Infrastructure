package implementation.site;

import implementation.database.entity.*;
import implementation.database.Library;
import abstraction.site.SiteInterface;
import java.util.*;

import static abstraction.site.SiteInterface.Response.*;

/**
 * A class that provides methods for the user to interact with the site
 */
public class Site extends SiteInterface {
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
    public Response startMenu() {
        Response[] variants = { RETURN_BOOK, SEE_BOOKS, EXIT };
        return AskUser(variants);
    }

    @Override
    public Pair<Response, ReadOnlyBook> chooseABook() {
        System.out.print("Choose book by ID ");
        Response[] exit = { EXIT };
        printVariantsList(exit);
        String str = getInput();
        if (isExit(str))
            return new Pair<>(EXIT, null);
        if (!isNumber(str))
            return new Pair<>(INCORRECT, null);
        int id = Integer.parseInt(str);
        ReadOnlyBook book = library.getBookByID(id);
        if (book == null)
            return new Pair<>(INCORRECT, null);
        return new Pair<>(YES, book);
    }

    @Override
    public void printBookInfo(ReadOnlyBook book) {
        if (book == null)
            return;
        System.out.println("book: " + book.getTitle());
        System.out.println("genre: " + book.getGenre().getName());
        System.out.println("author: " + book.getAuthor().getFullName());
        System.out.println("price: " + book.getPrice() + "$");
    }

    @Override
    public void printSmallBookInfo(ReadOnlyBook book) {
        if (book == null)
            return;
        System.out.println("\t" + book.getTitle() + " {" + book.getAuthor().getFullName() + "} id=" + book.getID());
    }

    @Override
    public Response askAboutBooking(ReadOnlyBook book) {
        if (!library.getIsBookAvailable(book)) {
            System.out.println("This book is already reserved.");
            Response[] exit = { EXIT };
            return AskUser(exit);
        }
        System.out.println("Want to reserve a book?");
        Response[] variants = { NO, YES, EXIT };
        return AskUser(variants);
    }

    @Override
    public Response askSearchType() {
        System.out.println("Which type of search do you prefer?");
        Response[] variants = { ALPHABETIC, GENRE, AUTHOR, EXIT };
        return AskUser(variants);
    }

    @Override
    public Pair<Response, Character> askLetter() {
        System.out.println("Type a letter to find?");
        Response[] exit = { EXIT };
        printVariantsList(exit);
        String letter = getInput();
        if (isExit(letter))
            return new Pair<>(EXIT, null);
        if (!isLetter(letter))
            return new Pair<>(INCORRECT, null);
        return new Pair<>(YES, letter.charAt(0));
    }

    @Override
    public Pair<Response, ReadOnlyAuthor> askAuthor() {
        System.out.println("Type a author full name to find?");
        Response[] exit = { EXIT };
        printVariantsList(exit);
        String name = getInput();
        if (isExit(name))
            return new Pair<>(EXIT, null);
        ReadOnlyAuthor author = library.getAuthorByFullName(name);
        if (author == null)
            return new Pair<>(INCORRECT, null);
        return new Pair<>(YES, author);
    }

    @Override
    public Pair<Response, ReadOnlyGenre> askGenre() {
        System.out.println("Type a 'genre' to find?");
        Response[] exit = { EXIT };
        printVariantsList(exit);
        String name = getInput();
        if (isExit(name))
            return new Pair<>(EXIT, null);
        ReadOnlyGenre genre = library.getGenreByName(name);
        if (genre == null)
            return new Pair<>(INCORRECT, null);
        return new Pair<>(YES, genre);
    }

    @Override
    public Response bookABook(ReadOnlyBook book) {
        System.out.print("Enter your first name ");
        Response[] exit = { EXIT };
        printVariantsList(exit);
        String first_name = getInput();
        if (isExit(first_name))
            return EXIT;

        System.out.print("Enter your last name ");
        printVariantsList(exit);
        String last_name = getInput();
        if (isExit(last_name))
            return EXIT;

        System.out.println("To book a book, you will need to pay money, do you agree?");
        Response[] variants = { NO, YES, EXIT };
        if (AskUser(variants) != YES)
            return EXIT;

        library.reqReserveBook(book, first_name, last_name);
        System.out.println("The book is successfully reserved (Please remember book ID = " + book.getID() + ").");
        waitEnter();
        return YES;
    }

    @Override
    public Response returnABook() {
        while (true) {
            System.out.print("Enter your first name ");
            Response[] exit = { EXIT };
            printVariantsList(exit);
            String first_name = getInput();
            if (isExit(first_name))
                return EXIT;

            System.out.print("Enter your last name ");
            printVariantsList(exit);
            String last_name = getInput();
            if (isExit(last_name))
                return EXIT;

            var books = library.getListOfBooksByReader(first_name, last_name);
            System.out.println("Ordered books:");
            for(int i = 0; i< books.size(); i++) {
                System.out.print(i + ") ");
                printSmallBookInfo(books.get(i));
            }

            System.out.print("Enter the order number you want to return or ");
            printVariantsList(exit);
            String id = getInput();
            if (isExit(id))
                return EXIT;
            if (!isNumber(id))
                break;
            int num = Integer.parseInt(id);
            if( num < 0 || num >= books.size())
                break;
            var book = books.get(num);
            if (library.getIsBookAvailable(book))
                break;
            library.reqReleaseBook(book);
            System.out.println("You successfully returned the book.");
            waitEnter();
            return YES;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
        waitEnter();
        return NO;
    }

    @Override
    public void printAlphabet() {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int count = library.getBooksByFirstLetter(ch).size();
            if (count == 0)
                continue;
            System.out.println(Character.toUpperCase(ch) + " (" + count + " books)");
        }
    }

    @Override
    public void printGenres() {
        for (ReadOnlyGenre genre : library.getListOfGenres()) {
            int count = library.getBooksByGenre(genre).size();
            if (count == 0)
                continue;
            System.out.println(genre.getName() + " (" + count + " books)");
        }
    }

    @Override
    public void printAuthors() {
        for (ReadOnlyAuthor author : library.getListOfAuthors()) {
            int count = library.getBooksByAuthor(author).size();
            if (count == 0)
                continue;
            System.out.println(author.getFullName() + " (" + count + " books)");
        }
    }

    @Override
    public void printBooksByAuthor(ReadOnlyAuthor author) {
        System.out.println(author.getFullName() + ":");
        for (ReadOnlyBook book : library.getBooksByAuthor(author))
            printSmallBookInfo(book);
        System.out.println();
    }

    @Override
    public void printBooksByGenre(ReadOnlyGenre genre) {
        System.out.println(genre.getName() + ":");
        for (ReadOnlyBook book : library.getBooksByGenre(genre))
            printSmallBookInfo(book);
        System.out.println();
    }

    @Override
    public void printBooksByLetter(Character letter) {
        System.out.println(letter + ":");
        for (ReadOnlyBook book : library.getBooksByFirstLetter(letter))
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
     * @param res list of variants
     * @return user response
     */
    private Response AskUser(Response[] res) {
        printVariantsList(res);
        return calculateInput(getInput(), res);
    }

    /**
     * @return the text printed by the user
     * 
     * TODO make it singleton
     */
    private String getInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Prints out a list of variants to the user
     */
    private void printVariantsList(Response[] res) {
        StringBuilder out = new StringBuilder("{");
        for (int i = 0; i < res.length; i++) {
            out.append("(");
            out.append(res[i] == EXIT ? "-" : Integer.toString(i));
            out.append(")");
            out.append(res[i].name());
            out.append((i + 1 == res.length) ? "}" : "; ");
        }
        System.out.println(out);
    }

    /**
     * Calculates the response received from the user
     *
     * @param str user text
     * @param res list of variants
     * @return user response
     */
    private Response calculateInput(String str, Response[] res) {
        if (isExit(str))
            return EXIT;
        if ((isNumber(str))) {
            int hasExit = (Arrays.asList(res).contains(EXIT)) ? 1 : 0;
            int i = Integer.parseInt(str);
            if (i < 0 || i >= res.length - hasExit)
                return INCORRECT;
            return res[i];
        }
        for (Response r : res)
            if (str.equalsIgnoreCase(r.name()))
                return r;
        return INCORRECT;
    }

    /**
     * Checks if the string is the word 'exit' or '-'
     *
     * @param str string for checking
     */
    private boolean isExit(String str) {
        return (str.equals("-") || str.equalsIgnoreCase(EXIT.name()));
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
     * Checks if the string is a letter
     *
     * @param str string for checking
     */
    private static boolean isLetter(String str) {
        if (str.length() != 1)
            return false;
        char[] ch = str.toCharArray();
        return Character.isLetter(ch[0]);
    }
}