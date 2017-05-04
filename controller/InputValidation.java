package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputValidation {
	
	public static boolean validateDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	   
	    formatter.setLenient(false);
	    Date parsedDate = null;
	    try {
	        parsedDate = formatter.parse(date);
	        return true;
	    } catch (ParseException e) {
	    }
	    return false;
	}
	public static boolean validateNumber(String nr){
		Integer value = null;
		if(nr.matches("\\d*")&& nr.length() > 0)
		{
			return true;
		}
		return false;
	}
}
