public class Book extends LibraryItem {

    private String author;
    private String isbn;

    // Constructor
    public Book(String itemId, String title, String author, String isbn) {
        super(itemId, title);
        this.author = author;
        this.isbn = isbn;
    }

    // --- Getters & Setters ---

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be blank.");
        }
        this.author = author.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be blank.");
        }
        this.isbn = isbn.trim();
    }

    // --- Loan period: 14 days ---
    @Override
    public int getLoanPeriod() {
        return 14;
    }

    // --- Item details ---
    public String getItemDetails() {
        return "Author: " + author + " | ISBN: " + isbn;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "[BOOK] " + super.toString() + " | " + getItemDetails();
    }
}
