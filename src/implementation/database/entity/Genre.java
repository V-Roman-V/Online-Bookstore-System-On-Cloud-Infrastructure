package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

// Genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE

public class Genre implements EntityInterface {
    /**
     * Property of Genre
     * 
     * final means key fuild
     */
    private final String name;
    private String description;

    /**
     * Creates a Genre
     */
    public Genre(String name) {
        this.name = name;
    }

    public Genre description(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return name;
    }

    public static String getKey(String name) {
        return name;
    }
}
