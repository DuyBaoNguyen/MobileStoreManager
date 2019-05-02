package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamHot/ThemSanPhamHot")
public class ThemSanPhamHot extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ThemSanPhamHot() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product(Integer.parseInt(request.getParameter("productId")));
		
		boolean insertHotProductError = ProductDAO.insertHotProduct(product);
		getServletContext().setAttribute("insertHotProductError", insertHotProductError);
		
		response.sendRedirect(request.getContextPath() + "/SanPhamHot");
	}
}
