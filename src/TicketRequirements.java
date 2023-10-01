import java.sql.*;
import java.util.*;

class TicketRequirements {
    // Code_Review: Write a singleton class and get connection when ever you need.
    private Connection connection;

    public TicketRequirements(Connection connection) {
        this.connection = connection;
    }

    public void createTicket(Ticket ticket) throws SQLException {
        String TicDetails = "INSERT INTO tickets(user,description,status,resolution,product,resolver) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement createTickets = connection.prepareStatement(TicDetails)) {
            createTickets.setString(1, ticket.getUser());
            createTickets.setString(2, ticket.getDescription());
            createTickets.setString(3, ticket.getStatus());
            createTickets.setString(4, ticket.getResolution());
            createTickets.setString(5, ticket.getProduct());
            createTickets.setString(6, ticket.getResolver());
            createTickets.executeUpdate();
        }
    }

    public void updateTicketSolution(int ticketid, String solution) throws SQLException {
        String TicResolution = "UPDATE tickets SET resolution = ?, status= 'close' WHERE id = ?";
        try (PreparedStatement UpdateResolver = connection.prepareStatement(TicResolution)) {
            UpdateResolver.setString(1, solution);
            UpdateResolver.setInt(2, ticketid);
            UpdateResolver.executeUpdate();
        }

    }

    // Code_Review: I don't think throws is required when you are using try
    public Ticket getTicketById(int ticketid) throws SQLException {
        String ticketId = "SELECT * FROM tickets where id = ?";
        try (PreparedStatement tickets = connection.prepareStatement(ticketId)) {
            tickets.setInt(1, ticketid);
            ResultSet rs = tickets.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String resolution = rs.getString("resolution");
                String product = rs.getString("product");
                String resolver = rs.getString("resolver");
                return new Ticket(id, user, description, status, resolution, product, resolver);
            }
        }
        return null;
    }

    public List<Ticket> getTicketsByUser(String user) throws SQLException {
        List<Ticket> userTickets = new ArrayList<>();
        String userticket = "SELECT * FROM tickets WHERE user=?";
        try (PreparedStatement Usertickets = connection.prepareStatement(userticket)) {
            Usertickets.setString(1, user);
            ResultSet rs = Usertickets.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String User = rs.getString("user");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String resolution = rs.getString("resolution");
                String product = rs.getString("product");
                String resolver = rs.getString("resolver");
                Ticket ticket = new Ticket(id, User, description, status, resolution, product, resolver);
                userTickets.add(ticket);

            }
        }
        return userTickets;
    }

    public List<Ticket> getOpenTickets() throws SQLException {
        List<Ticket> openTickets = new ArrayList<>();
        String openticket = "SELECT * FROM tickets WHERE status = 'open' AND resolver IS NULL";
        try (PreparedStatement opentickets = connection.prepareStatement(openticket)) {
            ResultSet rs = opentickets.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String resolution = rs.getString("resolution");
                String product = rs.getString("product");
                String resolver = rs.getString("resolver");
                Ticket ticket = new Ticket(id, user, description, status, resolution, product, resolver);
                openTickets.add(ticket);
            }
        }
        return openTickets;
    }

    public List<Ticket> getAssignedTickets(String username) throws SQLException {
        List<Ticket> openTickets = new ArrayList<>();
        String openticket = "SELECT * FROM tickets WHERE status = 'open' AND resolver = ?";
        try (PreparedStatement opentickets = connection.prepareStatement(openticket)) {
            opentickets.setString(1, username);
            ResultSet rs = opentickets.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String resolution = rs.getString("resolution");
                String product = rs.getString("product");
                String resolver = rs.getString("resolver");
                Ticket ticket = new Ticket(id, user, description, status, resolution, product, resolver);
                openTickets.add(ticket);
            }
        }
        return openTickets;
    }

    public void updateTicketResolver(int ticketId, String resolver) throws SQLException {
        String TicRes = "UPDATE tickets SET resolver = ?  WHERE id = ?";
        try (PreparedStatement UpdateResolver = connection.prepareStatement(TicRes)) {
            UpdateResolver.setString(1, resolver);
            UpdateResolver.setInt(2, ticketId);
            UpdateResolver.executeUpdate();
        }

    }
}
