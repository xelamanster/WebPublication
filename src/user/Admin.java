package user;

import dbconnection.DAO;

public class Admin extends User{
	private DAO dao=new DAO();
	private String name,password;
		   public String getName() {
	  return name;
	}

	public void setName(String name) {
	  this.name = name;
	}

	public String getPassword() {
	  return password;
	}

	public void setPassword(String password) {
	  this.password = password;
	}

	public String getStatus() {
	  return status;
	}

	public void setStatus(String status) {
	  this.status = status;
	}
		  String status="admin";
	public Admin(String name,String password){
		this.name=name;
		this.password=password;
	}
	
	public void pay(String title,String username){
		dao.pay(title, username);
	}
}
 