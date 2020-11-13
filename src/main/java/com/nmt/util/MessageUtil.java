package com.nmt.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	public Map<String,String> getMessage(String message){
		Map<String,String> result = new  HashMap<>();
		if(message.equals("create success")) {
			result.put("message", "Create success");
			result.put("alert", "success");
		}else if(message.equals("update success")) {
			result.put("message", "Update success");
			result.put("alert", "success");
		}else if(message.equals("error")) {
			result.put("message", "Error system");
			result.put("alert", "danger");
		}
		return result;
	}
}
