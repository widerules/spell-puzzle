package com.james.fa.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.services.ReportService;
import com.james.fa.vo.report.YearMonthVo;
import com.james.skeleton.util.DateUtils;

public class RealtimeReportAction extends BasicAction {

	private static final long serialVersionUID = -5135680136505859910L;

	private int month;

	private ReportService reportService;

	public String execute() {
		ReportCondition condition = new ReportCondition();
		Calendar now = Calendar.getInstance();
		// 当月 最后一天
		now.add(Calendar.MONTH, 1);
		now.add(Calendar.DATE, -now.get(Calendar.DAY_OF_MONTH));
		condition.setEndDate(DateUtils.date2StringByDay(now.getTime()));

		// 查询 month - 1 个月之前的数据
		now.add(Calendar.MONTH, 1 - month);
		now.set(Calendar.DATE, 1);
		condition.setStartDate(DateUtils.date2StringByDay(now.getTime()));

		List<List<String>> yearMonthReport = reportService
				.getYearMonthReportByCondition(condition);

		List<YearMonthVo> jsonList = new ArrayList<YearMonthVo>();
		if (!yearMonthReport.isEmpty()) {
			for (List<String> catalog : yearMonthReport) {
				YearMonthVo vo = new YearMonthVo();
				vo.setYearMonth(catalog.get(0));
				vo.setAmount(Float.parseFloat(catalog.get(1)) / 100);
				jsonList.add(vo);
			}
		}

		setJsonObj(jsonList);

		return jsonReturn();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
