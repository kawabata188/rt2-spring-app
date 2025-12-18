package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

@Component
public class AccountCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestURL = request.getRequestURI();
		String contextPath = request.getContextPath();

		if (requestURL.indexOf("/error/") != -1 ||
				requestURL.indexOf("/html/") != -1 ||
				requestURL.indexOf("/css/") != -1 ||
				requestURL.indexOf("/img/") != -1 ||
				requestURL.indexOf("/js/") != -1 ||
				requestURL.indexOf("/login") != -1 ||
				requestURL.indexOf("/logout") != -1 ||
				requestURL.endsWith(contextPath + "/")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = request.getSession(false);
		if (session == null) {
			chain.doFilter(request, response);
			return;
		}

		EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");

		if (loginUser == null) {
			chain.doFilter(request, response);
			return;
		}

		if (loginUser.getAuthority() == 2) {
			chain.doFilter(request, response);
			return;
		}

		if (requestURL.contains("/regist") || requestURL.contains("/delete")) {
			response.sendRedirect(contextPath + "/");
			return;
		}
		chain.doFilter(request, response);
	}
}
