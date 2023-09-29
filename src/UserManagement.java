
// @Code_Review: What is the use of keeping scanner as instance varible, better create and when needed
import java.util.*;
import java.sql.*;

class UserManagement {
    private Connection connection;
    private Scanner scanner;
    private String username;

    public UserManagement(Connection connection, Scanner scanner, String username) {
        this.connection = connection;
        this.scanner = scanner;
        this.username = username;
    }

    public void start() {
        while (true) {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. Create Ticket");
            System.out.println("2. View Tickets");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 1) {
                createTicket();
            } else if (userChoice == 2) {
                viewTickets();
            } else if (userChoice == 3) {
                // Logout
                break;
            }
        }
    }

    private void createTicket() {
        System.out.println("\nSelect product on which you want to create a ticket: ");
        System.out.println("1-'Electronics'");
        System.out.println("2-'Appliances'");
        String productType = scanner.nextLine();
        System.out.print("\nEnter ticket description: ");
        String description = scanner.nextLine();

        // @Code_Review: what if productType given is 3?? -- Done
        try {
            TicketRequirements ticketrequirements = new TicketRequirements(connection);

            if (productType.equals("1") || productType.equals("2")) {
                String product = (productType.equals("1")) ? "Electronics" : "Appliances";
                Ticket ticket = new Ticket(0, username, description, "open", null, product, null);

                ticketrequirements.createTicket(ticket);

                System.out.println("\nTicket created successfully.");
            } else {
                System.out.println("\nSelect valid product type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewTickets() {
        System.out.println("\nYour Tickets:");
        try {
            TicketRequirements ticketrequirements = new TicketRequirements(connection);
            List<Ticket> userTickets = ticketrequirements.getTicketsByUser(username);

            for (Ticket ticket : userTickets) {
                System.out.println("\nTicket ID: " + ticket.getId() + ", Description: " + ticket.getDescription() +
                        ", Status: " + ticket.getStatus() + ", Resolution: " + ticket.getResolution() + ", Product: "
                        + ticket.getProduct() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
