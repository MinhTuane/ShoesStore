package shoesStore.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.HeaderDAO;
import shoesStore.DAO.ProductDAO;
import shoesStore.DAO.UserService;
import shoesStore.model.HeaderImage;
import shoesStore.model.Product;
import shoesStore.model.user.User;

@WebServlet(urlPatterns ={ "/login"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 7444550712210615246L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/web/Login.jsp");		
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    UserService userService = new UserService();
	    
	    User user = userService.isAuthorize(username, password);
	    if (user != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);
	        if(user.getRole().equalsIgnoreCase("user"))
	        	response.sendRedirect("home");
	        else 
	        	response.sendRedirect("admin-home");
	    } else {
	        request.setAttribute("ErrorLogin", "Wrong username or password!");
	        RequestDispatcher rd = request.getRequestDispatcher("/view/web/Login.jsp");
	        rd.forward(request, response);
	    }
	}
}
