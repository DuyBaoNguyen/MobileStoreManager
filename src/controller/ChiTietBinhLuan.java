package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import model.Comment;

@WebServlet("/ChiTietBinhLuan")
public class ChiTietBinhLuan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChiTietBinhLuan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Comment comment = null;
		try {
			comment = new Comment(Integer.parseInt(request.getParameter("commentId")));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/BinhLuanChuaTraLoi");
			return;
		}

		CommentDAO.getComment(comment);
		request.setAttribute("comment", comment);

		Boolean ignoreCommentError = (Boolean) getServletContext().getAttribute("ignoreCommentError");
		if (ignoreCommentError != null) {
			getServletContext().removeAttribute("ignoreCommentError");
		} else {
			ignoreCommentError = false;
		}
		request.setAttribute("ignoreCommentError", ignoreCommentError);

		Boolean replyCommentError = (Boolean) getServletContext().getAttribute("replyCommentError");
		if (replyCommentError != null) {
			getServletContext().removeAttribute("replyCommentError");
		} else {
			replyCommentError = false;
		}
		request.setAttribute("replyCommentError", replyCommentError);

		request.getRequestDispatcher("/WEB-INF/ChiTietBinhLuan.jsp").forward(request, response);
	}
}
