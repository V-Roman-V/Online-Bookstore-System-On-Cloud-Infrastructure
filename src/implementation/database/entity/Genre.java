package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

// Genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE, ECCHI

public class Genre implements EntityInterface {
    /**
     * Property of Genre
     * 
     * final means key fuild
     */
    private final String name;
    private String discription;

    /**
     * Creates a Genre
     */
    public Genre(String name) {
        this.name = name;
    }

    public Genre discription(String discription) {
        this.discription = discription;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    @Override
    public String getKey() {
        return name;
    }

    public static String getKey(String name) {
        return name;
    }
}
