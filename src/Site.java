import org.jetbrains.annotations.NotNull;

public class Site {
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

}
