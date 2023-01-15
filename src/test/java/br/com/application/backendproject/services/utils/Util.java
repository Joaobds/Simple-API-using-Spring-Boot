package br.com.application.backendproject.services.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Util {
    

    public static Date convertJavaDateToSQLDate(String date) throws ParseException{
        
        java.util.Date dateInJava = new SimpleDateFormat("yyyy-MM-dd").parse(date); 
        java.sql.Date dateInSQl = new java.sql.Date(dateInJava.getTime());
        
        return dateInSQl;



    }

}
