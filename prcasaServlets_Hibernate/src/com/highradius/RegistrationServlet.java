package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.dao.UserDAO;
import com.highradius.model.User;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 4500930026192212367L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String userid = request.getParameter("userid");

		User user = new User();
		user.setName(username);
		user.setPwd(password);																//assigning user details
		user.setUserid(userid);
		boolean flag = UserDAO.selectUser(user);

		if (flag) {

			System.out.print("User id already existing");
			try {
				response.sendRedirect("registration.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			user.setUserid(userid);
			user.setName(username);
			user.setPwd(password);

			System.out.println(username + password + userid);

			int rows = UserDAO.insertROwIntoDb(user);

			if (rows != 0) {

				try {
					response.sendRedirect("index.jsp");
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				System.out.print("Register again.....");
				try {
					response.sendRedirect("registration.jsp");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
	}

}