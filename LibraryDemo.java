package library;

/**
 * Demonstrates the Library Management System.
 * Tests all key operations including the one-active-loan business rule.
 */
public class LibraryDemo {

    public static void main(String[] args) {

        // ── 1. Create Library ────────────────────────────────────────────────
        Library library = new Library();

        // ── 2. Register Members ──────────────────────────────────────────────
        System.out.println(">>> Registering members...");
        Member john = new Member("M001", "John");
        Member mary = new Member("M002", "Mary");
        Member abbey = new Member("M003", "Abbey");
        library.registerMember(john);
        library.registerMember(mary);
        library.registerMember(abbey);

        // ── 3. Add Books ─────────────────────────────────────────────────────
        System.out.println("\n>>> Adding books...");
        Book b1 = new Book("ISBN001", "Java Programming", "James Gosling");
        Book b2 = new Book("ISBN002", "Data Structures", "Robert Sedgewick");
        Book b4 = new Book("ISBN004", "Java for dummies", "Kasumba A");
        Book b3 = new Book("ISBN003", "Database Systems");   // uses 2-arg constructor
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);

        // ── 4. Display initial state ─────────────────────────────────────────
        System.out.println("\n>>> Initial library state:");
        System.out.println(library);

        // ── 5. Lend Book 1 to John ───────────────────────────────────────────
        System.out.println("\n>>> Lending 'Java Programming' to John...");
        library.lendBook("M001", "ISBN001");

        // ── 6. Attempt to lend Book 1 to Mary (must be rejected) ─────────────
        System.out.println("\n>>> Attempting to lend 'Java Programming' to Mary (should be denied)...");
        library.lendBook("M002", "ISBN001");

        // ── 7. Display state after first loan ────────────────────────────────
        System.out.println("\n>>> Library state after first loan:");
        System.out.println(library);

        // ── 8. Return Book 1 ─────────────────────────────────────────────────
        System.out.println("\n>>> John returns 'Java Programming'...");
        library.returnBook("M001", "ISBN001");

        // ── 9. Now lend Book 1 to Mary (should succeed) ──────────────────────
        System.out.println("\n>>> Lending 'Java Programming' to Mary (should now succeed)...");
        library.lendBook("M002", "ISBN001");

        // ── 10. Test search ──────────────────────────────────────────────────
        System.out.println("\n>>> Searching for 'Data'...");
        Book found = library.searchBookByTitle("Data");
        System.out.println("Found: " + (found != null ? found : "Not found"));

        // ── 11. Display final state ───────────────────────────────────────────
        System.out.println("\n>>> Final library state:");
        System.out.println(library);
    }
}
