package fr.treeptik.centreformation.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();  
		//resp.sendRedirect(req.getContextPath() + "/login.jsf");
		 HttpSession session=req.getSession(); 
		 session.invalidate();  
		 req.logout();
         out.print("You are successfully logged out!");  
           
         out.close();  
	}

}
