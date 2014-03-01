package com.seatbookie.svg.parse;

import java.util.LinkedHashMap;
import java.util.Map;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

public class MyFirebase {
	Firebase firebase=null;
	FirebaseResponse response=null;
	Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
	
	public MyFirebase(String baseUrl) throws FirebaseException{
		firebase=new Firebase(baseUrl);
	}
	
	public boolean add(Map<String, Object> dataMap) throws JacksonUtilityException, FirebaseException{
		response=firebase.put(dataMap);
		return response.getSuccess();
	}
	
	public boolean update(String path, Map<String, Object> dataMap) throws JacksonUtilityException, FirebaseException{
		response=firebase.post("/", dataMap);
		return response.getSuccess();
	}
}
