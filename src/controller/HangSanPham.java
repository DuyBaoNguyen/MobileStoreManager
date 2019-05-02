package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManufacturerDAO;
import model.Manufacturer;

@WebServlet("/HangSanPham")
public class HangSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public HangSanPham() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Manufacturer> manufs = ManufacturerDAO.getManufs();
		String searchStr = request.getParameter("search");
		manufs = ManufacturerDAO.searchManufs(manufs, searchStr);
		request.setAttribute("manufs", manufs);
		
		Boolean deleteManufError = (Boolean)getServletContext().getAttribute("deleteManufError");
		if (deleteManufError != null) {
			getServletContext().removeAttribute("deleteManufError");
		}
		else {
			deleteManufError = false;
		}
		request.setAttribute("deleteManufError", deleteManufError);
		
		request.getRequestDispatcher("/WEB-INF/HangSanPham.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
