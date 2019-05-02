package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/TaiKhoan/XoaTaiKhoan")
public class XoaTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public XoaTaiKhoan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = new Account();
		acc.setUsername(request.getParameter("username"));
		
		Account account = (Account)request.getSession().getAttribute("account");
		if (account.getUsername().equals(acc.getUsername())) {
			getServletContext().setAttribute("deleteAccountFail", true);
		}
		else {
			boolean deleteAccountError = AccountDAO.deleteAccount(acc);
			getServletContext().setAttribute("deleteAccountError", deleteAccountError);
		}
		
		response.sendRedirect(request.getContextPath() + "/TaiKhoan");
	}
}
