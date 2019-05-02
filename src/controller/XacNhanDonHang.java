package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;

@WebServlet("/XacNhanDonHang")
public class XacNhanDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XacNhanDonHang() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order = new Order(Integer.parseInt(request.getParameter("orderId")));

		boolean acceptOrderError = OrderDAO.acceptOrder(order);
		if (acceptOrderError == true) {
			getServletContext().setAttribute("acceptOrderError", acceptOrderError);
			response.sendRedirect(request.getContextPath() + "/ChiTietDonHangChuaThanhToan?orderId=" + order.getId());
		} else {
			response.sendRedirect(request.getContextPath() + "/DonHangChuaThanhToan");
		}
	}
}
