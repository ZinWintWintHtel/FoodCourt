package com.foodcourt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

public class SnackDAO {
	
	private DataSource dataSource;
	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public SnackDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
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
	
	public List<Snack> showSnackList(){
		List<Snack> snackList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from snack;");
			
			while(rs.next()) {
				snackList.add(new Snack(
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
		return snackList;
	}

	public Snack showSnackbyId(int id) {
		Snack snack = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from snack where id='"+id+"';");
			while(rs.next()) {
				snack = new Snack(
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
		return snack;
	}
	
	
	public int createSnack(Snack snack) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"INSERT INTO `snack` "
					+ "(`name`, `price`) "
					+ "VALUES (?, ?);"
					);
			pStmt.setString(1, snack.getName());
			pStmt.setDouble(2, snack.getPrice());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	
	public int updateSnack(Snack snack) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"UPDATE `snack` SET "
					+ "`name` = ?, "
					+ "`price` = ? "
					+ "WHERE (`id` = ?);"
					);
			
			pStmt.setString(1, snack.getName());
			pStmt.setDouble(2, snack.getPrice());
			pStmt.setInt(3, snack.getId());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}
	
	
	public int deleteSnack(int id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from snack where id=?;");
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
