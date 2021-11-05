package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class SeeBooksState extends UserState {

    public SeeBooksState(UserInterface user){
        super(user);
        state = States.SeeBooksState;

        clear();
        var response = user.site.askSearchType();
        switch(response){
            case EXIT -> back();
            case INCORRECT -> incorrect();
            case ALPHABETIC -> user.changeState(new SeeAlphabeticState(user));
            case GENRE -> user.changeState(new SeeGenresState(user));
            case AUTHOR -> user.changeState(new SeeAuthorsState(user));
        default -> throw new IllegalArgumentException("Unexpected value: " + response);
        }
    }

    protected void back(){
        user.changeState(new EnterSiteState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeBooksState(user));
    }

}

