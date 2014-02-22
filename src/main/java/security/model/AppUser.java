package security.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser {
	private final String loginId;
	private final String password;

	public AppUser(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public User getLoginUser() {
		List<GrantedAuthority> authorities = Arrays.asList(
				new GrantedAuthority[] {
						new SimpleGrantedAuthority("ROLE_USER")
				});
		
		return new User(this.loginId, this.password, authorities);
	}
}
