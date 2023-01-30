package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isPasswordValid(String password) {
        
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isEmailValid(String email) {

        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
  
        Pattern p = Pattern.compile(regex);
  
        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);
  
        return m.matches();
    }

    public static boolean isDateValid(String date) {

        if (date == null) {
            return false;
        }
        
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        
        try{
            sdfrmt.parse(date); 
        }
        
        catch (ParseException e)
        {
            return false;
        }
        
        return true;
    }

    public static boolean isTimeValid(String time) {
        if (time == null) {
            return false;
        }
        try{
            LocalTime.parse(time);
        }
        
        catch (DateTimeParseException e)
        {
            return false;
        }
        return true;
    }
    
}
