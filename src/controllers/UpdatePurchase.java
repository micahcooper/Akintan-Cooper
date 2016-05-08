package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.PersistenceModule;
import persist.PersistenceModuleFactory;

/**
 * Servlet implementation class UpdatePurchase
 */
@WebServlet(description = "Servlet to update purchase quantity", urlPatterns = { "/UpdatePurchase" })
public class UpdatePurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePurchase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newQuantityAmount = Integer.parseInt(request.getParameter("newQuantityAmount"));
		int purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
		int currentCartAmount = Integer.parseInt(request.getParameter("currentCartAmount"));
		int productid = Integer.parseInt(request.getParameter("productid"));
		String url = "/shoppingCart.jsp";
		
		// create a updateQuery object
		PersistenceModule updateModule;
		try {
			updateModule = PersistenceModuleFactory.createPersistenceModule();
			int inventoryAmount = updateModule.doGetProduct(productid).getQuantity()+currentCartAmount;
			
			System.out.println("UpdatePurchase: new="+newQuantityAmount+" inventory("+productid+")="+inventoryAmount);
			//make sure the new requested amount can be fulfilled
			if( newQuantityAmount <  inventoryAmount ){
				System.out.println("here");
				updateModule.doUpdateProductInventory(productid, inventoryAmount, newQuantityAmount);
				updateModule.doUpdatePurchaseQuantity(newQuantityAmount, purchaseid);
			}
			else{
				// pass rejection message with the execution on to the shopping cart jsp
				url = "/shoppingCart.jsp?message='requested quantity is too large'";
			}
			
			// Get the html table from the REadQuery object
			String table = updateModule.getHTMLCartTable( updateModule.doReadCustomerPurchases( request.getSession().getId() ) );
			
			// pass execution control to read.jsp along with the table
			request.setAttribute("table", table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
