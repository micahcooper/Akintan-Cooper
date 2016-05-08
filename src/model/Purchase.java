/**
 * 
 */
package model;

/**
 * @author mrcooper
 *
 */
public class Purchase {
	private int recnum;
	private int product;
	private int customer;
	private int quantity;
	private double cartTotal;
	private String date_added;
	private String date_purchased;
	private String status;
	
	/**
	 * 
	 */
	public Purchase() {
		// TODO Auto-generated constructor stub
	}
	
	public Purchase(int recnum, int product, int customer, int quantity, String date_added, String date_purchased, String status, double cartTotal){
		this.recnum=recnum;
		this.product=product;
		this.customer=customer;
		this.quantity=quantity;
		this.date_added=date_added;
		this.date_purchased=date_purchased;
		this.status=status;
		this.cartTotal=cartTotal;
	}
	
	
	/**
	 * 
	 * @param results
	 * @return String
	 */
	public String getHTMLPurchaseRow(){
		//NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String row ="";

		row +="<tr>";
		row +="\t<td>"+this.getRecnum()+"</td>";
		row +="<td>"+this.getProduct()+"</td>";
		row +="<td>"+this.getCustomer()+"</td>";
		row +="<td>"+this.getDate_added()+"</td>";
		row +="<td>"+this.getDate_purchased()+"</td>";
		row +="<td>"+this.getStatus()+"</td>";
		row +="<td>"+this.getCartTotal()+"</td>";
		
		row +="\n\t<td>";
		row += "<a class=button href=viewProduct?sku=" + this.getRecnum() + " >UPDATE</a>";
		row +="</td>\n";
		
		row +="\n\t<td>";
		row += "<a class=button href=buyProduct?sku=" + this.getRecnum() + " >REMOVE</a>";
		row +="</td>\n";
		
		row +="</tr>\n";

		return row;
	}
	/**
	 * @return the cartTotal
	 */
	public double getCartTotal() {
		return cartTotal;
	}

	/**
	 * @param cartTotal the cartTotal to set
	 */
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

	/**
	 * @return the recnum
	 */
	public int getRecnum() {
		return recnum;
	}

	/**
	 * @param recnum the recnum to set
	 */
	public void setRecnum(int recnum) {
		this.recnum = recnum;
	}

	/**
	 * @return the product
	 */
	public int getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(int product) {
		this.product = product;
	}

	/**
	 * @return the customer
	 */
	public int getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(int customer) {
		this.customer = customer;
	}

	/**
	 * @return the date_added
	 */
	public String getDate_added() {
		return date_added;
	}

	/**
	 * @param date_added the date_added to set
	 */
	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	/**
	 * @return the date_purchased
	 */
	public String getDate_purchased() {
		return date_purchased;
	}

	/**
	 * @param date_purchased the date_purchased to set
	 */
	public void setDate_purchased(String date_purchased) {
		this.date_purchased = date_purchased;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
