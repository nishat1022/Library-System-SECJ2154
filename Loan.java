import java.util.Date;

public class Loan {
    private String loanId;
    private String memberId;
    private String itemId;
    private Date borrowDate;
    private Date returnDate; // null means the loan is still active

    // Constructor
    public Loan(String loanId, String memberId, String itemId) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.borrowDate = new Date(); // Sets borrow date to today
        this.returnDate = null;       // Active by default
    }

    // Must-have Getters and Setters
    public String getLoanId() {
        return loanId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getItemId() {
        return itemId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // Must-have check
    public boolean isReturned() {
        return returnDate != null;
    }

    // Should-have check (Opposite of isReturned)
    public boolean isActive() {
        return returnDate == null;
    }

    // Should-have formatting summary string
    @Override
    public String toString() {
        String status = isActive() ? "ACTIVE" : "RETURNED on " + returnDate;
        return "Loan ID: " + loanId + 
               " | Member ID: " + memberId + 
               " | Item ID: " + itemId + 
               " | Borrowed: " + borrowDate + 
               " | Status: " + status;
    }
}