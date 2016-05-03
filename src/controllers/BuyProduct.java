package controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
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
		Purchase purchase = new Purchase();
		Customer customer = new Customer();
		String url = "ShoppingCart";
		
		int product = Integer.parseInt( request.getParameter("recnum") );
		//int customer = Integer.parseInt( request.getParameter("customer") );
		String date_added = LocalDateTime.now().toString();
		String status = "draft";
		
		PersistenceModule customerModule;
		try {
			customerModule = PersistenceModuleFactory.createPersistenceModule();
			customer = customerModule.doGetCustomer( request.getSession().getId() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// set up a purchase object
		purchase.setProduct(product);
		purchase.setCustomer(customer.getIdnumber());
		purchase.setDate_added(date_added);
		purchase.setStatus(status);
		
		//create a purchase module to make database calls
		PersistenceModule purchaseModule;
		try {
			purchaseModule = PersistenceModuleFactory.createPersistenceModule();
			purchaseModule.addPurchase(purchase);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
