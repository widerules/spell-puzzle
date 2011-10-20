package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.james.fa.po.Tag;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;

public class TagDaoImpl extends BasicDao<Tag> implements TagDao {

	private static final String SQL_FIND_ALL = "SELECT * FROM tag";

	private static final String SQL_INSERT_TAG = "INSERT INTO tag(name) "
			+ "VALUES(?)";

	private static final String SQL_DELETE_TAG = "DELETE FROM tag WHERE name=?";

	private static final String SQL_FIND_TAG_BY_ID = "SELECT * FROM tag WHERE name=?";

	private static class TagMultiRowMapper implements MultiRowMapper<Tag> {
		public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tag tag = new Tag();
			tag.setName(rs.getString("name"));
			return tag;
		}
	}

	private static class TagSingleRowMapper implements SingleRowMapper<Tag> {
		public Tag mapRow(ResultSet rs) throws SQLException {
			return new TagMultiRowMapper().mapRow(rs, 1);
		}
	}

	public List<Tag> findAll() {
		return query(SQL_FIND_ALL, new TagMultiRowMapper());
	}

	public String insert(Tag tag) {
		if (update(SQL_INSERT_TAG, new Object[] { tag.getName() },
				new int[] { Types.VARCHAR }) > 0) {
			return tag.getName();
		} else {
			return null;
		}
	}

	public String delete(String name) {
		if (update(SQL_DELETE_TAG, name) > 0) {
			return name;
		} else {
			return null;
		}
	}

	public Tag find(String name) {
		return (Tag) query(SQL_FIND_TAG_BY_ID, name, new TagSingleRowMapper());
	}
}
