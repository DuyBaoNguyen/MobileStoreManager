package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/TaiKhoan")
public class TaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TaiKhoan() {
        super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Account> accounts = AccountDAO.getAccounts();
		String searchStr = request.getParameter("search");
		accounts = AccountDAO.searchAccounts(accounts, searchStr);
		request.setAttribute("accounts", accounts);
		
		Boolean deleteAccountError = (Boolean)getServletContext().getAttribute("deleteAccountError");
		if (deleteAccountError != null) {
			getServletContext().removeAttribute("deleteAccountError");
		}
		else {
			deleteAccountError = false;
		}
		request.setAttribute("deleteAccountError", deleteAccountError);
		
		Boolean deleteAccountFail = (Boolean)getServletContext().getAttribute("deleteAccountFail");
		if (deleteAccountFail != null) {
			getServletContext().removeAttribute("deleteAccountFail");
		}
		else {
			deleteAccountFail = false;
		}
		request.setAttribute("deleteAccountFail", deleteAccountFail);
		
		request.getRequestDispatcher("/WEB-INF/TaiKhoan.jsp").forward(request, response);
	}
}
