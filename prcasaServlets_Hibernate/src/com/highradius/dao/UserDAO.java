package com.highradius.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highradius.model.User;
import com.highradius.util.DbUtil;

public class UserDAO {

	public static Connection connectToDB(String database) {
		Connection con = null;

		System.out.println("Registering Driver....");
		try {
			String url = "jdbc:mysql://localhost:3306/" + database;
			// System.out.println(url);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver has been registered.\n");
			con = DriverManager.getConnection(url, "root", "root");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}


	public static int insertROwIntoDb(User user) {

		Connection con = null;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		String query = "insert into user(user_id,name,password) values(?,?,?)";
		int rows = 0;
		try {
			con = DbUtil.connectToDB();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			rows = pstmt.executeUpdate();

			System.out.println(query + "\n\n");

			System.out.println("Number of rows inserted :" + rows);
			System.out.println("Details inserted successfully");

		} catch (SQLException e) {

		} catch (Exception e) {

		}

		finally {
			DbUtil.closeConnection(rs, null, pstmt, con);
		}
		return rows;
	}

	public static List<User> retrieveRowFromDb() {
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DbUtil.connectToDB();
		ResultSet rs = null;
		String query ="select * from user";
		List<User> users = new ArrayList<User>();

		try {
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(Integer.parseInt(rs.getString("userid")));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}

		} catch (SQLException e) {

		} finally {
			DbUtil.closeConnection(rs, null, pstmt, con);			
		}

		return users;
	}

	public static boolean selectUser(User givenuser) {

		Connection con = null;
		PreparedStatement pstmt = null;
		con=UserDAO.connectToDB("pradeep");
		System.out.println(con);
		ResultSet rs = null;

		String query = "select * from user where user_id= ? and password = ?";

		System.out.println(query);
		boolean flag = false;

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, givenuser.getUserId());
			pstmt.setString(2, givenuser.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {

			System.out.print("exception  in selectUser(...)");
		}

		finally {
			DbUtil.closeConnection(rs, null, pstmt, con);			
		}
		return flag;
	}

}