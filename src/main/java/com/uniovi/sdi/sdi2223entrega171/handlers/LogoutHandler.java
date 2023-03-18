package com.uniovi.sdi.sdi2223entrega171.handlers;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

	@Autowired
	private LogService logService;

	private final RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		logService.logger.info("User disconected");
		logService.addLog(Log.LogItemType.LOGOUT, "User disconected "+authentication.getName());
		redirectStrategy.sendRedirect(request, response, "/login?logout");
	}
}
