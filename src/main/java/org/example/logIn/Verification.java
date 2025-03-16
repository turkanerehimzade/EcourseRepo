package org.example.logIn;

import lombok.SneakyThrows;
import org.example.Menu;
import org.example.config.ConnectionConfig;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Verification {
    @SneakyThrows
    public static void logIn() {

        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Please enter username:");
            String username = scanner.next();
            System.out.println("Please enter password:");
            String password = scanner.next();
            Connection connection = ConnectionConfig.getConnection();
            String query = "select * from users where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Successful login");
                while (true) {
                    Menu.menu();
                }
            } else {
                if (i == 3) {
                    System.out.println("Unsuccessful!!! Exits the system...");
                    System.exit(0);
                }
                System.out.println("Fail!!!Please try again");
            }
        }
    }
}
