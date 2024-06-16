package shoesStore.controller.admin;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Date;
import javax.servlet.http.Part;
import shoesStore.DAO.ProductDAO;
import shoesStore.model.Product;
import javax.servlet.annotation.*;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();

        // Retrieve form data
        String name = request.getParameter("name");
        boolean success = productDAO.delete(productDAO.getIdByName(name));
        if (success) {
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/Product.jsp");
            rd.forward(request, response);
        } else {
            // Handle failure
            response.getWriter().println("Failed to delete product.");
        }
        
    }
}
