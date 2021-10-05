import java.util.ArrayList;

final class DataBase {
    private static DataBase instance;
    private final ArrayList<Book> list_of_books;


    private DataBase(ArrayList<Book> books){
        this.list_of_books = books;
    }

    public static DataBase getInstance(ArrayList<Book> books){
        if (instance == null){
            instance = new DataBase(books);
        }
        return instance;
    }

    public ArrayList<Book> getBookList() {
        return list_of_books;
    }
    
    public Book getBook(int BookID){

        for (Book list_of_book : list_of_books) {
            if (list_of_book.getID() == BookID) {
                return list_of_book;
            }
        }
        return null;
    }
}