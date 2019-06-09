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
		boolean insertAccountError = true;
		String message = "";
		
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String displayName = request.getParameter("displayName");

		if (username.length() == 0 || password.length() == 0) {
			message = "Tên tài khoản và mật khẩu còn thiếu";
		} else if (username.length() < 8 || username.length() > 20 || password.length() < 8 || password.length() > 20) {
			message = "Tên tài khoản và mật khẩu có độ dài lớn hơn 7 kí tự và nhỏ hơn 21 kí tự";
		} else if (!checkCharacter(password)) {
			message = "Mật khẩu chỉ có kí tự chữ và số, tối thiểu 1 kí tự hoa, 1 kí tự thường và 1 kí tự số";
		} else {
			insertAccountError = false;
		}
		
		if (insertAccountError == false) {
			Account acc = new Account();
			acc.setUsername(username);
			acc.setPassword(password);
			acc.setDisplayName(displayName);
			acc.setSex(request.getParameter("sex").equals("male"));
			
			if (insertAccountError = AccountDAO.insertAccount(acc)) {
				message = "Đã có lỗi xảy ra";
			}
		}
		
		if (insertAccountError == true) {
			getServletContext().setAttribute("insertAccountError", insertAccountError);
			getServletContext().setAttribute("message", message);
			response.sendRedirect(request.getContextPath() + "/TaiKhoan/ThemTaiKhoan");
		} else {
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
