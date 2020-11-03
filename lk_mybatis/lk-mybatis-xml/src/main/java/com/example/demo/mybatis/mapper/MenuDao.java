package com.example.demo.mybatis.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.beans.Menu;

public class MenuDao {
	public static List<Menu> getAll() {
		//声明Connection对象
		Connection con;
		
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		
		//URL指向要访问的数据库名mydata
		String dburl = "jdbc:mysql://192.168.30.37:3306/sfrz_dev?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//链接的mysql
		//MySQL配置时的用户名
		String user = "iap";
		
		//MySQL配置时的密码
		String password = "iap2019";
		
		List<Menu> list = new LinkedList<Menu>();
		
		try {
		    //加载驱动程序
		    Class.forName(driver);
		    
		    //1.getConnection()方法，连接MySQL数据库！！
		    con = DriverManager.getConnection(dburl,user,password);
		    
		    if(!con.isClosed()) {
		    	System.out.println("Succeeded connecting to the Database!");
		    }
		    
		    //2.创建statement类对象，用来执行SQL语句！！
		    Statement statement = con.createStatement();
		    //要执行的SQL语句
		    String sql = "select * from WEB_SYSTEM_SOURCE WHERE SOURCE_TYPE_ID='SOURCE_MENU'";
		    
		    //3.ResultSet类，用来存放获取的结果集！！
		    ResultSet rs = statement.executeQuery(sql);
		   
		    while(rs.next()){
		        String menuId = rs.getString("source_Id");
		        String name = rs.getString("source_Name");
		        String url = rs.getString("link_Url");
		        String parentId = rs.getString("parent_Source_Id");
		        
		       Menu menu = new Menu();
		       menu.setMenuId(menuId);
		       menu.setName(name);
		       menu.setParentId(parentId);
		       menu.setUrl(url);
		       list.add(menu);
		    }
		    rs.close();
		    con.close();
		    
		    
		} catch(ClassNotFoundException e) {   
		    //数据库驱动类异常处理
		    System.out.println("Sorry,can`t find the Driver!");   
		    e.printStackTrace();   
		    } catch(SQLException e) {
		    //数据库连接失败异常处理
		    e.printStackTrace();  
		    }catch (Exception e) {
		    // TODO: handle exception
		    e.printStackTrace();
		}finally{
		    System.out.println("数据库数据成功获取！！");
		}
		return list;
		
	}
}
