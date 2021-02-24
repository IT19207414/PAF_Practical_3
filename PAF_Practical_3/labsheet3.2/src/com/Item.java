package com;

import java.sql.*;

public class Item {
	
public Connection connect(){
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab3","root","");
			
			//For testing
			System.out.println("Successfully Connected");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
		
	}

	public String insertItem(String code, String name, String price, String desc){
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the databse";
			}
			
			String query = "insert into item(itemID, itemCode, itemName, itemPrice, itemDesc) values(?, ?, ?, ?, ?)";
			
			PreparedStatement preparedState = con.prepareStatement(query);
			
			preparedState.setInt(1, 0);
			preparedState.setString(2, code);
			preparedState.setString(3, name);
			preparedState.setDouble(4, Double.parseDouble(price));
			preparedState.setString(5, desc);
			
			//execute the statement
			preparedState.execute();
			con.close();
			
			output = "Inserted successfully";
		
		}
		
		catch (Exception e){
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
		
		
		
	}

}
