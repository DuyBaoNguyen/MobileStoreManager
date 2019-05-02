package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManufacturerDAO;
import model.Manufacturer;

@WebServlet("/HangSanPham/SuaHangSanPham")
public class SuaHangSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public SuaHangSanPham() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manufacturer manuf = new Manufacturer();
		manuf.setId(Integer.parseInt(request.getParameter("manufId")));
		ManufacturerDAO.getManuf(manuf, false, false);
		request.setAttribute("manuf", manuf);
		
		Boolean updateManufError = (Boolean)getServletContext().getAttribute("updateManufError");
		if (updateManufError != null) {
			getServletContext().removeAttribute("updateManufError");
		}
		else {
			updateManufError = false;
		}
		request.setAttribute("updateManufError", updateManufError);
		
		request.getRequestDispatcher("/WEB-INF/SuaHangSanPham.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Manufacturer manuf = new Manufacturer();
		manuf.setId(Integer.parseInt(request.getParameter("manufId")));
		manuf.setName(request.getParameter("manufName"));
		
		boolean updateManufError = ManufacturerDAO.updateManuf(manuf);
		if (updateManufError == true) {
			getServletContext().setAttribute("updateManufError", updateManufError);
			response.sendRedirect(request.getContextPath() + "/HangSanPham/SuaHangSanPham");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/HangSanPham");
		}
	}

}
