package shoesStore.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.CartDao;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.Cart;
import shoesStore.model.Product;
import shoesStore.model.user.User;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 7939956215984174474L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/web/Product.jsp");

		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getAll();
		CartDao cartDao = new CartDao();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Cart> carts = new ArrayList<>();
		List<Product> productCart = new ArrayList<>();

		if (user != null) {
			int userId = user.getId();
			carts = cartDao.getAllByUserId(userId);
			
			for (Cart cart : carts) {
				productCart.add(productDAO.detail(cart.getProductId()));
			}
		}

		request.setAttribute("productCart", productCart);
		request.setAttribute("products", products);

		String[] params = request.getParameterValues("productID");
		if (params != null) {
			String productID = params[0];
			request.setAttribute("id", productID);
		}
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle POST requests if needed
	}
}
