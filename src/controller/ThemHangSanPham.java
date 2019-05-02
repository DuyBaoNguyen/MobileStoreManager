package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManufacturerDAO;
import model.Manufacturer;

@WebServlet("/HangSanPham/ThemHangSanPham")
public class ThemHangSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ThemHangSanPham() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean insertManufError = (Boolean)getServletContext().getAttribute("insertManufError");
		if (insertManufError != null) {
			getServletContext().removeAttribute("insertManufError");
		}
		else {
			insertManufError = false;
		}
		request.setAttribute("insertManufError", insertManufError);
		
		request.getRequestDispatcher("/WEB-INF/ThemHangSanPham.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Manufacturer manuf = new Manufacturer();
		manuf.setName(request.getParameter("manufName"));
		
		boolean insertManufError = ManufacturerDAO.insertManuf(manuf);
		if (insertManufError == true) {
			getServletContext().setAttribute("insertManufError", insertManufError);
			response.sendRedirect(request.getContextPath() + "/HangSanPham/ThemHangSanPham");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/HangSanPham");
		}
	}
}
