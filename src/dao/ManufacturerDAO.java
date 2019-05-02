package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.Manufacturer;

public class ManufacturerDAO {
	public static List<Manufacturer> getManufs() {
		List<Manufacturer> manufs = new ArrayList<Manufacturer>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsHangSanPham}");
			result = statement.executeQuery();

			while (result.next()) {
				Manufacturer manuf = new Manufacturer();
				manuf.setId(result.getInt(1));
				manuf.setName(result.getString(2));
				
				manufs.add(manuf);
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

		return manufs;
	}

	public static void getManuf(Manufacturer manuf, boolean hasProducts, boolean activeProducts) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layHangSanPham(?, ?)}");
			statement.setInt(1, manuf.getId());
			statement.registerOutParameter(2, Types.VARCHAR);

			statement.execute();

			manuf.setName(statement.getString(2));
			if (hasProducts == true) {
				manuf.setProducts(ProductDAO.getProducts(con, manuf, activeProducts));
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

	public static List<Manufacturer> searchManufs(List<Manufacturer> manufs, String searchStr) {
		List<Manufacturer> entries = new ArrayList<Manufacturer>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (Manufacturer item: manufs) {
			if (item.getName().toLowerCase().contains(searchStr)) {
				entries.add(item);
			}
		}

		return entries;
	}

	public static boolean insertManuf(Manufacturer manuf) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themHangSanPham(?)}");
			statement.setString(1, manuf.getName().toUpperCase());

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

	public static boolean deleteManuf(Manufacturer manuf) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaHangSanPham(?)}");
			statement.setInt(1, manuf.getId());

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

	public static boolean updateManuf(Manufacturer manuf) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaHangSanPham(?, ?)}");
			statement.setInt(1, manuf.getId());
			statement.setString(2, manuf.getName().toUpperCase());

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
