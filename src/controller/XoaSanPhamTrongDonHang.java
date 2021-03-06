package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;
import model.Product;

@WebServlet("/XoaSanPhamTrongDonHang")
public class XoaSanPhamTrongDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public XoaSanPhamTrongDonHang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = null;
		Order order = null;
		try {
			order = new Order(Integer.parseInt(request.getParameter("orderId")));
			product = new Product(Integer.parseInt(request.getParameter("productId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/DonHangChuaThanhToan");
			return;
		}
		
		boolean deleteProductError = OrderDAO.deleteProductInOrder(order, product);
		getServletContext().setAttribute("deleteProductError", deleteProductError);
		
		response.sendRedirect(request.getContextPath() + "/ChiTietDonHangChuaThanhToan?orderId=" + order.getId()); 
	}
}
