package controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Product;
import model.Purchase;
import persist.PersistenceModule;
import persist.PersistenceModuleFactory;

/**
 * Servlet implementation class BuyProduct
 */
@WebServlet("/BuyProduct")
public class BuyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProduct() {
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
		Customer customer;
		Product product = null;;
		Purchase purchase = new Purchase();
		String url = "ShoppingCart";
		
		int productRecnum = Integer.parseInt( request.getParameter("recnum") );
		customer = (Customer) request.getSession().getAttribute("customer");
		int quantityRequested = Integer.parseInt( request.getParameter("quantity"));
		//String date_added = LocalDateTime.now().toString();
		String status = "draft";
		
		if( customer != null){
			PersistenceModule productModule;
			try {
				productModule = PersistenceModuleFactory.createPersistenceModule();
				product = productModule.doGetProduct( productRecnum );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			// set up a purchase object
			purchase.setProduct(product.getRecnum());
			purchase.setCustomer(customer.getIdnumber());
			Date date = new Date();
			purchase.setDate_added(date.toString());
			purchase.setStatus(status);
			purchase.setQuantity(quantityRequested);
			
			//TODO create a check to make sure there is enough inventory to cover the purchase
			if( product.getQuantity() > quantityRequested ){
				//create a purchase module to make database calls
				PersistenceModule purchaseModule;
				try {
					purchaseModule = PersistenceModuleFactory.createPersistenceModule();
					purchaseModule.addPurchase(purchase);
					product.setQuantity(product.getQuantity()-quantityRequested);
					purchaseModule.updateProduct( product );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Not enough inventory");
				request.setAttribute("message", "not enough in inventory to fulfill order");
				url = "ShoppingCart?ordered=false";
			}
		}
		else{
			url = "login.jsp?userMessage='Please Login'";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
