import java.sql.*;
import java.util.*;
// @Code_Review: Format code properly, Use prettier extension
class TicketCreds {
    private Connection connection;
public  TicketCreds(Connection connection){
    this.connection =connection;
}

public void createTicket(Ticket ticket) throws SQLException{
    String TicDetails = "INSERT INTO tickets(user,description,status,resolution,product) VALUES (?,?,?,?,?)";
    try (PreparedStatement insrtTicDet = connection.prepareStatement(TicDetails)){
        insrtTicDet.setString(1,ticket.getUser());
        insrtTicDet.setString(2,ticket.getDescription());
        insrtTicDet.setString(3,ticket.getStatus());
        insrtTicDet.setString(4,ticket.getResolution());
        insrtTicDet.setString(5,ticket.getProduct());
        insrtTicDet.executeUpdate();
    }
}

public void updateTicketSolution(int ticketid , String solution) throws SQLException {
    String TicRes = "UPDATE tickets SET resolution = ?, status= 'close' WHERE id = ?";
    try (PreparedStatement UpdTickRes = connection.prepareStatement(TicRes)){
         UpdTickRes.setString(1, solution);
         UpdTickRes.setInt(2, ticketid);
         UpdTickRes.executeUpdate();
    }
       
}

public Ticket getTicketById(int ticketid) throws SQLException {
    String idTic = "SELECT * FROM tickets where id = ?";
    try(PreparedStatement idTicSol = connection.prepareStatement(idTic)){
        idTicSol.setInt(1, ticketid);
        ResultSet rs = idTicSol.executeQuery();
        if(rs.next()){
            int id  = rs.getInt("id");
            String user = rs.getString("user");
            String description = rs.getString("description");
            String status = rs.getString("status");
            String resolution = rs.getString("resolution");
            String product = rs.getString("product");
            return new Ticket(id,user,description, status, resolution,product);
        }
    }
    return null;
}
public List<Ticket> getTicketsByUser(String user) throws SQLException{
    List<Ticket> userTickets = new ArrayList<>();
    String userTicks = "SELECT * FROM tickets WHERE user=?";
    try(PreparedStatement Usertickets = connection.prepareStatement(userTicks)){
        Usertickets.setString(1, user);
        ResultSet rs = Usertickets.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String User = rs.getString("user");
            String description = rs.getString("description");
            String status = rs.getString("status");
            String resolution = rs.getString("resolution");
            String product = rs.getString("product");
            Ticket ticket = new Ticket(id,User,description, status, resolution,product);
            userTickets.add(ticket);

        }
    }
    return userTickets;
}
public List<Ticket> getOpenTickets() throws SQLException{
    List<Ticket> openTickets = new ArrayList<>();
    // @Code_Review: make status as enum
    String openTic = "SELECT * FROM tickets WHERE status = 'open' ";
    try (PreparedStatement opentickets = connection.prepareStatement(openTic)) {
        ResultSet rs = opentickets.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String user = rs.getString("user");
            String description = rs.getString("description");
            String status = rs.getString("status");
            String resolution = rs.getString("resolution");
            String product = rs.getString("product");
            Ticket ticket = new Ticket(id,user,description, status, resolution,product);
            openTickets.add(ticket);
}
}
return openTickets;
}
}
