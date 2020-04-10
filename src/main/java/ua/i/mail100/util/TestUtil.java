package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import ua.i.mail100.config.MailConfig;
import ua.i.mail100.controller.rest.*;
import ua.i.mail100.model.*;
import ua.i.mail100.service.FileService;
import ua.i.mail100.service.MailService;
import ua.i.mail100.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestUtil {


    public static void main(String[] args) {
//        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
//        String str = "[";
//        for (byte b:bytes) {
//            str += "\"" + b + "\",";
//        }
//
//        System.out.println("str = " + str);


//        MapperDistanceUtil mapperDistanceUtil = new MapperDistanceUtil();
//        Distance distance = new Distance(null, null, null,
//                null, null, null, null, 11L,11L, RecordStatus.ACTIVE);
//        DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(distance);
//        DistanceDTO distanceDTO2 = new DistanceDTO();
//        Distance distance2 = mapperDistanceUtil.toObject(distanceDTO2);
//
//
//        MapperEventUtil mapperEventUtil = new MapperEventUtil();
//        Event event = new Event(null, null, null, null,
//                null, null, null, null, null, null, 11L,11L, RecordStatus.ACTIVE);
//        EventDTO eventDTO = mapperEventUtil.toDTO(event);
//        EventDTO eventDTO2 = new EventDTO();
//        Event event2 = mapperEventUtil.toObject(eventDTO2);
//
//
        //
//        Franchise franchise = new Franchise(1, "fran",null,11L, 12L, RecordStatus.ACTIVE);
//        Country country = new Country(1, "countr", 11L, 12L, RecordStatus.ACTIVE);
//
//        MapperEventUtil mapperEventUtil2 = new MapperEventUtil();
//
//        EventDTO eventDTO11 = new EventDTO(1, franchise.getId(), "orga", "name",
//                1L, 2L, 1, "ven", "url", "come", 11L,11L, "ACTIVE");
//        Event event11 = mapperEventUtil2.toObject(eventDTO11);

//        Event event10 = new Event(1, franchise, "orga", "name",
//                1L, 2L, country, "ven", "url", "come", 11L,11L, RecordStatus.ACTIVE);
//        EventDTO eventDTO10 = mapperEventUtil.toDTO(event10);
//        System.out.println("eventDTO10 = " + eventDTO10);
//
//        Long now = new Date().getTime();
//        System.out.println(now);

//        MailService mailService = new MailService();
//        mailService.sendMail(      "antonina.danilova2@gmail.com", "subj", "text");

//        UserService userService = new UserService();
//            User user = userService.getById(1);

//        Calendar calendar222 = new GregorianCalendar(1990, 09, 09);
//
//        calendar.get()



        String strToDate = "2020-04-12";
        Date date =  dateParse(strToDate);

        Date now = new Date();
        now.compareTo(date);

        System.out.println(now.compareTo(date));



//        Calendar calendar = new GregorianCalendar();
//        calendar.



//        LocalDate today = LocalDate.now();
//        LocalDate other = LocalDate.ofEpochDay(date.getTime());
//
//        Period p = Period.between(today, other);
//        System.out.println("p = " + p);

//        String strToDate = "1900-01-01";
//        Date date =  dateParse(strToDate);
//
//        String strToDate2 = "1960-01-01";
//        Date date2 =  dateParse(strToDate);
//        System.out.println("date = " + date.getTime());
//        System.out.println("date2 = " + date2.getTime());
//
//        System.out.println(date2.getTime()>date.getTime());
//
//
//        System.out.println("date = " + date);






    }


    public static Date dateParse(String str) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");


        Date parsingDate = null;
        try {
            parsingDate = ft.parse(str);
        }catch (ParseException e) {
        }
        return parsingDate;
    }
}
