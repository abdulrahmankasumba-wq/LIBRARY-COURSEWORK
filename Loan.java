package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a loan: links a Member to a Book for a period.
 */
public class Loan {
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Loan(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14); // 2-week loan period
    }

    // Getters
    public Member getMember()       { return member; }
    public Book getBook()           { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate()   { return dueDate; }

    // Setters
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return String.format("Loan[Member=%s, Book=\"%s\", Borrowed=%s, Due=%s]",
                member.getName(), book.getTitle(),
                borrowDate.format(FMT), dueDate.format(FMT));
    }
}
