package com;

import java.sql.*;

public class Item {
	
public Connection connect(){
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
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
	
	public String readItems()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr><th>Item Code</th>"
					+"<th>Item Name</th><th>Item Price</th>"
					+ "<th>Item Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from item";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
					// iterate through the rows in the result set
			while (rs.next())
			{
				String itemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");
				
					// Add a row into the html table
				
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemPrice + "</td>";
					output += "<td>" + itemDesc + "</td>";
					
					// buttons
					
					// buttons
					output += "<td><form method='post' action='update.jsp'><input name='btnUpdate' "
					+ " type='submit' value='Update' class = 'btn btn-secondary'><input name='itemID' type='hidden'"
					+ " value='" + itemID + "'>"
					+" <input name='itemCode' type='hidden' value='" + itemCode + "'>"
					+" <input name='itemName' type='hidden' value='" + itemName+ "'>"
					+" <input name='itemPrice' type='hidden' value='" + itemPrice + "'>"
					+" <input name='itemDesc' type='hidden' value='" + itemDesc + "'>"
					+ "</form></td>"
					+ "<td><form method='post' action='items.jsp'>"
					+ "<input name='btnRemove'"
					+ " type='submit' value='Remove' class = 'btn btn-danger'>"
					+ "<input name='itemID' type='hidden' "
					+ " value='" + itemID + "'>" + "</form></td></tr>";
			}
					
			con.close();
			
					// Complete the html table
			output += "</table>";
					
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteItem(String itemID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from item where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateItem(String id, String code, String name, String price, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "update item set itemCode = ?, itemName = ?, itemPrice = ?, itemDesc = ? where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, id);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
