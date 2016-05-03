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
import util.PasswordService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		Customer customer = new Customer();
		String url = "index.jsp";
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");

		//encrypt the password to check against what's stored in DB
		PasswordService pws = new PasswordService();
		password = pws.encrypt(password);
		
		// set up a customer object
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setDob(dob);
		customer.setSessionid( request.getSession().getId() );
		
		//create a user helper class to make database calls, and call authenticate user method
		PersistenceModule customerModule;
		try {
			customerModule = PersistenceModuleFactory.createPersistenceModule();
			customerModule.addUser(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//forward our request along
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
	}

}
