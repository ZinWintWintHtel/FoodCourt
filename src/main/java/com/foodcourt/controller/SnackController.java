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
import javax.xml.crypto.Data;

import com.foodcourt.model.Snack;
import com.foodcourt.model.SnackDAO;

/**
 * Servlet implementation class SnackController
 */
public class SnackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/FoodCourt")
	private DataSource dataSource;
	
	private SnackDAO snackDAO;
	
	@Override
	public void init() throws ServletException {
		snackDAO = new SnackDAO(dataSource);
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnackController() {
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
			showSnackList(request, response);
			break;
			
		case "LOAD":
			loadSnack(request, response);
			break;
			
		case "CREATE":
			createSnack(request, response);
			break;
			
		case "UPDATE":
			updateSnack(request, response);
			break;
			
		case "DELETE":
			deleteSnack(request, response);
			break;

		default:
			showSnackList(request, response);
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
	
	private void showSnackList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Snack> snackList = this.snackDAO.showSnackList();
		request.setAttribute("snackList", snackList);
		RequestDispatcher rd = request.getRequestDispatcher("snack.jsp");
		rd.forward(request, response);
		
	}
	
	
	private void loadSnack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Snack snack = this.snackDAO.showSnackbyId(id);
		request.setAttribute("snack", snack);
		RequestDispatcher rd = request.getRequestDispatcher("snack-update.jsp");
		rd.forward(request, response);
	}
	
	
	private void createSnack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Snack snack = new Snack(name, price);
		
		int rowEffected = this.snackDAO.createSnack(snack);
		
		if(rowEffected > 0)
			showSnackList(request, response);
		
	
	}
	
	private void updateSnack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Snack snack = new Snack(id,name, price);
		
		int rowEffected = this.snackDAO.updateSnack(snack);
		
		if(rowEffected > 0)
			showSnackList(request, response);
		
		
	}


	private void deleteSnack(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int rowEffected = this.snackDAO.deleteSnack(id);
		
		if(rowEffected > 0)
			showSnackList(request, response);
		
	}

}
