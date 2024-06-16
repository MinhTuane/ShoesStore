package shoesStore.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoesStore.DAO.UserService;
import shoesStore.model.user.User;

@WebServlet(urlPatterns ={ "/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/web/Register.jsp");		
		
		rd.forward(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the registration form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate the input (you can add more robust validation as per your requirements)

        // Basic validation for demonstration (should be enhanced)
        if (username == null || username.isEmpty() || email == null || email.isEmpty() ||
                password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            response.sendRedirect("register"); // Redirect back to registration page with error message
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register"); // Redirect back to registration page with error message
            return;
        }

        // Create a new user object

        // Save the user to the database using the UserService
        UserService userService = new UserService();
        userService.createUser(username, confirmPassword);

        // Optionally, you can redirect to a login page or any other page after successful registration
        response.sendRedirect("login"); // Redirect to login page after successful registration
    }
}
