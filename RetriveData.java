package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetriveData {
	public void getId(String mob) {

		Connection con;
		PreparedStatement pst;
		ResultSet rs;

		Connections co = new Connections();
		con = co.getConnection();

		try {
			pst = con.prepareStatement("select * from user where mobile=?");
			pst.setString(1, mob);
			rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String nm = rs.getString(2);
				System.out.println();
				System.out.println(nm + " Please Remember This Id " + id + " When You Login");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
