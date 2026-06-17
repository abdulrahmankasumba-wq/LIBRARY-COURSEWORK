package library;

/**
 * Represents a book in the library system.
 * Tracks availability for loan management.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    // Constructor 1: isbn and title only
    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.author = "Unknown";
        this.available = true;
    }

    // Constructor 2: isbn, title, and author
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters
    public String getIsbn()    { return isbn; }
    public String getTitle()   { return title; }
    public String getAuthor()  { return author; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setIsbn(String isbn)       { this.isbn = isbn; }
    public void setTitle(String title)     { this.title = title; }
    public void setAuthor(String author)   { this.author = author; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title=\"%s\", Author=%s, Available=%s]",
                isbn, title, author, available ? "Yes" : "No");
    }
}
