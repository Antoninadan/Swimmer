package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import ua.i.mail100.service.FileService;

import java.io.UnsupportedEncodingException;

public class TestUtil {
    public static void main(String[] args) {
        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
        String str = "[";
        for (byte b:bytes) {
            str += "\"" + b + "\",";
        }

        System.out.println("str = " + str);



    }
}
