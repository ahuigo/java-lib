import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import java.util.*;
import java.util.Date;
import java.util.TimeZone;

public class date {
    /**
     * from GMT-04:00 to  GMT +05:00 
     **/
    public static void main(String []args) {
        try {
            //String s = "2012-06-20 18:09:38";
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //Jul 9 12:1PM EDT
            String s = "July 9 12:1PM";
            SimpleDateFormat df = new SimpleDateFormat("MMM dd HH:ma");

            //df.setTimeZone(TimeZone.getTimeZone("EDT"));
            df.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
            Date timestamp = df.parse(s);

            df.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            String parsed = df.format(timestamp);
            System.out.println("Old = " + s);
            System.out.println("New = " + parsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hello World"); // 打印 Hello World
    }
}
