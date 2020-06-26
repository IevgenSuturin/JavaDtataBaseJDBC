package com.yevhensuturin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBMain {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\h241705\\IdeaProjects\\JavaDataBaseJDBC\\TestDB\\testjava.db");
             Statement statement = connection.createStatement()){

            //statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");

        }catch(SQLException e ){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
