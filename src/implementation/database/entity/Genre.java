package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

// Genre example: HORROR, DETECTIVE, COMEDY, FICTION, THRILLER, EROTIC, DRAMA, PSYCHOLOGICAL, ROMANCE, ECCHI

public class Genre implements EntityInterface {
    // Integer author_id;
    public String name;
    public String discription;

    public Genre(String name) {
        this.name = name;
    }

    public Genre discription(String discription) {
        this.discription = discription;
        return this;
    }

    @Override
    public String getKey() {
        return name;
    }
}
