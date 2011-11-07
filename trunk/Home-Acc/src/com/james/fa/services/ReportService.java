package com.james.fa.services;

import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.vo.ReportUnitVo;

public interface ReportService {

	List<ReportUnitVo> getAllReportUnitByCondition(ReportCondition condition);

	List<List<String>> getYearMonthReportByCondition(ReportCondition condition);

	List<List<String>> getConsumeTypeReportByConditon(ReportCondition condition);

	List<List<String>> getDailyReportByCondition(ReportCondition condition);

	List<List<String>> getTargetReportByCondition(ReportCondition condition);

}
