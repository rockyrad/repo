package com.highradius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highradius.model.Album;

public class SharedFiles {

	public void shared() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = null;
		String user_id = null;
		ResultSet resultset = null;
		List<Album> albumslist = new ArrayList<Album>();
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hrcasa", "root", "");

			query = "SELECT album_id FROM album WHERE create_user_id = ? UNION SELECT album_id FROM album_share WHERE user_id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				Album album = new Album();
				album.setAlbum_id(resultset.getInt("album_id"));
				albumslist.add(album);
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
