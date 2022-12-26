package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CrudOperations {
	public void index() {
		System.out.println("-------------E-Commerce Project---------------");
		int no;
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Press 1 For Register ");
		System.out.println("Press 2 For Login");
		System.out.println("Press 3 For Products List ");
		System.out.println();

		no = sc.nextInt();

		switch (no) {
			case 1:

				register();
				break;

			case 2:

				login();
				break;

			case 3:

				pList();
				break;

			default:

				System.out.println("Invalid Input");

		}
	}

	public void register() {
		RetriveData rd = new RetriveData();

		System.out.println("--------------Registration Form ---------------");
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Your Name : ");
		String nm = sc.nextLine();

		System.out.println("Enter Password : ");
		String ps = sc.nextLine();

		System.out.println("Enter City : ");
		String ct = sc.nextLine();

		System.out.println("Enter Mobile : ");
		String mob = sc.nextLine();

		Connection con;
		PreparedStatement pst;
		try {
			Connections co = new Connections();
			con = co.getConnection();

			pst = con.prepareStatement("insert into user(user_name,password,city,mobile) values(?,?,?,?);");
			pst.setString(1, nm);
			pst.setString(2, ps);
			pst.setString(3, ct);
			try {
				pst.setString(4, mob);
				pst.executeUpdate();
				System.out.println();
				System.out.println("User Register Sucessfully.....");

				rd.getId(mob);
				System.out.println();

				System.out.println("Press 1 For Login");
				System.out.println();
				System.out.println("Press 2 For Home ");
				System.out.println();

				int no;
				no = sc.nextInt();

				switch (no) {
					case 1:
						login();
						break;

					case 2:
						index();
						break;

					default:
						System.out.println("Invalid Input");
						break;
				}

			} catch (Exception e) {
				System.out.println("User Already Register.... ");
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void login() {
		System.out.println("------------Login Form-----------------");
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Enter Id : ");
		int id = sc.nextInt();

		sc.nextLine();
		System.out.println("Enter Password : ");
		String ps = sc.nextLine();

		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		try {
			Connections co = new Connections();
			con = co.getConnection();
			pst = con.prepareStatement("select * from user where user_id=? and password=?");
			pst.setInt(1, id);
			pst.setString(2, ps);
			rs = pst.executeQuery();

			if (rs.next()) {
				String nm1 = rs.getString(2);
				String usertype = rs.getString(4);

				if (usertype.equalsIgnoreCase("customer")) {

					Customer c = new Customer();
					c.cust(id, nm1);
				} else {

					Admin a = new Admin();
					a.admin();
				}
			} else {
				System.out.println();
				System.out.println("Invalid ID OR Password Please Enter Correct Information..");
				System.out.println();
				login();
			}
		} catch (Exception e) {
			System.out.println("Wrong");
		}
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

			System.out.println();
			System.out.println("Press 1 For Login ");
			System.out.println();

			Scanner sc = new Scanner(System.in);
			int no;
			no = sc.nextInt();

			switch (no) {
				case 1:
					login();
					break;

				default:
					System.out.println("Invalid Input ");
					index();

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void pList(int id, String nm1) {

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

			System.out.println();
			System.out.println("Press 1 for Purchase Product  ");
			System.out.println();
			System.out.println("Press 2 For Return Home Page  ");
			System.out.println();

			Scanner sc = new Scanner(System.in);
			int no;
			no = sc.nextInt();
			switch (no) {
				case 1:
					getProduct(id, nm1);
					break;
				case 2:
					index();
					break;

				default:

					System.out.println("Invalid Input");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void uList() {

		Connection con;
		Statement st;
		ResultSet rs;

		try {
			Connections co = new Connections();
			con = co.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from user");
			while (rs.next()) {
				System.out.print("User Id : " + rs.getInt(1) + "    ||    ");
				System.out.print("User Name : " + rs.getString(2) + "    ||    ");
				System.out.print("Password : " + rs.getString(3) + "    ||    ");
				System.out.print("User Type : " + rs.getString(4) + "    ||    ");
				System.out.print("City : " + rs.getString(5) + "    ||    ");
				System.out.println("Mobile No : " + rs.getString(6) + "    ||    ");
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getProduct(int id, String nm1) {

		Scanner sc = new Scanner(System.in);

		Connection con;
		PreparedStatement pst;
		ResultSet rs;

		Connections co = new Connections();
		con = co.getConnection();

		float totalprice = 0.0f;

		String Choice = "yes";
		int prodcount = 0;

		ArrayList<String> al = new <String>ArrayList();

		ArrayList<String> al1 = new <String>ArrayList();

		while (Choice.equalsIgnoreCase("yes")) {
			try {

				System.out.print("Enter Product Name : ");
				String nm = sc.nextLine();
				System.out.println();
				pst = con.prepareStatement("select * from products where product_name=?");
				pst.setString(1, nm);
				rs = pst.executeQuery();

				if (rs.next()) {
					String pname = rs.getString(2);
					String pid = rs.getString(1);
					int items = rs.getInt(5);

					if (items > 1) {
						totalprice = totalprice + (rs.getFloat(4));
						al.add(pname);
						al1.add(pid);
						System.out.println("Item Added Into Cart : " + al);
						System.out.println("Price of Product : " + totalprice);
						prodcount++;
						System.out.println("Product Quantity : " + prodcount);
					} else {

						System.out.println();
						System.out.println("Sorry! " + nm1 + " , " + pname + " Is  Out OF Stock ... ");
						System.out.println();
					}
				} else {
					System.out.println("Product Does Not Exist ");
				}

			} catch (Exception e) {
				System.out.println(e);

			}

			System.out.println("Do You want to add anather Product Yes OR No : ");
			Choice = sc.nextLine();

		}
		System.out.println("Press 1 For Place the Order ");
		System.out.println();
		System.out.println("Press 2 For Remove Product ");
		System.out.println();
		System.out.println("press 3 For Home Page ");
		System.out.println();

		int no = sc.nextInt();
		switch (no) {
			case 1:
				placeOrder(id, prodcount, totalprice, al, al1);
				break;

			case 2:
				removeProduct(id, prodcount, totalprice, al, al1);
				break;

			case 3:
				index();
				break;
		}

	}

	public void placeOrder(int id, int prodcount, Float totalprice, ArrayList al, ArrayList al1) {
		Scanner sc = new Scanner(System.in);

		Connection con;
		PreparedStatement pst;
		CallableStatement cs;

		Connections co = new Connections();
		con = co.getConnection();

		String product_id = new String();
		String prodnm = new String();

		for (int i = 0; i < al.size(); i++) {

			String pid = (String) al1.get(i);
			try {

				cs = con.prepareCall("{call updatequantity(?)}");
				cs.setString(1, pid);
				cs.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			prodnm = prodnm.concat(al.get(i) + ",");
			product_id = product_id.concat(al1.get(i) + ",");

		}

		try {
			System.out.println("Enter Name : ");
			String nm = sc.nextLine();

			System.out.println("Enter Address : ");
			String ad = sc.nextLine();

			System.out.println("Enter Mobile No : ");
			String mob = sc.nextLine();

			System.out.println("Enter Pay Mode : ");
			String pm = sc.nextLine();

			pst = con.prepareStatement(
					"insert into placed_order(user_id,user_name,product_id,products_name ,total_items,total_price,address,mobile,pay_mode,order_date,Time) values (?,?,?,?,?,?,?,?,?,Curdate(),Curtime());");
			pst.setInt(1, id);
			pst.setString(2, nm);
			pst.setString(3, product_id);
			pst.setString(4, prodnm);
			pst.setInt(5, prodcount);
			pst.setFloat(6, totalprice);
			pst.setString(7, ad);
			pst.setString(8, mob);
			pst.setString(9, pm);

			pst.executeUpdate();

			System.out.println();
			System.out.println("Your Order has been  Placed ...");

			System.out.println();
			System.out.println("Press 1 For Home Page ");
			System.out.println();

			int no = sc.nextInt();

			switch (no) {
				case 1:

					index();
					break;

				default:
					System.out.println("Invalid Input");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void removeProduct(int id, int prodcount, Float totalprice, ArrayList al, ArrayList al1) {
		Scanner sc = new Scanner(System.in);
		Connection con;
		PreparedStatement pst;
		ResultSet rs;

		Connections co = new Connections();
		con = co.getConnection();

		String ch = "yes";

		while (ch.equalsIgnoreCase("yes")) {

			try {
				System.out.println();
				System.out.print("Enter Product : ");
				String nm = sc.nextLine();
				System.out.println();

				pst = con.prepareStatement("select * from products where product_name=? ");
				pst.setString(1, nm);
				rs = pst.executeQuery();
				if (rs.next()) {
					String pname = rs.getString(2);
					String pid = rs.getString(1);
					Float price = rs.getFloat(4);

					al.remove(pname);
					al1.remove(pid);
					prodcount--;
					totalprice = totalprice - price;

					System.out.println("Item Added Into Cart : " + al);
					System.out.println("Price of Product : " + totalprice);

					System.out.println("Product Quantity : " + prodcount);
					System.out.println();

				} else {
					System.out.println("This Product Is Not In Cart ");
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			System.out.print("Do You want to Remove anather Product Yes OR No : ");
			ch = sc.nextLine();

		}

		System.out.println();
		System.out.println("Press 1 For Place the Order ");
		System.out.println();
		System.out.println("press 3 For Home Page ");
		System.out.println();

		int no = sc.nextInt();
		switch (no) {
			case 1:
				placeOrder(id, prodcount, totalprice, al, al1);
				break;

			case 2:
				index();
				break;

			default:

				System.out.println("Invalid Input");
		}

	}

	public void orderList() {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st;
		ResultSet rs;

		try {
			Connections co = new Connections();
			con = co.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from placed_order");
			while (rs.next()) {
				System.out.print("srno :" + rs.getInt(1) + "  ||  ");
				System.out.print("user_id :" + rs.getInt(2) + "  ||    ");
				System.out.print("user_name :" + rs.getString(3) + "  ||  ");
				System.out.print("product_id :" + rs.getString(4) + " ||  ");
				System.out.print("products_name :" + rs.getString(5) + "  || ");
				System.out.print("total_items :" + rs.getInt(6) + "  ||  ");
				System.out.print("total_price :" + rs.getFloat(7) + "  ||  ");
				System.out.print("address :" + rs.getString(8) + "  ||  ");
				System.out.print("mobile :" + rs.getString(9) + "  ||  ");
				System.out.print("pay_mode :" + rs.getString(10) + "  ||  ");
				System.out.print("order_date :" + rs.getString(11) + "  ||  ");
				System.out.println("Time :" + rs.getString(12) + "  ||  ");

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
