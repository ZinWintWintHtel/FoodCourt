package com.foodcourt.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.foodcourt.model.Drink;
import com.foodcourt.model.DrinkDAO;

/**
 * Servlet implementation class DrinkController
 */
public class DrinkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/FoodCourt")
	private DataSource dataSource;
	
	private DrinkDAO drinkDAO;
	
	@Override
	public void init() throws ServletException {
		drinkDAO = new DrinkDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrinkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "LIST";
		}
		
		switch (mode) {
		case "LIST":
			showDrinkList(request, response);
			break;

		case "LOAD":
			loadDrink(request, response);
			break;
			
		case "CREATE":
			createDrink(request, response);
			break;
			
		case "UPDATE":
			updateDrink(request, response);
			break;
			
		case "DELETE":
			deleteDrink(request, response);
			break;
			
		default:
			showDrinkList(request, response);
			break;
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showDrinkList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Drink> drinkList = this.drinkDAO.showDrinkList();
		request.setAttribute("drinkList", drinkList);
		RequestDispatcher rd = request.getRequestDispatcher("drink.jsp");
		rd.forward(request, response);
		
	}
	
	private void loadDrink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Drink drink = this.drinkDAO.showDrinkById(id);
		request.setAttribute("drink", drink);
		RequestDispatcher rd = request.getRequestDispatcher("drink-update.jsp");
		rd.forward(request, response);
		
	}
	
	
	private void createDrink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Drink drink = new Drink(name, price);
		
		int rowEffected = this.drinkDAO.createDrink(drink);
		
		if(rowEffected > 0)
			showDrinkList(request, response);
		
		
	}
	

	
	private void updateDrink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Drink drink = new Drink(id,name, price);
		
		int rowEffected = this.drinkDAO.updateDrink(drink);
		
		if(rowEffected > 0)
			showDrinkList(request, response);
		
	}
	
	
	private void deleteDrink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.drinkDAO.deleteDrink(id);
		
		if(rowEffected > 0)
			showDrinkList(request, response);
		
	}
	
	

}
