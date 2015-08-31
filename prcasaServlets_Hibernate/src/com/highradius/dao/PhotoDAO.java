package com.highradius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.highradius.model.Album;
import com.highradius.model.Photo;
import com.highradius.util.DbUtil;

public class PhotoDAO {
	
/*	public static String photoIdGenerator()
	{
		Connection con = null;
		String photo_id=null;
		PreparedStatement pstmt = null;
		con = DbUtility.connectToDB();
		ResultSet rs = null;
		String mid;
		
		String query = "select max(photo_id) photo_id from Photo";

		try {
			pstmt = con.prepareStatement(query);
			
			System.out.println("Query is : "+query);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			
				if (rs.next()) {
				photo_id = rs.getString("photo_id");
			}
				
				if(photo_id==null)
				photo_id="photo1";
				
				else{
				int length=photo_id.length();
				mid=photo_id.substring(5,length);
				int max=Integer.parseInt(mid);
				photo_id="photo".substring(0,5)+(++max);
				System.out.println(photo_id);
				
				}
		} catch (SQLException e) {

			System.out.println("Exception in displayAlbums(...)");
		} finally {

		}
		
		return photo_id;
	}*/

	
	
	public static void storeAPhoto(Photo photo)
	{
		
		Connection con = DbUtil.connectToDB();
		String query = "insert into Photo values(?,?,?,?)";
		System.out.println("Query :"+query);
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
 		
				pstmt.setInt(1,photo.getPhotoId());
				pstmt.setInt(2,photo.getAlbumId());
				pstmt.setString(3,photo.getPhotoPath());
				pstmt.setString(4,photo.getPhotoName());
				int flag  = pstmt.executeUpdate();
				System.out.println("No of rows executed :"+flag);
				
				if(flag!=0)
				{
					System.out.println("Number of Photo inserted are :"+flag);
				}
				
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("exception in storing Photo into database");
		}
		
	}
	
	public static void storePhoto(List<Photo> listPhoto)
	{
		Photo photo;
 		Iterator<Photo> iterator=listPhoto.iterator();
		while(iterator.hasNext()){
				photo=(Photo)iterator.next();

				storeAPhoto(photo);
		}
		
	}
	
	
	/*public static Photo displayPhoto(Photo givenphoto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = PhotoDAO.connectToDB("hrcasa");
		ResultSet rs = null;
		
		String query = "select * from Photo where album_id = '"+ givenphoto.getPhoto_id() + "'";

		try {
			pstmt = con.prepareStatement(query);

			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				givenphoto.setPhoto_id(rs.getString("photo_id"));
				givenphoto.setAlbum_id(rs.getString("album_id"));
				givenphoto.setPhoto_path(rs.getString("photo_path"));
				givenphoto.setPhoto_name(rs.getString("photo_name"));

			}
		} catch (SQLException e) {

			System.out.println("Exception in displayphoto(...)");
		} finally {

		}

		return givenphoto;

	}*/
	
	
	public static List<Photo> displayPhoto(Album givenalbum)
	{
		List<Photo> Photolist=new ArrayList<Photo>();
		
		PreparedStatement pstmt=null;
		Connection con = null;
		ResultSet resultset = null;
		String query = "select * from Photo where album_id =?";
		con = DbUtil.connectToDB();
		System.out.println(query);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, givenalbum.getAlbumId());
			resultset = pstmt.executeQuery();

			while (resultset.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(resultset.getInt("photo_id"));
				photo.setAlbumId(resultset.getInt("album_id"));
				photo.setPhotoPath(resultset.getString("photo_path"));
				photo.setPhotoName(resultset.getString("photo_name"));
				Photolist.add(photo);
			}
		} catch (SQLException e) {

			System.out.println("Exception in displayPhoto(...)");
			e.printStackTrace();
		} finally {

		}

		return Photolist;

	}	
	
}
