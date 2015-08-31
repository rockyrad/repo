package com.highradius;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.dao.AlbumDAO;
import com.highradius.model.Album;
import com.highradius.model.User;

public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119506613340279431L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher rd = null;
		HttpSession session = null;

		session = request.getSession(true);

		String userid = (String) session.getAttribute("userid");

		if (userid == null) {

			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		User user = new User();
		user.setUserid(userid);

		List<Album> albumslist = new ArrayList<Album>();

		albumslist = AlbumDAO.displayAlbums(user);
		session.setAttribute("albumslist",albumslist);

		rd = request.getRequestDispatcher("HomePage.jsp"); // here dispatching to home page with user's details to be displayed in home page

		try {

			rd.forward(request, response);

		} catch (ServletException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}