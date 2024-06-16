package shoesStore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoesStore.DAO.ProductDAO;
import shoesStore.model.Product;

@WebServlet(urlPatterns = {"/admin-product/product-detail"})
public class ProductDetailController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7939956215984174434L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/admin/ProductDetail.jsp");
	
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
	
	}
}
