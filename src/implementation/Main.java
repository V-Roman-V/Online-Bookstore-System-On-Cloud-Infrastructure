package implementation;

import implementation.site.Site;
import implementation.user.User;

public class Main {
    public static void main(String[] args) {
        Site site = new Site();
        User user = new User(site);
        user.startInteraction();
    }
}
