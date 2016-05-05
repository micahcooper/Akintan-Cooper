package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import persist.PersistenceModule;
import persist.PersistenceModuleFactory;

/**
 * Servlet implementation class ShopProducts
 */
@WebServlet("/ShopProducts")
public class ShopProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopProducts() {
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
		// Create a persistence object
		PersistenceModule persist;
		String url;
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		//TODO removing customer login check for now
		//if( customer != null){
		if( true){
			try {
				persist = PersistenceModuleFactory.createPersistenceModule();
	
				// Get the html table from the REadQuery object
				String table = persist.getProductDivs( persist.doReadAllProducts() );
				
				// pass execution control to read.jsp along with the table
				request.setAttribute("table", table);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			url = "/shopProducts.jsp";
		}
		else{
			//TODO add friendly message about logging in first
			url = "/login.jsp?message=loginFirst";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
