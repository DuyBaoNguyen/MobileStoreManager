package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet("/TaiKhoan/DoiMatKhau")
public class DoiMatKhau extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DoiMatKhau() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = new Account();
		acc.setUsername(request.getParameter("username"));
		request.setAttribute("account", acc);
		
		Boolean changePasswordError = (Boolean) getServletContext().getAttribute("changePasswordError");
		if (changePasswordError != null) {
			getServletContext().removeAttribute("changePasswordError");
		} else {
			changePasswordError = false;
		}
		request.setAttribute("changePasswordError", changePasswordError);

		request.getRequestDispatcher("/WEB-INF/DoiMatKhau.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Account acc = new Account();
		acc.setUsername(username);
		acc.setPassword(password);
		
		boolean changePasswordError = AccountDAO.changePassword(acc);
		if (changePasswordError == true) {
			getServletContext().setAttribute("changePasswordError", changePasswordError);
			response.sendRedirect(request.getContextPath() + "/TaiKhoan/DoiMatKhau?username=" + username);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/TaiKhoan");
		}
	}

}
