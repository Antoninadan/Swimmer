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
import java.util.*;

public class TestUtil {


    public static void main(String[] args) {
//        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
//        String str = "[";
//        for (byte b:bytes) {
//            str += "\"" + b + "\",";
//        }
//
//        System.out.println("str = " + str);



//        EnumComboboxModel sexs = new EnumComboboxModel();
//
//        Enum<Sex> sexEnum =  new Enum<Sex>() {
//            @Override
//            public String toString() {
//                return super.toString();
//            }
//        }



        List sexList = Arrays.asList(Sex.values());
        System.out.println("sexList = " + sexList);


    }
}
