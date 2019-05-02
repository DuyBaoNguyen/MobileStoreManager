package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.Account;

public class AccountDAO {
	public static List<Account> getAccounts() {
		List<Account> accs = new ArrayList<Account>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsTaiKhoan}");
			result = statement.executeQuery();

			while (result.next()) {
				Account acc = new Account();
				acc.setUsername(result.getString(1));
				acc.setDisplayName(result.getString(2));
				acc.setSex(result.getBoolean(3));
				
				accs.add(acc);
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

		return accs;
	}
	
	public static boolean checkLogin(Account acc) {
		boolean success = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{ ? = call kiemTraDangNhap(?, ?)}");
			statement.registerOutParameter(1, Types.BIT);
			statement.setString(2, acc.getUsername());
			statement.setString(3, acc.getPassword());
			statement.execute();

			success = statement.getBoolean(1);
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

		return success;
	}
	
	public static void getAccount(Account acc) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layTaiKhoan(?, ?, ?)}");
			statement.setString(1, acc.getUsername());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.BIT);
			statement.execute();

			acc.setDisplayName(statement.getString(2));
			acc.setSex(statement.getBoolean(3));
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
	
	public static boolean insertAccount(Account acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themTaiKhoan(?, ?, ?, ?)}");
			statement.setString(1, acc.getUsername());
			statement.setString(2, acc.getPassword());
			statement.setString(3, acc.getDisplayName());
			statement.setBoolean(4, acc.getSex());

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
	
	public static boolean deleteAccount(Account acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaTaiKhoan(?)}");
			statement.setString(1, acc.getUsername());

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
	
	public static boolean updateAccount(Account acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaTaiKhoan(?, ?, ?)}");
			statement.setString(1, acc.getUsername());
			statement.setString(2, acc.getDisplayName());
			statement.setBoolean(3, acc.getSex());

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
	
	public static boolean changePassword(Account acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call doiMatKhau(?, ?)}");
			statement.setString(1, acc.getUsername());
			statement.setString(2, acc.getPassword());
			
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
	
	public static List<Account> searchAccounts(List<Account> accs, String searchStr) {
		List<Account> entries = new ArrayList<Account>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (Account item: accs) {
			if (item.getUsername().toLowerCase().contains(searchStr)
				|| item.getDisplayName().toLowerCase().contains(searchStr)) {
				entries.add(item);
			}
		}

		return entries;
	}
}
