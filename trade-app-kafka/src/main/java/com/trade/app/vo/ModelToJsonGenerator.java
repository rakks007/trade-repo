package com.trade.app.vo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ModelToJsonGenerator {

	public static void main(String[] a) 
    { 
  
        

        String s="2020-11-11T10:15:30+01:00[Europe/Paris]";
        ZonedDateTime t=ZonedDateTime.parse(s);
        System.out.println(t.toString()); 

        String date ="2021-01-24";
       // String newstring = convertToDateFormat(date,"yyyy-MM-dd",LocalDate.CALENDAR_DATE_FORMAT);
       // System.out.println(newstring);  
        
        TradeMessageVO workRequest=new TradeMessageVO(); 
  
        // Creating Object of ObjectMapper define in Jakson Api 
        ObjectMapper Obj = new ObjectMapper();   
        try { 
  
            // get Oraganisation object as a json string 
           // String jsonStr = Obj.writeValueAsString(workRequest); 
            String jsonStr = Obj.writeValueAsString(workRequest); 
            // Displaying JSON String 	
            System.out.println(jsonStr); 
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }

	/*private static void getObjectDataWorkResponse(WorkResponse response) {
		//MessageHeader message=new MessageHeader();
		//response.setMessageHeader(message);
		response.setStatus(Status.S);
		
	}

	private static void getObjectDataWork(WorkRequest workRequest) {
		MessageHeader message=new MessageHeader();
		ShipmentWorkDetail workDetail=new ShipmentWorkDetail();
		
		//workRequest.setMessageHeader(message);
		
		workRequest.getWorks().add(workDetail);
	} */
	
	public static String convertToDateFormat(String strDate,String formatProvided,String formatNeeded) 
	{
	    DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern(formatProvided)
	                                        .toFormatter();
	    java.time.LocalDate parsedDate = java.time.LocalDate.parse(strDate, f);
	    DateTimeFormatter f2 = DateTimeFormatter.ofPattern(formatNeeded);
	    String newDate = parsedDate.format(f2);
	    return newDate;
	}
  
  
}