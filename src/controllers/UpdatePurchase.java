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
		int update = Integer.parseInt(request.getParameter("update"));
		int recnum = Integer.parseInt(request.getParameter("recnum"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		// create a deleteQuery object
		PersistenceModule updateModule;
		try {
			updateModule = PersistenceModuleFactory.createPersistenceModule();
			updateModule.doUpdateProduct(quantity, update);
			updateModule.doUpdatePurchase(update, recnum);
			
			
			// Get the html table from the REadQuery object
			String table = updateModule.getHTMLCartTable( updateModule.doReadCustomerProducts( request.getSession().getId() ) );
			
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

