package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class EnterSiteState extends UserState {

    public EnterSiteState(UserInterface user){
        super(user);
        state = States.EnterSiteState;

        clear();
        user.site.enterSite();
        var response = user.site.startMenu();
        switch (response) {
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case RETURN_BOOK -> returnBook();
            case SEE_BOOKS -> user.changeState(new SeeBooksState(user));
        default -> throw new IllegalArgumentException("Unexpected value: " + response);
        }
    }

    private void returnBook(){
        clear();
        user.site.returnABook();
        user.changeState(new EnterSiteState(user));
    }

    protected void back(){
        clear();
        user.site.exitSite();
    }

    protected void incorrect(){
        user.changeState(new EnterSiteState(user));
    }

}
