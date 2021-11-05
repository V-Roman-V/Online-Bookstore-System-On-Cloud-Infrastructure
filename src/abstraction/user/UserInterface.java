package abstraction.user;

import implementation.site.Site;
import implementation.user.userStateMachine.EnterSiteState;

public abstract class UserInterface {
    private UserState state;
    public Site site;

    public UserInterface(Site site){
        this.site = site;
        state = null;
    }

    public void startInteraction() {
        state = new EnterSiteState(this);
    }

    public void changeState(UserState state){
        this.state = state;
    }
}
