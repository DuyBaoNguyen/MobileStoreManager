package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManufacturerDAO;
import model.Manufacturer;

@WebServlet("/HangSanPham/XoaHangSanPham")
public class XoaHangSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XoaHangSanPham() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Manufacturer manuf = null;
		try {
			manuf = new Manufacturer(Integer.parseInt(request.getParameter("manufId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/HangSanPham");
			return;
		}

		boolean deleteManufError = ManufacturerDAO.deleteManuf(manuf);
		getServletContext().setAttribute("deleteManufError", deleteManufError);
		response.sendRedirect(request.getContextPath() + "/HangSanPham");
	}
}
