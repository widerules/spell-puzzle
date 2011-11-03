package com.james.fa.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.daos.ReportDao;
import com.james.fa.vo.ReportUnitVo;
import com.james.skeleton.util.DateUtils;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao;

	public List<ReportUnitVo> getAllReportUnitByCondition(
			ReportCondition condition) {
		return reportDao.findUnitByCondition(condition);
	}

	public List<List<String>> getYearMonthReportByCondition(ReportCondition condition) {
		List<List<String>> reportList = reportDao.findYearMonthReport(condition);
		
		if (!reportList.isEmpty()){
			// fix empty data
			
			List<String> last = reportList.get(reportList.size() - 1);
			String lastCatalog = last.get(0);
			Date end = DateUtils.string2Date(lastCatalog, "yyyy-MM");
			
		}
		
		return reportList;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	public static void main(String[] args) {
		String str = "2011-11";
		System.out.println();
		
	}
}
