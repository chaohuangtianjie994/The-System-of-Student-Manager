/*
 * 功能：学生成绩管理系统
 * 步骤1、登录界面的静态实现
 * 步骤2：实现界面的切换
 * 步骤3：使用数据库来验证用户名和密码
 * 步骤4：对代码进行优化。增加专门用来与数据库进行连接的类
 * 步骤5：优化代码，增加判断条件。
 * 步骤6：使用数据库进行查询时，优化查询方法和判断条件。数据库的表中可有多个数据。引入不同的表来查询。
 * 步骤7：教师界面实现了查询某个学生信息和某教师信息的功能。
 * author：ywq
 */
package com.package_7;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login6 extends JFrame implements ActionListener {

	//定义登录界面的组件
	JButton jb1,jb2,jb3=null;
	JRadioButton jrb1,jrb2=null;
	JPanel jp1,jp2,jp3,jp4=null;
    JTextField jtf=null;
	JLabel jlb1,jlb2,jlb3=null;
	JPasswordField jpf=null;
	ButtonGroup bg=null;	
	
	//菜单项
	JMenuBar jmb=null;	
	JMenu jm=null;
	JMenuItem jmi1,jmi2=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login6  ms=new Login6();
		
						
	}
	//构造函数
	public Login6()
	{
		 //创建组件
		jb1=new JButton("登录");
		jb2=new JButton("重置");
		jb3=new JButton("退出");
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		jmb=new JMenuBar(); //JMenuBar指菜单栏
		jm=new JMenu("选项"); //JMenu是菜单栏中的选项栏
		jmi1=new JMenuItem("开始"); //JMenuItem指选项栏中的选项
		jmi2=new JMenuItem("退出系统");
		jm.add(jmi1);
		jm.add(jmi2);
		jmb.add(jm);
		
		
		jrb1=new JRadioButton("教师",true);
		jrb2=new JRadioButton("学生");
		bg=new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
//		jrb2.setSelected(true);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();				
		
		jlb1=new JLabel("用户名：");
		jlb2=new JLabel("密    码：");
		jlb3=new JLabel("权    限：");
		
		jtf=new JTextField(10);
		jpf=new JPasswordField(10);
		//加入到JPanel中
		jp1.add(jlb1);
		jp1.add(jtf);
		
		jp2.add(jlb2);
		jp2.add(jpf);
		
		jp3.add(jlb3);
		jp3.add(jrb1);
		jp3.add(jrb2);
		
		jp4.add(jb1);
		jp4.add(jb2);
		jp4.add(jb3);
		
		//加入JFrame中
		this.setJMenuBar(jmb);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		//设置布局管理器
		this.setLayout(new GridLayout(4,1));
		//给窗口设置标题
		this.setTitle("学生成绩管理系统");
		//设置窗体大小
		this.setSize(300,250);
		//设置窗体初始位置
		this.setLocation(200, 150);
		//设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
		this.setResizable(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand()=="退出")
		{
			System.exit(0);
		}else if(e.getActionCommand()=="登录")
		{
			if(!jtf.getText().isEmpty() && !jpf.getText().isEmpty())
			{
				//当点击登录按钮时，首先与数据库建立连接
				GetSQL.ConnectSQL();
				//如果选中教师登录
				if(jrb1.isSelected())
				{
					GetSQL.querytea("教师",jtf.getText());
					//首先判断是否存在该用户，即是否得到了密码
					if(GetSQL.pwd ==null)
					{
                         this.clear();
					}else
					{
						//调用登录方法
						this.tealogin();
					}
				}else if(jrb2.isSelected()) //学生在登录系统
				{
					GetSQL.querystu("学生",jtf.getText());
					//首先判断是否存在该用户，即是否得到了密码
					if(GetSQL.pwd ==null)
					{
                         this.clear();
					}else
					{
						//调用登录方法
						this.stulogin();
					}
				   
				}
			}else if(jtf.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"请输入用户名","提示消息",JOptionPane.WARNING_MESSAGE);
			    this.clear();
			}else if(jpf.getText().isEmpty())	
			{
				JOptionPane.showMessageDialog(null,"请输入密码","提示消息",JOptionPane.WARNING_MESSAGE);
			    this.clear();
			}
		}else if(e.getActionCommand()=="重置")
		{
			this.clear();
		}			
		
	}
				
		//清空文本框和密码框
	public	void clear()
		{
			jtf.setText("");
			jpf.setText("");
		}
		    //学生登录判断方法
			public void stulogin()
			{
				if(GetSQL.pwd.equals(jpf.getText()))
				{
//					System.out.println("登录成功");
					JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
					this.clear();
					//关闭当前界面
					 dispose();
					 //创建一个新界面
					 Stu_UI6 ui=new Stu_UI6();
				}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else if(jtf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else if(jpf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else
				{
					JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
				    //清空输入框
					this.clear();
				}
			}
			
			//教师登录判断方法
			public void tealogin()
			{
				if(GetSQL.pwd.equals(jpf.getText()))
				{
//					System.out.println("登录成功");
					 JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
					 this.clear();	
					//关闭当前界面
					 dispose();
					 //创建一个新界面，适用于教师来管理学生
					Teacher6 t=new Teacher6();				 					
				}else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else if(jtf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else if(jpf.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
				}else
				{
					JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
				    //清空输入框
					this.clear();
				}
			}
		
}






	
