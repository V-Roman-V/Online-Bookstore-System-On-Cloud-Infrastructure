package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class SeeAlphabeticState extends UserState {

    public SeeAlphabeticState(UserInterface user){
        super(user);
        state = States.SeeAlphabeticState;

        clear();
        user.site.printAlphabet();
        var response = user.site.askLetter();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new SeeByLetterState(user, response.second));
        default -> throw new IllegalArgumentException("Unexpected value: " + response.first);
        }
    }

    protected void back(){
        user.changeState(new SeeBooksState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeAlphabeticState(user));
    }

}
