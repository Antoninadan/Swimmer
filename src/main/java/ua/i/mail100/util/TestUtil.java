package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import ua.i.mail100.controller.rest.DistanceDTO;
import ua.i.mail100.controller.rest.EventDTO;
import ua.i.mail100.controller.rest.MapperDistanceUtil;
import ua.i.mail100.controller.rest.MapperEventUtil;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.DistanceType;
import ua.i.mail100.model.Event;
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
                null, null, null, null);
        DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(distance);
        DistanceDTO distanceDTO2 = new DistanceDTO();
        Distance distance2 = mapperDistanceUtil.toObject(distanceDTO2);


        MapperEventUtil mapperEventUtil = new MapperEventUtil();
        Event event = new Event(null, null, null, null,
                null, null, null, null, null, null);
        EventDTO eventDTO = mapperEventUtil.toDTO(event);
        EventDTO eventDTO2 = new EventDTO();
        Event event2 = mapperEventUtil.toObject(eventDTO2);



    }
}
