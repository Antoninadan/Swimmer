package ua.i.mail100.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.config.FileStorageConfig;

@Component
public class TestClass {
    @Autowired
    FileStorageConfig fileStorageConfig;

    public void test1(){
        System.out.println(fileStorageConfig.getUploadDir());
    }
}
