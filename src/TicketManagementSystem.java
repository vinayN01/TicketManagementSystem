import java.sql.*;
import java.util.Scanner;

public class TicketManagementSystem {
    public static void main(String[] args) {
        try {
            // Code_Review: Write a class to get connection... Should be a single ton class
            // Code_Review: Also I think I said to move the strings to variables and use it here
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_system", "Your_Username",
                    "Password");
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nWelcome to the Ticket Management System");

            while (true) {
                // Code_Review: Instead of using multiple prints create a multi line string and print it
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                // Code_Review: Better to make this switch case and handle invalid input case
                if (choice == 1) {
                    registerUser(connection, scanner);
                } else if (choice == 2) {
                    User user = loginUser(connection, scanner);
                    if (user != null) {
                        // Code_Review: User types can be enum.. change it
                        if (user.getRole().equals("user")) {
                            UserManagement users = new UserManagement(connection, scanner, user.getUsername());
                            users.start();
                        } else if (user.getRole().equals("resolver")) {
                            ResolverManagement resolverManagement = new ResolverManagement(connection, scanner,
                                    user.getUsername());
                            resolverManagement.start();
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("\nExiting the Ticket Management System.");
                    connection.close();
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Code_Review: don't pass scanner as params and also connection
    private static void registerUser(Connection connection, Scanner scanner) {
        System.out.println("\nSelect your role:");
        System.out.println("1. User");
        System.out.println("2. Ticket Resolver");
        System.out.print("Enter the number of your role: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        if (roleChoice != 1 && roleChoice != 2) {
            System.out.println("\nInvalid role selection. Exiting.");
            return;
        }

        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            UserRequirements userCredentials = new UserRequirements(connection);
            userCredentials.createUser(username, password, (roleChoice == 1) ? "user" : "resolver");
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Code_Review: don't pass scanner as params and also connection
    private static User loginUser(Connection connection, Scanner scanner) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            UserRequirements userRepository = new UserRequirements(connection);
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
