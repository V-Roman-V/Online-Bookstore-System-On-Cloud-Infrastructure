import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Site {
    public static final int INCORRECT = -2;
    public static final int EXIT = -1;
    public static final int RETURN_BOOK = 0;
    public static final int SEE_BOOKS = 1;
    public static final int YES = 1;
    public static final int NO = 0;

    private final DataBase listOfBooks;
    public Site(){listOfBooks = DataBase.getInstance();}
    public void enterSite(){System.out.println("Welcome to the \"Booka\" Bookstore website. \nWhat do you want to do?");}
    public void exitSite(){System.out.println("We will be glad to meet you again in our bookstore  \"Booka\"");}

    public int startMenu(){
        String[] variants = {"return_book","see_books","exit"};
        return AskUser(variants);
    }

    public int chooseABook(){
        System.out.print("Choose bookID or ");
        String[] exit = {"exit"};
        printVariantsList(exit);

        String str = getInput();
        if(isExit(str)) return EXIT;
        if(!isNumber(str)) return INCORRECT;
        int id = Integer.parseInt(str);
        if(listOfBooks.getBook(id) == null) return INCORRECT;
        return id;
    }
    public void printBookInfo(int bookID){
        Book book = listOfBooks.getBook(bookID);
        System.out.println("Book: "+book.getTitle());
        System.out.println("genre: "+book.getTitle());
        System.out.println("author: "+book.getAuthor());
        System.out.println("price: "+book.getPrice()+"$");
    }
    public void printSmallBookInfo(Book book){
        System.out.println("Book("+book.getID()+"): "+book.getTitle());
    }
    public int askAboutBooking(int bookID){
        if(listOfBooks.getBook(bookID).isBooked()){
            System.out.println("This book is already reserved.");
            String[] variants = {"exit"};
            return AskUser(variants);
        } else {
            System.out.println("Want to reserve a book?");
            String[] variants = {"no","yes","exit"};
            return AskUser(variants);
        }
    }
    public boolean bookABook(int bookID) {
        System.out.print("Enter your name or ");
        String[] exit = {"exit"};
        printVariantsList(exit);
        String name = getInput();
        if (isExit(name)) return false;

        System.out.println("To book a book, you will need to pay money, do you agree?");
        String[] variants = {"no", "yes", "exit"};
        if (AskUser(variants) != YES)return false;

        listOfBooks.getBook(bookID).setBooker(name);
        System.out.println("The book is successfully reserved.");
        return true;
    }
    public void returnABook(){
        while(true) {
            System.out.print("Enter your name or ");
            String[] exit = {"exit"};
            printVariantsList(exit);
            String name = getInput();
            if (isExit(name)) return;

            System.out.print("Enter the book ID you want to return or");
            printVariantsList(exit);
            String id = getInput();
            if (isExit(id)) return;
            if (!isNumber(id)) break;
            Book book = listOfBooks.getBook(Integer.parseInt(id));
            if(book == null) break;
            if(!book.getBookerName().equals(name)) break;

            book.releaseBook();
            System.out.println("You successfully returned the book.");
            return;
        }
        System.out.println("The book is not returned. Incorrect data was entered.");
    }

    public void printBookList(){
        Book[] list = listOfBooks.getBookList();
        for(Book book:list)
            printSmallBookInfo(book);
    }

    public void waitEnter(){
        System.out.println("{Press enter to continue}");
        String a = getInput();
    }


    private int AskUser(String @NotNull [] var){
        printVariantsList(var);
        return calculateInput(getInput(), var);
    }

    private boolean isExit(String str){
        return (str.equals("-") || str.equals("exit"));
    }
    private String getInput(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }
    private void printVariantsList(String @NotNull [] variants){
        StringBuilder out = new StringBuilder("{");
        for(int i = 0; i<variants.length; i++)
            out.append("(").append(variants[i].equals("exit") ? "-" : Integer.toString(i)).append(")").append(variants[i]).append((i + 1 == variants.length) ? "}" : "; ");
        System.out.println(out);
    }
    private int calculateInput(@NotNull String str, String[] variants){
        if(isExit(str)) return EXIT;
        if((isNumber(str))){
            int hasExit = (Arrays.asList(variants).contains("exit"))? 1 : 0;
            int i = Integer.parseInt(str);
            if( i< 0 || i >= variants.length-hasExit) return INCORRECT;
            return i;
        }
        for(int i = 0; i< variants.length; i++)
            if (str.equals(variants[i]))
                return i;
        return INCORRECT;
    }
    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}