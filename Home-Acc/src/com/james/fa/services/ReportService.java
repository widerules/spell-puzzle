package com.james.fa.services;

import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.vo.ReportUnitVo;

public interface ReportService {

	List<ReportUnitVo> getAllReportUnitByCondition(ReportCondition condition);

	List<List<String>> getYearMonthReportByCondition(ReportCondition condition);

}
