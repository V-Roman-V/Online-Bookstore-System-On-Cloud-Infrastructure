package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import static jdk.javadoc.internal.doclets.toolkit.util.Utils.toLowerCase;

// Genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE

public class Genre implements EntityInterface {
    /**
     * Property of Genre
     * 
     * final means key field
     */
    private final String name;
    private String description;

    /**
     * Creates a Genre
     */
    public Genre(String name) {
        this.name = name;
    }

    //Below is the implementation of Builder pattern

    public Genre description(String description) {
        this.description = description;
        return this;
    }

    //Below are getters which returns private fields of Genre class

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return toLowerCase(name);
    }

    public static String getKey(String name) {
        return toLowerCase(name);
    }
}
