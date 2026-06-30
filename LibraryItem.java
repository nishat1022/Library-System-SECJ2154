public abstract class LibraryItem {

    private String itemId;
    private String title;
    private ItemStatus status;

    // Constructor
    public LibraryItem(String itemId, String title) {
        this.itemId = itemId;
        this.title = title;
        this.status = ItemStatus.AVAILABLE; // default
    }

    // --- Getters & Setters with validation ---

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be blank.");
        }
        this.itemId = itemId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        this.title = title.trim();
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    // --- Abstract method: each subclass must define its own loan period ---
    public abstract int getLoanPeriod();

    // --- toString ---
    @Override
    public String toString() {
        return "ID: " + itemId + " | Title: " + title + " | Status: " + status + " | Loan Period: " + getLoanPeriod() + " days";
    }
}
