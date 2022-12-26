package com.project;

import java.util.Scanner;

public class Customer {

	public void cust(int id, String nm1) {
		Scanner sc = new Scanner(System.in);
		CrudOperations co = new CrudOperations();

		System.out.println();
		System.out.println("Congratulation! " + nm1 + " now You can do your shopping");
		System.out.println();
		System.out.println("Press 1 For Products List ");
		System.out.println("Press 2 For LogOut");

		System.out.println();

		int no;
		no = sc.nextInt();

		switch (no) {
			case 1:
				co.pList(id, nm1);
				break;

			case 2:

				co.index();
				break;

			default:

				System.out.println("Invalid Input");
		}
	}
}
