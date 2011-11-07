package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.po.Record;
import com.james.skeleton.util.Validators;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;
import com.james.skeleton.util.dao.SqlHandler;

public class RecordDaoImpl extends BasicDao<Record> implements RecordDao {
	private static final String SQL_FIND_ALL = "SELECT * FROM records";

	private static final String SQL_INSERT_RECORD = "INSERT INTO records(id,type,consume_type_id,"
			+ "consume_date,target,amount,description,creation_time,last_update) "
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	private static final String SQL_DELETE_RECORD = "DELETE FROM records WHERE id=?";

	private static final String SQL_UPDATE_RECORD = "UPDATE records SET type=?,consume_type_id=?,"
			+ "consume_date=?,target=?,amount=?,description=?,last_update=? WHERE id=?";

	private static final String SQL_FIND_RECORD_BY_ID = "SELECT * FROM records WHERE id=?";

	private static class RecordMultiRowMapper implements MultiRowMapper<Record> {
		public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
			Record record = new Record();
			record.setId(rs.getString("id"));
			record.setType(rs.getInt("type"));
			record.setConsumeTypeId(rs.getString("consume_type_id"));
			record.setConsumeDate(rs.getString("consume_date"));
			record.setTarget(rs.getString("target"));
			record.setAmount(rs.getInt("amount"));
			record.setDesc(rs.getString("description"));
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
		Date now = new Date();
		record.setCreationTime(now);
		record.setLastUpdate(now);
		if (update(
				SQL_INSERT_RECORD,
				new Object[] { record.getId(), new Integer(record.getType()),
						record.getConsumeTypeId(), record.getConsumeDate(),
						record.getTarget(), new Integer(record.getAmount()),
						record.getDesc(), record.getCreationTime(),
						record.getLastUpdate() }, new int[] { Types.CHAR,
						Types.INTEGER, Types.CHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
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
		record.setLastUpdate(new Date());
		if (update(
				SQL_UPDATE_RECORD,
				new Object[] { new Integer(record.getType()),
						record.getConsumeTypeId(), record.getConsumeDate(),
						record.getTarget(), new Integer(record.getAmount()),
						record.getDesc(), record.getLastUpdate(),
						record.getId() }, new int[] { Types.INTEGER,
						Types.CHAR, Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,
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

	@Override
	public List<Record> findByCondition(RecordCondition condition) {
		SqlHandler handler = new SqlHandler(SQL_FIND_ALL, false);
		handler.and("consume_date >= ?", condition.getStartDate(),
				!Validators.isEmpty(condition.getStartDate()));
		handler.and("consume_date <= ?", condition.getEndDate(),
				!Validators.isEmpty(condition.getEndDate()));
		handler.and("type = ?", condition.getType(), condition.getType() != 0);
		handler.and("amount >= ?", condition.getStartAmount(),
				condition.getStartAmount() != -1);
		handler.and("amount <= ?", condition.getEndAmount(),
				condition.getEndAmount() != -1);
		handler.and("target = ?", condition.getTarget(),
				!Validators.isEmpty(condition.getTarget()));
		handler.and("description like ?", "%" + condition.getDesc() + "%",
				!Validators.isEmpty(condition.getDesc()));
		handler.and("consume_type_id=?", condition.getConsumeTypeId(),
				!Validators.isEmpty(condition.getConsumeTypeId()));

		return query(handler.getSQL(), handler.getArgs(),
				new RecordMultiRowMapper());
	}

}
