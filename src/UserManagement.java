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
            scanner.nextLine(); // Consume the newline character

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
        System.out.println("Select product on which you want to create a ticket: ");
        System.out.println("1-'Electronics'");
        System.out.println("2-'Appliances'");
        int productType = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter ticket description: ");
        String description = scanner.nextLine();
    
        try {
            TicketCreds ticketcreds = new TicketCreds(connection);
    

            String product = (productType == 1) ? "Electronics" : "Appliances";
    

            Ticket ticket = new Ticket(0, username, description, "open", null, product);
    

            ticketcreds.createTicket(ticket);
            
            System.out.println("Ticket created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void viewTickets() {
        System.out.println("Your Tickets:");
        try {
            TicketCreds ticketRepository = new TicketCreds(connection);
            List<Ticket> userTickets = ticketRepository.getTicketsByUser(username);

            for (Ticket ticket : userTickets) {
                System.out.println("Ticket ID: " + ticket.getId() + ", Description: " + ticket.getDescription() +
                        ", Status: " + ticket.getStatus() + ", Resolution: " + ticket.getResolution() + ", Product: " +ticket.getProduct() + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




