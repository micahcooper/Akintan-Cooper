package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Purchase;
import persist.PersistenceModule;
import persist.PersistenceModuleFactory;

/**
 * Servlet implementation class DeletePurchase
 */
@WebServlet(description = "Deletes a product from shopping cart", urlPatterns = { "/DeletePurchase" })
public class DeletePurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePurchase() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				
				
				int recnum = Integer.parseInt(request.getParameter("recnum"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				Purchase purchase = new Purchase();
				Purchase q = new Purchase();
				purchase.setRecnum(recnum);
				q.setQuantity(quantity);
				
				// create a deleteQuery object
				PersistenceModule deleteRecModule;
				try {
					deleteRecModule = PersistenceModuleFactory.createPersistenceModule();
					deleteRecModule.doUpdatePurchaseQuantity(q);
					deleteRecModule.doDeleteProduct(purchase);
					// Get the html table from the REadQuery object
					String table = deleteRecModule.getHTMLCartTable( deleteRecModule.doReadCustomerPurchases( request.getSession().getId() ) );
					
					// pass execution control to read.jsp along with the table
					request.setAttribute("table", table);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// pass execution on to the shopping cart jsp
				String url = "/shoppingCart.jsp";
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
	}

}
