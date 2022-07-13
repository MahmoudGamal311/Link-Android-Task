package com.linkdevelopment.newsfeedapp.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String changeUTCToLocaleTime(String UTCDateAndTime) {

        String result = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(UTCDateAndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat output = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

//        TimeZone temp = TimeZone.getTimeZone("GMT"+SplashScreen.BRAND_SETTING.getBusinessTimezone());
        TimeZone temp = TimeZone.getTimeZone("GMT+2:00");
        output.setTimeZone(temp);
        String formattedDate = output.format(date);
        result = formattedDate;

        return result;
    }

}
