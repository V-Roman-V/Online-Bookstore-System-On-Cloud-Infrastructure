public class Main {
    private static void clear(){
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    public static void main(String[] args) {
        Site site = new Site();
        while (true) { // enter the site
            clear();
            site.enterSite();
            int choose = site.startMenu();
            if (choose == Site.INCORRECT) continue;
            if (choose == Site.EXIT) break;
            if (choose == Site.RETURN_BOOK) { // returning the book
                clear();
                site.provideReturnForm();
                site.returnABook();
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
                            site.provideReservationForm();
                            site.bookABook(ID);
                        }
                    }
                }
        }
        clear();
        site.exitSite();
    }
}
