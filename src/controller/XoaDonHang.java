package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;

@WebServlet("/XoaDonHang")
public class XoaDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public XoaDonHang() {
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
		
		boolean deleteOrderError = OrderDAO.deleteOrder(order);
		getServletContext().setAttribute("deleteOrderError", deleteOrderError);
		
		response.sendRedirect(request.getContextPath() + "/DonHangChuaThanhToan"); 
	}
}
