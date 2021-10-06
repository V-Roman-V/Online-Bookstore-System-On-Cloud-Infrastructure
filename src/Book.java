public class Book {
    private static int ID_coefficient;

    private final String title;
    private final String author;
    private final Genre genre;
    private final double price;
    private final int ID;
    private boolean booked;
    private String bookerName;

    public Book(String title, Genre genre, String author, double price){
        ID = ++ID_coefficient;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
    }

    public boolean isBooked() {return booked;}
    public void releaseBook(){this.booked = false;}
    public void setBooker(String bookerName) {
        this.bookerName = bookerName;
        this.booked = true;
    }

    public String getTitle() {return title;}
    public int getID() {return ID;}
    public String getAuthor() {return author;}
    public Genre getGenre() {return genre;}
    public String getBookerName() {return bookerName;}
    public double getPrice() {return price;}
}