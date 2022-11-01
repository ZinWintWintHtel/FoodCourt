package com.foodcourt.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.foodcourt.model.Food;
import com.foodcourt.model.FoodDAO;
import com.foodcourt.model.User;

/**
 * Servlet implementation class FoodController
 */
public class FoodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/FoodCourt")
	private DataSource dataSource;
	
	private FoodDAO foodDAO;
	
	@Override
	public void init() throws ServletException {
		foodDAO = new FoodDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if(user != null) {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "LIST";
		}
		
		switch (mode) {
		case "LIST":
			showFoodList(request, response);
			break;
			
		case "LOAD":
			loadFood(request, response);
			break;
			
		case "CREATE":
			createFood(request, response);
			break;
			
		case "UPDATE":
			updateFood(request, response);
			break;
			
		case "DELETE":
			deleteFood(request, response);
			break;
		
		case "LOGOUT":
			session.invalidate();
			response.sendRedirect("login");
			break;

		default:
			showFoodList(request, response);
			break;
		}
		}else {
			response.sendRedirect("login");
		}
		
	}
	
	private void showFoodList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		List<Food> foodList = this.foodDAO.showFoodList();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		request.setAttribute("foodList", foodList);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
			
	}
	
	
	private void loadFood(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		int id = Integer.parseInt(request.getParameter("id"));
		Food food = this.foodDAO.showFoodbyId(id);
		request.setAttribute("food", food);
		RequestDispatcher rd = request.getRequestDispatcher("food-update.jsp");
		rd.forward(request, response);
	}
	
	private void createFood(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Food food = new Food(name, price);
		
		int rowEffected = this.foodDAO.createFood(food);
		
		if(rowEffected > 0)
			showFoodList(request, response);
	}
	
	
	private void updateFood(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Food food = new Food(id, name, price);
		
		int rowEffected = this.foodDAO.updateFood(food);
		
		if(rowEffected > 0)
			showFoodList(request, response);
		
	}
	
	
	private void deleteFood(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.foodDAO.deleteFood(id);
		
		if(rowEffected > 0)
			showFoodList(request, response);
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
