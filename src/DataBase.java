import java.util.ArrayList;

final class DataBase {
    private static DataBase instance;
    private ArrayList<Book> books;

    private DataBase(ArrayList<Book> books){
        this.books = books;
    }
    public static DataBase getInstance(ArrayList<Book> books){
        if (instance == null){
            instance = new DataBase(books);
        }
        return instance;
    }

    public Book getBook(int BookID){

        for (int i = 0; i < books.size(); ++i){
            if (books.get(i).getID() == BookID){
                return books.get(i);
            }
        }
        return null;
    }
}