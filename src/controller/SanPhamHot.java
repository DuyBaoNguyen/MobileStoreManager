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

@WebServlet("/SanPhamHot")
public class SanPhamHot extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public SanPhamHot() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> hotProducts = ProductDAO.getHotProducts();
		request.setAttribute("hotProducts", hotProducts);
		
		List<Product> products = ProductDAO.getNotHotProducts();
		String searchStr = request.getParameter("search");
		products = ProductDAO.searchProducts(products, searchStr);
		request.setAttribute("products", products);
		
		Boolean deleteHotProductError = (Boolean)getServletContext().getAttribute("deleteHotProductError");
		if (deleteHotProductError != null) {
			getServletContext().removeAttribute("deleteHotProductError");
		}
		else {
			deleteHotProductError = false;
		}
		request.setAttribute("deleteHotProductError", deleteHotProductError);
		
		Boolean insertHotProductError = (Boolean)getServletContext().getAttribute("insertHotProductError");
		if (insertHotProductError != null) {
			getServletContext().removeAttribute("insertHotProductError");
		}
		else {
			insertHotProductError = false;
		}
		request.setAttribute("insertHotProductError", insertHotProductError);
		
		request.getRequestDispatcher("/WEB-INF/SanPhamHot.jsp").forward(request, response);
	}
}
