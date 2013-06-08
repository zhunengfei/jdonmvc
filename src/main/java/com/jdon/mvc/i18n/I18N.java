package com.jdon.mvc.i18n;

import com.jdon.mvc.util.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
	
	//这是约定的资源文件名,希望用户在web.xml中配置自定义的名字，如果没配则使用这个名字
	private final static String MESSAGE_NAME = "message";
	
	private String messageName;
	
	private Locale locale;
	
	
	public I18N(String messageName, Locale locale) {
		if(StringUtils.isEmpty(messageName))
			this.messageName = MESSAGE_NAME;
		else 
			this.messageName = messageName;
		this.locale = locale;
	}

	
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(messageName, locale);
	}
	
	public String getMessage(String key) {
		if (key == null)
			throw new I18NException(
				"I18N message.key.required");
		if (locale == null)
			locale = Locale.getDefault();
		ResourceBundle resourceBundle = getResourceBundle();
		if (resourceBundle == null)
			throw new I18NException(
					"I18n resource.bundle.required");
		try {
			String msg = resourceBundle.getString(key);
			if (msg == null)
				throw new I18NException("can't load the message for the I18N key");
			return msg;
		} catch (java.util.MissingResourceException e) {
			throw new I18NException("can't load the message for the I18N key",e);
		}
	}

}
