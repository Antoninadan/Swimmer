package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.config.MailConfig;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.mapper.MapperEventUtil;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.MailService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mail")
@Slf4j
@Profile("rest")
public class MailController {
    @Autowired
    MailService mailService;

//    @GetMapping("code" + "/{personalCode}")
//    public ResponseEntity getEvent(@PathVariable String personalCode) {
//        String result = mailService.getEmailCheckUrl(personalCode);
//        return new ResponseEntity(result, HttpStatus.OK);
//    }
}
