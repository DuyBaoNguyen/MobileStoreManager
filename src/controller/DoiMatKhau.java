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
		boolean changePasswordError = true;
		String message = "";
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (password.length() == 0) {
			message = "Mật khẩu còn thiếu";
		} else if (password.length() < 8 || password.length() > 20) {
			message = "Mật khẩu có độ dài lớn hơn 7 kí tự và nhỏ hơn 21 kí tự";
		} else if (!checkCharacter(password)) {
			message = "Mật khẩu chỉ có kí tự chữ và số, tối thiểu 1 kí tự hoa, 1 kí tự thường và 1 kí tự số";
		} else {
			changePasswordError = false;
		}
		
		if (changePasswordError == false) {
			Account acc = new Account();
			acc.setUsername(username);
			acc.setPassword(password);
			
			if (changePasswordError = AccountDAO.changePassword(acc)) {
				message = "Đã có lỗi xảy ra";
			}
		}
		
		if (changePasswordError == true) {
			getServletContext().setAttribute("changePasswordError", changePasswordError);
			getServletContext().setAttribute("message", message);
			response.sendRedirect(request.getContextPath() + "/TaiKhoan/DoiMatKhau?username=" + username);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/TaiKhoan");
		}
	}

	// Check password containing special characters that is not word characters,
	// containing enough required characters and two consecutive character is different
	private boolean checkCharacter(String password) {
		int length = password.length();
		boolean lowercase = false;
		boolean uppercase = false;
		boolean number = false;
		
		for (int i = 0; i < length; i++) {
			char c = password.charAt(i);
			if (c >= '0' && c <= '9') {
				number = true;
			} else if (c >= 'A' && c <= 'Z') {
				uppercase = true;
			} else if (c >= 'a' && c <= 'z') {
				lowercase = true;
			} else {
				return false;
			}
			
			if (i < length - 1 && c == password.charAt(i + 1)) {
				return false;
			}
		}
		return lowercase && uppercase && number;
	}
}
