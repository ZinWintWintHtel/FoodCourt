package com.foodcourt.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.foodcourt.model.User;
import com.foodcourt.model.UserDAO;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/FoodCourt")
	private DataSource dataSource;
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "SIGNUP";
		}
		switch (mode) {
		case "SIGNUP":
			signup(request,response);
			break;

		default:
			signup(request,response);
			break;
		}
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean adminOk = Boolean.parseBoolean(request.getParameter("role"));
		String role = adminOk ? "admin" : "user";
		
		User user = new User(username,email,password,role);
		
		int rowEffetcted = this.userDAO.createUser(user);
		if(rowEffetcted > 0)
			response.sendRedirect("login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
