package library;

import java.util.ArrayList;

/**
 * Manages the collection of books and members.
 * Handles lending and returning of books, enforcing the one-active-loan rule.
 */
public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // ─── Collection Management ────────────────────────────────────────────────

    /** Adds a book to the library's collection. */
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    /** Registers a new member with the library. */
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    // ─── Search ───────────────────────────────────────────────────────────────

    /**
     * Searches for a book by title (case-insensitive, partial match).
     * @return the first matching Book, or null if not found.
     */
    public Book searchBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return b;
            }
        }
        return null;
    }

    /** Finds a member by ID. */
    private Member findMember(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) return m;
        }
        return null;
    }

    /** Finds a book by ISBN. */
    private Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    // ─── Lending and Returning ──────────────────────────────────────────────

    /**
     * Lends a book to a member.
     * Business rule: a book can only have ONE active loan at a time.
     *
     * @param memberId the ID of the borrowing member
     * @param isbn     the ISBN of the book to borrow
     */
    public void lendBook(String memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (member == null) {
            System.out.println("ERROR: Member not found (ID=" + memberId + ").");
            return;
        }
        if (book == null) {
            System.out.println("ERROR: Book not found (ISBN=" + isbn + ").");
            return;
        }

        // ── Core business rule ──
        if (!book.isAvailable()) {
            System.out.println("DENIED: \"" + book.getTitle() + "\" is already on loan and unavailable.");
            return;
        }

        // Create the loan and update state
        Loan loan = new Loan(member, book);
        book.setAvailable(false);
        member.addLoan(loan);
        System.out.println("SUCCESS: \"" + book.getTitle() + "\" lent to " + member.getName() + ". " + loan);
    }

    /**
     * Returns a book previously lent to a member.
     *
     * @param memberId the ID of the returning member
     * @param isbn     the ISBN of the book being returned
     */
    public void returnBook(String memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (member == null) {
            System.out.println("ERROR: Member not found (ID=" + memberId + ").");
            return;
        }
        if (book == null) {
            System.out.println("ERROR: Book not found (ISBN=" + isbn + ").");
            return;
        }

        boolean removed = member.removeLoan(book);
        if (removed) {
            book.setAvailable(true);
            System.out.println("SUCCESS: \"" + book.getTitle() + "\" returned by " + member.getName() + ".");
        } else {
            System.out.println("ERROR: No active loan found for \"" + book.getTitle()
                    + "\" under member " + member.getName() + ".");
        }
    }

    // ─── Display ─────────────────────────────────────────────────────────

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LIBRARY STATE ===\n");
        sb.append("--- Books (").append(books.size()).append(") ---\n");
        for (Book b : books) sb.append("  ").append(b).append("\n");
        sb.append("--- Members (").append(members.size()).append(") ---\n");
        for (Member m : members) sb.append("  ").append(m).append("\n");
        sb.append("=====================");
        return sb.toString();
    }
}
