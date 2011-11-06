package com.james.fa.daos;

import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.vo.ReportUnitVo;

public interface ReportDao {

	List<ReportUnitVo> findUnitByCondition(ReportCondition condition);

	List<List<String>> findYearMonthReport(ReportCondition condition);

	List<List<String>> findTypeAndConsumeTypeByCondition(
			ReportCondition condition);

	List<List<String>> findDailyByCondition(ReportCondition condition);

}
