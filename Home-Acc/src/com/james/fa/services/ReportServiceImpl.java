package com.james.fa.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<List<String>> getYearMonthReportByCondition(
			ReportCondition condition) {
		List<List<String>> resultList = new ArrayList<List<String>>();

		List<List<String>> reportList = reportDao
				.findYearMonthReport(condition);

		if (!reportList.isEmpty()) {
			// fix empty data

			String catalogKeyFormat = "yyyy-MM";

			Map<String, List<String>> catalogMap = new HashMap<String, List<String>>();
			for (List<String> catalog : reportList) {
				catalogMap.put(catalog.get(0), catalog);
			}

			String endDate = condition.getEndDate();
			Date end = DateUtils.string2Date(endDate, catalogKeyFormat);
			String startDate = condition.getStartDate();

			Calendar cursor = Calendar.getInstance();
			cursor.setTime(DateUtils.string2Date(startDate, catalogKeyFormat));
			while (!cursor.getTime().after(end)) {
				String catalogKey = DateUtils.date2String(cursor.getTime(),
						catalogKeyFormat);
				if (catalogMap.containsKey(catalogKey)) {
					resultList.add(catalogMap.get(catalogKey));
				} else {
					List<String> catalog = new ArrayList<String>();
					catalog.add(catalogKey);
					catalog.add("0");
					resultList.add(catalog);
				}

				cursor.add(Calendar.MONTH, 1);
			}
		}

		return resultList;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
}
