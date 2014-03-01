package net.thegreshams.firebase4j.demo;

import java.util.LinkedHashMap;
import java.util.Map;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

public class Test {
	public static void main(String[] args) throws FirebaseException, JacksonUtilityException{
		String firebase_baseUrl = "https://seatbookie.firebaseio.com/";
		
		FirebaseResponse response=null;
		
		Firebase firebase = new Firebase( firebase_baseUrl );
		
		// "PUT" (test-map into the fb4jDemo-root)
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put( "PUT-root", "This was PUT into the fb4jDemo-root" );
		response = firebase.put( dataMap );
		System.out.println( "\n\nResult of PUT (for the test-PUT to fb4jDemo-root):\n" + response );
		System.out.println("\n");
				
	}
}
