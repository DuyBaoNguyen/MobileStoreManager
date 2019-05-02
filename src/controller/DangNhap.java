package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DangNhap() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("account") == null) {
			Boolean loginFail = (Boolean) getServletContext().getAttribute("loginFail");
			if (loginFail != null) {
				getServletContext().removeAttribute("loginFail");
			} else {
				loginFail = false;
			}
			request.setAttribute("loginFail", loginFail);

			request.getRequestDispatcher("/WEB-INF/DangNhap.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/SanPhamHot");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = new Account(username, password);

		boolean success = AccountDAO.checkLogin(account);
		if (success == false) {
			getServletContext().setAttribute("loginFail", !success);
			response.sendRedirect(request.getContextPath() + "/DangNhap");
		} else {
			account.setPassword(null);
			AccountDAO.getAccount(account);
			request.getSession().setAttribute("account", account);
			response.sendRedirect(request.getContextPath() + "/SanPhamHot");
		}
	}

}
