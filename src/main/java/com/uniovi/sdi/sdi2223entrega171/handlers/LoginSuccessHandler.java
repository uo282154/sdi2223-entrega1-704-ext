package com.uniovi.sdi.sdi2223entrega171.handlers;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import com.uniovi.sdi.sdi2223entrega171.services.RolesService;
import com.uniovi.sdi.sdi2223entrega171.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private LogService logService;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		logService.logger.info("User logged succesfully ");
		logService.addLog(Log.LogItemType.LOGIN_EX, "User logged succesfully "+request.getParameter("username"));
		redirectStrategy.sendRedirect(request, response, "/home");
	}
}
