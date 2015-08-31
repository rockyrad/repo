package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.dao.AlbumDAO;
import com.highradius.dao.UserDAO;
import com.highradius.model.Album;
import com.highradius.model.User;

public class LoginActionServlet extends HttpServlet {

	PrintWriter out =null;
	
	
	private static final long serialVersionUID = 8887021664282541388L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getParameter("userid");		
		String pwd = request.getParameter("pwd");
		
		RequestDispatcher rd = null;
		HttpSession session =null;
		
		User user = new User();
		user.setUserid(userid);
		user.setPwd(pwd);
				
		boolean flag=false;
		
		try {
			
			out = response.getWriter();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
											
		flag = UserDAO.selectUser(user);											// performing login validation	
		
		if (flag) {
		
			List<Album> albumslist = new ArrayList<Album>();
			albumslist = AlbumDAO.displayAlbums(user);
			session = request.getSession(true);
			session.setAttribute("userid", userid);						//Here we are setting session to user logged id		
			session.setAttribute("albumslist",albumslist);
			
			rd = request.getRequestDispatcher("HomePage.jsp");		// here dispatching to home page with user's details to be displayed in homepage

			
			try {

				rd.forward(request, response);

			
			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}
	
		}
		
		else {
			
			System.out.print("No user available.Redirected to login page");
			try {

				response.sendRedirect("index.jsp?message='fail'");

			} catch (IOException e) {

				e.printStackTrace();
			}
			
		}
		
	}

}
