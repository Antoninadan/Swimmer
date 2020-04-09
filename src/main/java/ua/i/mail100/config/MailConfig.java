package ua.i.mail100.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    public final String username = "antonina.danilova2@gmail.com";
    public final String password = "ujuoznasghtqlqlk";
    public final String from = "antonina.danilova2@gmail.com";

    public final String smtpAuth = "true";
    public final String smtpStarttlsEnable = "true";
    public final String smtpHost = "smtp.gmail.com";
    public final String smtpPort = "587";

    public final Integer durationCodeInSec = 60;
    public final Integer numberCodeDigits = 5;

    public final String subjectMailWithCode = "Get SWIMMER code";
    public final String textMailWithCode = "Your code is: ";



}
