package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;

@WebServlet("/ChiTietDonHangDaThanhToan")
public class ChiTietDonHangDaThanhToan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChiTietDonHangDaThanhToan() {
        super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = null;
		try {
			order = new Order(Integer.parseInt(request.getParameter("orderId")));
		} catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/DonHangDaThanhToan");
			return;
		}
		
		OrderDAO.getOrder(order);
		request.setAttribute("order", order);
				
		request.getRequestDispatcher("/WEB-INF/ChiTietDonHangDaThanhToan.jsp").forward(request, response);
	}
}
