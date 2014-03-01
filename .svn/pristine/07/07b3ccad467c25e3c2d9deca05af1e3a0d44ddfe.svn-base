package com.seatbookie.svg.parse;

import java.util.LinkedHashMap;
import java.util.Map;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;

public class ConnectFirebase {
	private static String BASEURL="https://seatbookie.firebaseio.com/";
	
	public enum SeatState{
		EMPTY, SELECTED, BOOKED;
	}
	
	public static boolean addGraphic(long graphId, String width, String height){
		MyFirebase firebase;
		try {
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			firebase = new MyFirebase(BASEURL+graphId+"/");

			param.put("width", width);
			param.put("height", height);
			
			return firebase.add(param);
			
		} catch (FirebaseException e) {
			e.printStackTrace();
			return false;
		} catch (JacksonUtilityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addSeat(long graphId, String... seatNames){
		MyFirebase firebase;
		try {
			for(String seatName:seatNames){
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				firebase = new MyFirebase(BASEURL+graphId+"/seat/"+seatName+"/");
				
				param.put( "state", SeatState.EMPTY );	
				firebase.add(param);
			}
			
			return true;
		} catch (FirebaseException e) {
			e.printStackTrace();
			return false;
		} catch (JacksonUtilityException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addGraphAndSeat(long graphId, String width, String height, String... seatNames){
		MyFirebase firebase;
		try {
			Map<String, Object> param = new LinkedHashMap<String, Object>();
			firebase = new MyFirebase(BASEURL+graphId+"/");
			
			Map<String, Object> seatVal = new LinkedHashMap<String, Object>();
			for(String seatName:seatNames){
				Map<String, Object> seatNameVal = new LinkedHashMap<String, Object>();
				seatNameVal.put( "state", SeatState.EMPTY );
				seatVal.put(seatName, seatNameVal);
			}
			param.put("seat", seatVal);
			param.put("width", width);
			param.put("height", height);
			
			return firebase.add(param);
			
		} catch (FirebaseException e) {
			e.printStackTrace();
			return false;
		} catch (JacksonUtilityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateSeat(long graphId, SeatState state, String... seatNames){
		MyFirebase firebase;
		try {
			for(String seatName:seatNames){
				Map<String, Object> param = new LinkedHashMap<String, Object>();
				firebase = new MyFirebase(BASEURL+graphId+"/seat/"+seatName+"/");
				
				param.put( "state", state );	
				firebase.add(param);
			}
			
			return true;
		} catch (FirebaseException e) {
			e.printStackTrace();
			return false;
		} catch (JacksonUtilityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		//ConnectFirebase.addGraphAndSeat(22L,"100px","200px", "A1","A2");
		//ConnectFirebase.addGraphic(33, "777px", "777px");
		ConnectFirebase.updateSeat(22, SeatState.BOOKED, "A1","A2");
	}
}
