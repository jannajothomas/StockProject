package testclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParseDate {
    /**
     * This helper method takes date as a String with the format M/d/yyyy and parses it to a Calendar object
     *
     * @param dateString a String representation of the date in the format M/d/yyyy
     * @return date as Calendar object
     */
    static Calendar parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        Date date;
        Calendar calendar = Calendar.getInstance();

        date = formatter.parse(dateString);
        calendar.setTime(date);

        return calendar;
    }
}