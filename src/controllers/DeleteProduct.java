package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.Purchase;
import persist.PersistenceModule;
import persist.PersistenceModuleFactory;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet(description = "Deletes a product from shopping cart", urlPatterns = { "/DeleteProduct" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
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
					deleteRecModule.doUpdateQuantity(q);
					deleteRecModule.doDeleteProduct(purchase);
					// Get the html table from the REadQuery object
					String table = deleteRecModule.getHTMLCartTable( deleteRecModule.doReadCustomerProducts( request.getSession().getId() ) );
					
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
		doGet(request, response);
	}

}
