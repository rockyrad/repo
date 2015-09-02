package com.highradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.dao.AlbumShareDAO;
import com.highradius.model.AlbumShare;

public class AlbumShareServlet extends HttpServlet {


	private static final long serialVersionUID = 7187017701919630829L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String userid =(String)session.getAttribute("userid"); 				//getting session attribute 
		
		if(userid==null)
		{
			try {
				response.sendRedirect("index.jsp");
				System.out.println("Session expired.Redirected to login page");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		String shareduser = request.getParameter("shareduser");
		String albumnum = (String)session.getAttribute("albumid");
		int albumid = Integer.parseInt(albumnum);
		AlbumShare albumshare =new AlbumShare();
		
		albumshare.setAlbumId(albumid);
		albumshare.setUserId(shareduser);
		
		int flag = AlbumShareDAO.shareAlbum(albumshare);
		if(flag!=0)
		{
			
		try {

			response.sendRedirect("HomePage.jsp");
			System.out.println("album shared");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		}
	}
}
