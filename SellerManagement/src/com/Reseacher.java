package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Reseacher {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf2", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertResearch(String ResearchID , String ResearchName, String ResearchDiscription, String ResearcherName,String ResearchPrice ){
			String output = "" ;
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = "INSERT INTO `research`(`ResearchID`, `ResearchName`, `ResearchDiscription`, `ResearcherName`,  `ResearchPrice`) VALUES (?,?,?,?,?)";
					PreparedStatement preparedStatement = con.prepareStatement(query);
					
					
					 // binding values
					preparedStatement.setInt(1, 0);
					preparedStatement.setString(2, ResearchName);
					preparedStatement.setString(3, ResearchDiscription);
					preparedStatement.setString(4, ResearcherName);					 
					preparedStatement.setDouble(5, Float.parseFloat(ResearchPrice)); 
					
					 
					 // execute the statement
					preparedStatement.execute();
					 con.close();
					 
					 String newResearch = readResearch(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Research.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readResearch(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
						     //"<th >Research ID</th>" +	
							 "<th >Research Name</th>" +
							 "<th>Research Discription</th>" +
							 "<th>Researcher Name</th>" +							 
							 "<th>Research Price</th>" +							 
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "SELECT * from `research`";
				 Statement statement = con.createStatement();
				 ResultSet resultSet = statement.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (resultSet.next()){
					 
					 
					 
					 int ResearchID = resultSet.getInt("ResearchID");
					 String ResearchName = resultSet.getString("ResearchName");
					 String ResearchDiscription = resultSet.getString("ResearchDiscription");
					 String ResearcherName = resultSet.getString("ResearcherName");					 
					 String ResearchPrice = Float.toString(resultSet.getFloat("ResearchPrice"));
					 
	
					 
					 // Add into the html table
					 
					// output += "<tr><td>" + ResearchID + "</td>";
					 output += "<td>" + ResearchName + "</td>";
					 output += "<td>" + ResearchDiscription + "</td>";
					 output += "<td>" + ResearcherName + "</td>";					 
					 output += "<td>" + ResearchPrice + "</td>";
					
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + ResearchID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + ResearchID + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the researches.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateResearch(String ResearchID, String ResearchName, String ResearchDiscription, String ResearcherName, String ResearchPrice){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `research` SET `ResearchName`=?,`ResearchDiscription`=?,`ResearcherName`=?,`ResearchPrice`=? WHERE `ResearchID`=?";
				 PreparedStatement preparedStatement = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStatement.setString(1, ResearchName);
				 preparedStatement.setString(2, ResearchDiscription);
				 preparedStatement.setString(3, ResearcherName);				 
				 preparedStatement.setDouble(4, Float.parseFloat(ResearchPrice)); 
				 preparedStatement.setString(5, ResearchID);
				 
				
				 
				 // execute the statement
				 preparedStatement.execute(); 
				 con.close(); 
				 
				 String newResearch = readResearch(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the research.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteResearch(String ResearchID) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `research` WHERE ResearchID=?";
					PreparedStatement preparedStatement = con.prepareStatement(query); 
					
					// binding values
					preparedStatement.setString(1, ResearchID); 
					
					// execute the statement
					preparedStatement.execute(); 
					con.close(); 
					
					  
					String newResearch = readResearch(); 
					output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the research.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}