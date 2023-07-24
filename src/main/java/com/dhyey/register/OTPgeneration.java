package com.dhyey.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    class OTP{
    String status ="";
	ResultSet rs;
	
	Connection connection = null;//The Connection connection = null; line declares a variable named "connection" of type Connection and initializes it with a null value. The Connection type is typically used to establish a connection to a database
	Statement statement = null;//The Statement type is used to execute SQL statements against a database.
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_info_db";
	private static final String username = "root";
	private static final String password = "";
	
	OTP(String val , String uemail) throws SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, username, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Connection established");
		
	
		statement.executeUpdate("insert into otp_val values('"+val+"','"+uemail+"')");
	}
	
    }

