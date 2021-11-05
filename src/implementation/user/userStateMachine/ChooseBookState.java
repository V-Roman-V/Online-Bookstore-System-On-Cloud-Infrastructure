package implementation.user.userStateMachine;

import abstraction.user.UserInterface;
import abstraction.user.UserState;
import implementation.database.entity.ReadOnlyBook;

public class ChooseBookState extends UserState {
    UserState prev;
    ReadOnlyBook book;

    public ChooseBookState(UserInterface user, ReadOnlyBook book, UserState previous){
        super(user);
        state = States.ChooseBookState;
        this.book = book;
        prev = previous;

        clear();
        user.site.printBookInfo(book);
        var response = user.site.askAboutBooking(book);
        switch (response) {
            case INCORRECT -> incorrect();
            case EXIT, NO -> back();
            case YES -> returnBook();
        default -> throw new IllegalArgumentException("Unexpected value: " + response);
        }
    }

    private void returnBook(){
        clear();
        user.site.bookABook(book);
        user.changeState(new ChooseBookState(user, book, prev));
    }

    protected void back(){
        switch (prev.state) {
            case SeeByAuthorState -> {
                var authorState = (SeeByAuthorState) prev;
                user.changeState(new SeeByAuthorState(user, authorState.author));
            }
            case SeeByGenreState -> {
                var genreState = (SeeByGenreState) prev;
                user.changeState(new SeeByGenreState(user, genreState.genre));
            }
            case SeeByLetterState -> {
                var letterState = (SeeByLetterState) prev;
                user.changeState(new SeeByLetterState(user, letterState.letter));
            }
            default -> user.changeState(new SeeBooksState(user));
        }
    }

    protected void incorrect(){
        user.changeState(new ChooseBookState(user, book, prev));
    }

}
