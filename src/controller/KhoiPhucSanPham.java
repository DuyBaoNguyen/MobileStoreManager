package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/SanPhamNgungKinhDoanh/KhoiPhucSanPham")
public class KhoiPhucSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public KhoiPhucSanPham() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manufId = request.getParameter("manufId");
		Product product = null;
		try {
			product = new Product(Integer.parseInt(request.getParameter("productId")));
		} catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamNgungKinhDoanh/HangSanPham?manufId=" + manufId);
			return;
		}
		
		boolean restoreProductError = ProductDAO.restoreProduct(product);
		getServletContext().setAttribute("restoreProductError", restoreProductError);
		response.sendRedirect(request.getContextPath() + "/SanPhamNgungKinhDoanh/HangSanPham?manufId=" + manufId);
	}
}
