package shoesStore.controller.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoesStore.DAO.CartDao;
import shoesStore.model.Product;
import shoesStore.model.user.User;

@WebServlet("/DeleteFromCartServlet")
public class DeleteFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 int productId = Integer.parseInt(request.getParameter("productId"));
    	 HttpSession session = request.getSession();
 		User user = (User) session.getAttribute("user");
         CartDao cartdao = new CartDao();
         boolean deleted = cartdao.deleteByProductIdAndUserId(productId,user.getId());

         if (deleted) {
             response.setContentType("text/plain");
             response.getWriter().write("Product deleted from cart successfully");
         } else {
             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product from cart");
         }
    }
}
