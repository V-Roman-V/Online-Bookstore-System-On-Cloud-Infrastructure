package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;
import implementation.database.entity.Author;

public class SeeByAuthorState extends UserState {
    public Author author;

    public SeeByAuthorState(UserInterface user, Author author){
        super(user);
        state = States.SeeByAuthorState;
        this.author = author;

        clear();
        user.site.printBooksByAuthor(author);
        var response = user.site.chooseABook();
        switch (response.first){
            case INCORRECT -> incorrect();
            case EXIT -> back();
            case YES -> user.changeState(new ChooseBookState(user, response.second, this));
        }
    }

    protected void back(){
        user.changeState(new SeeAuthorsState(user));
    }

    protected void incorrect(){
        user.changeState(new SeeByAuthorState(user,author));
    }
}
