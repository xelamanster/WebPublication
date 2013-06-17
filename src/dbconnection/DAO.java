package dbconnection;

import user.DefaultUser;
import user.User;
import publication.Publication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Class to work with the database.<br>
 * The database must contain the names of the tables: user, publication and 
 * many tables with the names of publication.<p>
 * For example:<br>
 * Table "user":
 * <TABLE BORDER=1>
 *       <TR>
 *               <TH>id</TH><TH>user</TH><TH>password</TH><TH>eMail</TH>
 *       </TR>
 *      <TR><TD>1</TD><TD>user1</TD><TD>password1</TD><TD>eMail@qwe.ua</TD></TR>
 *		<TR><TD>2</TD><TD>имя2</TD><TD>пароль2</TD><TD>qwe@eMail.ru</TD></TR>
 *		<TR><TD>3</TD><TD>имя3</TD><TD>password3</TD><TD>qwe@eMail.com</TD></TR>	
 * </TABLE><P>
 * Table "publication":
 * <TABLE BORDER=1>
 *       <TR>
 *               <TH>id</TH><TH>title</TH><TH>cost</TH>
 *       </TR>
 *      <TR><TD>1</TD><TD>title1</TD><TD>152.63</TD></TR>
 *		<TR><TD>2</TD><TD>название2</TD><TD>500</TD></TR>
 * </TABLE><P>
 * Table "title1":
 * <TABLE BORDER=1>
 *       <TR>
 *               <TH>id</TH><TH>subscribe_user_id</TH><TH>paid</TH>
 *       </TR>
 *      <TR><TD>1</TD><TD>2</TD><TD>0</TD></TR>
 *		<TR><TD>2</TD><TD>3</TD><TD>1</TD></TR>
 * </TABLE><P>
 * Table "title2":
 * <TABLE BORDER=1>
 *       <TR>
 *               <TH>id</TH><TH>subscribe_user_id</TH><TH>paid</TH>
 *       </TR>
 *      <TR><TD>1</TD><TD>2</TD><TD>0</TD></TR>
 *		<TR><TD>2</TD><TD>3</TD><TD>1</TD></TR>
 *		<TR><TD>3</TD><TD>1</TD><TD>0</TD></TR>	
 * </TABLE><P> 
 * 
 * @author Chugunov Alexandr
 * @version 1.0, 03/04/2013
 */
public class DAO {
  Connection c = null;
  // String db_name = "root", db_password = "root",
  // db = "jdbc:mysql://localhost:3306/DbWebPublication";
  String db_name = "adminUgfv4EA", db_password = "2LCWcCypLb9f",
	          db = "jdbc:mysql://127.6.144.129:3306/sample";
  Statement s = null;

