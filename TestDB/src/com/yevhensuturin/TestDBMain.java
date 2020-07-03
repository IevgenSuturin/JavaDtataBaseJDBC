package com.yevhensuturin;

import java.sql.*;

public class TestDBMain {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\h241705\\IdeaProjects\\JavaDataBaseJDBC\\TestDB\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(CONNECTION_STRING)
        ){
//            connection.setAutoCommit(false);
             try(Statement statement = connection.createStatement()) {

                 statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
                 statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                    " (" +COLUMN_NAME+ " TEXT, "+ COLUMN_PHONE +" INTEGER, " + COLUMN_EMAIL + " TEXT)");

                 insertContact(statement, "Tim", 45643, "tim@email.com");
                 insertContact(statement, "Joe", 342123, "joe@email.com");
                 insertContact(statement, "Jane", 948372, "jane@email.com");
                 insertContact(statement, "Fido", 2020302, "dog2@email.com");

                 statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE + "= 556789 WHERE " + COLUMN_NAME + "='Jane'");
                 statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Joe'");
//                 statement.execute("SELECT * FROM contacts");
//                 ResultSet results = statement.getResultSet();

                 ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
                  while(results.next()){
                     System.out.println(results.getString(COLUMN_NAME) + " " +
                                        results.getInt(COLUMN_PHONE) + " " +
                                        results.getString(COLUMN_EMAIL));
                 }
                 results.close();

             } catch (SQLException e){
                 System.out.println("Something went wrong: " + e.getMessage());
                 e.printStackTrace();
             }
        }catch(SQLException e ){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                "(" + COLUMN_NAME+ ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
                "VALUES('" + name +"', " + phone +", '" + email + "')");
    }
}
