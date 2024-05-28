package shoesStore.controller.web;

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

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7939956215984174474L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/web/Product.jsp");
		
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getAll();
		String[] params = request.getParameterValues("productID");
		if(params!=null) {
			String productID = params[0];
		    request.setAttribute("id", productID);
		}    
		request.setAttribute("products", products);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
	
	}
}
