package com.login;

import java.io.IOException;

import com.DAO.DAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 private DAO loginDao;

	    public void init() {
	        loginDao = new DAO();
	    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            authenticate(request, response);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	 private void authenticate(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        String email = request.getParameter("email");
			        String password = request.getParameter("password");

			        if (loginDao.validate(email, password)) {
			            RequestDispatcher dispatcher = request.getRequestDispatcher("neww.jsp");
			            dispatcher.forward(request, response);
			        } else {
			            throw new Exception("Login not successful..");
			        }
			    }

}
