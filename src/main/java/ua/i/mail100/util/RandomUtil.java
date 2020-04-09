package ua.i.mail100.util;

import org.springframework.beans.factory.annotation.Autowired;

public class RandomUtil {

    public static int randomInRange(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static int randomFixedLength(int numberOfDigits) {
        int min = (int)(Math.pow(10, numberOfDigits -1));
        int max = (int)(Math.pow(10, numberOfDigits) - 1);
        return randomInRange(min,max);
    }



}
