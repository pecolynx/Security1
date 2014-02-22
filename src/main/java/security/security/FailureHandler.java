package security.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import security.controller.login.LoginViewController;

public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static Logger logger = LoggerFactory.getLogger("Security");
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.info("onAuthenticationFailure");
		
		String errorId = "unknown";
		if (exception instanceof BadCredentialsException) {
			errorId = LoginViewController.BAD_CREDENTIAL;
		}

		RedirectStrategy redirectStrategy = getRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/login/" + errorId);
	}
}
