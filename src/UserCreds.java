import java.sql.*;
class UserCreds {
    private Connection connection;
public UserCreds(Connection connection){
    this.connection = connection;
}
    public void createUser(String username, String password, String role) throws SQLException {
        String insertUser = "INSERT INTO users(username, password ,role) VALUES (?,?,?)";
       try(PreparedStatement insertUserTable = connection.prepareStatement(insertUser)){
        insertUserTable.setString(1, username);
        insertUserTable.setString(2, password);
        insertUserTable.setString(3, role);
        insertUserTable.executeUpdate();
       }
    }
    
    public User loginUser(String username, String password) throws SQLException {
        String checkUser = "SELECT * FROM users WHERE username = ? AND password =? ";
        try(PreparedStatement checkUserTable = connection.prepareStatement(checkUser)){
        checkUserTable.setString(1, username);
        checkUserTable.setString(2, password);
        ResultSet rs = checkUserTable.executeQuery();
        if(rs.next()){
            String role = rs.getString("role");
            return new User(username, password, role);
        }
    }
        return null;
    }
}
