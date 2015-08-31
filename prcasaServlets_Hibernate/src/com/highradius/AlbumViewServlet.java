package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.dao.PhotoDAO;
import com.highradius.model.Album;
import com.highradius.model.Photo;

public class AlbumViewServlet extends HttpServlet{

	
	private static final long serialVersionUID = -3561998373807126601L;

	
	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	{
		doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request ,HttpServletResponse response)
	{
		RequestDispatcher rd =null;
		RequestDispatcher rd1 =null;
		PrintWriter out=null;
		int albumid =Integer.parseInt(request.getParameter("albumid"));
	
		
		RequestDispatcher dispatcher =null;
		Album album = new Album();
			try {
				out = response.getWriter();
				System.out.println("albumid : "+albumid);
			} catch (IOException e) {

				e.printStackTrace();
			}
		
			album.setAlbumId(albumid);
						
			List<Photo> photoslist = PhotoDAO.displayPhoto(album);
			
			if(photoslist.isEmpty())
			{
				
				rd = request.getRequestDispatcher("CreateAlbum.jsp?albumid="+albumid);
				try {
					rd.forward(request, response);
					return;
				} catch (ServletException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			else
			{	
				HttpSession session = request.getSession();
				session.setAttribute("photos", photoslist);
				rd1 = request.getRequestDispatcher("viewalbum.jsp?albumid="+albumid);
				try {
					rd1.forward(request, response);
				} catch (ServletException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			
	}
}
