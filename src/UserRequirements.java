import java.sql.*;

class UserRequirements {
    private Connection connection;

    public UserRequirements(Connection connection) {
        this.connection = connection;
    }

    public void createUser(String username, String password, String role) throws SQLException {
        String insertUser = "INSERT INTO users(username, password ,role) VALUES (?,?,?)";
        try (PreparedStatement insertUserInTable = connection.prepareStatement(insertUser)) {
            insertUserInTable.setString(1, username);
            insertUserInTable.setString(2, password);
            insertUserInTable.setString(3, role);
            insertUserInTable.executeUpdate();
        }
    }

    public User loginUser(String username, String password) throws SQLException {
        String checkUser = "SELECT * FROM users WHERE username = ? AND password =? ";
        try (PreparedStatement checkUserInTable = connection.prepareStatement(checkUser)) {
            checkUserInTable.setString(1, username);
            checkUserInTable.setString(2, password);
            ResultSet rs = checkUserInTable.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return new User(username, password, role);
            }
        }
        return null;
    }
}
