package com.jdbc.demo1;

import java.sql.*;
import java.util.Scanner;

public class JDBCDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mp", "root", "root");

        Scanner scanner=new Scanner(System.in);
        int nextInt = scanner.nextInt();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from tbl_employee where id='2' and last_name='MP' or  1=1");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+"\t"+ resultSet.getString("last_name"));

        }


        statement.close();
        connection.close();
    }
}
