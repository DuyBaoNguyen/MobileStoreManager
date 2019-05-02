package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.Order;
import model.OrderDetail;
import model.Product;

public class OrderDAO {
	public static List<Order> getNotPaidOrder() {
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsDonHangChuaThanhToan}");
			result = statement.executeQuery();

			while (result.next()) {
				Order order = new Order();
				order.setId(result.getInt(1));
				order.setTimeOrder(result.getTimestamp(2));
				order.setValue(result.getInt(3));
				
				orders.add(order);
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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return orders;
	}
	
	public static List<Order> getPaidOrder() {
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsDonHangDaThanhToan}");
			result = statement.executeQuery();

			while (result.next()) {
				Order order = new Order();
				order.setId(result.getInt(1));
				order.setTimeOrder(result.getTimestamp(2));
				order.setValue(result.getInt(3));
				
				orders.add(order);
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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return orders;
	}
	
	public static void getOrder(Order order) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDonHangChuaThanhToan(?, ?, ?, ?, ?, ?, ?)}");
			statement.setInt(1, order.getId());
			statement.registerOutParameter(2, Types.TIMESTAMP);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.registerOutParameter(4, Types.BIT);
			statement.registerOutParameter(5, Types.CHAR);
			statement.registerOutParameter(6, Types.VARCHAR);
			statement.registerOutParameter(7, Types.INTEGER);
			
			statement.execute();

			order.setTimeOrder(statement.getTimestamp(2));
			order.setPersonName(statement.getString(3));
			order.setPersonSex(statement.getBoolean(4));
			order.setPersonPhone(statement.getString(5));
			order.setPersonAddress(statement.getString(6));
			order.setValue(statement.getInt(7));
			
			order.setOrderDetails(OrderDetailDAO.getOrder(con, order));
			
			for (OrderDetail item : order.getOrderDetails()) {
				ProductDAO.getProduct(con, item.getProduct());
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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean updateOrder(Order order) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call suaDonHang(?, ?, ?, ?, ?, ?)}");
			statement.setInt(1, order.getId());
			statement.setString(2, order.getPersonName());
			statement.setBoolean(3, order.getPersonSex());
			statement.setString(4, order.getPersonPhone());
			statement.setString(5, order.getPersonAddress());
			statement.setInt(6, order.getValue());

			statement.executeUpdate();
			
			OrderDetailDAO.updateOrderDetail(con, order);
			
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			error = true;
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return error;
	}
	
	public static boolean deleteOrder(Order order) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaDonHang(?)}");
			statement.setInt(1, order.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return error;
	}
	
	public static boolean acceptOrder(Order order) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xacNhanDonHang(?)}");
			statement.setInt(1, order.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return error;
	}
	
	public static boolean deleteProductInOrder(Order order, Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaSanPhamTrongDonHang(?, ?)}");
			statement.setInt(1, order.getId());
			statement.setInt(2, product.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return error;
	}
}
