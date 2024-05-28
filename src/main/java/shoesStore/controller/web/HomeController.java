package shoesStore.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoesStore.DAO.HeaderDAO;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.HeaderImage;
import shoesStore.model.Product;

@WebServlet(urlPatterns ={ "/home"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1652026503372560852L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/web/Home.jsp");		
		
		HeaderDAO headerDao = new HeaderDAO();
		List<HeaderImage> headerImages = headerDao.getAll();
		request.setAttribute("headerImages", headerImages);
		
		ProductDAO productDao = new ProductDAO();
		List<Product> tenPopularProducts = productDao.getTenPopular();
		request.setAttribute("tenPopularProducts", tenPopularProducts);
		
		List<Product> tenRatingProducts = productDao.getTenRating();
		request.setAttribute("tenRatingProducts", tenRatingProducts);
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
