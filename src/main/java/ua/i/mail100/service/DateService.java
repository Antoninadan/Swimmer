package ua.i.mail100.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Date parse(String str) {

        Date parsingDate = null;
        try {
            parsingDate = simpleDateFormat.parse(str);
        } catch (ParseException e) {
        }
        return parsingDate;
    }

    public String toString(Date date) {
        String result = simpleDateFormat.format(date);
        return result;
    }
}
