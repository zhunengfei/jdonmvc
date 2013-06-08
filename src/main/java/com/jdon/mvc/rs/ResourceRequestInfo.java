package com.jdon.mvc.rs;


/**
 * 表示客户端某此对资源的请求信息
 * @author oojdon
 *
 */


public class ResourceRequestInfo {
	
	private final String url;
	
	private final String extension;
	
	private final String verb;
	
	
	
	public ResourceRequestInfo(String url, String extension, String verb) {
		this.url = url;
		this.extension = extension;
		this.verb = verb;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getExtension() {
		return extension;
	}

	public String getVerb() {
		return verb;
	}
	
	

}
