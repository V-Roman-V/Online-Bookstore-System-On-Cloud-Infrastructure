public class DataBase {
    private static DataBase instance;
    private DataBase(){}

    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public Book getBook(Integer bookID){return new Book();}
    public Book[] getBookList(){return new Book[5];}
}
