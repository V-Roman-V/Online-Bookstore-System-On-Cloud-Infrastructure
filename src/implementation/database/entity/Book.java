package implementation.database.entity;


public class Book extends ReadOnlyBook {

    public Book(String title, ReadOnlyAuthor author) {
        super(title, author);
    }

    public Book genre(ReadOnlyGenre genre) {
        this.genre = genre;
        return this;
    }

    public Book note(String note) {
        this.note = note;
        return this;
    }

    public Book price(Double price) {
        this.price = price;
        return this;
    }
   
}