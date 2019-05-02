package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamMoi")
public class SanPhamMoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public SanPhamMoi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> newProducts = ProductDAO.getNewProducts();
		request.setAttribute("newProducts", newProducts);
		
		List<Product> products = ProductDAO.getNotNewProducts();
		String searchStr = request.getParameter("search");
		products = ProductDAO.searchProducts(products, searchStr);
		request.setAttribute("products", products);
		
		Boolean deleteNewProductError = (Boolean)getServletContext().getAttribute("deleteNewProductError");
		if (deleteNewProductError != null) {
			getServletContext().removeAttribute("deleteNewProductError");
		}
		else {
			deleteNewProductError = false;
		}
		request.setAttribute("deleteNewProductError", deleteNewProductError);
		
		Boolean insertNewProductError = (Boolean)getServletContext().getAttribute("insertNewProductError");
		if (insertNewProductError != null) {
			getServletContext().removeAttribute("insertNewProductError");
		}
		else {
			insertNewProductError = false;
		}
		request.setAttribute("insertNewProductError", insertNewProductError);
		
		request.getRequestDispatcher("/WEB-INF/SanPhamMoi.jsp").forward(request, response);
	}
}
