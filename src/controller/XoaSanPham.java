package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamDangKinhDoanh/XoaSanPham")
public class XoaSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XoaSanPham() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String manufId = request.getParameter("manufId");
		Product product = null;
		try {
			product = new Product(Integer.parseInt(request.getParameter("productId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamDangKinhDoanh");
			return;
		}

		boolean deleteProductError = ProductDAO.deleteProduct(product);
		getServletContext().setAttribute("deleteProductError", deleteProductError);
		response.sendRedirect(request.getContextPath() + "/SanPhamDangKinhDoanh/HangSanPham?manufId=" + manufId);
	}
}
