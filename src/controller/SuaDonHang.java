package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;
import model.OrderDetail;
import model.Product;

@WebServlet("/SuaDonHang")
public class SuaDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuaDonHang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Order order = new Order(Integer.parseInt(request.getParameter("orderId")));
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		order.setOrderDetails(orderDetails);

		int i = 0;
		while (true) {
			String productId = request.getParameter("product" + i + "Id");
			if (productId == null) {
				break;
			}
			Product product = new Product(Integer.parseInt(productId));
			String productPrice = request.getParameter("product" + i + "UnitPrice");
			String productColor = request.getParameter("product" + i + "Color");
			String productAmount = request.getParameter("product" + i + "Amount");

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(product);
			orderDetail.setColor(productColor);
			orderDetail.setUnitPrice(Integer.parseInt(productPrice));
			orderDetail.setAmountProduct(Integer.parseInt(productAmount));

			orderDetails.add(orderDetail);
			i++;
		}

		String personName = StringEscapeUtils.escapeHtml(request.getParameter("personName"));
		String personPhone = StringEscapeUtils.escapeHtml(request.getParameter("personPhone"));
		String personAddress = StringEscapeUtils.escapeHtml(request.getParameter("personAddress"));

		order.setPersonName(personName);
		order.setPersonPhone(personPhone);
		order.setPersonAddress(personAddress);
		if (request.getParameter("personSex").equals("male")) {
			order.setPersonSex(true);
		} else {
			order.setPersonSex(false);
		}

		int value = 0;
		for (OrderDetail item : order.getOrderDetails()) {
			value += item.getUnitPrice() * item.getAmountProduct();
		}
		order.setValue(value);
		
		boolean updateOrderError = OrderDAO.updateOrder(order);
		if (updateOrderError == true) {
			getServletContext().setAttribute("updateOrderError", updateOrderError);
			response.sendRedirect(request.getContextPath() + "/ChiTietDonHangChuaThanhToan?orderId=" + order.getId());
		}
		else {
			request.getSession().removeAttribute("order");
			response.sendRedirect(request.getContextPath() + "/DonHangChuaThanhToan");
		}
	}
}
