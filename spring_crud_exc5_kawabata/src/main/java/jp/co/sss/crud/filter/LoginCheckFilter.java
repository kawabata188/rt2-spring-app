package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

//@Component
public class LoginCheckFilter extends HttpFilter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		String requestURI = httpServletRequest.getRequestURI();

		if (requestURI.endsWith(".css") ||
				requestURI.endsWith(".js") ||
				requestURI.endsWith(".jpg") ||
				requestURI.endsWith(".png")) {
			chain.doFilter(request, response);
			return;
		}

		if (requestURI.equals("/") ||
				requestURI.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		EmployeeBean user = (EmployeeBean) session.getAttribute("user");

		//ログイン状態の判定
		if (user == null) {
			httpServletResponse.sendRedirect("/");
			return;
		}

		//ユーザー権限の判定
		if (user.getAuthority() == 1) {
			if (requestURI.startsWith("/regist") || requestURI.startsWith("/delete")) {
				httpServletResponse.sendRedirect("/");
				return;
			}
		}
		chain.doFilter(request, response);
	}
}
