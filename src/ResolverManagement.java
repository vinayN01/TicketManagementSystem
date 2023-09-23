
    import java.util.*;
import java.sql.*;
class ResolverManagement {
    private Connection connection;
    private Scanner scanner;

    public ResolverManagement(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\nResolver Menu:");
            System.out.println("1. View Tickets");
            System.out.println("2. Resolve Ticket");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int resolverChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (resolverChoice == 1) {
                viewTickets();
            } else if (resolverChoice == 2) {
                resolveTicket();
            } else if (resolverChoice == 3) {
                // Logout
                break;
            }
        }
    }

    private void viewTickets() {
        System.out.println("Tickets to Resolve:");
        try {
            TicketCreds ticketcreds = new TicketCreds(connection);
            List<Ticket> openTickets = ticketcreds.getOpenTickets();

            for (Ticket ticket : openTickets) {
                System.out.println("Ticket ID: " + ticket.getId() + ", User: " + ticket.getUser()+", Description: " + ticket.getDescription() +
                         ", Status: "+ticket.getStatus() +", Product: "+ticket.getProduct() +  "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resolveTicket() {
        System.out.print("Enter the ticket ID to resolve: ");
        int ticketid = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            TicketCreds ticketcreds = new TicketCreds(connection);
            Ticket ticket = ticketcreds.getTicketById(ticketid);

            if (ticket != null &&  ticket.getStatus().equals("open")) {
                System.out.print("Enter the solution: ");
                String solution = scanner.nextLine();
                ticketcreds.updateTicketSolution(ticketid, solution);
                System.out.println("Ticket resolved successfully.");
            } else {
                System.out.println("Ticket not found or already resolved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

