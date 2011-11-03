package com.james.fa.actions;

import java.util.Calendar;

import com.james.fa.actions.conditions.ReportCondition;
import com.james.skeleton.util.DateUtils;

public class RealtimeReportAction extends BasicAction {

	private static final long serialVersionUID = -5135680136505859910L;

	private int month;

	public String execute() {
		ReportCondition condition = new ReportCondition();
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DATE, 1);
		now.add(Calendar.MONTH, 1);
		condition.setEndDate(DateUtils.date2StringByDay(now.getTime()));
		now.add(Calendar.MONTH, 2 - month);
		now.set(Calendar.DATE, 1);
		condition.setStartDate(DateUtils.date2StringByDay(now.getTime()));

		return jsonReturn();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
