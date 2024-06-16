package shoesStore.controller.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.CartDao;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.Cart;
import shoesStore.model.user.User;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String productName = request.getParameter("name");
        String color = request.getParameter("color");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String size = request.getParameter("size");
        ProductDAO dto = new ProductDAO(); 
        int productId =dto.getIdByName(productName);
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
        Cart cart = new Cart(0,  user.getId(),  productId,  productName,  color,  size,  quantity);

        CartDao cartDao = new CartDao();
        boolean success = cartDao.insert(cart);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (success) {
            out.print("Product added to cart successfully");
        } else {
            out.print("Failed to add product to cart");
        }
        out.flush();
    }
}
