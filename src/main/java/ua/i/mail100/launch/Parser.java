package ua.i.mail100.launch;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URL;

public class Parser {

    public static ModelList parseModelList(String str){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(str, ModelList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ModelList parseModelListFromUrl(String str){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new URL(str), ModelList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
