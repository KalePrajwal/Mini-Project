package com.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {
	public void admin() {
		Scanner sc = new Scanner(System.in);
		CrudOperations co = new CrudOperations();

		String ch = "yes";
		while (ch.equalsIgnoreCase("yes")) {

			System.out.println("------------Admin Panal-----------");
			System.out.println();
			System.out.println("Press 1 For User List ");
			System.out.println("Press 2 For Product List");
			System.out.println("Press 3 For Place Order List ");
			System.out.println("Press 4 For LogOut ");
			System.out.println();

			int no;
			no = sc.nextInt();

			switch (no) {
				case 1:

					co.uList();
					break;

				case 2:

					pList();
					break;
				case 3:

					co.orderList();
					break;
				default:

					System.out.println("Invalid Input");

			}

			System.out.println();
			sc.nextLine();
			System.out.println("Do You want to go Admin Panel Yes OR No : ");
			ch = sc.nextLine();

		}

		co.index();
	}

	public void pList() {

		Connection con = null;
		Statement st;
		ResultSet rs;

		try {
			Connections co = new Connections();
			con = co.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from products");
			while (rs.next()) {
				System.out.print("Product Id :" + rs.getString(1) + "  ||  ");
				System.out.print("Product Name :" + rs.getString(2) + "  ||    ");
				System.out.print("Product Discriptions :" + rs.getString(3) + "  ||  ");
				System.out.print("Price :" + rs.getFloat(4) + " ||  ");
				System.out.println("Quantity :" + rs.getInt(5) + "  || ");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
