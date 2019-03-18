package com.uttara.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHelper {

	public static void close(ResultSet x)
	{
		try
		{
			if(x!=null)
				x.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Statement x)
	{
		try
		{
			if(x!=null)
				x.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Connection x)
	{
		try
		{
			if(x!=null)
				x.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		String url = "jdbc:hsqldb:hsql://localhost/xdb";
		String dName = "org.hsqldb.jdbcDriver";
		String uid = "SA";
		String pass = "";
		
		Connection con = null;
		try
		{
			Class.forName(dName);
			
			con = DriverManager.getConnection(url,uid,pass);
			System.out.println("established con "+con);
			return con;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
