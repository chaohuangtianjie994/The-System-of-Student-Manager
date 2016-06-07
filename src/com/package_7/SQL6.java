/*
 * 功能：用来和数据库SQLserver进行连接，以及相应的查询方法。
 */
package com.package_7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

//写一个类，用来与数据库建立连接，并且查询数据
class GetSQL {
	// 设定用户名和密码
	static String userword;
	static String pwd;
	
	static String english;
	static String num;
	static String name;
	static String chinese;
	static String zhengzhi;
	static String math;
	
	static String age;
	static String salary;
	static String sex;
	static String zhicheng;
	static String teanum;
	static String teaname;

	static Connection ct = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	// 用于连接数据库的方法，可用于子类的继承
	public static void ConnectSQL() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			ct = DriverManager.getConnection("jdbc:odbc:ywq");
			System.out.println("The SQL is connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 用于向数据库进行查询的方法
	public static void querystu(String s,String username) {
		// 创建火箭车
		try {
			ps = ct.prepareStatement("select * from info where 权限=? and 用户名=? ");
			// 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
			ps.setString(1, s);
			ps.setString(2, username);
			// ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = ps.executeQuery();
			// 循环取出
			if (rs.next()) {
				// 将教师的用户名和密码取出
				userword = rs.getString(2);
				pwd = rs.getString(3);
				System.out.println("成功获取到密码和用户名from数据库");
				System.out.println(userword + "\t" + pwd + "\t");
				
				//调用登录方法
			
			}else
			{
				JOptionPane.showMessageDialog(null, "没有此用户，请重新输入！", "提示消息", JOptionPane.WARNING_MESSAGE);
			    
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//在教师表中进行查询
	public static void querytea(String s,String name ) {
		// 创建火箭车
		try {
			ps = ct.prepareStatement("select * from info_tea where 权限=? and 用户名=? ");
			// 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
			ps.setString(1, s);
			ps.setString(2, name);
			// ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = ps.executeQuery();
			// 循环取出
			if (rs.next()) {
				// 将教师的用户名和密码取出
				userword = rs.getString(2);
				pwd = rs.getString(3);
				System.out.println("成功获取到密码和用户名from数据库");
				System.out.println(userword + "\t" + pwd + "\t");
			}else
			{
				JOptionPane.showMessageDialog(null, "没有此用户，请重新输入！", "提示消息", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	//从数据库中根据学号或者教工号来查询数据，并且填入表格。
	public static void getdatastu(String s) {
		// 创建火箭车
		try {
			ps = ct.prepareStatement("select * from info where 学号 =? ");
			// 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
			ps.setString(1, s);
			// ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = ps.executeQuery();
			if(rs.next())	
			{
				// 将教师的用户名和密码取出
				num = rs.getString(4);
				name = rs.getString(5);
				math = rs.getString(6);
				chinese = rs.getString(7);
				english = rs.getString(8);
				zhengzhi = rs.getString(9);
			}else
			{
				JOptionPane.showMessageDialog(null, "沒有此学生，请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);
			}
	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void getdatatea(String s) {
		// 创建火箭车
		try {
			ps = ct.prepareStatement("select * from info_tea where 教师编号号 =? ");
			// 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
			ps.setString(1, s);
			// ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = ps.executeQuery();
			if(rs.next())	
			{
				// 将教师的用户名和密码取出
				teanum = rs.getString(4);
				teaname = rs.getString(5);
				sex = rs.getString(6);
				salary = rs.getString(7);
				zhicheng = rs.getString(8);
				age = rs.getString(9);
			}else
			{
				JOptionPane.showMessageDialog(null, "沒有此教师，请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);
			}
	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}