package shoesStore.controller.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.BuyDao;
import shoesStore.DAO.CartDao;
import shoesStore.model.Buy;
import shoesStore.model.Cart;
import shoesStore.model.user.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


@WebServlet("/transfer-buy")
public class TransferBuyController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the current session and retrieve the user object
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // If user is not logged in, redirect to login page
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Initialize DAOs
        CartDao cartDao = new CartDao();
        BuyDao buyDao = new BuyDao();
        
        // Retrieve carts for the current user
        List<Cart> carts = cartDao.getAllByUserId(user.getId());
        
        // Transfer each cart item to buy (purchase) and delete from cart
        for (Cart cart : carts) {
            Buy buy = new Buy(0, cart.getName(), cart.getColor(), cart.getSize(), cart.getQuantity(), "Preparing", user.getId());
            boolean success = buyDao.insert(buy);
            
            if (success) {
                // Delete cart item if buy (purchase) was successful
                cartDao.delete(cart.getId());
            } else {
                // Handle insertion failure, e.g., log error or show message
                response.getWriter().println("Failed to transfer item to buy.");
                return;
            }
        }
        
        // Redirect to a success page or back to shopping cart
        
    }
}
