package com.jdon.mvc.validation;

import com.jdon.mvc.Constant;
import com.jdon.mvc.i18n.I18N;
import com.jdon.mvc.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:Chard Rapid
 * @author:oojdon
 * 
 * 框架提供的核心校验器，可对表单输入和符合JSR BeanValiadtor的模型对象进行校验
 * 使用@Context在表现层控制器中直接注入即可使用
 * 
 */
public class Validation {

	private List<String> errorsList = new ArrayList<String>();

	private Map<String, String> errorsMap = new HashMap<String, String>();

	private static ValidatePass OK = new ValidatePass();

	private static final String ERRORS = "errors";

	private static final String ERRORS_MAP = "errorsMap";
	
	private I18N i18n;

	public Validation() {
	}

	public Validation(HttpServletRequest request) {
		request.setAttribute(ERRORS, errorsList);
		request.setAttribute(ERRORS_MAP, errorsMap);
		i18n = new I18N(request.getSession().getServletContext().getInitParameter(Constant.I18N_MESSAGE_NAME),request.getLocale());
	}

	public boolean hasErrors() {
		return errorsList.size() < 1 ? false : true;
	}

	public void addErrors(String errorMsg) {
		errorsList.add(errorMsg);
	}

	public void addErrors(String positionKey, String errorMsg) {
		errorsList.add(errorMsg);
		errorsMap.put(positionKey, errorMsg);
	}

	public void addErrors(List<ValidateMessage> errorMessageList) {
		for (ValidateMessage msg : errorMessageList) {
			String i18key = msg.getI18nKey();
			String message = msg.getMessage();
			String positionKey = msg.getPositionKey();
			if (StringUtils.isNotEmpty(positionKey)) {
				if (StringUtils.isNotEmpty(i18key))
					message = i18n.getMessage(i18key);
				errorsMap.put(positionKey, message);
			}
			errorsList.add(i18n.getMessage(message));
		}

	}

	public void addErrorUsingResource(String positionKey, String i18nKey) {
		String message = i18n.getMessage(i18nKey);
		errorsList.add(message);
		errorsMap.put(positionKey, message);
	}

	public void addErrorUsingResource(String i18nKey) {
		errorsList.add(i18n.getMessage(i18nKey));
	}

	public Validation use(Validator validator) {
		ValidateMessage result = validator.validate();
		if (result instanceof OkMessage)
			return OK;
		return addToErrorList(result);
	}

	public void message(String message) {
		checkUse();
		errorsList.remove(errorsList.size() - 1);
		errorsList.add(message);

	}

	public void message(String positionKey, String message) {
		checkUse();
		errorsList.remove(errorsList.size() - 1);
		errorsList.add(message);
		errorsMap.put(positionKey, message);

	}

	public void i18nMessage(String i18nKey) {
		checkUse();
		errorsList.remove(errorsList.size() - 1);
		errorsList.add(i18n.getMessage(i18nKey));

	}

	public void i18nMessage(String positionKey, String i18nKey) {
		checkUse();
		String message = i18n.getMessage(i18nKey);
		errorsList.remove(errorsList.size() - 1);
		errorsList.add(message);
		errorsMap.put(positionKey, message);

	}
	
	private Validation addToErrorList(ValidateMessage error) {
		String message = error.getMessage();
		if (StringUtils.isNotEmpty(message))
			errorsList.add(message);
		else
			errorsList.add("");
		return this;
	}

	private void checkUse() {
		if (errorsList.size() == 0)
			throw new ValidationException(
					"you must use validation like this:validation.use(new NotNull(user.getName())).message() or validation.use(new NotNull(user.getName())).i18nMessage()");
	}

}
