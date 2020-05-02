package ua.i.mail100.util;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import ua.i.mail100.config.FileStorageConfig;
import ua.i.mail100.model.*;
import ua.i.mail100.service.file.FileStorageService;

import java.io.*;
import java.net.*;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
public class TestUtil {


    public static void main(String[] args) {
//        byte[] bytes = FileService.getBytesFromFile("D://", "oceanman.png");
//        String str = "[";
//        for (byte b : bytes) {
//            str += "\"" + b + "\",";
//        }
//        System.out.println("str = " + str);

//        File file = new File("D:/oceanman_xs.png");
//        FileService fileService = new FileService();
//        String base64 = null;
//        try {
//            base64 = fileService.convertFileToString(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("base64 = " + base64);
//        System.out.println(FileService.readTextFromFile("D:/", "oceanman_xs.png"));



        TestClass testClass = new TestClass();
        testClass.test1();

//        <img src="${pageContext.request.contextPath}/images/1.jpg"/>


    }

    public static void getFileFromUrl() {
        try {
            URL url = new URL("file://c:/Perl");
//            writeBytesToFile(System.out.println(url.toString()), "");
            System.out.println(url.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
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
