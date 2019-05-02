package dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import db.DbConnection;
import model.Manufacturer;
import model.Product;

public class ProductDAO {
	public static List<Product> getProducts(Connection con, Manufacturer manuf, boolean activeProducts) {
		List<Product> products = new ArrayList<Product>();
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			if (activeProducts == true) {
				statement = con.prepareCall("{call layDsSanPhamDangKinhDoanhTheoHang(?)}");
			} else {
				statement = con.prepareCall("{call layDsSanPhamNgungKinhDoanhTheoHang(?)}");
			}
			statement.setInt(1, manuf.getId());
			result = statement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));
				product.setImageBase64(getImageBase64(result.getBlob(3)));
				product.setPrice(result.getInt(4));

				products.add(product);
			}
		} catch (SQLException | IOException e) {
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

		return products;
	}

	public static void getProduct(Product product) {
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call laySanPham(?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?)}");
			statement.setInt(1, product.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.BLOB);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.registerOutParameter(5, Types.FLOAT);
			statement.registerOutParameter(6, Types.VARCHAR);
			statement.registerOutParameter(7, Types.VARCHAR);
			statement.registerOutParameter(8, Types.VARCHAR);
			statement.registerOutParameter(9, Types.VARCHAR);
			statement.registerOutParameter(10, Types.VARCHAR);
			statement.registerOutParameter(11, Types.VARCHAR);
			statement.registerOutParameter(12, Types.VARCHAR);
			statement.registerOutParameter(13, Types.VARCHAR);
			statement.registerOutParameter(14, Types.VARCHAR);
			statement.registerOutParameter(15, Types.VARCHAR);
			statement.registerOutParameter(16, Types.BIT);
			statement.registerOutParameter(17, Types.BIT);
			statement.registerOutParameter(18, Types.BIT);
			statement.registerOutParameter(19, Types.BIT);
			statement.registerOutParameter(20, Types.BIT);
			statement.registerOutParameter(21, Types.BIT);
			statement.registerOutParameter(22, Types.INTEGER);

			statement.execute();

			product.setName(statement.getString(2));
			product.setImageBase64(getImageBase64(statement.getBlob(3)));
			product.setPrice(statement.getInt(4));

			if (statement.getObject(5) != null) {
				product.setScreenSize(Float.parseFloat(statement.getObject(5).toString()));
			} else {
				product.setScreenSize(null);
			}
			product.setScreenTechnology(statement.getString(6));
			product.setOs(statement.getString(7));
			product.setFrontCamera(statement.getString(8));
			product.setPosteriorCamera(statement.getString(9));
			product.setCpu(statement.getString(10));
			product.setRam(statement.getString(11));
			product.setInternalMemory(statement.getString(12));
			product.setMemoryStick(statement.getString(13));
			product.setSim(statement.getString(14));
			product.setPin(statement.getString(15));
			product.setWaterproof(statement.getBoolean(16));
			product.setDualCamera(statement.getBoolean(17));
			product.setFastCharging(statement.getBoolean(18));
			product.setFaceSecurity(statement.getBoolean(19));
			product.setFingerprintSecurity(statement.getBoolean(20));
			product.setStatus(statement.getBoolean(21));
			product.setManufacturer(new Manufacturer(statement.getInt(22)));

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{call layMauSanPham(?)}");
			statement.setInt(1, product.getId());
			result = statement.executeQuery();

			List<String> colors = new ArrayList<String>();
			while (result.next()) {
				colors.add(result.getString(1));
			}
			product.setColors(colors);
		} catch (SQLException | IOException e) {
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
	}

	public static void getProduct(Connection con, Product product) {
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call laySanPhamTheoMa(?, ?, ?, ?)}");
			statement.setInt(1, product.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.BLOB);
			statement.registerOutParameter(4, Types.INTEGER);

			statement.execute();

			product.setName(statement.getString(2));
			product.setImageBase64(getImageBase64(statement.getBlob(3)));
			product.setPrice(statement.getInt(4));

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{call layMauSanPham(?)}");
			statement.setInt(1, product.getId());
			result = statement.executeQuery();

			List<String> colors = new ArrayList<String>();
			while (result.next()) {
				colors.add(result.getString(1));
			}
			product.setColors(colors);
		} catch (SQLException | IOException e) {
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
	}

	private static String getImageBase64(Blob blob) throws SQLException, IOException {
		String imageBase64 = null;

		if (blob == null) {
			return imageBase64;
		}

		InputStream inputStream = blob.getBinaryStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		byte[] imageBytes = outputStream.toByteArray();
		imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

		inputStream.close();
		outputStream.close();

		return imageBase64;
	}

	private static Blob getBlob(String imageBase64) throws SerialException, SQLException {
		if (imageBase64 == null) {
			return null;
		}

		byte[] decoder = Base64.getDecoder().decode(imageBase64);
		Blob blob = new SerialBlob(decoder);

		return blob;
	}

	public static List<Product> searchProducts(List<Product> products, String searchStr) {
		List<Product> entries = new ArrayList<Product>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (Product item : products) {
			if (item.getName().toLowerCase().contains(searchStr)) {
				entries.add(item);
			}
		}

		return entries;
	}

	public static boolean insertProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall(
					"{call themSanPham(?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?)}");
			statement.setString(1, product.getName());
			statement.setBlob(2, getBlob(product.getImageBase64()));
			statement.setInt(3, product.getPrice());

			if (product.getScreenSize() != null) {
				statement.setFloat(4, product.getScreenSize());
			} else {
				statement.setNull(4, Types.FLOAT);
			}

			statement.setString(5, product.getScreenTechnology());
			statement.setString(6, product.getOs());
			statement.setString(7, product.getFrontCamera());
			statement.setString(8, product.getPosteriorCamera());
			statement.setString(9, product.getCpu());
			statement.setString(10, product.getRam());
			statement.setString(11, product.getInternalMemory());
			statement.setString(12, product.getMemoryStick());
			statement.setString(13, product.getSim());
			statement.setString(14, product.getPin());
			statement.setBoolean(15, product.getWaterproof());
			statement.setBoolean(16, product.getDualCamera());
			statement.setBoolean(17, product.getFastCharging());
			statement.setBoolean(18, product.getFaceSecurity());
			statement.setBoolean(19, product.getFingerprintSecurity());
			statement.setInt(20, product.getManufacturer().getId());

			statement.executeUpdate();

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{ ? = call layMaSanPham()}");
			statement.registerOutParameter(1, Types.INTEGER);
			statement.execute();

			product.setId(statement.getInt(1));

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{call themMauSanPham(?, ?)}");
			for (String color : product.getColors()) {
				statement.setInt(1, product.getId());
				statement.setString(2, color);

				statement.executeUpdate();
			}

			con.commit();
		} catch (SQLException e) {
			error = true;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	public static boolean deleteProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaSanPham(?)}");
			statement.setInt(1, product.getId());

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

	public static boolean updateProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall(
					"{call suaSanPham(?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?)}");
			statement.setInt(1, product.getId());
			statement.setString(2, product.getName());
			statement.setBlob(3, getBlob(product.getImageBase64()));
			statement.setInt(4, product.getPrice());

			if (product.getScreenSize() != null) {
				statement.setFloat(5, product.getScreenSize());
			} else {
				statement.setNull(5, Types.FLOAT);
			}

			statement.setString(6, product.getScreenTechnology());
			statement.setString(7, product.getOs());
			statement.setString(8, product.getFrontCamera());
			statement.setString(9, product.getPosteriorCamera());
			statement.setString(10, product.getCpu());
			statement.setString(11, product.getRam());
			statement.setString(12, product.getInternalMemory());
			statement.setString(13, product.getMemoryStick());
			statement.setString(14, product.getSim());
			statement.setString(15, product.getPin());
			statement.setBoolean(16, product.getWaterproof());
			statement.setBoolean(17, product.getDualCamera());
			statement.setBoolean(18, product.getFastCharging());
			statement.setBoolean(19, product.getFaceSecurity());
			statement.setBoolean(20, product.getFingerprintSecurity());

			statement.executeUpdate();

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{call xoaMauSanPham(?)}");
			statement.setInt(1, product.getId());
			statement.executeUpdate();

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{call themMauSanPham(?, ?)}");
			for (String color : product.getColors()) {
				statement.setInt(1, product.getId());
				statement.setString(2, color);

				statement.executeUpdate();
			}

			con.commit();
		} catch (SQLException e) {
			error = true;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	public static boolean restoreProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call khoiPhucSanPham(?)}");
			statement.setInt(1, product.getId());

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

	public static List<Product> getHotProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsSanPhamHot}");
			result = statement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));

				products.add(product);
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

		return products;
	}

	public static List<Product> getNotHotProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsKhongPhaiSanPhamHot}");
			result = statement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));

				products.add(product);
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

		return products;
	}

	public static boolean insertHotProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themSanPhamHot(?)}");
			statement.setInt(1, product.getId());

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

	public static boolean deleteHotProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaSanPhamHot(?)}");
			statement.setInt(1, product.getId());

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

	public static List<Product> getNewProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsSanPhamMoi}");
			result = statement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));

				products.add(product);
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

		return products;
	}

	public static List<Product> getNotNewProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsKhongPhaiSanPhamMoi}");
			result = statement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setName(result.getString(2));

				products.add(product);
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

		return products;
	}

	public static boolean insertNewProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themSanPhamMoi(?)}");
			statement.setInt(1, product.getId());

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

	public static boolean deleteNewProduct(Product product) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaSanPhamMoi(?)}");
			statement.setInt(1, product.getId());

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
