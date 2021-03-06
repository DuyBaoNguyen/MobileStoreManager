package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamMoi/XoaSanPhamMoi")
public class XoaSanPhamMoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XoaSanPhamMoi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = null;
		try {
			product = new Product(Integer.parseInt(request.getParameter("productId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamMoi");
			return;
		}

		boolean deleteNewProductError = ProductDAO.deleteNewProduct(product);
		getServletContext().setAttribute("deleteNewProductError", deleteNewProductError);

		response.sendRedirect(request.getContextPath() + "/SanPhamMoi");
	}
}
