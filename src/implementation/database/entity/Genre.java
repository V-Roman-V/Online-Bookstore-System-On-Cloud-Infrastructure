package implementation.database.entity;

// genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE

public class Genre extends ReadOnlyGenre {

    public Genre(String name) {
        super(name);
    }

    //Below is the implementation of Builder pattern

    public Genre description(String description) {
        this.description = description;
        return this;
    }

}
