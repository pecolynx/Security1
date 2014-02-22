package security;

import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 組み込みJettyで起動するためのクラス。
 *
 */
public class Main {
	/**
	 * 指定できる引数は以下のとおり。
	 * port ポート、デフォルトは8080
	 * @param args 引数
	 * @throws Exception 例外
	 */
	public static void main(String[] args) throws Exception {
		String value = System.getProperty("port", "8080");

		int port = 8080;
		if (value != null) {
			port = Integer.parseInt(value);
		}

		Server server = new Server(port);

		WebAppContext webApp = new WebAppContext();
		webApp.setContextPath("/");
		ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
		webApp.setWar(protectionDomain.getCodeSource().getLocation().toExternalForm());

		server.setHandler(webApp);

		server.start();
		server.join();
	}
}
