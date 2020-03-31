package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;

public class PasswordUtil {

    public static final String KEY = "ghfgf";

    public static String encodePassword(String password) {
        return KEY + DigestUtils.md5Hex(password.getBytes());
    }

    public static boolean isCorrectPassword(String comparativeEncodedPassword, String inputClearPassword){
        String encodedInputPass = encodePassword(inputClearPassword);
        return encodedInputPass.equals(comparativeEncodedPassword);
    }
}
