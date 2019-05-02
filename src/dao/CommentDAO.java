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
import model.Comment;
import model.Product;

public class CommentDAO {
	public static List<Comment> getNotAnswerdComments() {
		List<Comment> comments = new ArrayList<Comment>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsBinhLuanChuaTraLoi}");
			result = statement.executeQuery();

			while (result.next()) {
				Comment comment = new Comment();
				comment.setId(result.getInt(1));
				comment.setPersonName(result.getString(2));
				comment.setTime(result.getTimestamp(3));
				if (result.getObject(4) == null) {
					comment.setAnswerComment(null);
				} else {
					comment.setAnswerComment(new Comment(result.getInt(4)));
				}
				Product product = new Product();
				product.setName(result.getString(5));
				comment.setProduct(product);

				comments.add(comment);
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

		return comments;
	}
	
	public static List<Comment> getAnswerdComments() {
		List<Comment> comments = new ArrayList<Comment>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsBinhLuanDaTraLoi}");
			result = statement.executeQuery();

			while (result.next()) {
				Comment comment = new Comment();
				comment.setId(result.getInt(1));
				comment.setPersonName(result.getString(2));
				comment.setTime(result.getTimestamp(3));
				if (result.getObject(4) == null) {
					comment.setAnswerComment(null);
				} else {
					comment.setAnswerComment(new Comment(result.getInt(4)));
				}
				Product product = new Product();
				product.setName(result.getString(5));
				comment.setProduct(product);

				comments.add(comment);
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

		return comments;
	}

	public static void getComment(Comment comment) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layBinhLuan(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			statement.setInt(1, comment.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.TIMESTAMP);
			statement.registerOutParameter(4, Types.VARCHAR);
			statement.registerOutParameter(5, Types.BIT);
			statement.registerOutParameter(6, Types.VARCHAR);
			statement.registerOutParameter(7, Types.BIT);
			statement.registerOutParameter(8, Types.INTEGER);
			statement.registerOutParameter(9, Types.VARCHAR);
			statement.registerOutParameter(10, Types.BIT);
			statement.execute();

			comment.setContent(statement.getString(2));
			comment.setTime(statement.getTimestamp(3));
			comment.setPersonName(statement.getString(4));
			comment.setPersonSex(statement.getBoolean(5));
			if (statement.getString(6) == null) {
				comment.setAnswerAccount(null);
			} else {
				Account acc = new Account();
				acc.setDisplayName(statement.getString(6));
				acc.setSex(statement.getBoolean(7));
				comment.setAnswerAccount(acc);
			}
			Product product = new Product();
			product.setId(statement.getInt(8));
			product.setName(statement.getString(9));
			comment.setProduct(product);
			comment.setAnswerd(statement.getBoolean(10));

			if (statement != null) {
				statement.close();
				statement = null;
			}

			comment.setComments(getCommentsByCommentId(con, comment));
			for (Comment item : comment.getComments()) {
				item.setComments(getCommentsByCommentId(con, item));
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

	public static List<Comment> getCommentsByCommentId(Connection con, Comment comment) {
		List<Comment> comments = new ArrayList<Comment>();
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			statement = con.prepareCall("{call layDsBinhLuanTheoBinhLuan(?)}");
			statement.setInt(1, comment.getId());
			result = statement.executeQuery();

			while (result.next()) {
				Comment cmt = new Comment();
				cmt.setId(result.getInt(1));
				cmt.setContent(result.getString(2));
				cmt.setTime(result.getTimestamp(3));
				cmt.setPersonName(result.getString(4));
				cmt.setPersonSex(result.getBoolean(5));
				if (result.getString(6) == null) {
					cmt.setAnswerAccount(null);
				} else {
					Account acc = new Account();
					acc.setDisplayName(result.getString(6));
					acc.setSex(result.getBoolean(7));
					cmt.setAnswerAccount(acc);
				}
				cmt.setAnswerd(result.getBoolean(8));

				comments.add(cmt);
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

		return comments;
	}

	public static boolean insertComment(Comment comment) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themBinhLuanAdmin(?, ?, ?, ?)}");
			statement.setString(1, comment.getContent());
			statement.setInt(2, comment.getAnswerComment().getId());
			statement.setInt(3, comment.getProduct().getId());
			statement.setString(4, comment.getAnswerAccount().getUsername());

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
	
	public static boolean deleteComment(Comment comment) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaBinhLuan(?)}");
			statement.setInt(1, comment.getId());
			
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
	
	public static boolean ignoreComment(Comment comment) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call boQuaBinhLuan(?)}");
			statement.setInt(1, comment.getId());
			
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
