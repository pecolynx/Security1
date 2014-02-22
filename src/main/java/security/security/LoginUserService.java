package security.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import security.model.AppUser;
import security.service.model.appuser.impl.AppUserMapper;

public class LoginUserService implements UserDetailsService {

	private static final String SQL
			= "SELECT * FROM "
			+ "APP_USER_LIST "
			+ "WHERE LOGIN_ID = :loginId";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbc;

	@Autowired
	private AppUserMapper appUserMapper;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) {
		User user = this.getUserByLoginId(loginId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}

		return user;
	}

	public User getUserByLoginId(String loginId) {
		Map<String, Object> params = new HashMap<>();
		params.put("loginId", loginId);
		
		try {
			AppUser appUser = this.namedParameterJdbc
					.queryForObject(SQL, params, this.appUserMapper);
			return appUser.getLoginUser();
		}
		catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
}
