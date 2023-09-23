import java.sql.*;
import java.util.Scanner;



public class TicketManagement {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_system", "root", "Vinayreddy@1234");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Ticket Management System");

            while (true) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice == 1) {
                    registerUser(connection, scanner);
                } else if (choice == 2) {
                    User user = loginUser(connection, scanner);
                    if (user != null) {
                        if (user.getRole().equals("user")) {
                            UserManagement usermng = new UserManagement(connection, scanner, user.getUsername());
                            usermng.start();
                        } else if (user.getRole().equals("resolver")) {
                            ResolverManagement resolverManagement = new ResolverManagement(connection, scanner);
                            resolverManagement.start();
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting the Ticket Management System.");
                    connection.close();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerUser(Connection connection, Scanner scanner) {
        System.out.println("Select your role:");
        System.out.println("1. User");
        System.out.println("2. Ticket Resolver");
        System.out.print("Enter the number of your role: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (roleChoice != 1 && roleChoice != 2) {
            System.out.println("Invalid role selection. Exiting.");
            return;
        }

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            UserCreds usercred = new UserCreds(connection);
            usercred.createUser(username, password, (roleChoice == 1) ? "user" : "resolver");
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User loginUser(Connection connection, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            UserCreds userRepository = new UserCreds(connection);
            User user = userRepository.loginUser(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                return user;
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
