import java.util.ArrayList;
import java.util.Date;

public class Library {

    private ArrayList<LibraryItem> itemList;
    private ArrayList<Member> memberList;
    private ArrayList<Loan> loanList;

    private String[] sections;
    private int loanCounter;

    public Library() {
        itemList = new ArrayList<>();
        memberList = new ArrayList<>();
        loanList = new ArrayList<>();
        sections = new String[]{"Fiction", "Non-Fiction", "Science", "Technology", "History"};
        loanCounter = 1;
    }

    // --- Item operations ---

    public void addItem(LibraryItem item) {
        itemList.add(item);
    }

    // exception handling 
    public String removeItem(String itemId) throws ItemUnavailableException {
        LibraryItem item = searchItemById(itemId);
        if (item == null) {
            return "Item not found.";
        }
        if (item.getStatus() == ItemStatus.BORROWED || item.getStatus() == ItemStatus.RESERVED) {
            throw new ItemUnavailableException("Cannot remove: item is currently " + item.getStatus().toString().toLowerCase() + ".");
        }
        itemList.remove(item);
        return "Item removed successfully.";
    }

    public LibraryItem searchItemById(String itemId) {
        for (LibraryItem item : itemList) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    // polymorphism — LibraryItem reference calls overridden toString() at runtime
    public String displayAllItems() {
        if (itemList.isEmpty()) {
            return "No items in the library.";
        }
        StringBuilder sb = new StringBuilder();
        for (LibraryItem item : itemList) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String displaySections() {
        StringBuilder sb = new StringBuilder("Library Sections:\n");
        for (int i = 0; i < sections.length; i++) {
            sb.append((i + 1) + ". " + sections[i] + "\n");
        }
        return sb.toString().trim();
    }

    // --- Member operations ---

    // aggregation
    public void addMember(Member member) {
        memberList.add(member);
    }

    public Member searchMemberById(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public String removeMember(String memberId) {
        Member member = searchMemberById(memberId);
        if (member == null) {
            return "Member not found.";
        }
        if (member.hasActiveLoans()) {
            return "Cannot remove: member has active loans.";
        }
        memberList.remove(member);
        return "Member removed successfully.";
    }

    public String displayAllMembers() {
        if (memberList.isEmpty()) {
            return "No members registered.";
        }
        StringBuilder sb = new StringBuilder();
        for (Member member : memberList) {
            sb.append(member.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    // --- Borrow and return ---

    // exception handling — throws both custom exceptions
    // enum — switch on ItemStatus
    public String borrowItem(String memberId, String itemId)
            throws ItemUnavailableException, MaxLoanExceededException {

        Member member = searchMemberById(memberId);
        if (member == null) {
            return "Member not found.";
        }

        LibraryItem item = searchItemById(itemId);
        if (item == null) {
            return "Item not found.";
        }

        switch (item.getStatus()) {
            case BORROWED:
            case RESERVED:
                throw new ItemUnavailableException(
                        "Item '" + item.getTitle() + "' is currently " + item.getStatus().toString().toLowerCase() + ".");
            case AVAILABLE:
                break;
        }

        if (member.getActiveLoanCount() >= member.getMaxLoans()) {
            throw new MaxLoanExceededException(
                    member.getName() + " has reached the maximum loan limit of " + member.getMaxLoans() + ".");
        }

        item.setStatus(ItemStatus.BORROWED);
        member.incrementLoanCount();

        String loanId = "L" + String.format("%03d", loanCounter++);
        Loan loan = new Loan(loanId, memberId, itemId);
        loanList.add(loan);

        return "Borrow successful. Loan ID: " + loanId;
    }

    // exception handling — custom exceptions caught by caller (Main.java)
    public String returnItem(String memberId, String itemId) {
        for (Loan loan : loanList) {
            if (loan.getItemId().equals(itemId) && loan.getMemberId().equals(memberId) && loan.isActive()) {

                LibraryItem item = searchItemById(itemId);
                if (item != null) {
                    item.setStatus(ItemStatus.AVAILABLE);
                }

                loan.setReturnDate(new Date());

                Member member = searchMemberById(memberId);
                if (member != null) {
                    member.decrementLoanCount();
                }

                return "Item returned successfully. Loan ID: " + loan.getLoanId();
            }
        }
        return "No active loan found for this member and item.";
    }

    public String viewActiveLoans() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Loan loan : loanList) {
            if (loan.isActive()) {
                sb.append(loan.toString()).append("\n");
                found = true;
            }
        }
        if (!found) {
            return "No active loans.";
        }
        return sb.toString().trim();
    }

    // polymorphism — getLoanPeriod() resolves to subclass version at runtime
    public String checkOverdue() {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        long today = new Date().getTime();

        for (Loan loan : loanList) {
            if (loan.isActive()) {
                LibraryItem item = searchItemById(loan.getItemId());
                if (item != null) {
                    long borrowTime = loan.getBorrowDate().getTime();
                    long loanPeriodMs = (long) item.getLoanPeriod() * 24 * 60 * 60 * 1000;
                    if (today - borrowTime > loanPeriodMs) {
                        long daysOverdue = (today - borrowTime) / (24 * 60 * 60 * 1000) - item.getLoanPeriod();
                        sb.append("OVERDUE — Loan ID: ").append(loan.getLoanId())
                          .append(" | Item ID: ").append(loan.getItemId())
                          .append(" | Member ID: ").append(loan.getMemberId())
                          .append(" | Days overdue: ").append(daysOverdue)
                          .append("\n");
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            return "No overdue loans.";
        }
        return sb.toString().trim();
    }
}
