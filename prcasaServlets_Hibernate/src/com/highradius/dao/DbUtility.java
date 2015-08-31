package com.highradius.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.highradius.HrCasa;

public class DbUtility {

	public static Connection connectToDB() {
		Connection con = null;

		try {
			String url = "jdbc:mysql://localhost:3306/"+"pradeep";
			System.out.println(url);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver has been registered.\n");
			
			con = DriverManager.getConnection(url,"root","root");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}


	 public static void closeConnection(ResultSet rs,Statement stmt,PreparedStatement pstmt, Connection con) {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			try{
				if(stmt!=null&& !stmt.isClosed())
				{
					stmt.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

}