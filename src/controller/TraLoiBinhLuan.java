package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import model.Account;
import model.Comment;
import model.Product;

@WebServlet("/TraLoiBinhLuan")
public class TraLoiBinhLuan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TraLoiBinhLuan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Comment comment = new Comment();
		comment.setContent(request.getParameter("content"));
		comment.setAnswerComment(new Comment(Integer.parseInt(request.getParameter("answerCommentId"))));
		comment.setProduct(new Product(Integer.parseInt(request.getParameter("productId"))));

		Account account = (Account) request.getSession().getAttribute("account");
		Account acc = new Account();
		acc.setUsername(account.getUsername());
		comment.setAnswerAccount(acc);
		boolean replyCommentError = CommentDAO.insertComment(comment);
		getServletContext().setAttribute("replyCommentError", replyCommentError);

		response.sendRedirect(request.getContextPath() + "/ChiTietBinhLuan?commentId=" + request.getParameter("commentId"));
	}
}
