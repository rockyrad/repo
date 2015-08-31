package com.highradius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.highradius.model.Album;
import com.highradius.model.User;
import com.highradius.util.DbUtil;

public class AlbumDAO {

	
	
	public static Album displayAlbum(Album givenalbum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbUtil.connectToDB();
		ResultSet rs = null;
		Album album = new Album();
		String query = "select * from album where album_id = '"+ givenalbum.getAlbumId() + "'";

		try {
			pstmt = con.prepareStatement(query);

			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				album.setAlbumId(rs.getInt("album_id"));
				album.setAlbumName(rs.getString("album_name"));
				album.setCreateUserId(Integer.parseInt(rs.getString("create_user_id")));
				album.setCreatetime(new Date(rs.getString("create_time")));

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

		con = DbUtil.connectToDB();
		ResultSet rs = null;

		List<Album> albumslist = new ArrayList<Album>();

		String query = "SELECT * FROM album WHERE album_id IN (SELECT album_id FROM album WHERE create_user_id = ? UNION SELECT album_id FROM album_share WHERE user_id=?)";
		String query1 = "select * from album where create_user_id = '"+ givenuser.getUserId() + "'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, givenuser.getUserId());
			pstmt.setInt(2, givenuser.getUserId());
			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Album album = new Album();
				album.setAlbumId(rs.getInt("album_id"));
				album.setAlbumName(rs.getString("album_name"));
				album.setCreateUser(rs.getString("create_user_id"));
				album.setCreatetime(new Date(rs.getString("create_time")));
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
		con = DbUtil.connectToDB();
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
		con = DbUtil.connectToDB();
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

		con = DbUtil.connectToDB();
		int flag=0;
		System.out.println("User ID :" + album.getCreateUserId());

		String query = "INSERT INTO album(album_name,create_user_id,create_time)VALUES(?,?,?)";
		//String query1 = "INSERT INTO album VALUES(?,?,?,?)";
		System.out.println("Query is : "+query);	
		try {
			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, albumIdGenerator());
			pstmt.setString(1,album.getAlbumName());
			pstmt.setInt(2,album.getCreateUserId());
			pstmt.setDate(3,new java.sql.Date(Long.parseLong(album.getCreatetime().toString())));
			flag =pstmt.executeUpdate();
			
			System.out.println("No.of rows executed :"+flag);


		} catch (SQLException e) {
		System.out.println("Exception in createAlbum(...)");
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(null, null, pstmt, con);
		}

		return flag;

	}
	
	
	public static int deleteAlbum(Album album) {

		Connection con = null;
		PreparedStatement pstmt = null;

		con = DbUtil.connectToDB();
		int flag=0;

		String query = "delete from album where album_id=?";
		System.out.println("Query is : " + query);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, album.getAlbumId());
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
		System.out.println("Exception in deleteAlbum(...)");
		e.printStackTrace();
		} finally {
			DbUtil.closeConnection(null, null, pstmt, con);
		}

		return flag;

	}
	
	
}
