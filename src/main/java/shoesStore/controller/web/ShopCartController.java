package shoesStore.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.BuyDao;
import shoesStore.DAO.CartDao;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.Buy;
import shoesStore.model.Cart;
import shoesStore.model.Product;
import shoesStore.model.user.User;

@WebServlet(urlPatterns = {"/shop-cart"})
public class ShopCartController extends HttpServlet {
	
	private static final long serialVersionUID = -6751058475526499039L;

	protected  void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException {
		RequestDispatcher request = rq.getRequestDispatcher("/view/web/ShopCart.jsp");
		
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
		BuyDao bd = new BuyDao();
		HttpSession session = rq.getSession();
		User user = (User) session.getAttribute("user");
		CartDao cartdto = new CartDao();
		List<Cart> carts =cartdto.getAll();
		for(Cart cart: carts) {
			Buy b = new Buy(0,cart.getName(),cart.getColor(),cart.getSize(),cart.getQuantity(),"Preparing",user.getId());
			bd.insert(b);
		}
		
	}
}
