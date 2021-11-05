package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;

public class SeeGenresState extends UserState {

    public SeeGenresState(UserInterface user){
        super(user);
        state = States.SeeGenresState;

        clear();
        user.site.printGenres();
        var response = user.site.askGenre();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new SeeByGenreState(user, response.second));
        default -> throw new IllegalArgumentException("Unexpected value: " + response.first);
        }
    }

    protected void back(){
        user.changeState(new SeeBooksState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeGenresState(user));
    }
}
