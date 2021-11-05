package abstraction.user;

public abstract class UserState {
    public enum States{
        ChooseBookState,
        EnterSiteState,
        SeeAlphabeticState,
        SeeAuthorsState,
        SeeBooksState,
        SeeByAuthorState,
        SeeByGenreState,
        SeeByLetterState,
        SeeGenresState
    }
    public States state;

    protected UserInterface user;

    public UserState(UserInterface user){
        this.user = user;
    }

    protected static void clear(){
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * The option when the user goes back
     */
    protected abstract void back();

    /**
     * The variant when the user entered incorrect data
     */
    protected abstract void incorrect();
}
