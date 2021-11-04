package database.object;

public class Genre {
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
}
