/**
 * 
 */
package com.jdbc_connection_test_20_01_2019;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author lenovo
 *
 */
public class DisplayMethod {
	
	
	public static void showOptions() {
		System.out.println("Enter 1 for insert data");
		System.out.println("Enter 2 for display data");
		System.out.println("Enter 3 for update data");
		System.out.println("Enter 4 for delete data");
		System.out.println("Enter 5 for export in html file");
		System.out.println("Enter 6 for export in xml file");
		System.out.println("Enter 7 for export in csv file");
		System.out.println("Enter 8 for insert image");
		
		System.out.println("Enter 0 for exit");
		
	}
	
	
	public static void insertData(Scanner sc, Connection con, Statement st) throws Exception {
		System.out.println("Enter employee id");
		int e_id = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter employee name");
		String e_name = sc.nextLine();
		
		
		System.out.println("Enter employee salary");
		double e_sal = sc.nextDouble();
		
		sc.nextLine();
		System.out.println("Enter employee address");
		String e_address= sc.nextLine();
		
		
		String query = "insert into employee_info values (" + e_id + ",'" + e_name + "'," + e_sal + ",'" + e_address + "')";
		
		int count = st.executeUpdate(query);
		if(count > 0) {
			System.out.println("Record is inserted");
		} else {
			System.out.println("Record is not inserted");
		}
		
	}
	
	public static void displayData(Statement st) throws Exception {
		
		String query = "select * from employee_info";
		
		ResultSet rs = st.executeQuery(query);
		
		boolean temp = rs.next();
		if(temp) {
			while(temp) {
				System.out.println("\ne_id" +" "+ rs.getInt("e_id") + "\ne_name"  +" " + rs.getString("e_name") + "\ne_sal"  +" " + rs.getDouble("e_sal") + "\ne_address"  +" " + rs.getString("e_address") );
				
				temp = rs.next();
			}
			
		} else {
			System.out.println("No records are available");
		}
	}
	
	
	
	public static void update(Statement st,Scanner sc) throws SQLException {
		
		System.out.println("Enter employee id");
		int e_id = sc.nextInt();
	
		sc.nextLine();
		
		
		
		System.out.println("Enter employee name");
		String e_name = sc.nextLine();
		
		System.out.println("Enter employee salary");
		double e_sal = sc.nextDouble();
		
		sc.nextLine();
		
		System.out.println("Enter employee address");
		String e_address= sc.nextLine();
		
	
		
	
	//	System.out.println(e_address);
		String updateQuery = "update employee_info set e_name = '" + e_name + "', e_sal = " + e_sal + " , e_address = '" + e_address + "' where e_id = " + e_id;

		
		int count = st.executeUpdate(updateQuery);
		if (count > 0) {
			System.out.println("Record updated");
		} else {
			System.out.println("Something went wrong..!!");
		}
	}
	
	
	
	public static void deleteById(Statement st,Scanner sc) throws Exception {
		
		System.out.println("Enter employee id");
		int id = sc.nextInt();
		
		String deleteQuery = "delete from employee_info where e_id = " + id;

		int resCount = st.executeUpdate(deleteQuery);
		if (resCount > 0) {
			System.out.println("Record deleted ");
		} else {
			System.out.println("Record not available with this id..!!");
		}

	}
	
	public static void exportHTML(Statement st) throws Exception {
		
		ResultSet rs = st.executeQuery("select * from employee_info");
		
		String data = "<html><body><center><br><br>";
		data = data + "Employee Details";
		
		data = data + "<h2>";
		data = data + "<table border='1' bgcolor='lightblue'>";
		
		data = data + "<tr>";
		data = data + "<td>ENO</td><td>ENAME</td><td>ESAL</td><td>EADDRESS</td></tr>";
		
		while(rs.next()) {
			data = data + "<tr>";
			data = data +"<td>" + rs.getInt("e_id") + "</td>";
			data = data +"<td>" + rs.getString("e_name") + "</td>";
			data = data +"<td>" + rs.getDouble("e_sal") + "</td>";
			data = data +"<td>" + rs.getString("e_address") + "</td>";
			
			data = data + "</tr>";
			
		}
		
		data = data + "</table></center></body></html>";
		
		
		
		FileOutputStream fos = new FileOutputStream("D:\\export\\employeeinfo.html");
		byte[] bt = data.getBytes();
		fos.write(bt);
		
		System.out.println("Employee data retrieve successfully and send to D:\\export\\employeeinfo.html");
		
		fos.close();
		
		
	}
	
	
	public static void exportXml(Statement st) throws  Exception {
		
		ResultSet rs = st.executeQuery("select * from employee_info");
		
		String data = "<employees>";
		
		
		
		while(rs.next()) {
			data = data + "<employee>";
			
				data = data +"<e_id>" + rs.getInt("e_id") + "</e_id>";
				data = data +"<e_name>" + rs.getString("e_name") + "</e_name>";
				data = data +"<e_sal>" + rs.getDouble("e_sal") + "</e_sal>";
				data = data +"<e_address>" + rs.getString("e_address") + "</e_address>";

			data = data + "</employee>";
			
		}
		
		data = data + "</employees>";
		
		
		FileOutputStream fos = new FileOutputStream("D:\\export\\employeeinfo.xml");
		byte[] bt = data.getBytes();
		fos.write(bt);
		
		System.out.println("Employee data retrieve successfully and send to  D:\\export\\employeeinfo.xml");
		
		fos.close();
	}
	
	
	public static void exportCsv(Statement st) throws Exception {
		ResultSet rs = st.executeQuery("select * from employee_info");
		
		 PrintWriter pw = new PrintWriter(new File("D:\\export\\employeeinfo.csv"));
	        StringBuilder sb = new StringBuilder();
	        sb.append("e_id");
	        sb.append(',');
	        sb.append("e_name");
	        sb.append(',');
	        sb.append("e_sal");
	        sb.append(',');
	        sb.append("e_address");
	        sb.append("\n");

	        while(rs.next()) {
	        	
	        	sb.append("" + rs.getInt("e_id"));
		        sb.append(',');
		        
		        sb.append("" + rs.getString("e_name"));
		        sb.append(',');
		        sb.append("" + rs.getString("e_sal"));
		        
		        sb.append(',');
		        sb.append("" + rs.getString("e_address"));
		        
		        sb.append("\n");
		        
	        }
		        pw.write(sb.toString());
		        pw.close();
	
			System.out.println("Employee data retrieve successfully and send to D:\\export\\employeeinfo.csv");
			
			
		}

	
	
	public static void insertImage(Connection con , Scanner sc) throws SQLException, Exception {
		
		PreparedStatement pst = con.prepareStatement("insert into employee_info values(?,?)");
		
		System.out.println("Enter employee id");
		int e_id = sc.nextInt();
		
		
	//	PreparedStatement pst = con.prepareStatement("update employee_info set image = '" +   +" '  where e_id = " + e_id );
		
		
		
		
		//pst.setString(1,"e_id");
		File f = new File("E:\\images\\flower.jpg");
		FileInputStream fis = new FileInputStream (f);
		
		pst.setBlob(5, fis);
		
		int Count = pst.executeUpdate();
		
				if(Count > 0) {
					System.out.println("Image is inserted in Database");
					
					
				} else {
					System.out.println("Image is Not inserted in Database");
				}
				
		
	}
	
}
