package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class SeeByLetterState extends UserState {
    public Character letter;

    public SeeByLetterState(UserInterface user, Character letter){
        super(user);
        state = States.SeeByLetterState;
        this.letter = letter;

        clear();
        user.site.printBooksByLetter(letter);
        var response = user.site.chooseABook();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new ChooseBookState(user, response.second, this));
        }
    }

    protected void back(){
        user.changeState(new SeeAlphabeticState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeByLetterState(user,letter));
    }
}
