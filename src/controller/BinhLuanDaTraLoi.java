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

@WebServlet("/BinhLuanDaTraLoi")
public class BinhLuanDaTraLoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BinhLuanDaTraLoi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comment> comments = CommentDAO.getAnswerdComments();
		request.setAttribute("comments", comments);
		
		Boolean deleteCommentError = (Boolean)getServletContext().getAttribute("deleteCommentError");
		if (deleteCommentError != null) {
			getServletContext().removeAttribute("deleteCommentError");
		}
		else {
			deleteCommentError = false;
		}
		request.setAttribute("deleteCommentError", deleteCommentError);
		
		request.getRequestDispatcher("/WEB-INF/BinhLuanDaTraLoi.jsp").forward(request, response);
	}
}
