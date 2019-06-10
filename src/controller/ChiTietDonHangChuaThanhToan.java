package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;

@WebServlet("/ChiTietDonHangChuaThanhToan")
public class ChiTietDonHangChuaThanhToan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChiTietDonHangChuaThanhToan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = null;
		try {
			order = new Order(Integer.parseInt(request.getParameter("orderId")));
		} catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/DonHangChuaThanhToan");
			return;
		}
		
		OrderDAO.getOrder(order);
		
		if (order.getOrderDetails().size() == 0) {
			response.sendRedirect(request.getContextPath() + "/XoaDonHang?orderId=" + order.getId());
			return;
		}
		request.setAttribute("order", order);
		
		Boolean acceptOrderError = (Boolean)getServletContext().getAttribute("acceptOrderError");
		if (acceptOrderError != null) {
			getServletContext().removeAttribute("acceptOrderError");
		}
		else {
			acceptOrderError = false;
		}
		request.setAttribute("acceptOrderError", acceptOrderError);
		
		Boolean saveOrderError = (Boolean)getServletContext().getAttribute("saveOrderError");
		if (saveOrderError != null) {
			getServletContext().removeAttribute("saveOrderError");
		}
		else {
			saveOrderError = false;
		}
		request.setAttribute("saveOrderError", saveOrderError);
		
		Boolean deleteOrderError = (Boolean)getServletContext().getAttribute("deleteOrderError");
		if (deleteOrderError != null) {
			getServletContext().removeAttribute("deleteOrderError");
		}
		else {
			deleteOrderError = false;
		}
		request.setAttribute("deleteOrderError", deleteOrderError);
		
		request.getRequestDispatcher("/WEB-INF/ChiTietDonHangChuaThanhToan.jsp").forward(request, response);
	}
}
