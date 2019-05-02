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

@WebServlet("/SanPhamNgungKinhDoanh")
public class SanPhamNgungKinhDoanh extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SanPhamNgungKinhDoanh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Manufacturer> manufs = ManufacturerDAO.getManufs();
		String searchStr = request.getParameter("search");
		manufs = ManufacturerDAO.searchManufs(manufs, searchStr);
		request.setAttribute("manufs", manufs);
		
		request.getRequestDispatcher("/WEB-INF/HangSanPhamSanPhamNgungKinhDoanh.jsp").forward(request, response);
	}
}
