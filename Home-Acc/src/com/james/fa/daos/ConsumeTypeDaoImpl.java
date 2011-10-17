package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.james.fa.po.ConsumeType;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;

public class ConsumeTypeDaoImpl extends BasicDao<ConsumeType> implements
		ConsumeTypeDao {

	private static class ConsumeTypeSingleRowMapper implements
			SingleRowMapper<ConsumeType> {
		@Override
		public ConsumeType mapRow(ResultSet rs) throws SQLException {
			return new ConsumeTypeMultiRowMapper().mapRow(rs, 1);
		}
	}

	private static class ConsumeTypeMultiRowMapper implements
			MultiRowMapper<ConsumeType> {

		@Override
		public ConsumeType mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConsumeType po = new ConsumeType();
			po.setId(rs.getString("id"));
			po.setText(rs.getString("name"));
			po.setParentId(rs.getString("parent_id"));
			return po;
		}
	}

	private static final String SQL_FIND_ALL = "SELECT * FROM consume_type";

	private static final String SQL_INSERT_CONSUMETYPE = "INSERT INTO consume_type(id,name,parent_id) "
			+ "VALUES(?,?,?)";

	private static final String SQL_DELETE_CONSUMETYPE = "DELETE FROM consume_type WHERE id=?";

	private static final String SQL_UPDATE_CONSUMETYPE = "UPDATE consume_type SET name=?,parent_id=? WHERE id=?";

	private static final String SQL_FIND_CONSUMETYPE_BY_ID = "SELECT * FROM consume_type WHERE id=?";

	public List<ConsumeType> findAll() {
		return query(SQL_FIND_ALL, new ConsumeTypeMultiRowMapper());
	}

	public String insert(ConsumeType consumeType) {
		consumeType.setId(createId());
		if (update(SQL_INSERT_CONSUMETYPE, new Object[] { consumeType.getId(),
				consumeType.getText(), consumeType.getParentId() }, new int[] {
				Types.CHAR, Types.VARCHAR, Types.CHAR }) > 0) {
			return consumeType.getId();
		} else {
			return null;
		}
	}

	public String delete(String consumeTypeId) {
		if (update(SQL_DELETE_CONSUMETYPE, consumeTypeId) > 0) {
			return consumeTypeId;
		} else {
			return null;
		}
	}

	public String update(ConsumeType consumeType) {
		if (update(SQL_UPDATE_CONSUMETYPE,
				new Object[] { consumeType.getText(),
						consumeType.getParentId(), consumeType.getId() },
				new int[] { Types.VARCHAR, Types.CHAR, Types.CHAR }) > 0) {
			return consumeType.getId();
		} else {
			return null;
		}
	}

	public ConsumeType findById(String consumeTypeId) {
		return (ConsumeType) query(SQL_FIND_CONSUMETYPE_BY_ID, consumeTypeId,
				new ConsumeTypeSingleRowMapper());
	}
}
