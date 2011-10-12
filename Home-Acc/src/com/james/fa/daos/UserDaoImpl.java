package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.james.fa.po.User;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;

public class UserDaoImpl extends BasicDao<User> implements UserDao {

	private static class UserSingleRowMapper implements SingleRowMapper<User> {
		@Override
		public User mapRow(ResultSet rs) throws SQLException {
			return new UserMultiRowMapper().mapRow(rs, 1);
		}
	}

	private static class UserMultiRowMapper implements MultiRowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setCreateTime(rs.getTimestamp("create_time"));
			user.setRole(rs.getInt("role"));
			return user;
		}
	}

	private static final String SQL_FIND_ALL = "SELECT * FROM user";

	private static final String SQL_INSERT_USER = "INSERT INTO user(id,username,password,"
			+ "create_time,role) " + "VALUES(?,?,?,?,?)";

	private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";

	private static final String SQL_UPDATE_USER = "UPDATE user SET username=?,password=?,"
			+ "create_time=?,role=? WHERE id=?";

	private static final String SQL_FIND_BY_USERNAME_PASSWORD = "SELECT * FROM user WHERE username=? and password=?";

	public User findByUserNamePassword(String username, String password) {
		return query(SQL_FIND_BY_USERNAME_PASSWORD, new Object[] { username,
				password }, new UserSingleRowMapper());
	}

	public List<User> findAll() {
		return query(SQL_FIND_ALL, new UserMultiRowMapper());
	}

	public String insert(User user) {
		user.setId(createId());
		if (update(
				SQL_INSERT_USER,
				new Object[] { user.getId(), user.getUsername(),
						user.getPassword(), user.getCreateTime(),
						new Integer(user.getRole()) }, new int[] { Types.CHAR,
						Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
						Types.INTEGER }) > 0) {
			return user.getId();
		} else {
			return null;
		}
	}

	public String delete(String userId) {
		if (update(SQL_DELETE_USER, userId) > 0) {
			return userId;
		} else {
			return null;
		}
	}

	public String update(User user) {
		if (update(
				SQL_UPDATE_USER,
				new Object[] { user.getUsername(), user.getPassword(),
						user.getCreateTime(), new Integer(user.getRole()),
						user.getId() }, new int[] { Types.VARCHAR,
						Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,
						Types.CHAR }) > 0) {
			return user.getId();
		} else {
			return null;
		}
	}

}
