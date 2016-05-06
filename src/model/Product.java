/**
 * 
 */
package model;

import java.text.NumberFormat;

/**
 * @author micah cooper
 *
 */
public class Product {
	private int recnum;
	private String name;
	private String description;
	private String category;
	private String imageURL;
	private double cost;
	private double price;
	private int quantity;
	
	public Product(){
		super();
	}
	
	/**
	 * 
	 */
	public Product(int recnum, String name, String description, String category, String imageURL, int quantity, double cost, double price) {
		this.recnum = recnum;
		this.name = name;
		this.description = description;
		this.category=category;
		this.imageURL=imageURL;
		this.quantity =  quantity;
		this.cost = cost;
		this.price = price;
	}
	
	/**
	 * 
	 * @param results
	 * @return String
	 */
	public String getHTMLProductRow(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String row ="";

		row +="<tr>";
		row +="\t<td>"+this.getRecnum()+"</td>";
		row +="<td>"+this.getName()+"</td>";
		row +="<td>"+this.getDescription()+"</td>";
		row +="<td>"+this.getCategory()+"</td>";
		row +="<td>"+formatter.format(this.getPrice())+"</td>";
		row +="<td align=center>"+this.getQuantity()+"</td>";
		
		row +="\n\t<td>";
		row += "<a class=button href=viewProduct?product=" + this.getRecnum() + " >VIEW</a>";
		row +="</td>\n";
		
		row +="\n\t<td>";
		row += "<form action=\"BuyProduct\" method=\"post\">";
		row += "<input type=\"text\" name=\"quantity\" value=\"1\">";
		row += "<input type=\"hidden\" name=\"recnum\" value=\"" + this.getRecnum() + "\">";
		row += "<input type=\"submit\" value=\"Buy\"></form>";
		row +="</td>\n";
		
		row +="</tr>\n";

		return row;
	}
	
	public String getBrowseList(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String divContainter ="";

		divContainter +="<div class=product>";
		divContainter +="<p><img class=browseProduct src=\"products/"+this.getRecnum()+".jpg\" /></p>";
		divContainter +="<p><a href=viewProduct?product=" + this.getRecnum() + " >"+this.getName()+"</a></p>";
		divContainter +="<p>"+this.getDescription()+"</p>";
		divContainter +="<p>Category: "+this.getCategory()+"</p>";
		divContainter +="<p>"+formatter.format(this.getPrice())+" ("+this.getQuantity()+" left)</p>";
		
		divContainter += "<form action=\"BuyProduct\" method=\"post\">";
		divContainter += "<input type=\"text\" name=\"quantity\" value=\"1\" size=\"5\">";
		divContainter += "<input type=\"hidden\" name=\"recnum\" value=\"" + this.getRecnum() + "\">";
		divContainter += "<input type=\"submit\" value=\"Buy\"></form>";
		
		divContainter +="</div>\n";

		return divContainter;
	}
	
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


}
