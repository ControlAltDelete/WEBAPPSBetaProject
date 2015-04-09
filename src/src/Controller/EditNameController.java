package src.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Database;

/**
 * Servlet implementation class EditNameController
 */
@WebServlet("/EditNameController")
public class EditNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNameController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			String name = request.getParameter("inputName");
			String Newname = request.getParameter("inputNewName");
			
			//Store class is SIMULATION OF DATABASE
			Database store = new Database();
			
			try
			{
			  store.initialize();
			  
			  int error= store.EditName(name,Newname);
			  
			  
			  if(error == 1)
			  {
					session.setAttribute("Change_Status","Success") ;
					response.sendRedirect("settings.jsp");
			  }
			  else
			  {

				  System.out.println(error);
				  System.out.println("Change failed");
				  session.setAttribute("Change_Status","Failed") ;
				  response.sendRedirect("settings.jsp");
			  }
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			
		}
	
	}
