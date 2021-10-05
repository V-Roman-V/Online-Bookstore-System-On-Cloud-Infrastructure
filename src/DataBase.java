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
    
    public Book getBook(int bookID){

        for (int i = 0; i < list_of_books.size(); ++i){
            if (list_of_books.get(i).getID() == BookID){
                return list_of_books.get(i);
            }
        }
        return null;
    }
}