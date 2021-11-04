package Implementation;

import Implementation.database.object.Book;
import Implementation.database.object.Genre;
import Implementation.site.Site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    private static void clear() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        // System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private static void fillArrayList(ArrayList<Book> BookList, ArrayList<Book> arrayList, Genre KeyWord) {
        for (Book book : BookList)
            if (Objects.equals(book.getGenre(), KeyWord))
                arrayList.add(book);
    }

    private static ArrayList<Book> generateBooksArray() {
        ArrayList<Book> BookList = new ArrayList<>();
        //
        BookList.add(new Book("Max", Genre.DETECTIVE, "Pushkin", 22.2));
        BookList.add(new Book("Nastya", Genre.HORROR, "Lenin", 12.22));
        BookList.add(new Book("Ronaldo", Genre.COMEDY, "Thinker", 10.9));
        BookList.add(new Book("Rust", Genre.FICTION, "krug", 85.4));
        BookList.add(new Book("Bong", Genre.THRILLER, "King", 24.4));
        BookList.add(new Book("ProbStat", Genre.EROTIC, "Gorodos", 9999.9));
        BookList.add(new Book("Center", Genre.HORROR, "Osborn", 9.2));
        //
        BookList.add(new Book("My House of Horrors (LN)", Genre.HORROR, "I Fix Air-Conditioner (我会修空调)", 12.10));
        BookList.add(new Book("The Beginning After the End (LN)", Genre.FICTION, "TurtleMe", 85.0));
        BookList.add(new Book("Classroom of the Elite (LN)", Genre.DETECTIVE, "KINUGASA Shougo", 18.9));
        BookList.add(new Book("I'm A Spider, So What? (LN)", Genre.DRAMA, "BABA Okina", 61.5));
        BookList.add(new Book("Jobless Reincarnation: I Will Seriously Try If I Go to Another World (WN)", Genre.COMEDY,
                "Rifujin na Magonote", 37.6));
        //
        BookList.add(new Book("No Game No Life (LN)", Genre.ECCHI, "KAMIYA Yuu", 7.5));
        BookList.add(new Book("How to forget C++, for dummies", Genre.PSYCHOLOGICAL, "Real life", -0.01));
        BookList.add(new Book("Spice and Wolf", Genre.DRAMA, "Isuna Hasekura", 56.7));
        BookList.add(new Book("Danganronpa/Zero (N)", Genre.DETECTIVE, "Kazutaka Kodaka", 3.3));
        BookList.add(new Book("Another (N)", Genre.HORROR, "Yukito Ayatsuji", 2.2));
        //
        BookList.add(new Book("Re:Zero - Starting Life in Another World", Genre.ROMANCE, "NAGATSUKI Tappei", 20.9));
        BookList.add(new Book("Knights of the Forty Islands", Genre.FICTION, "Sergey Lukyanenko", 19.90));
        BookList.add(new Book("The Stormlight Archive", Genre.FICTION, "Brandon Sanderson", 9.45));
        BookList.add(new Book("Mistborn Trilogy ", Genre.FICTION, "Brandon Sanderson", 8.66));
        BookList.add(new Book("Violet Evergarden (N)", Genre.ROMANCE, "AKATSUKI Kana", 6.7));
        //
        return BookList;
    }

    private static HashMap<Genre, ArrayList<Book>> generateBooksGenre() {
        ArrayList<Book> BookList = generateBooksArray();
        HashMap<Genre, ArrayList<Book>> BookGenre = new HashMap<>();
        for (Genre gen : Genre.values()) {
            ArrayList<Book> arr = new ArrayList<>();
            fillArrayList(BookList, arr, gen);
            BookGenre.put(gen, arr);
        }
        return BookGenre;
    }

    public static void main(String[] args) {
        Site site = new Site(generateBooksGenre());
        while (true) { // enter the site
            clear();
            site.enterSite();
            int choose = site.startMenu();
            if (choose == Site.INCORRECT)
                continue;
            if (choose == Site.EXIT)
                break;
            if (choose == Site.RETURN_BOOK) { // returning the book
                clear();
                site.returnABook();
                continue;
            }
            if (choose == Site.SEE_BOOKS) { // Implementation.database.object.Book previews
                Genre gen = null;
                while (true) {
                    clear();
                    site.printBookList(gen);
                    Site.Pair<Genre, Integer> data = site.chooseABook();
                    gen = data.first;
                    int ID = data.second;

                    if (ID == Site.EXIT)
                        break;
                    if (ID == Site.CHOOSE_GENRE)
                        continue;
                    if (ID == Site.INCORRECT)
                        continue;
                    while (true) { // Viewing a particular book
                        clear();
                        site.printBookInfo(ID);
                        int bk = site.askAboutBooking(ID);
                        if (bk == Site.EXIT)
                            break;
                        if (bk == Site.NO)
                            break;
                        if (bk == Site.INCORRECT)
                            continue;
                        if (bk == Site.YES) {// Implementation.database.object.Book reservation
                            clear();
                            site.bookABook(ID);
                        }
                    }
                }
            }
        }
        clear();
        site.exitSite();
    }
}