package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class SeeAuthorsState extends UserState {

    public SeeAuthorsState(UserInterface user){
        super(user);
        state = States.SeeAuthorsState;

        clear();
        user.site.printAuthors();
        var response = user.site.askAuthor();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new SeeByAuthorState(user, response.second));
        }
    }

    protected void back(){
        user.changeState(new SeeBooksState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeAuthorsState(user));
    }
}
