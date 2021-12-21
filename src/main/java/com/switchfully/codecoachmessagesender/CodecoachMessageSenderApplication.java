package com.switchfully.codecoachmessagesender;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodecoachMessageSenderApplication {

    private TwilioService twilioService;

    public CodecoachMessageSenderApplication(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodecoachMessageSenderApplication.class, args);
    }

    @RabbitListener(queues = {"messagesForCoaches", "messagesForCoachees"})
    public void listen(String in) {
        twilioService.sendSMS(in);
        //System.out.println(in);

    }
}
