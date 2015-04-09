package src.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Database;
import src.Model.User;

/**
 * Servlet implementation class EditEmailController
 */
@WebServlet("/EditEmailController")
public class EditEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmailController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			String email = request.getParameter("inputEmail");
			String password = request.getParameter("inputPassword");
			
			//Store class is SIMULATION OF DATABASE
			Database store = new Database();
			
			try
			{
			  store.initialize();
			  ArrayList<User> matchItem = store.getLogin(email,password);
			  
			  if(matchItem.size() == 1)
			  {
				  System.out.println("Login successful!");
				  session.setAttribute("Login_Status","Success") ;
				  session.setAttribute("email",email);
				  response.sendRedirect("MainPage.jsp");
			  }
			  else
			  {
				  System.out.println("Login failed");
				  session.setAttribute("Login_Status","Failed") ;
				  response.sendRedirect("index.jsp");
			  }
			}
			
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			
		}
	
	}
