package com.copy;
import java.sql.*;

public class Item {
		
	public Connection connect(){
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
			
			//For testing
			System.out.println("Successfully Connected");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
		
	}

}

