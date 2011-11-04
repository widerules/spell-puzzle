package com.james.fa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.vo.ReportUnitVo;
import com.james.skeleton.util.Validators;
import com.james.skeleton.util.dao.BasicDao;
import com.james.skeleton.util.dao.MultiRowMapper;
import com.james.skeleton.util.dao.SingleRowMapper;
import com.james.skeleton.util.dao.SqlHandler;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class ReportDaoImpl extends BasicDao implements ReportDao {

	private static class UnitMultiRowMapper implements
			MultiRowMapper<ReportUnitVo> {
		public ReportUnitVo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ReportUnitVo unit = new ReportUnitVo();
			unit.setDate(rs.getString("consume_date"));
			unit.setConsumeTypeId(rs.getString("consume_type_id"));
			unit.setType(rs.getInt("type"));
			unit.setTarget(rs.getString("target"));
			unit.setAmount(rs.getInt("amount"));
			return unit;
		}
	}

	private static class UnitSingleRowMapper implements
			SingleRowMapper<ReportUnitVo> {
		public ReportUnitVo mapRow(ResultSet rs) throws SQLException {
			return new UnitMultiRowMapper().mapRow(rs, 1);
		}
	}

	private static final String BASE = "SELECT consume_date, consume_type_id, type, target, SUM(type * amount) AS amount FROM records";
	private static final String YEAR_MONTH_REPORT = "SELECT SUBSTRING(consume_date, 1, 7) AS date, SUM(type * amount) AS amount FROM records";

	public List<List<String>> findYearMonthReport(ReportCondition condition) {
		SqlHandler handler = new SqlHandler(YEAR_MONTH_REPORT, false);
		handler.and("consume_date >= ?", condition.getStartDate(),
				!Validators.isEmpty(condition.getStartDate()));
		handler.and("consume_date <= ?", condition.getEndDate(),
				!Validators.isEmpty(condition.getEndDate()));
		handler.and("type = ?", condition.getType(), condition.getType() != 0);
		handler.and("target = ?", condition.getTarget(),
				!Validators.isEmpty(condition.getTarget()));
		return query(handler.getSQL()
				+ " GROUP BY date ORDER BY date", handler.getArgs(),
				new MultiRowMapper<List<String>>() {

					@Override
					public List<String> mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						List<String> list = new ArrayList<String>();
						list.add(rs.getString("date"));
						list.add(String.valueOf(rs.getInt("amount")));
						return list;
					}
				});
	}

	public List<ReportUnitVo> findUnitByCondition(ReportCondition condition) {
		SqlHandler handler = new SqlHandler(BASE, false);
		handler.and("consume_date >= ?", condition.getStartDate(),
				!Validators.isEmpty(condition.getStartDate()));
		handler.and("consume_date <= ?", condition.getEndDate(),
				!Validators.isEmpty(condition.getEndDate()));
		handler.and("type = ?", condition.getType(), condition.getType() != 0);
		handler.and("target = ?", condition.getTarget(),
				!Validators.isEmpty(condition.getTarget()));
		return query(handler.getSQL()
				+ " GROUP BY consume_date, consume_type_id, type, target",
				handler.getArgs(), new UnitMultiRowMapper());
	}
}
