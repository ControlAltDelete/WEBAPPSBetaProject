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
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = request.getParameter("inputEmail");
		String nickname = request.getParameter("inputNickname");
		String password = request.getParameter("inputPassword");
		String confirm = request.getParameter("cnfrmPassword");
		
		Database store = new Database();
		
		if(password.length() == 0 || confirm.length() == 0 ||
				email.length() == 0 || nickname.length() == 0)
		{
			session.setAttribute("Registration_Status","Null");
			response.sendRedirect("signup.jsp");
		}
		else if(password.length() > 10 || nickname.length() > 10 ||
				email.length() > 30 || confirm.length() > 10)
		{
			session.setAttribute("Registration_Status","Exceed");
			response.sendRedirect("signup.jsp");
		}
		
		else if (nickname.contains("'"))
		{
		  session.setAttribute("Registration_Status","Failed");
          response.sendRedirect("signup.jsp");
		}
		else if(password.equals(confirm))
		{
			try {
				store.initialize();
				ArrayList<User> matchItem = store.getDuplicate(email);
				
				if(matchItem.size() > 0)
				{
					session.setAttribute("Registration_Status","Duplicate");
					response.sendRedirect("signup.jsp");
				}
				else
				{
					int error = store.getRegistration(email, nickname, password);
					if(error == 1)
					{
						session.setAttribute("Registration_Status","Success") ;
						response.sendRedirect("index.jsp");
					}
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			session.setAttribute("Registration_Status","IncorrectPassword");
			response.sendRedirect("signup.jsp");
		}
		
	}

}
