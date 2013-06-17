package publication;

import dbconnection.DAO;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class for representing each publication as an object.<br>
 * Has methods for setting and getting parameters of publication.
 * @author Chugunov Alexandr
 * @version 1.0, 03/05/2013
 * @param title - publication title
 * @param price - publication cost
 * @param url - url of publication
 */
public class Publication {
  
  private DAO dao = new DAO();
  private String title;
  private double price;
  private String url;

  public Publication() {

  }

  public Publication(String title, double price, String url) {
	this.title = title;
	this.price = price;
	this.url = url;
  }

  public Publication(String title) {
	this.title = title;
  }

  /** 
	 * Add publication to database;
	 */ 
  public void addToDb() {
	dao.addPublication(this);
  }

  /** 
	 * Delete publication from database;
	 */ 
  public void deleteFromDb() {
	dao.deletePublication(this);
  }

  public LinkedList viewAll() {
	LinkedList list = dao.vievPubs();
	return list;
  }
  
  /** 
	 * @return Title of publication
	 */ 
  public String getTitle() {
    return title;
  }

  /** 
	 * @return Price of publication
	 */ 
  public double getPrice() {
    return price;
  }

  /** 
	 * @return url of publication
	 */ 
  public String getUrl() {
    return url;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
