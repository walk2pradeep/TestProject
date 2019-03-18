package com.uttara.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Service {
	
	public String register(RegBean bean)
	{
		Connection con = null;
		PreparedStatement ps_ins = null;
		try
		{
			con = JDBCHelper.getConnection();
			ps_ins = con.prepareStatement("insert into register(name,email,dob) values(?,?,?)");
			ps_ins.setString(1,bean.getUname());
			ps_ins.setString(2,bean.getEmail());
			ps_ins.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			ps_ins.execute();
			
			return "success";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return "Oops something bad happened "+e.getMessage();
		}
		finally
		{
			JDBCHelper.close(ps_ins);
			JDBCHelper.close(con);
		}
		
	}

	public List<RegBean> getUserDetails() {
		
		List<RegBean> users = new ArrayList<RegBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps_sel = null;
		try
		{
			con = JDBCHelper.getConnection();
			ps_sel = con.prepareStatement("select * from register");
			ps_sel.execute();
			rs = ps_sel.getResultSet();
			RegBean bean = null;
			while(rs.next())
			{
				bean = new RegBean();
				bean.setUname(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				users.add(bean);
			}
			return users;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(con);
		}
	}
}
