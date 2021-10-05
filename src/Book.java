public class Book {
    private String title;
    private int ID;
    private String author;
    private String bookerName;
    private double price;
    private boolean booked;

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookerName() {
        return bookerName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }
}