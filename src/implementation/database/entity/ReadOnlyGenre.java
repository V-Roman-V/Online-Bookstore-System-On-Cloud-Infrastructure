package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

// genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE

public class ReadOnlyGenre implements EntityInterface {
    /**
     * Property of ReadOnlyGenre
     * 
     * final means key fuild
     */
    protected final String name;
    protected String description;

    /**
     * Creates a ReadOnlyGenre
     */
    public ReadOnlyGenre(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return getKey(name);
    }

    public static String getKey(String name) {
        return name.toLowerCase();
    }
}
