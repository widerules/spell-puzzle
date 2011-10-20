package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.james.fa.po.Record;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;

public class RecordDaoImpl extends BasicDao<Record> implements RecordDao {
	private static final String SQL_FIND_ALL = "SELECT * FROM details";

	private static final String SQL_INSERT_RECORD = "INSERT INTO details(id,type,consume_type_id,"
			+ "consume_date,target,amount,creation_time,last_update) "
			+ "VALUES(?,?,?,?,?,?,?,?)";

	private static final String SQL_DELETE_RECORD = "DELETE FROM details WHERE id=?";

	private static final String SQL_UPDATE_RECORD = "UPDATE details SET type=?,consume_type_id=?,"
			+ "consume_date=?,target=?,amount=?,creation_time=?,last_update=? WHERE id=?";

	private static final String SQL_FIND_RECORD_BY_ID = "SELECT * FROM details WHERE id=?";

	private static class RecordMultiRowMapper implements MultiRowMapper<Record> {
		public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
			Record record = new Record();
			record.setId(rs.getString("id"));
			record.setType(rs.getInt("type"));
			record.setConsumeTypeId(rs.getString("consume_type_id"));
			record.setConsumeDate(rs.getString("consume_date"));
			record.setTarget(rs.getString("target"));
			record.setAmount(rs.getInt("amount"));
			record.setCreationTime(rs.getTimestamp("creation_time"));
			record.setLastUpdate(rs.getTimestamp("last_update"));
			return record;
		}
	}

	private static class RecordSingleRowMapper implements
			SingleRowMapper<Record> {
		public Record mapRow(ResultSet rs) throws SQLException {
			return new RecordMultiRowMapper().mapRow(rs, 1);
		}
	}

	public List<Record> findAll() {
		return query(SQL_FIND_ALL, new RecordMultiRowMapper());
	}

	public String insert(Record record) {
		record.setId(createId());
		if (update(SQL_INSERT_RECORD,
				new Object[] { record.getId(), new Integer(record.getType()),
						record.getConsumeTypeId(), record.getConsumeDate(),
						record.getTarget(), new Integer(record.getAmount()),
						record.getCreationTime(), record.getLastUpdate() },
				new int[] { Types.CHAR, Types.INTEGER, Types.CHAR,
						Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
						Types.TIMESTAMP, Types.TIMESTAMP }) > 0) {
			return record.getId();
		} else {
			return null;
		}
	}

	public String delete(String recordId) {
		if (update(SQL_DELETE_RECORD, recordId) > 0) {
			return recordId;
		} else {
			return null;
		}
	}

	public String update(Record record) {
		if (update(
				SQL_UPDATE_RECORD,
				new Object[] { new Integer(record.getType()),
						record.getConsumeTypeId(), record.getConsumeDate(),
						record.getTarget(), new Integer(record.getAmount()),
						record.getCreationTime(), record.getLastUpdate(),
						record.getId() }, new int[] { Types.INTEGER,
						Types.CHAR, Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP,
						Types.CHAR }) > 0) {
			return record.getId();
		} else {
			return null;
		}
	}

	public Record findById(String recordId) {
		return (Record) query(SQL_FIND_RECORD_BY_ID, recordId,
				new RecordSingleRowMapper());
	}

}
