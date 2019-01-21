/**
 * 
 */
package com.jdbc_connection_test_20_01_2019;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author lenovo
 *
 */
public class Runner {

	/**
	 * @param args
	 * 
	 * @author Rashmi
	 */
	public static void main(String[] args) throws Exception {
		
		
		Driver d = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/employee_database", "root", "Ankit@123");
		Statement st = con.createStatement();
		
		
		Scanner sc = new Scanner(System.in);

		int choice;
		
		do {
			DisplayMethod.showOptions();
			choice = sc.nextInt();
			
			switch(choice) {
				
				case 1: DisplayMethod.insertData(sc,con,st);
						break;
						
				case 2: DisplayMethod.displayData(st);
						break;
						
				case 3: DisplayMethod.update(st, sc);
						break;
						
				case 4: DisplayMethod.deleteById(st, sc);
						break;
						
				case 5: DisplayMethod.exportHTML(st);
						break;
						
				case 6: DisplayMethod.exportXml(st);
						break;
						
				case 7: DisplayMethod.exportCsv(st);
						break;
						
				case 8: DisplayMethod.insertImage(con, sc);
				break;
				
			}
			
		} while(choice != 0);
		sc.close();
	}

}


