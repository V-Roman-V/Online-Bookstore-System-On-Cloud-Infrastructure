package implementation.user;

import abstraction.user.UserInterface;
import implementation.site.Site;

public class User extends UserInterface {

    public User(Site site){
        super(site);
    }
}
