package com.epam.web.controller.filter;
import static com.epam.web.controller.command.ParameterProvider.*;
import static com.epam.web.controller.command.PagePath.*;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebFilter(urlPatterns = {"/jsp/*"})
public class CacheFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 	HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;
	        HttpSession session = ((HttpServletRequest) request).getSession();
	        String role = (String)session.getAttribute(ROLE);
	        String page = LOGIN_PAGE;
	        resp.setHeader("Cache-control", "no-cache");
	        resp.setHeader("Cache-control", "no-store");
	        resp.setHeader("Pragma", "no-cache");
	        resp.setDateHeader("Expire", 0);
	        if(role == null) {
	        	resp.sendRedirect(page);
	        }
	        chain.doFilter(request, response);
	}

}
