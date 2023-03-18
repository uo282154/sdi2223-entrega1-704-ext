package com.uniovi.sdi.sdi2223entrega171.handlers;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private LogService logService;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
		logService.logger.info("User login failed");
		logService.addLog(Log.LogItemType.LOGIN_ERR, "User login failed: "+request.getParameter("username"));

		redirectStrategy.sendRedirect(request, response, "/login?error");
	}
}
