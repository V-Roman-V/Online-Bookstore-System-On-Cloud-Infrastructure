import java.util.ArrayList;

public class Main {
    private static void clear(){
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    private static ArrayList<Book> generateBooks(){
        ArrayList<Book> BookList = new ArrayList<>();
        BookList.add(new Book("Max", "Detective", "Pushkin", 22.2));
        BookList.add(new Book("Nastya", "Horror", "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", "Comedy", "Thinker", 10.9));
        BookList.add(new Book("Rust", "Fiction", "krug", 85.4));
        BookList.add(new Book("Bong", "Thriller", "King", 24.4));
        BookList.add(new Book("Probstat", "Erotic", "Gorodos", 9999.9));
        return BookList;
    }

    public static void main(String[] args) {
        Site site = new Site(generateBooks());
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
