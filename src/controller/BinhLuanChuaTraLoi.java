package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import model.Comment;

@WebServlet("/BinhLuanChuaTraLoi")
public class BinhLuanChuaTraLoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public BinhLuanChuaTraLoi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comment> comments = CommentDAO.getNotAnswerdComments();
		request.setAttribute("comments", comments);
		
		Boolean deleteCommentError = (Boolean)getServletContext().getAttribute("deleteCommentError");
		if (deleteCommentError != null) {
			getServletContext().removeAttribute("deleteCommentError");
		}
		else {
			deleteCommentError = false;
		}
		request.setAttribute("deleteCommentError", deleteCommentError);
		
		Boolean ignoreCommentError = (Boolean)getServletContext().getAttribute("ignoreCommentError");
		if (ignoreCommentError != null) {
			getServletContext().removeAttribute("ignoreCommentError");
		}
		else {
			ignoreCommentError = false;
		}
		request.setAttribute("ignoreCommentError", ignoreCommentError);
		
		request.getRequestDispatcher("/WEB-INF/BinhLuanChuaTraLoi.jsp").forward(request, response);
	}
}
