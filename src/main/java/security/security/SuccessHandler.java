package security.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger("Security");
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		logger.info("onAuthenticationSuccess");

		RedirectStrategy redirectStrategy = getRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/view");
	}
}
