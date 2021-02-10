/**
 * Static methods that can be used in any class that requires them
 */

package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFunctions {

    /**
     * This method takes a Calendar date and returns it as a String in the
     * format M/d/yyyy.
     *
     * @param date the date to convert
     * @return the String representation of the date
     */
    public static String dateToString(Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        String dateString = null;
        if (date != null) {
            dateString = formatter.format(date.getTime());
        }
        return dateString;
    }
}
