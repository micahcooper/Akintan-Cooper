package controllers;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import persist.PersistenceModule;
import persist.PersistenceModuleFactory;
import util.PasswordService;

/**
 * Servlet implementation class LoginController
 * A controller for handling user authentication and login
 */
@WebServlet(description = "A controller for handling user logins", urlPatterns = { "/Login" })
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	
	private int loginAttempts;

	/**
	 * Servlet constructor
	 */
	public Login() {
		super();
	}

	/**
	 * Process GET requests/responses (logout)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		//User has clicked the logout link
		session = request.getSession();

		//check to make sure we've clicked link
		if(request.getParameter("logout") !=null){

			//logout and redirect to frontpage
			logout();
			url="index.jsp";
		}

		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * Process POST requests/responses (login)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = null;
		String url = "index.jsp";
		//get our current session
		session = request.getSession();

		//get the number of logins
		if(session.getAttribute("loginAttempts") == null){
			loginAttempts = 0;
		}
		
		//exceeded logins
		if(loginAttempts > 2){
			String errorMessage = "Error: Number of Login Attempts Exceeded";
			request.setAttribute("errorMessage", errorMessage);
			url = "index.jsp";
		}else{	//proceed
			//pull the fields from the form
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//encrypt the password to check against what's stored in DB
			PasswordService pws = new PasswordService();
			String encryptedPass = pws.encrypt(password);
			
			//create a customer module for database connection
			PersistenceModule customerModule;
			try {
				customerModule = PersistenceModuleFactory.createPersistenceModule();
				customer = customerModule.authenticateUser(username, encryptedPass);

				//we've found a user that matches the credentials
				if(customer != null){
					System.out.println("Customer found");
					//invalidate current session, then get new session for our user (combats: session hijacking)
					session.invalidate();
					session=request.getSession(true);
					//update the customer table with the new sessionid
					customerModule.doUpdateCustomerSessionId( customer.getIdnumber(), request.getSession().getId() );
					session.setAttribute("customer", customer);
					url="ShopProducts";
				}
				else{
					System.out.println("Customer not found");
					String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
					request.setAttribute("errorMessage", errorMessage);

					//track login attempts (combats: brute force attacks)
					session.setAttribute("loginAttempts", loginAttempts++);
					url = "login.jsp";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// user doesn't exist, redirect to previous page and show error
			
		}
		//forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * Logs the user out
	 */
	public void logout() {
		session.invalidate();
	}
}
