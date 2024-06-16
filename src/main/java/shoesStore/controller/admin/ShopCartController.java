package shoesStore.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoesStore.DAO.CartDao;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.Cart;
import shoesStore.model.Product;

@WebServlet(urlPatterns = {"/admin-shop-cart"})
public class ShopCartController extends HttpServlet {
	
	private static final long serialVersionUID = -6751058475526499039L;

	protected  void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException {
		RequestDispatcher request = rq.getRequestDispatcher("/view/admin/ShopCart.jsp");
		
		ProductDAO productDAO = new ProductDAO();
		CartDao cartdto = new CartDao();
		List<Cart> carts = cartdto.getAll();
		List<Product> productCart = new ArrayList<>();
		for(Cart cart : carts) {
			productCart.add(productDAO.detail(cart.getProductId()));
		}
		
		rq.setAttribute("productCart", productCart);
		
		request.forward(rq, rs);
	}
	
	protected  void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException {
		
	}
}
