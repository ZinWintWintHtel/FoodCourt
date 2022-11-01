package com.foodcourt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DrinkDAO {
	
	private DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public DrinkDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Drink> showDrinkList(){
		List<Drink> drinkList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from drink");
			
			while(rs.next()) {
				drinkList.add(new Drink(
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
		return drinkList;	
	}

	
	public Drink showDrinkById(int id) {
		Drink drink = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from drink where id='"+id+"';");
			while(rs.next()) {
				drink = new Drink(
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
		return drink;
	}
	
	
	public int createDrink(Drink drink) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"INSERT INTO `drink` (`name`, `price`) "
					+ "VALUES (?, ?);"
					);
			pStmt.setString(1, drink.getName());
			pStmt.setDouble(2, drink.getPrice());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	
	public int updateDrink(Drink drink) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"UPDATE `drink` SET "
					+ "`name` = ?, "
					+ "`price` = ? "
					+ "WHERE (`id` = ?);"
					);
			pStmt.setString(1, drink.getName());
			pStmt.setDouble(2, drink.getPrice());
			pStmt.setInt(3, drink.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	
	public int deleteDrink(int id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from drink where id=?;");
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
