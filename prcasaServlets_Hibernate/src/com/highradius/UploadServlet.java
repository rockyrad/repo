package com.highradius;

// Import required java libraries
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.highradius.dao.PhotoDAO;
import com.highradius.model.Album;
import com.highradius.model.Photo;
import com.highradius.model.User;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -8220354763161935917L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart;

		int maxFileSize = 500 * 1024;
		int maxMemSize = 4 * 1024;
		File file;
		String filePath = HrCasa.root_directory;
		int j;
		int count = 0;
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid"); // getting
																	// session
																	// attribute
		
		if (userid == null) {
			try {
				response.sendRedirect("index.jsp");
				System.out.println("Session expired.Redirected to login page");

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	isMultipart = ServletFileUpload.isMultipartContent(request);

		Album album = new Album();

		List<Photo> photoslist = new ArrayList<Photo>();
		response.setContentType("text/html");

		int albumid = (Integer) session.getAttribute("albumid");
		album.setAlbumId(albumid);
		
		User user = new User();
		user.setUserId(userid);
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (!isMultipart) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(HrCasa.root_directory));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			j = fileItems.size();

			// Process the uploaded file items

			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					if ("".equals(fileName) || fileName == null)
						continue;

					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					if(sizeInBytes>500*1024){
						throw new SizeLimitExceededException();
					}
						System.out.print(sizeInBytes);
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ albumid
										+ "/"
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath+"/"+
										+ albumid
										+ "/"
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					
					Photo photo = new Photo();
					photo.setPhotoName(fileName);
					photo.setAlbumId(albumid);
					photo.setPhotoPath(albumid + "/" + fileName);
					photoslist.add(photo);
					
					out.println("Uploaded Filename: " + fileName + "<br>");
				
				} 
			}
			
			PhotoDAO.storePhoto(photoslist);
			
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
		
			try {
				response.sendRedirect("MaxFileSizeError.jsp?albumid="+albumid);
				return;
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		try {

			rd = request.getRequestDispatcher("homepage");
			rd.forward(request, response);
			return;
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
}