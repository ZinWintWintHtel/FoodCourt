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

import javax.sql.DataSource;

import com.foodcourt.model.User;
import com.foodcourt.model.UserDAO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
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
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		
		if(mode == null) {
			mode = "LOGIN_PAGE";
		}
		
		switch (mode) {
		case "LOGIN_PAGE":
			loginPage(request,response);
			break;
			
		case "SIGNIN":
			signin(request,response);
			break;

		default:
			loginPage(request,response);
			break;
		}
		
		
	}

	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String originalPassword = request.getParameter("password");
		
		boolean valid = this.userDAO.isValidUser(email, originalPassword);
		
		if(valid) {
			response.sendRedirect("food");
			User user = this.userDAO.getUserByEmail(email);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
		}else {
			boolean loginFail = true;
			request.setAttribute("loginFail", loginFail);
			RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
		}
			
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean loginFail = false;
		request.setAttribute("loginFail", loginFail);
		RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
