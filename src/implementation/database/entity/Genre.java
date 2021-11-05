package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

// Genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE

public class Genre implements EntityInterface {
    // Integer author_id;
    public String name;
    public String description;

    public Genre(String name) {
        this.name = name;
    }

    public Genre description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getKey() {
        return name;
    }
}
