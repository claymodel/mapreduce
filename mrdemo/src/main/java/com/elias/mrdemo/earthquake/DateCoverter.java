package com.elias.mrdemo.earthquake;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCoverter
{
    public static void main(String[] args)
    {
        String input = "2013-05-01 09:51:28.060+00:00";
        
        System.out.println(convertDate(input));

    }
    
    public static String convertDate(String input)
    {
        try
        {
            input = input.substring(0, 10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = formatter.parse(input);
            formatter.applyPattern("dd-MM-yyyy");
            String dtstr = formatter.format(dt);

            return dtstr;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }

}
