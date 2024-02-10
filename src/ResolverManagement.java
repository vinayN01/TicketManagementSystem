import java.util.*;
import java.sql.*;

class ResolverManagement {
    private Connection connection;
    private Scanner scanner;
    private String username;

    public ResolverManagement(Connection connection, Scanner scanner, String username) {
        this.connection = connection;
        this.scanner = scanner;
        this.username = username;
    }

    public void start() {
        while (true) {
            System.out.println("\nResolver Menu:");
            System.out.println("\n1. View Unaasigned Tickets");
            System.out.println("2. Assign a Ticket");
            System.out.println("3. View Assigned Tickets");
            System.out.println("4. Resolve Ticket");
            System.out.println("5. Logout");
            System.out.print("\nEnter your choice: ");
            int resolverChoice = scanner.nextInt();
            scanner.nextLine();

            
            if (resolverChoice == 1) {
                viewUnassignedTickets();
            } else if (resolverChoice == 2) {
                updateResolver();
            } else if (resolverChoice == 3) {
                ViewAssignedTickets();
            } else if (resolverChoice == 4) {
                resolveTicket();
            } else if (resolverChoice == 5) {
                break;
            }
        }
    }

    private void viewUnassignedTickets() {
        System.out.println("\nUnassigned Tickets:");
        try {
            TicketRequirements TicketRequirements = new TicketRequirements(connection);
            List<Ticket> openTickets = TicketRequirements.getOpenTickets();
            if (openTickets.isEmpty()) {
                System.out.println("\nThere is no any open tickets.");
            } else {
                for (Ticket ticket : openTickets) {
                    System.out.println("\nTicket ID: " + ticket.getId() + ", User: " + ticket.getUser()
                            + ", Description: "
                            + ticket.getDescription() +
                            ", Status: " + ticket.getStatus() + ", Product: " + ticket.getProduct() + ", Resolver: "
                            + ticket.getResolver() + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ViewAssignedTickets() {
        System.out.println("\nAssigned Tickets:");
        try {
            TicketRequirements TicketRequirements = new TicketRequirements(connection);
            List<Ticket> openTickets = TicketRequirements.getAssignedTickets(username);
            if (openTickets.isEmpty()) {
                System.out.println("\nThere are no assigned tickets.");
            } else {
                for (Ticket ticket : openTickets) {
                    System.out.println("\nTicket ID: " + ticket.getId() + ", User: " + ticket.getUser() +
                            ", Description: " + ticket.getDescription() +
                            ", Status: " + ticket.getStatus() + ", Product: " + ticket.getProduct() +
                            ", Resolver: " + ticket.getResolver() + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resolveTicket() {
        System.out.print("\nEnter the ticket ID to resolve: ");
        int ticketid = scanner.nextInt();
        scanner.nextLine();

        try {
            TicketRequirements TicketRequirements = new TicketRequirements(connection);
            Ticket ticket = TicketRequirements.getTicketById(ticketid);

            if (ticket != null && ticket.getStatus().equals("open")) {
                System.out.print("\nEnter the solution: ");
                String solution = scanner.nextLine();
                TicketRequirements.updateTicketSolution(ticketid, solution);
                System.out.println("\nTicket resolved successfully.");
            } else {
                System.out.println("\nTicket not found or already resolved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateResolver() {
        System.out.print("\nEnter the ticket ID to Assign: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine();

        try {
            TicketRequirements TicketRequirements = new TicketRequirements(connection);
            Ticket ticket = TicketRequirements.getTicketById(ticketId);

            if (ticket != null && ticket.getStatus().equals("open") && ticket.getResolver() == null) {
                TicketRequirements.updateTicketResolver(ticketId, username);
                System.out.println("\nAssigned.");
            } else {
                System.out.println("\nCannot assign.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
