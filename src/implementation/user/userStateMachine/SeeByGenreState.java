package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;
import implementation.database.entity.Genre;

public class SeeByGenreState extends UserState {
    public Genre genre;

    public SeeByGenreState(UserInterface user, Genre genre){
        super(user);
        state = States.SeeByGenreState;
        this.genre = genre;

        clear();
        user.site.printBooksByGenre(genre);
        var response = user.site.chooseABook();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new ChooseBookState(user, response.second, this));
        default -> throw new IllegalArgumentException("Unexpected value: " + response.first);
        }
    }

    protected void back(){
        user.changeState(new SeeGenresState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeByGenreState(user,genre));
    }
}
