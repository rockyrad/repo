package com.highradius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.highradius.model.AlbumShare;
import com.highradius.util.DbUtil;

public class AlbumShareDAO {

	
	/*public static Connection connectToDB(String database) {
		Connection con = null;

		System.out.println("Registering Driver....");
		try {
			String url = "jdbc:mysql://localhost:3306/" + database;
			// System.out.println(url);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver has been registered.\n");
			con = DriverManager.getConnection(url, "root", "");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}*/
	
	
	public static int shareAlbum(AlbumShare albumshare) {
		Connection con = null;
		int flag=0;
		String query = "insert into album_share values(?,?)";
		con = DbUtil.connectToDB();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, albumshare.getAlbumId());
			pstmt.setString(2, albumshare.getUserId());
			flag = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
}
