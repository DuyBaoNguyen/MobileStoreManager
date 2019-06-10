package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamMoi/ThemSanPhamMoi")
public class ThemSanPhamMoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemSanPhamMoi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Product product = new Product(Integer.parseInt(request.getParameter("productId")));

			boolean insertNewProductError = ProductDAO.insertNewProduct(product);
			getServletContext().setAttribute("insertNewProductError", insertNewProductError);
		} catch (Exception e) {

		} finally {
			response.sendRedirect(request.getContextPath() + "/SanPhamMoi");
		}
	}
}