  public boolean exist(User user) {
	createConnection();
	try {
	  ResultSet rs = s.executeQuery("select * from `user` where( name=\""
		            + user.getName() + "\"and password=\"" + user.getPassword() + "\");");
	  boolean res = rs.next();
	  closeConnection();
	  return res;
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  public boolean isAdmin(User user) {
	createConnection();
	try {
	  ResultSet rs = s.executeQuery("select * from `user` where name=\""
		                            + user.getName() + "\"");
	  rs.next();
	  boolean res = (rs.getString("Status").equals("admin"));
	  closeConnection();
	  return res;
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  public boolean exist(Publication pub) {
	createConnection();
	try {
	  ResultSet rs = s.executeQuery("select * from publication where title=\""
		                            + pub.getTitle() + "\"");
	  boolean res = rs.next();
	  closeConnection();
	  return res;
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  /**
	 * Create connects to the database.
	 * @return Result of connection;
	 */
  public boolean createConnection() {
	try {
	  Class.forName("com.mysql.jdbc.Driver");
	  c = DriverManager.getConnection(db, db_name, db_password);
	  s = c.createStatement();
	  return true;
	} catch (ClassNotFoundException e) {
	  e.printStackTrace();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  /**
	 * Close connect with database.
	 */
  protected void closeConnection() {
	try {
	  c.close();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }

  /**
	 * Entered into a database user data.<br>
	 * INSERT INTO `user`(`name`,`password`,`email`) VALUES ("name","pass","eMail");
	 */
  public void addUser(User user) {
	try {
	  createConnection();
	  System.out.println("add");
	  s.execute("insert into user(name,password,email,status)values('"
		     + user.getName() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '"
		     + user.getStatus() + "')");
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}

  }

  /**
	 * Remove the user from the database.<br>
	 * DELETE FROM `user` WHERE `name`="name";
	 * @param User - user
	 */
  public void deleteUser(User user) {
	try {
	  createConnection();
	  LinkedList pubs = vievSubscribes(user);
	  Iterator it = pubs.iterator();
	  while (it.hasNext()) {
		Publication pub = (Publication) it.next();
		s.execute("delete from '" + pub.getTitle() + "'where(name='" + user.getName()
			      + "')");
	  }
	  s.execute("delete from user where(name=" + user.getName() + ")"); 
	} catch (SQLException e) {
	  e.printStackTrace();
	} finally {
	  closeConnection();
	}
  }

  public void addPublication(Publication pub) {
	try {
	  createConnection();
	  s.execute("insert into `publication`(title,price,url)values('"
		  + pub.getTitle() + "','" + pub.getPrice() + "','" + pub.getUrl() + "');");
	  s.execute("create table `" + pub.getTitle()
		        + "`(number int not null auto_increment,username varchar(20),"
		        +"paid int(1) default 0, primary key (number));");
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }

  public void deletePublication(Publication pub) {
	try {
	  createConnection();
	  s.execute("delete from publication where(title=\"" + pub.getTitle() + "\");"
		        + "drop table `" + pub.getTitle() + "`;");
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }

  public void addSubscribe(String pub_title, User user) {
	try {
	  createConnection();
	  s.execute("insert into `" + pub_title + "`(username) values(\""
		        + user.getName() + "\");");
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}

  }

  public void deleteSubscribe(String title, User user) {
	try {
	  createConnection();
	  System.out.println(title);
	  s.execute("delete from `" + title + "` where(username=\"" + user.getName()
		        + "\");");
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }

  public LinkedList vievSubscribers(Publication pub) {
	LinkedList<User> list = new LinkedList();
	try {
	  createConnection();
	  ResultSet rs = s.executeQuery("select * from `" + pub.getTitle() + "`;");
	  while (rs.next()) {
		User user = new DefaultUser();
		user.setName(rs.getString("username"));
		list.add(user);
	  }
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return list;
  }

  public LinkedList vievSubscribes(User user) {
	LinkedList<Publication> list = new LinkedList();
	user.setSum(0);
	try {
	  LinkedList<Publication> pubs = vievPubs();
	  Iterator it = pubs.iterator();
	  while (it.hasNext()) {
		createConnection();
		Publication pub = (Publication) it.next();
		ResultSet rs = s.executeQuery("select * from `" + pub.getTitle()
			                       + "` where username=\"" + user.getName() + "\"");
		if (rs.next()) {
		  user.setSum(user.getSum()+pub.getPrice());
		  list.add(pub);
		}
	  }
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return list;
  }

  public LinkedList vievPubs() {
	LinkedList<Publication> list = new LinkedList();
	try {
	  createConnection();
	  ResultSet rs = s.executeQuery("select * from `publication`;");
	  while (rs.next()) {
		Publication pub = new Publication();
		pub.setTitle(rs.getString("title"));
		pub.setPrice(rs.getDouble("price"));
		pub.setUrl(rs.getString("url"));
		list.add(pub);
	  }
	  closeConnection();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return list;
  }

  public void pay(String title, String username) {
	try {
	  s.execute("UPDATE `" + title + "` SET `paid`='1' WHERE username='"
		        + username + "';");
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }
}
