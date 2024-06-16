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

@MultipartConfig
@WebServlet(
        name = "FileUploadServlet",
        urlPatterns = { "/admin-AddProduct" },
        loadOnStartup = 1
)
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();

        // Retrieve form data
        String name = request.getParameter("name");
        String cost = request.getParameter("cost");
        String date = request.getParameter("date");
        String sold = request.getParameter("sold");
        String rate = request.getParameter("rate");

        // Handle file upload
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        byte[] imageBytes = new byte[(int) filePart.getSize()];
        fileContent.read(imageBytes);

        // Create Product object
        Product product = new Product(0, name, Double.parseDouble(cost), imageBytes, Date.valueOf(date), Long.parseLong(sold), Double.parseDouble(rate));

        // Insert product into database using DAO
        boolean success = productDAO.insert(product);

        if (success) {
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/Product.jsp");
            rd.forward(request, response);
        } else {
            // Handle failure
            response.getWriter().println("Failed to add product.");
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/adminAddProduct.jsp");
        rd.forward(request, response);
    }
}
