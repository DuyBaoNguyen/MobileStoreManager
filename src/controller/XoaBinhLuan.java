package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import model.Comment;

@WebServlet("/XoaBinhLuan")
public class XoaBinhLuan extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public XoaBinhLuan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment comment = new Comment(Integer.parseInt(request.getParameter("commentId")));
		
		boolean deleteCommentError = CommentDAO.deleteComment(comment);
		getServletContext().setAttribute("deleteCommentError", deleteCommentError);

		String answerdComment = request.getParameter("answerdComment");
		if (answerdComment != null) {
			response.sendRedirect(request.getContextPath() + "/BinhLuanDaTraLoi");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/BinhLuanChuaTraLoi");
		}
	}
}
