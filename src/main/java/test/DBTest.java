package test;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.foodcourt.model.Drink;
import com.foodcourt.model.Food;
import com.foodcourt.model.Snack;

/**
 * Servlet implementation class DBTest
 */
public class DBTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/FoodCourt")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from snack;");
			
			while(rs.next()) {
				/*
				Food food = new Food(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getDouble("price"));
				out.println(food+"\n");
				*/
				/*
				Drink drink = new Drink(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getDouble("price"));
				out.println(drink+"\n");
				*/
				
				Snack snack = new Snack(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getDouble("price"));
				out.println(snack+"\n");
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
