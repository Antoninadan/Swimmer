package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import ua.i.mail100.controller.rest.*;
import ua.i.mail100.model.*;
import ua.i.mail100.service.FileService;

import java.io.UnsupportedEncodingException;

public class TestUtil {
    public static void main(String[] args) {
//        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
//        String str = "[";
//        for (byte b:bytes) {
//            str += "\"" + b + "\",";
//        }
//
//        System.out.println("str = " + str);


        MapperDistanceUtil mapperDistanceUtil = new MapperDistanceUtil();
        Distance distance = new Distance(null, null, null,
                null, null, null, null, 11L,11L, RecordStatus.ACTIVE);
        DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(distance);
        DistanceDTO distanceDTO2 = new DistanceDTO();
        Distance distance2 = mapperDistanceUtil.toObject(distanceDTO2);


        MapperEventUtil mapperEventUtil = new MapperEventUtil();
        Event event = new Event(null, null, null, null,
                null, null, null, null, null, null, 11L,11L, RecordStatus.ACTIVE);
        EventDTO eventDTO = mapperEventUtil.toDTO(event);
        EventDTO eventDTO2 = new EventDTO();
        Event event2 = mapperEventUtil.toObject(eventDTO2);

        
        //
        Franchise franchise = new Franchise(1, "fran",null,11L, 12L, RecordStatus.ACTIVE);
        Country country = new Country(1, "countr", 11L, 12L, RecordStatus.ACTIVE);

//        MapperEventUtil2 mapperEventUtil2 = new MapperEventUtil2();
        MapperEventUtil mapperEventUtil2 = new MapperEventUtil();

        EventDTO eventDTO11 = new EventDTO(1, franchise.getId(), "orga", "name",
                1L, 2L, 1, "ven", "url", "come", 11L,11L, "ACTIVE");
        Event event11 = mapperEventUtil2.toObject(eventDTO11);

//        Event event10 = new Event(1, franchise, "orga", "name",
//                1L, 2L, country, "ven", "url", "come", 11L,11L, RecordStatus.ACTIVE);
//        EventDTO eventDTO10 = mapperEventUtil.toDTO(event10);
//        System.out.println("eventDTO10 = " + eventDTO10);
        
    }
}
