package com.topn.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IsLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

	
		/* HttpSession session= ((HttpServletRequest)request).getSession();
		 if(session.getAttribute("id")==null||session.getAttribute("id")=="")
		 {
			 session.setAttribute("id", "70");	
			 
			 ((HttpServletResponse)response).sendRedirect("/TopN/index.jsp");
		 }
		 else
		 {
			 chain.doFilter(request, response);
		 }
*/	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
