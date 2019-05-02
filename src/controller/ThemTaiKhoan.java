package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/TaiKhoan/ThemTaiKhoan")
public class ThemTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemTaiKhoan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean insertAccountError = (Boolean) getServletContext().getAttribute("insertAccountError");
		if (insertAccountError != null) {
			getServletContext().removeAttribute("insertAccountError");
		} else {
			insertAccountError = false;
		}
		request.setAttribute("insertAccountError", insertAccountError);

		request.getRequestDispatcher("/WEB-INF/ThemTaiKhoan.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String displayName = request.getParameter("displayName");

		Account acc = new Account();
		acc.setUsername(username);
		acc.setPassword(password);
		acc.setDisplayName(displayName);
		if (request.getParameter("sex").equals("male")) {
			acc.setSex(true);
		} else {
			acc.setSex(false);
		}
		
		boolean insertAccountError = AccountDAO.insertAccount(acc);
		if (insertAccountError == true) {
			getServletContext().setAttribute("insertAccountError", insertAccountError);
			response.sendRedirect(request.getContextPath() + "/TaiKhoan/ThemTaiKhoan");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/TaiKhoan");
		}
	}

}
