package implementation.user;

import abstraction.user.UserInterface;
import implementation.database.entity.Genre;
import implementation.site.Site;

public class User implements UserInterface {

    private static void clear() {
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public void startInteraction(Site site) {
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
