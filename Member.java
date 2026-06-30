public class Member {

    private String memberId;
    private String name;
    private MemberType type;
    private int activeLoanCount;

    // Constructor
    public Member(String memberId, String name, MemberType type) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be blank.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Member type cannot be null.");
        }
        this.memberId = memberId.trim();
        this.name = name.trim();
        this.type = type;
        this.activeLoanCount = 0;
    }

    // --- Getters & Setters ---

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be blank.");
        }
        this.memberId = memberId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name.trim();
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        if (type == null) {
            throw new IllegalArgumentException("Member type cannot be null.");
        }
        this.type = type;
    }

    public int getActiveLoanCount() {
        return activeLoanCount;
    }

    // --- Loan limit by member type ---

    public int getMaxLoans() {
        switch (type) {
            case STUDENT: return 3;
            case STAFF: return 5;
            case GUEST: return 1;
            default: return 1;
        }
    }

    public void incrementLoanCount() {
        activeLoanCount++;
    }

    public void decrementLoanCount() {
        if (activeLoanCount > 0) {
            activeLoanCount--;
        }
    }

    public boolean hasActiveLoans() {
        return activeLoanCount > 0;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + " | Name: " + name + " | Type: " + type + " | Active Loans: " + activeLoanCount + "/" + getMaxLoans();
    }
}