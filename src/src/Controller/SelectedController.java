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
import src.Model.Request;

/**
 * Servlet implementation class SelectedController
 */
@WebServlet("/SelectedController")
public class SelectedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectedController() {
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
		int id = Integer.parseInt(request.getParameter("requestID"));
		String selected = request.getParameter("selected");
		HttpSession session = request.getSession();
		ArrayList<Request> requestList = new ArrayList<Request>();
		
		Database store = new Database();
		try
		{
			store.initialize();
			System.out.println(selected);
			if(selected.equals("yes"))
			{
				selected = "no";
			}
			else
			{
				selected = "yes";
			}
			store.changeStatus(id, selected);
			requestList = store.getAdminUpdate();
			session.setAttribute("Selected_status", "Success");
			session.setAttribute("adminPhoto",requestList);
	        response.sendRedirect("MainPage.jsp");
		}catch(Exception e)
		{
			
		}
	}

}
