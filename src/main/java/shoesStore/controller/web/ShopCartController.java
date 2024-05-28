package shoesStore.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/shop-cart"})
public class ShopCartController extends HttpServlet {
	
	private static final long serialVersionUID = -6751058475526499039L;

	protected  void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException {
		RequestDispatcher request = rq.getRequestDispatcher("/view/web/ShopCart.jsp");
		request.forward(rq, rs);
	}
	
	protected  void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException {
		
	}
}
