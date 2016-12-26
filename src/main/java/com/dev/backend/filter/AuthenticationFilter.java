package com.dev.backend.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dev.backend.delegate.DatabaseDelegate;
import com.dev.backend.dto.User;

public class AuthenticationFilter implements Filter {

	private DatabaseDelegate delegate;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		delegate = WebApplicationContextUtils.getRequiredWebApplicationContext(
				filterConfig.getServletContext()).getBean(
				DatabaseDelegate.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		/*
		 * Header is in format
		 * Base64 encoding of Username:token
		 */
		String authHeader = httpRequest
				.getHeader(AuthenticationConstants.AUTHENTICATION_HEADER);
		String decodedHeaderValue[] = new String(Base64.getDecoder().decode(authHeader.getBytes())).split(":");
		String userId = decodedHeaderValue[0];
		String token = decodedHeaderValue[1];
		if (!validateToken(userId, token)) {
			PrintWriter out = response.getWriter();
			out.write("Authentication Error: 401");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public boolean validateToken(String userId, String token) {
		User user = delegate.getUser(userId);
		return user.getAccessTokens().getAcessToken().equals(token);
	}

	@Override
	public void destroy() {

	}

}
