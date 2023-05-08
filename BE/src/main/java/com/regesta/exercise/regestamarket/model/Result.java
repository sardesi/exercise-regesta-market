package com.regesta.exercise.regestamarket.model;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private boolean result;
	private Object data;
	private List<String> messages;
	
	public Result() {
		messages = new ArrayList<String>();
	}
	
	public Result(boolean result, List<String> messages) {
		this.result = result;
		this.messages = messages;
	}
	
	public Result(boolean result, String message) {
		this.result = result;
		this.messages = new ArrayList<String>();		
		this.messages.add(message);
	}

	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{result=");
		sb.append(result ? "true" : "false");
		sb.append(", messages=");
		sb.append("[");
		for(int i=0; i<messages.size(); i++) {
			String message = messages.get(i);
			sb.append("\"" + message + "\"");
			if(i < messages.size()-1) {
				sb.append(", ");
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}
}
