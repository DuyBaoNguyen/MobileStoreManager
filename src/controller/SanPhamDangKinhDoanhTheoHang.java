package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManufacturerDAO;
import dao.ProductDAO;
import model.Manufacturer;

@WebServlet("/SanPhamDangKinhDoanh/HangSanPham")
public class SanPhamDangKinhDoanhTheoHang extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SanPhamDangKinhDoanhTheoHang() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchStr = request.getParameter("search");

		Manufacturer manuf = null;
		try {
			manuf = new Manufacturer(Integer.parseInt(request.getParameter("manufId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamDangKinhDoanh");
			return;
		}

		ManufacturerDAO.getManuf(manuf, true, true);
		manuf.setProducts(ProductDAO.searchProducts(manuf.getProducts(), searchStr));

		request.setAttribute("manuf", manuf);

		Boolean deleteProductError = (Boolean) getServletContext().getAttribute("deleteProductError");
		if (deleteProductError != null) {
			getServletContext().removeAttribute("deleteProductError");
		} else {
			deleteProductError = false;
		}
		request.setAttribute("deleteProductError", deleteProductError);

		request.getRequestDispatcher("/WEB-INF/SanPhamDangKinhDoanhTheoHang.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
