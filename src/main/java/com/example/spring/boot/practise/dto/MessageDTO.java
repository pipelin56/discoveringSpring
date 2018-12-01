package com.example.spring.boot.practise.dto;

public class MessageDTO {
	
	private String messageKey;
	private Boolean result;
	private String arguments[];
	
	public MessageDTO() {}
	
	public MessageDTO(String messageKey, Boolean result, String ... arguments) {
		super(); 
		this.setMessageKey(messageKey);
		this.setResult(result);
		this.setArguments(arguments);
	}

	public MessageDTO(String messageKey,Boolean result) {
		super(); 
		this.setMessageKey(messageKey);
		this.setResult(result);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String arguments[]) {
		this.arguments = arguments;
	}
	

}
