package user;

import dbconnection.DAO;
import java.util.LinkedList;

import publication.Publication;

public class DefaultUser extends User {
  DAO dao = new DAO();

  public DefaultUser(String name, String password) {
	this.setName(name);
	this.setPassword(password);
	this.setStatus("user");
  }

  public DefaultUser(String name, String password, String email) {
	this.setName(name);
	this.setPassword(password);
	this.setEmail(email);
	this.setStatus("user");
  }

  public DefaultUser() {
	this.setStatus("user");
  }

  @Override
  public void addToDB() {
	dao.addUser(this);
  }

  @Override
  public boolean existInDb() {
	System.out.println("run existInDb");
	if (dao.exist(this))
	  return true;
	return false;
  }

  public LinkedList subscribes() {
	LinkedList<Publication> list = dao.vievSubscribes(this);
	return list;
  }

  public void addSubscrib(String pub_title) {
	dao.addSubscribe(pub_title, this);

  }

  public void deleteSub(String pub_title) {
	dao.deleteSubscribe(pub_title, this);
  }

  public boolean isAdmin() {
	return dao.isAdmin(this);
  }
}
