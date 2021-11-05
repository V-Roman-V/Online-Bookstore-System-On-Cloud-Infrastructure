package abstraction.user;

import implementation.site.Site;
import implementation.user.userStateMachine.EnterSiteState;

public abstract class UserInterface {
    private UserState state;
    public Site site;

    public UserInterface(Site site){
        this.site = site;
        setState(null);
    }

    public UserState getState() {
        return state;
    }

    private void setState(UserState state) {
        this.state = state;
    }

    public void startInteraction() {
        setState(new EnterSiteState(this));
    }

    public void changeState(UserState state){
        this.setState(state);
    }
}
