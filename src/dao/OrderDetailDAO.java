package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import model.Product;
public class OrderDetailDAO {
	public static List<OrderDetail> getOrder(Connection con, Order order) {
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			statement = con.prepareCall("{call layChiTietDonHang(?)}");
			statement.setInt(1, order.getId());
			
			result = statement.executeQuery();
			
			while (result.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(new Product(result.getInt(1)));
				orderDetail.setAmountProduct(result.getInt(2));
				orderDetail.setUnitPrice(result.getInt(3));
				orderDetail.setColor(result.getString(4));
				
				orderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderDetails;
	}
	
	public static void updateOrderDetail(Connection con, Order order) {
		CallableStatement statement = null;

		try {
			
			for (OrderDetail item : order.getOrderDetails()) {
				statement = con.prepareCall("{call suaChiTietDonHang(?, ?, ?, ?)}");
				statement.setInt(1, order.getId());
				statement.setInt(2, item.getProduct().getId());
				statement.setInt(3, item.getAmountProduct());
				statement.setString(4, item.getColor());
				
				statement.executeQuery();
				
				if (statement != null) {
					statement.close();
					statement = null;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
