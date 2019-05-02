package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/TaiKhoan/SuaTaiKhoan")
public class SuaTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SuaTaiKhoan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = new Account();
		acc.setUsername(request.getParameter("username"));
		AccountDAO.getAccount(acc);
		request.setAttribute("account", acc);
		
		Boolean updateAccountError = (Boolean) getServletContext().getAttribute("updateAccountError");
		if (updateAccountError != null) {
			getServletContext().removeAttribute("updateAccountError");
		} else {
			updateAccountError = false;
		}
		request.setAttribute("updateAccountError", updateAccountError);

		request.getRequestDispatcher("/WEB-INF/SuaTaiKhoan.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String displayName = request.getParameter("displayName");

		Account acc = new Account();
		acc.setUsername(username);
		acc.setDisplayName(displayName);
		if (request.getParameter("sex").equals("male")) {
			acc.setSex(true);
		} else {
			acc.setSex(false);
		}
		
		boolean updateAccountError = AccountDAO.updateAccount(acc);
		if (updateAccountError == true) {
			getServletContext().setAttribute("updateAccountError", updateAccountError);
			response.sendRedirect(request.getContextPath() + "/TaiKhoan/SuaTaiKhoan?username=" + username);
		}
		else {
			Account account = (Account)request.getSession().getAttribute("account");
			if (account.getUsername().equals(username)) {
				account.setDisplayName(acc.getDisplayName());
				account.setSex(acc.getSex());
			}
			response.sendRedirect(request.getContextPath() + "/TaiKhoan");
		}
	}

}
