package model;
/**
 * Java representation of the User table in the database
 * @author based on Benzinger's work
 *
 */
public class Customer {

	private int idnumber;
	private String username;
	private String password;
	private String dob;
	private String firstname;
	private String lastname;
	private String sessionid;
	
	public Customer() {

	}
	/**
	 * Constructor
	 * @param id  the unique user ID
	 * @param username  the unique user's username
	 * @param password	the password associated with the account
	 */
	public Customer(int id, String username, String password) {
		this.idnumber = id;
		this.username = username;
		this.password = password;
	}
	
	public Customer(int id, String firstname, String lastname, String dob, String username, String password, String sessionid ) {
		this.idnumber = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.username = username;
		this.password = password;
		this.sessionid = sessionid;
	}
	
	

	/**
	 * Return the id
	 */
	public int getIdnumber() {
		return idnumber;
	}

	/**
	 * Set a new id
	 */
	public void setIdnumber(int id) {
		this.idnumber = id;
	}

	/**
	 * Return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set a new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Return the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set a new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the sessionid
	 */
	public String getSessionid() {
		return sessionid;
	}
	/**
	 * @param sessionid the sessionid to set
	 */
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [idnumber=" + idnumber + ", username=" + username + ", password=" + password + ", dob=" + dob
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", sessionid=" + sessionid + "]";
	}
}
