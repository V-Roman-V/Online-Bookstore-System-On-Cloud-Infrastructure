import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

final class DataBase {
    private static DataBase instance;
    private final ArrayList<Book> list_of_books;
    HashMap<Genre, ArrayList<Book> > genre_of_books;

    private DataBase(ArrayList<Book> books, HashMap<Genre, ArrayList<Book>> genres){
        this.list_of_books = books;
        this.genre_of_books = genres;
    }

    public static DataBase getInstance(ArrayList<Book> books, HashMap<Genre, ArrayList<Book>> genres){
        if (instance == null){
            instance = new DataBase(books, genres);
        }
        return instance;
    }

    public ArrayList<Book> getBookList() {
        return list_of_books;
    }

    public HashMap<Genre, ArrayList<Book>> getGenre_of_books() {
        return genre_of_books;
    }


    ArrayList<Book> sortBooks(Genre genre){
//        return genre_of_books.values();
        Collection<ArrayList<Book>> values = genre_of_books.values();
        for (ArrayList<Book> v : values) {
            return v;
        }
        return null;
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