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
import java.text.NumberFormat;


public class PersistenceModule {

	private Connection connection = null;
	/**
	 * Prepared SQL statement (combats: SQL Injections)
	 */

  public PersistenceModule( Connection connection ) throws Exception {
		this.connection = connection;
	}
  
/*****************************************Start Customer*****************************************/
	
	/**
	 * Authenticates a user in the database.
	 * @param username  The username for the user.
	 * @param password  The password for the user.
	 * @return A user object if successful, null if unsuccessful.
	 */
	public Customer authenticateUser(String username, String password) {
		String query = "select idnumber, firstname, lastname, dob, username, password, sessionid from customer where username=? and password=?";
		
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
		String query = "SELECT recnum, name, description, category, imageURL, quantity, cost, price  FROM product";

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
	
	public Product doGetProduct( int recnum ){
		String query = "select recnum, name, description, category, imageURL, quantity, cost, price from product where recnum=?";
		
		Product product = null;
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			//Add parameters to the ?'s in the preparedstatement and execute
			ps.setInt(1, recnum);
			ResultSet rs = ps.executeQuery();
			
			//if we've returned a row, turn that row into a new user object
			if (rs.next()) {
				product = new Product(
						rs.getInt("recnum"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("category"),
						rs.getString("imageURL"),
						rs.getInt("quantity"),
						rs.getDouble("cost"),
						rs.getDouble("price"));
			}
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return product;
	}
	
	public void updateProduct(Product product){
		String query = "UPDATE product SET name=?, description=?, category=?, imageURL=?, quantity=?, cost=?, price=? WHERE recnum=?";

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getImageURL());
            ps.setInt(5, product.getQuantity());
            ps.setDouble(6, product.getCost());
            ps.setDouble(7, product.getPrice());
            ps.setInt(8, product.getRecnum());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block; add real error handling!
			e.printStackTrace();
		}
	}
	
	/**
	 * @param results
	 * @return String
	 */
	public String getHTMLProductTable(ResultSet rs){
		String table ="";
		table += "<table>\n";
		table += "<tr><th>ID</th><th>Product Name</th><th>Description</th><th>Category</th><th>Price</th><th>Quantity</th><th></th><th></th></tr>";
		try {
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("recnum"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("category"),
						rs.getString("imageURL"),
						rs.getInt("quantity"),
						rs.getDouble("cost"),
						rs.getDouble("price"));
				table += product.getHTMLProductRow();
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
		String query = 
				"SELECT  product.recnum as productid, product.name as productName, product.description, product.imageURL, product.category, product.quantity as inventoryQuantity, product.cost, product.price,"
				+ "purchase.recnum as purchaseid, purchase.quantity as purchaseQuantity, date_added, date_purchased, status,"
				+ "idnumber as customerid, firstname, lastname, dob, password, username, sessionid "
				+ "from purchase, customer, product "
				+ "where purchase.customer=customer.idnumber AND purchase.product=product.recnum AND customer.sessionid=?";
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
	
	public String getHTMLCartTable( ResultSet rs ){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String table ="";
		table += "<table>\n";
		table += "<tr><th>ID</th><th>Product Name</th><th>Description</th><th>Price</th><th>Quantity</th><th></th><th></th></tr>";
		try {
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("productid"),
						rs.getString("productName"),
						rs.getString("description"),
						rs.getString("category"),
						rs.getString("imageURL"),
						rs.getInt("inventoryQuantity"),
						rs.getDouble("cost"),
						rs.getDouble("price"));
				Purchase purchase = new Purchase (
						rs.getInt("purchaseid"),
						rs.getInt("productid"),
						rs.getInt("customerid"),
						rs.getInt("purchaseQuantity"),
						rs.getString("date_added"),
						rs.getString("date_purchased"),
						rs.getString("status"));
				
				table += "<tr>";
				table += "<td>"+purchase.getRecnum()+"</td>";
				table += "<td>"+product.getName()+"</td>";
				table += "<td>"+product.getDescription()+"</td>";
				table += "<td>"+formatter.format(product.getPrice())+"</td>";
				table += "<td>"+purchase.getQuantity()+"</td>";
				
				table +="\n\t<td>";
				table += "<form action=\"BuyProduct\" method=\"post\">";
				table += "<input type=\"hidden\" name=\"recnum\" value=\"" + purchase.getRecnum() + "\">";
				table += "<input type=\"submit\" value=\"Delete\"></form>";
				table +="</td>\n";
				
				table +="</tr>\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}
}
