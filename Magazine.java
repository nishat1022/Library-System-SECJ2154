public class Magazine extends LibraryItem {

    private String issueNo;
    private String publisher;

    // Constructor
    public Magazine(String itemId, String title, String issueNo, String publisher) {
        super(itemId, title);
        this.issueNo=issueNo;
        this.publisher=publisher;
    }

    // --- Getters & Setters ---

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        if (issueNo == null || issueNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Issue number cannot be blank.");
        }
        this.issueNo = issueNo.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new IllegalArgumentException("Publisher cannot be blank.");
        }
        this.publisher = publisher.trim();
    }

    // --- Loan period: 3 days ---
    @Override
    public int getLoanPeriod() {
        return 3;
    }

    // --- Item details ---
    public String getItemDetails() {
        return "Issue No: " + issueNo + " | Publisher: " + publisher;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "[MAGAZINE] " + super.toString() + " | " + getItemDetails();
    }
}
