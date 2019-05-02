package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;

@WebServlet("/DonHangChuaThanhToan")
public class DonHangChuaThanhToan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonHangChuaThanhToan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> orders = OrderDAO.getNotPaidOrder();
		request.setAttribute("orders", orders);
		
		Boolean deleteOrderError = (Boolean)getServletContext().getAttribute("deleteOrderError");
		if (deleteOrderError != null) {
			getServletContext().removeAttribute("deleteOrderError");
		}
		else {
			deleteOrderError = false;
		}
		request.setAttribute("deleteOrderError", deleteOrderError);
		
		request.getRequestDispatcher("/WEB-INF/DonHangChuaThanhToan.jsp").forward(request, response);
	}
}
