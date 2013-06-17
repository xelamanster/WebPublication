package user;

/**
 * Class with the data of user.<br>
 * Has methods for setting and getting these parameters.
 * 
 * @author Chugunov Alexandr
 * @version 1.0, 03/05/2013
 * @param id
 *          - user id
 * @param name
 *          - user name
 * @param password
 *          - user password
 * @param email
 *          - user eMail
 * @param status
 *          - status of user
 * @param sum
 *          - summary price of publications
 */
public abstract class User {
  private int id;
  private String name, password, email, status;
  private double sum;

  public void addToDB() {
  }

  public boolean deleteFromDb() {
	return false;
  }

  public boolean existInDb() {
	return false;

  }

  public boolean isAdmin() {
	return false;
  }

  public int getId() {
	return id;
  }

  public void setId(int id) {
	this.id = id;
  }

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

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getStatus() {
	return status;
  }

  public void setStatus(String status) {
	this.status = status;
  }

  public double getSum() {
	return sum;
  }

  public void setSum(double sum) {
	this.sum = sum;
  }

}
