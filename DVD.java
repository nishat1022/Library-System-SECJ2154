public class DVD extends LibraryItem {

    private String director;
    private int duration; // in minutes

    // Constructor
    public DVD(String itemId, String title, String director, int duration) {
        super(itemId, title);
        this.director = director;
        this.duration = duration;
    }

    // --- Getters & Setters ---

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director cannot be blank.");
        }
        this.director = director.trim();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        this.duration = duration;
    }

    // --- Loan period: 7 days ---
    @Override
    public int getLoanPeriod() {
        return 7;
    }

    // --- Item details ---
    public String getItemDetails() {
        return "Director: " + director + " | Duration: " + duration + " mins";
    }

    // --- toString ---
    @Override
    public String toString() {
        return "[DVD] " + super.toString() + " | " + getItemDetails();
    }
}
