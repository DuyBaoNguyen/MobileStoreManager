package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import model.Comment;

@WebServlet("/BoQuaBinhLuan")
public class BoQuaBinhLuan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoQuaBinhLuan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Comment comment = null;
		try {
			comment = new Comment(Integer.parseInt(request.getParameter("ignoreCommentId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/BinhLuanChuaTraLoi");
			return;
		}

		boolean ignoreCommentError = CommentDAO.ignoreComment(comment);
		getServletContext().setAttribute("ignoreCommentError", ignoreCommentError);

		String commentId = request.getParameter("commentId");
		if (commentId != null) {
			response.sendRedirect(request.getContextPath() + "/ChiTietBinhLuan?commentId=" + commentId);
		} else {
			response.sendRedirect(request.getContextPath() + "/BinhLuanChuaTraLoi");
		}
	}
}
