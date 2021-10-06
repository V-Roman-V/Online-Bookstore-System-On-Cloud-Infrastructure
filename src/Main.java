import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Main {

    private static void clear(){
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private static void fillArrayList(ArrayList<Book> BookList, ArrayList<Book> arrayList, Genre KeyWord) {
        for (Book book : BookList)
            if (Objects.equals(book.getGenre(), KeyWord))
                arrayList.add(book);
    }
    private static ArrayList<Book> generateBooksArray(){
        ArrayList<Book> BookList = new ArrayList<>();
        BookList.add(new Book("Max", Genre.DETECTIVE, "Pushkin", 22.2));
        BookList.add(new Book("Nastya", Genre.HORROR, "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", Genre.COMEDY, "Thinker", 10.9));
        BookList.add(new Book("Rust", Genre.FICTION, "krug", 85.4));
        BookList.add(new Book("Bong", Genre.THRILLER, "King", 24.4));
        BookList.add(new Book("ProbStat", Genre.EROTIC, "Gorodos", 9999.9));
        BookList.add(new Book("Center", Genre.HORROR, "Osborn", 9.2));
        return BookList;
    }
    private static HashMap<Genre, ArrayList<Book>> generateBooksGenre(ArrayList<Book> BookList){
        HashMap<Genre, ArrayList<Book>> BookGenre = new HashMap<>();
        for (Genre gen:Genre.values()){
            ArrayList<Book> arr = new ArrayList<>();
            fillArrayList(BookList, arr, gen);
            BookGenre.put(gen, arr);
        }
        return BookGenre;
    }

    public static void main(String[] args) {
    	ArrayList<Book> books = generateBooksArray();
    	HashMap<Genre, ArrayList<Book>> booksGenre = generateBooksGenre(books);
        Site site = new Site(books, booksGenre);
        while (true) { // enter the site
            clear();
            site.enterSite();
            int choose = site.startMenu();
            if (choose == Site.INCORRECT) continue;
            if (choose == Site.EXIT) break;
            if (choose == Site.RETURN_BOOK) { // returning the book
                clear();
                if(site.returnABook())
                    site.waitEnter();
                continue;
            }
            if (choose == Site.SEE_BOOKS)
                while (true) { // Book previews
                    clear();
                    site.printBookList();
                    int ID = site.chooseABook();
                    if (ID == Site.EXIT) break;
                    if (ID == Site.INCORRECT) continue;
                    while (true) { // Viewing a particular book
                        clear();
                        site.printBookInfo(ID);
                        int bk = site.askAboutBooking(ID);
                        if (bk == Site.EXIT) break;
                        if (bk == Site.NO) break;
                        if (bk == Site.INCORRECT) continue;
                        if (bk == Site.YES) {// Book reservation
                            clear();
                            if(site.bookABook(ID))
                                site.waitEnter();
                        }
                    }
                }
        }
        clear();
        site.exitSite();
    }
}
