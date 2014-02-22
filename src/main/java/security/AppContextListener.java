package security;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppContextListener implements ServletContextListener {

	private static Logger logger = LoggerFactory.getLogger("Security");

    private JdbcTemplate jdbc;
    
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");

		ServletContext servletContext = event.getServletContext();
		WebApplicationContext springContext
				= WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		this.jdbc = springContext.getBean(JdbcTemplate.class);
		
		this.createTable();
		this.addUser("admin", "pass");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	
	void createTable() {
		this.jdbc.execute("DROP TABLE IF EXISTS APP_USER_LIST");
		this.jdbc.execute("CREATE TABLE APP_USER_LIST ("
				+ "LOGIN_ID VARCHAR(16) PRIMARY KEY"
				+ ",PASSWORD VARCHAR(64) NOT NULL"
				+ ")");
	}
	
	void addUser(String loginId, String password) {
		Object[] params = new Object[] {
				loginId, password,
		};
		
		this.jdbc.update("INSERT INTO APP_USER_LIST VALUES(?, ?)",
				params);
	}
}
