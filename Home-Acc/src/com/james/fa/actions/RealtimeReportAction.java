package com.james.fa.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.fa.actions.conditions.RecordCondition;
import com.james.fa.actions.conditions.ReportCondition;
import com.james.fa.po.ConsumeType;
import com.james.fa.po.Record;
import com.james.fa.services.ConsumeTypeService;
import com.james.fa.services.RecordService;
import com.james.fa.services.ReportService;
import com.james.fa.vo.RecordVo;
import com.james.fa.vo.report.KeyAmountVo;
import com.james.skeleton.util.DateUtils;

public class RealtimeReportAction extends BasicAction {

	private static final long serialVersionUID = -5135680136505859910L;

	private int month;
	private int type;
	private String dateLike;

	private ReportService reportService;
	private ConsumeTypeService consumeTypeService;
	private RecordService recordService;

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

		List<KeyAmountVo> jsonList = new ArrayList<KeyAmountVo>();
		if (!yearMonthReport.isEmpty()) {
			for (List<String> catalog : yearMonthReport) {
				KeyAmountVo vo = new KeyAmountVo();
				vo.setKey(catalog.get(0));
				vo.setAmount(Float.parseFloat(catalog.get(1)) / 100);
				jsonList.add(vo);
			}
		}

		setJsonObj(jsonList);

		return jsonReturn();
	}

	public String consumeTypeReport() {
		ReportCondition condition = new ReportCondition();
		condition.setDateLike(dateLike);
		condition.setType(type);
		List<List<String>> consumeReport = reportService
				.getConsumeTypeReportByConditon(condition);

		List<KeyAmountVo> jsonList = new ArrayList<KeyAmountVo>();
		if (!consumeReport.isEmpty()) {
			List<ConsumeType> cyList = consumeTypeService.getAll();
			Map<String, String> cyMap = new HashMap<String, String>();
			for (ConsumeType cy : cyList) {
				cyMap.put(cy.getId(), cy.getText());
			}

			for (List<String> catalog : consumeReport) {
				KeyAmountVo vo = new KeyAmountVo();
				vo.setKey(cyMap.get(catalog.get(0)));
				vo.setAmount(Float.parseFloat(catalog.get(1)) / 100);
				jsonList.add(vo);
			}
		}

		setJsonObj(jsonList);

		return jsonReturn();
	}

	public String dailyReport() {
		ReportCondition condition = new ReportCondition();
		condition.setDateLike(dateLike);
		condition.setType(type);

		List<List<String>> dailyReport = reportService
				.getDailyReportByCondition(condition);

		List<KeyAmountVo> jsonList = new ArrayList<KeyAmountVo>();
		// "11.11.11"
		if (!dailyReport.isEmpty()) {
			for (List<String> catalog : dailyReport) {
				KeyAmountVo vo = new KeyAmountVo();
				vo.setKey(catalog.get(0));
				vo.setAmount(Float.parseFloat(catalog.get(1)) / 100);
				jsonList.add(vo);
			}
		}

		setJsonObj(jsonList);

		return jsonReturn();
	}

	public String detailReport() {
		RecordCondition condition = new RecordCondition();
		condition.setType(type);

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(DateUtils.string2Date(dateLike, "yyyy-MM"));
		} catch (Exception e) {
			c.setTime(DateUtils.string2Date(dateLike, "yyyy-MM"));
		}
		String startDate = DateUtils.date2StringByDay(c.getTime());
		c.add(Calendar.MONTH, 1);
		String endDate = DateUtils.date2StringByDay(c.getTime());

		condition.setStartDate(startDate);
		condition.setEndDate(endDate);

		List<Record> searchRecords = recordService.searchRecord(condition);

		if (!searchRecords.isEmpty()) {
			List<RecordVo> resultList = new ArrayList<RecordVo>();

			List<ConsumeType> typeList = consumeTypeService.getAll();
			Map<String, String> typeMap = new HashMap<String, String>();
			for (ConsumeType type : typeList) {
				typeMap.put(type.getId(), type.getText());
			}

			for (Record record : searchRecords) {
				RecordVo vo = new RecordVo(record);
				vo.setAmount(vo.getAmount() / 100);
				vo.setConsumeTypeValue(typeMap.get(vo.getConsumeTypeId()));
				resultList.add(vo);
			}

			setJsonObj(resultList);
		} else {
			setJsonObj(searchRecords);
		}
		return jsonReturn();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDateLike() {
		return dateLike;
	}

	public void setDateLike(String dateLike) {
		this.dateLike = dateLike;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setConsumeTypeService(ConsumeTypeService consumeTypeService) {
		this.consumeTypeService = consumeTypeService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
}
