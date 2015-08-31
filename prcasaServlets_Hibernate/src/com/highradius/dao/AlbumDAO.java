package com.highradius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highradius.model.Album;
import com.highradius.model.User;

public class AlbumDAO {

	
	
	public static Album displayAlbum(Album givenalbum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbUtility.connectToDB();
		ResultSet rs = null;
		Album album = new Album();
		String query = "select * from album where album_id = '"+ givenalbum.getAlbum_id() + "'";

		try {
			pstmt = con.prepareStatement(query);

			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				album.setAlbum_id(rs.getInt("album_id"));
				album.setAlbum_name(rs.getString("album_name"));
				album.setCreate_user_id(rs.getString("create_user_id"));
				album.setCreate_time(rs.getString("create_time"));

			}
		} catch (SQLException e) {

			System.out.println("Exception in displayAlbum(...)");
		} finally {

		}

		return album;

	}
	
	public static List<Album> displayAlbums(User givenuser) {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DbUtility.connectToDB();
		ResultSet rs = null;

		List<Album> albumslist = new ArrayList<Album>();

		String query = "SELECT * FROM album WHERE album_id IN (SELECT album_id FROM album WHERE create_user_id = ? UNION SELECT album_id FROM album_share WHERE user_id=?)";
		String query1 = "select * from album where create_user_id = '"+ givenuser.getUserid() + "'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, givenuser.getUserid());
			pstmt.setString(2, givenuser.getUserid());
			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Album album = new Album();
				album.setAlbum_id(rs.getInt("album_id"));
				album.setAlbum_name(rs.getString("album_name"));
				album.setCreate_user_id(rs.getString("create_user_id"));
				album.setCreate_time(rs.getString("create_time"));
				albumslist.add(album);
			}
		} catch (SQLException e) {

			System.out.println("Exception in displayAlbums(...)");
		} finally {

		}

		return albumslist;

	}
	
	

	public static int albumIdGenerator()
	{
		Connection con = null;
		String mid;
		int album_id=1;
		PreparedStatement pstmt = null;
		con = DbUtility.connectToDB();
		ResultSet rs = null;
		
		String query = "select max(album_id) album_id from album";

		try {
			pstmt = con.prepareStatement(query);
			
			System.out.println("Query is : "+query);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			
				if (rs.next()) {
				album_id = rs.getInt("album_id");
			}
				
				System.out.println(album_id);
			
		} catch (SQLException e) {

			System.out.println("Exception in displayAlbums(...)");
		} finally {

		}
		
		return album_id;
	}
	
	
	
	public static int getAlbumId()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbUtility.connectToDB();
		ResultSet rs = null;
		String query = "select max(album_id) album_id from album";
		int albumid=0;
		try {
			pstmt = con.prepareStatement(query);
			
			System.out.println("Query is : "+query);
			rs = pstmt.executeQuery();
	
				if (rs.next()) {
				albumid = rs.getInt("album_id");
			}
							
		} catch (SQLException e) {

			System.out.println("Exception in displayAlbums(...)");
		} finally {

		}
		
		return albumid;
	}
	
	public static int createAlbum(Album album) {

		Connection con = null;
		PreparedStatement pstmt = null;

		con = DbUtility.connectToDB();
		int flag=0;
		System.out.println("User ID :" + album.getCreate_user_id());

		String query = "INSERT INTO album(album_name,create_user_id,create_time)VALUES(?,?,?)";
		//String query1 = "INSERT INTO album VALUES(?,?,?,?)";
		System.out.println("Query is : "+query);	
		try {
			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, albumIdGenerator());
			pstmt.setString(1,album.getAlbum_name());
			pstmt.setString(2,album.getCreate_user_id());
			pstmt.setString(3,album.getCreate_time());
			flag =pstmt.executeUpdate();
			
			System.out.println("No.of rows executed :"+flag);


		} catch (SQLException e) {
		System.out.println("Exception in createAlbum(...)");
			e.printStackTrace();
		} finally {
			DbUtility.closeConnection(null, null, pstmt, con);
		}

		return flag;

	}
	
	
	public static int deleteAlbum(Album album) {

		Connection con = null;
		PreparedStatement pstmt = null;

		con = DbUtility.connectToDB();
		int flag=0;

		String query = "delete from album where album_id=?";
		System.out.println("Query is : " + query);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, album.getAlbum_id());
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
		System.out.println("Exception in deleteAlbum(...)");
		e.printStackTrace();
		} finally {
			DbUtility.closeConnection(null, null, pstmt, con);
		}

		return flag;

	}
	
	
}
