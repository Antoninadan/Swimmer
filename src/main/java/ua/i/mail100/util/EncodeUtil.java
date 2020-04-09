package ua.i.mail100.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;

public class EncodeUtil {

    public static final String KEY = "ghfgf";

    public static String encode(String str) {
        return KEY + DigestUtils.md5Hex(str.getBytes());
    }

    public static boolean isCorrectPassword(String comparativeEncodedPassword, String inputClearPassword){
        String encodedInputPass = encode(inputClearPassword);
        return encodedInputPass.equals(comparativeEncodedPassword);
    }
}
