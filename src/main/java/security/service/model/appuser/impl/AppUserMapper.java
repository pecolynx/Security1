package security.service.model.appuser.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import security.model.AppUser;

@Component("userMapper")
public class AppUserMapper implements RowMapper<AppUser> {

	public static final String COL_LOGIN_ID = "LOGIN_ID";

	public static final String COL_PASSWORD = "PASSWORD";

	@Override
	public AppUser mapRow(ResultSet rs, int number) throws SQLException {
		String loginId = rs.getString(COL_LOGIN_ID);
		String password = rs.getString(COL_PASSWORD);

		return new AppUser(loginId, password);
	}
}
