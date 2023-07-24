package com.dhyey.register;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
	String status ="";
	ResultSet rs;
	
	Connection connection = null;//The Connection connection = null; line declares a variable named "connection" of type Connection and initializes it with a null value. The Connection type is typically used to establish a connection to a database
	Statement statement = null;//The Statement type is used to execute SQL statements against a database.
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_info_db";
	private static final String username = "root";
	private static final String password = "";
	
	public UserService(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, username, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection established");
	}
	
	public String checkLogin(String uname , String EncUpwd ) {
		try {
			rs = statement.executeQuery("select * from reg_user where UNAME = '"+uname+"' and UPWD = '"+EncUpwd+"'");
			boolean b = rs.next();
			
			if (b == true) {
				status = "success";
				}
			
				else {
					status = "failure";
				}
			
		}catch(Exception e) {
		 e.getMessage();
		}
		return status;
	}

	public String registration(String uname,String EncUpwd, String uemail){
		
		try {
			
			rs = statement.executeQuery("select * from reg_user where UNAME='+uname+'");
			boolean b = rs.next();
			if(b == true) {
				status = "existed";
			}
			else {
				int rowCount = statement.executeUpdate("insert into reg_user values('"+uname+"','"+EncUpwd+"','"+uemail+"')");	
				if(rowCount == 1) {
					status ="success";
				}else {
					status = "failure";
				}
			}
		}
		catch(Exception e){
		}		
		return status;
	}
	 public List<String> getOTPByEmail(String uemail) {
	        List<String> otps = new ArrayList<>();
	        try {
	            String query = "SELECT otp1 FROM otp_val WHERE UEMAIL = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, uemail);

	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                otps.add(rs.getString("otp1"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return otps;
	    }
		
	}
