package com.james.fa.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.PropertyResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.james.fa.config.Config;
import com.james.skeleton.util.Validators;
import com.james.skeleton.util.ajax.Reply;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

public class BasicAction extends ActionSupport {

	private static final long serialVersionUID = -5098565305331510249L;

	public static final String JSON = "json"; // return json;
	public static final String AJAX = "ajax"; // return reply object;

	public static Logger logger = Logger.getLogger(BasicAction.class);

	private Reply reply = new Reply();
	private Object jsonObj;

	public String execute() {
		return SUCCESS;
	}

	public String ajaxReturn() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setIntHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		// OutputStream os = response.getOutputStream();
		PrintWriter outer;
		try {
			outer = response.getWriter();
			if (logger.isDebugEnabled()) {
				logger.debug(JSONObject.fromObject(reply, Config.JSON_CONFIG)
						.toString());
			}
			outer.print(JSONObject.fromObject(reply, Config.JSON_CONFIG)
					.toString());
			outer.flush();
			outer.close();
			response.setStatus(HttpServletResponse.SC_OK);
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AJAX;
	}

	public String jsonReturn() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setIntHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		// OutputStream os = response.getOutputStream();
		PrintWriter outer;
		try {
			outer = response.getWriter();
			if (jsonObj instanceof List) {
				if (logger.isDebugEnabled()) {
					logger.debug(JSONArray.fromObject(jsonObj,
							Config.JSON_CONFIG).toString());
				}
				outer.print(JSONArray.fromObject(jsonObj, Config.JSON_CONFIG)
						.toString());
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(JSONObject.fromObject(jsonObj,
							Config.JSON_CONFIG).toString());
				}
				outer.print(JSONObject.fromObject(jsonObj, Config.JSON_CONFIG)
						.toString());
			}
			outer.flush();
			outer.close();
			response.setStatus(HttpServletResponse.SC_OK);
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON;
	}

	protected void makeSuccess() {
		getReply().setSuccess(true);
	}

	protected void makeFailure() {
		getReply().setSuccess(false);
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public void addActionError(String anErrorMessage) {
		super.addActionError(anErrorMessage);
		reply.addErrMessage(anErrorMessage);
	}

	public void addActionError(List<String> errMessages) {
		reply.getErrMessage().addAll(errMessages);
	}

	public List<String> getActionError() {
		return reply.getErrMessage();
	}

	public void addActionMessage(String message) {
		super.addActionMessage(message);
		reply.addMessage(message);
	}

	public List<String> getActionMessages() {
		return reply.getMessage();
	}

	public void addActionMessage(List<String> messages) {
		reply.getMessage().addAll(messages);
	}

	public boolean hasActionErrors() {
		return !Validators.isEmpty(reply.getErrMessage());
	}

	public boolean hasActionMessages() {
		return !Validators.isEmpty(reply.getMessage());
	}

	public void setMsg(String msg) {
		reply.setMsg(msg);
	}

	public Object getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(Object jsonObj) {
		this.jsonObj = jsonObj;
	}

	public class I18N {
		public String getI18nText(String key) {
			return getText(key);
		}

		public PropertyResourceBundle getBundle() {
			return (PropertyResourceBundle) getTexts("message");
		}
	}

	public I18N getI18n() {
		return i18n;
	}

	private I18N i18n = new I18N();
}
