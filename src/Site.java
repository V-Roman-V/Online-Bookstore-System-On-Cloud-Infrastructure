import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class Site {
    private static final int INCORRECT = -2;
    private static final int EXIT = -1;

    private final DataBase listOfBooks;

    public Site(){listOfBooks = DataBase.getInstance();}
    public void enterSite(){}
    public void exitSite(){}
    public Book chooseABook(Integer bookID){return new Book();}
    public void bookABook(Book book){}
    public void returnABook(Integer bookID){}


    private void printBookList(){
        Book[] list = listOfBooks.getBookList();
    }
    private void showBookInfo(){}

    private int AskUser(String @NotNull [] var){
        Scanner sc=new Scanner(System.in);
        printMenu(var);
        String answer;
        answer = sc.nextLine();
        return calculateInput(answer, var);
    }

    private void printMenu(String @NotNull [] variants){
        StringBuilder out = new StringBuilder("{");
        for(int i = 0; i<variants.length; i++)
            out.append("(").append(variants[i].equals("exit") ? "-" : Integer.toString(i)).append(")").append(variants[i]).append((i + 1 == variants.length) ? "}" : "; ");
        System.out.println(out);
    }
    private int calculateInput(@NotNull String str, String[] variants){
        if(str.equals("-") || str.equals("exit")) return EXIT;
        if((isNumber(str))){
            int hasExit = (Arrays.stream(variants).anyMatch("exit"::equals))? 1 : 0;
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
