package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;

@WebFilter("/*")
public class DangNhapFilter implements Filter {
	static int count = 0;

	public DangNhapFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		Account acc = null;
		String uri = ((HttpServletRequest)request).getRequestURI();
		if ( uri.indexOf("/css") > 0 || uri.indexOf("/images") > 0) {
	        chain.doFilter(request, response);
	        return;
	    }
		
		if (req.getServletPath().equals("/DangNhap")) {
			chain.doFilter(request, response);
			return;
		}

		if (session != null) {
			acc = (Account)session.getAttribute("account");
		}
		if (acc == null) {
			res.sendRedirect(req.getContextPath() + "/DangNhap");
			return;
		}

		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		res.setDateHeader("Expires", 0);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
