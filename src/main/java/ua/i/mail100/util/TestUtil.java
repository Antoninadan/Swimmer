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

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class TestUtil {


    public static void main(String[] args) {
        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
        String str = "[";
        for (byte b:bytes) {
            str += "\"" + b + "\",";
        }

        System.out.println("str = " + str);

        File f = writeBytesToFile(bytes, "oceanman33.png");

//        <img src="${pageContext.request.contextPath}/images/1.jpg"/>


//        EnumComboboxModel sexs = new EnumComboboxModel();
//
//        Enum<Sex> sexEnum =  new Enum<Sex>() {
//            @Override
//            public String toString() {
//                return super.toString();
//            }
//        }



//        try {
//            httpRequest();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public static FileOutputStream writeBytesToFile(byte[] bytes, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            return fileOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
        return null;
        }
    }

    public static void httpRequest() throws IOException {
//        String url = "http://www.google.com/";
        String url = "http://localhost:8080/country/open-all?userId=1";

        URL obj = null;
        obj = new URL(url);

        HttpURLConnection connection = null;
        connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }
}
