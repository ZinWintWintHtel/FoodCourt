package com.foodcourt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class FoodDAO {
	
	private DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close(){
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public FoodDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Food> showFoodList(){
		List<Food> foodList = new ArrayList<>();
				
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from food");
			
			while(rs.next()) {
				foodList.add(new Food(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getDouble("price")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return foodList;	
	}
	
	public Food showFoodbyId(int id){
		Food food = null;
				
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from food where id='"+id+"';");
			
			while(rs.next()) {
				food = new Food(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getDouble("price"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return food;	
	}
	
	
	public int createFood(Food food) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"INSERT INTO `food` "
					+ "(`name`, `price`) "
					+ "VALUES (?,?);"
					);
			pStmt.setString(1, food.getName());
			pStmt.setDouble(2, food.getPrice());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	public int updateFood(Food food) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement(
					"UPDATE `food` SET "
					+ "`name` = ?, "
					+ "`price` = ? WHERE (`id` = ?);"
					);
			
			pStmt.setString(1, food.getName());
			pStmt.setDouble(2, food.getPrice());
			pStmt.setInt(3, food.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;	
	}
	
	
	public int deleteFood(int id) {
		int rowEffected = 0;
			try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement("delete from food where id=?;");
				pStmt.setInt(1, id);
				rowEffected = pStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return rowEffected;
	}
	

}
