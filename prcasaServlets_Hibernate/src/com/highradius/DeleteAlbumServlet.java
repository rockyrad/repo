package com.highradius;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.highradius.dao.AlbumDAO;
import com.highradius.model.Album;

public class DeleteAlbumServlet extends HttpServlet {

	private static final long serialVersionUID = -2091698164562231917L;
	String path = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		int albumid = Integer.parseInt(request.getParameter("albumid"));
		Album album = new Album();
		album.setAlbumId(albumid);
		RequestDispatcher rd2= null;
		if (userid == null) {
			System.out.print("session has expired .Redirected to login page");
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		Album verifyalbum = new Album();
		
		verifyalbum = AlbumDAO.displayAlbum(album);
		if(userid.equals(verifyalbum.getCreateUserId()))
		{
		File file = new File(HrCasa.root_directory +"/"+ albumid );
		boolean status = false;
		int flag=0;
		RequestDispatcher rd= null;
		RequestDispatcher rd1= null;
		
		if (file.isDirectory())
			try {
				FileUtils.deleteDirectory(file);
				flag = AlbumDAO.deleteAlbum(album);
			
				if(flag==0)
				{
					rd = request.getRequestDispatcher("DeletePage.jsp?message='fail'");
					try {

						rd.forward(request, response);
						return;	
					} catch (ServletException e) {

						e.printStackTrace();
					}
				}
					
				else{
					Album newalbum = new Album();
					newalbum.setAlbumId(albumid);
					AlbumDAO.deleteAlbum(newalbum);
					rd1 = request.getRequestDispatcher("DeleteAlbum.jsp?message='pass'");
					try {

						rd1.forward(request, response);
						return;	
					} catch (ServletException e) {

						e.printStackTrace();
					}
				}
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(status);
		}
		
		else{
			Album valbum = new Album();
			valbum.setAlbumId(albumid);
			
			rd2 = request.getRequestDispatcher("DeleteAlbum.jsp?message='Not Authenticated'");
			try {

				rd2.forward(request, response);
				return;	
			} catch (ServletException | IOException e) {

				e.printStackTrace();
			}
			
		}
	}
}
