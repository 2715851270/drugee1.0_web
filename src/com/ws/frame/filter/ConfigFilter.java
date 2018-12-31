package com.ws.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		res.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		Object admin = request.getSession().getAttribute("admin");
		//对session中存放的admin验证，如果存在则可以进入后台页面，如果不存在，则跳回登陆页
		if(null == admin){
			if(request.getRequestURI().contains("login.do")){
				filterChain.doFilter(req, res);
			} else {
				response.sendRedirect(request.getContextPath()+"/login.do");
			}
		} else {
			filterChain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
