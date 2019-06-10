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

@WebServlet("/SanPhamNgungKinhDoanh/HangSanPham")
public class SanPhamNgungKinhDoanhTheoHang extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SanPhamNgungKinhDoanhTheoHang() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchStr = request.getParameter("search");

		Manufacturer manuf = null;
		try {
			manuf = new Manufacturer(Integer.parseInt(request.getParameter("manufId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamNgungKinhDoanh");
			return;
		}
		
		ManufacturerDAO.getManuf(manuf, true, false);
		manuf.setProducts(ProductDAO.searchProducts(manuf.getProducts(), searchStr));

		request.setAttribute("manuf", manuf);

		Boolean restoreProductError = (Boolean) getServletContext().getAttribute("restoreProductError");
		if (restoreProductError != null) {
			getServletContext().removeAttribute("restoreProductError");
		} else {
			restoreProductError = false;
		}
		request.setAttribute("restoreProductError", restoreProductError);

		request.getRequestDispatcher("/WEB-INF/SanPhamNgungKinhDoanhTheoHang.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
