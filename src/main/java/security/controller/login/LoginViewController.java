package security.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginViewController {

	private static Logger logger = LoggerFactory.getLogger("Security");
	
	public static final String PATH = "login";
	
	public static final String BAD_CREDENTIAL = "error01";
	
	private Map<String, String> errors = new HashMap<>();
	
	public LoginViewController() {
		this.errors.put(BAD_CREDENTIAL, "ログインIDまたはパスワードが間違っています。");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("login");
		
		return "login";
	}
	
	@RequestMapping(value="/{errorId}", method = RequestMethod.GET)
	public String login(Model model,
			@PathVariable("errorId") String errorId) {
		logger.info("login");
		
		if (this.errors.containsKey(errorId)) {
			model.addAttribute("error", this.errors.get(errorId));
		}
		
		return "login";
	}
}
