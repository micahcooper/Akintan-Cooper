/**
 * 
 */
package persist;

import model.Customer;

/**
 * @author micah cooper
 *
 */


import model.Product;
import model.Purchase;

import java.sql.*;


public class PersistenceModule {

	private Connection connection = null;
	/**
	 * Prepared SQL statement (combats: SQL Injections)
	 */

  public PersistenceModule( Connection connection ) throws Exception {
		this.connection = connection;
	}
  
  public void doAdd(Product product){
		String query = "INSERT INTO products (SKU, `Product Type`, Flavor, Cost, Price, Quantity) values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			//ps.setString(1, product.getSku());
			//ps.setString(2, product.getProductType());
			//ps.setString(3, product.getFlavor());
			ps.setDouble(4, product.getCost());
			ps.setDouble(5, product.getPrice());
			ps.setInt(6, product.getQuantity());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}

	public void doDelete(String sku) {
		String query = "DELETE FROM products WHERE SKU = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, sku);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}

	public void doUpdate(Product product){
		String query = "UPDATE products SET SKU=?, `Product Type`=?, Flavor=?, Cost=?, Price=?, Quantity=? WHERE SKU=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			//ps.setString(1, product.getSku());
            //ps.setString(2, product.getProductType());
            //ps.setString(3, product.getFlavor());
            ps.setDouble(4, product.getCost());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getQuantity());
            //ps.setString(7, product.getSku());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}

	/**
	 * doReadAll() is a refactor of the ReadQuery object's doRead() method
	 * in the previous version.
	 * 
	 * In this version, doReadAll() returns a result set rather than 
	 * storing it as a field of this helper object. The {@link #getHTMLTable()}
	 * helper is modified to accept the result set instead.
	 *  
	 * @return ResultSet
	 */
	public ResultSet doReadAll(){
		String query = "SELECT SKU,`Product Type`,Flavor,Cost,Price,Quantity FROM products"; // <-- Better

		ResultSet results = null;
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			results = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}

		return results;
	}



	
	/**
	 * doSearch
	 * 
	 * @param int
	 * @return Product
	 **/
	public ResultSet doSearch(String sku) {
		String query = "SELECT SKU,`Product Type`,Flavor,Cost,Price,Quantity FROM products where SKU like ?"; // <-- Better

		ResultSet results = null;
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, "%"+sku+"%");
			results = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}

		return results;
	}
	

/*****************************************Start Customer*****************************************/
	
	/**
	 * Authenticates a user in the database.
	 * @param username  The username for the user.
	 * @param password  The password for the user.
	 * @return A user object if successful, null if unsuccessful.
	 */
	public Customer authenticateUser(String username, String password) {
		String query = "select * from customer where username=? and password=?";
		
		Customer customer = null;
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println("username: "+username);
			
			//Add parameters to the ?'s in the preparedstatement and execute
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			//if we've returned a row, turn that row into a new user object
			if (rs.next()) {
				customer = new Customer(rs.getInt("idnumber"), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return customer;
	}

	public void addUser(Customer customer){
		String query = "INSERT INTO customer (firstname, lastname, dob, username, password) values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, customer.getFirstname());
			ps.setString(2, customer.getLastname());
			ps.setString(3, customer.getDob());
			ps.setString(4, customer.getUsername());
			ps.setString(5, customer.getPassword());


			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}
	
	public void doUpdateCustomerSessionId( int idnumber, String sessionid ){
		String query = "UPDATE customer set sessionid = ? where idnumber = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, sessionid);
			ps.setInt(2, idnumber);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}
	
	public Customer doGetCustomer( String sessionid ){
		String query = "select * from customer where sessionid=?";
		
		Customer customer = null;
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			//Add parameters to the ?'s in the preparedstatement and execute
			ps.setString(1, sessionid);
			ResultSet rs = ps.executeQuery();
			
			//if we've returned a row, turn that row into a new user object
			if (rs.next()) {
				customer = new Customer(
						rs.getInt("idnumber"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("dob"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("sessionid"));
			}
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return customer;
	}
	
/*****************************************Start Products*****************************************/
	/**
	 * doReadAll() returns a result set rather than 
	 *  
	 * @return ResultSet
	 */
	public ResultSet doReadAllProducts(){
		String query = "SELECT recnum, name, description, imageURL, quantity, cost, price  FROM product";

		ResultSet results = null;
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			results = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}

		return results;
	}
	
	/**
	 * @param results
	 * @return String
	 */
	public String getHTMLProductTable(ResultSet results){
		String table ="";
		table += "<table>\n";
		table += "<tr><th>ID</th><th>Product Name</th><th>Description</th><th>Price</th><th>Quantity</th><th></th><th></th></tr>";
		try {
			while(results.next()) {
				Product product = new Product(
						results.getInt("recnum"),
						results.getString("name"),
						results.getString("description"),
						results.getDouble("cost"),
						results.getDouble("price"),
						results.getInt("quantity")
						);
				table += product.getHTMLShopRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}
	
/*****************************************Start Purchases*****************************************/
	public void addPurchase(Purchase purchase){
		String query = "INSERT INTO purchase (product, customer, quantity, date_added, status) values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, purchase.getProduct());
			ps.setInt(2, purchase.getCustomer());
			ps.setInt(3, purchase.getQuantity());
			ps.setString(4, purchase.getDate_added());
			ps.setString(5, purchase.getStatus());


			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}
	
	public ResultSet doReadCustomerProducts( String sessionid ){
		String query = "SELECT product.recnum, product.name, product.description, product.cost, cost, price, purchase.quantity from purchase, customer, product where purchase.customer=customer.idnumber AND purchase.product=product.recnum AND customer.sessionid=?";
		ResultSet results = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, sessionid);
			results = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
		return results;
	}
	
	public String getHTMLCartTable( ResultSet results ){
		String table ="";
		table += "<table>\n";
		table += "<tr><th>ID</th><th>Product Name</th><th>Description</th><th>Price</th><th>Quantity</th><th></th><th></th></tr>";
		try {
			while(results.next()) {
				Product product = new Product(
						results.getInt("recnum"),
						results.getString("name"),
						results.getString("description"),
						results.getDouble("cost"),
						results.getDouble("price"),
						results.getInt("quantity")
						);
				table += product.getHTMLShopRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}
}
