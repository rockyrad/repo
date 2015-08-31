package com.highradius;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.dao.AlbumDAO;
import com.highradius.model.Album;
import com.highradius.model.User;

public class CreateAlbumServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String path = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		doPost(request, response);
	}
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid == null) {
			System.out.print("session has expired .Redirected to login page");
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			User user = new User();
			user.setUserId(userid);

			String albumname = request.getParameter("albumname");
			System.out.println(albumname);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			Album album = new Album();

			album.setCreatetime(new Date(dateFormat.format(date)));
			album.setCreateUserId(user.getUserId());
			album.setAlbumName(albumname);

			int flag = AlbumDAO.createAlbum(album);
			int albumid = AlbumDAO.getAlbumId();
			
			
			System.out.println(path);
			File f = new File(HrCasa.root_directory+"/"+albumid);
			 

			
			  if(f.exists()&&flag!=0){
				  System.out.println("File exists");
			  }else{
				  f.mkdir();
				  System.out.println("File not found!");
			  }
			try {
				response.sendRedirect("homepage");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
