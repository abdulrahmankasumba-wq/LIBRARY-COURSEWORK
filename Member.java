package library;

import java.util.ArrayList;

/**
 * Represents a library member who can borrow books.
 */
public class Member {
    private String memberId;
    private String name;
    private ArrayList<Loan> loans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName()     { return name; }
    public ArrayList<Loan> getLoans() { return loans; }

    // Setters
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name)         { this.name = name; }

    /** Adds a loan to this member's active loans. */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * Removes the loan for a specific book from this member's active loans.
     * @return true if the loan was found and removed, false otherwise.
     */
    public boolean removeLoan(Book book) {
        return loans.removeIf(loan -> loan.getBook().equals(book));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Member[ID=%s, Name=%s, ActiveLoans=%d]",
                memberId, name, loans.size()));
        for (Loan l : loans) {
            sb.append("\n    ").append(l);
        }
        return sb.toString();
    }
}
