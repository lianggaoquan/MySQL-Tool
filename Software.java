package com.mysql.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple tool that can operate the MySQL databases quickly
 * 
 * @author lianggaoquan
 *
 */
@SuppressWarnings("all")
public class Software {
	
	static Scanner scan = new Scanner(System.in);
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static int operation = 0;
	static String form = "";
	
	static String field = "";
	static String symbol = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Please enter the name of database:");

			String database = scan.next(); // test_jdbc
			
			String localhost = "";
			String password = "";
			System.out.println("Enter the localhost and password of the database:");
			localhost = scan.next();
			password = scan.next();

//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, "root", "13570286252@");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:"+localhost+"/"
												+ database, "root", password);
			conn.setAutoCommit(false);
			
			//the list you want to operate
			System.out.println("Enter the list you want to edit:");
			form = scan.next();
			
			
			System.out.println("Enter the operation you want to continue:");
			System.out.println("enter 1 to query, 2 to delete record, and 3 to insert.");
			
			
			try{
				operation = scan.nextInt();
				
				if ((operation >= 1 && operation <= 3) == false) {
					throw new Exception("The value you input should be among 1~3 !");
				}
				
				if (operation == 1) {
					Query();
				} else if (operation == 2) {
					Delete();
				} else {
					Insert();
				}
				
				conn.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(operation==1){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (operation == 1 || operation == 2) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Query the database
	 */
	private static void Query() {

		try {
			
			
			inputValue();
			System.out.println("Inquiring the dataset...");
			
			String query = "select * from " + form + " where " + field + symbol + " ?";
			ps = conn.prepareStatement(query);
			
			System.out.println("Enter the inqure condition:");
			String condition = scan.next();
			
			ps.setObject(1, condition);
			
			rs = ps.executeQuery();		//Return the result set
			
			System.out.println("==========finding the result===========");
			
			while (rs.next()) {
				
				System.out.println(rs.getObject(1) + "---" + rs.getObject(2) + 
						"---" + rs.getObject(3));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Remove records
	 */
	private static void Delete(){
		
		try {
			
			System.out.println("Warning ! you are deleting the records....");
			
			inputValue();
			
			String delete = "delete from " + form + " where " + field + symbol + " ?";
			ps = conn.prepareStatement(delete);
			
			System.out.println("Enter the delete condition:");
			String condition = scan.next();
			ps.setObject(1, condition);
			ps.execute();
			System.out.println("=======Done!=======");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Insert records
	 */
	private static void Insert(){
		try {
			
			int capacity = 0;
			
			System.out.println("How many variables do you want to deal with?");
			capacity = scan.nextInt();
			
			String[] variables = new String[capacity];
			System.out.println("Now enter the variable names:");
			
			for(int m=0;m<variables.length;m++){
				variables[m] = scan.next();
			}
			
			String[] ins_arr = new String[2 * capacity - 1];
			String[] ins_arr_two = new String[ins_arr.length];	// ∑≈÷√"?"
			
			List<String> blanks = new ArrayList<String>();
			List<String> blanks_two = new ArrayList<String>();	// ∑≈÷√"?"
			
			for (int i = 0; i < variables.length - 1; i++) {
				blanks.add(variables[i]);
				blanks.add(",");
				
				blanks_two.add("?");
				blanks_two.add(",");
			}
			blanks.add(variables[variables.length - 1]);
			blanks_two.add("?");
			
			for(int j=0;j<blanks.size();j++){
				ins_arr[j] = blanks.get(j);
				ins_arr_two[j] = blanks_two.get(j);
			}
			
			String temp = "";
			String temp_two = "";
			
			for(String str:ins_arr){
				temp += str;
			}
			
			for(String str2:ins_arr_two){
				temp_two += str2;
			}
			
			String insert = "insert into "+form+"("+temp+")"+" values("+temp_two+")";
			
			ps = conn.prepareStatement(insert);
			System.out.println("Now enter the insert values:");
			
			//setObject
			String value = "";
			for (int i = 1; i < capacity + 1; i++) {
				value = scan.next();
				ps.setObject(i, value);
			}
			
			ps.execute();
			
			System.out.println("===========Done!=============");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * input operating values
	 */
	private static void inputValue(){
		System.out.println("Enter the operation field:");
		field = scan.next();
		
		System.out.println("Enter the operation symbol");
		symbol = scan.next();
	}
}
