package com.switchfully.codecoachmessagesender;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {


    public final static String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public final static String AUTH_TOKEN = System.getenv("AUTH_TOKEN");



    public void sendSMS(String rabbitMessage) {


       String phoneNumber = rabbitMessage.substring(7).split(" ")[0];
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        System.getenv("MESSAGE_SID"),
                        rabbitMessage.substring(rabbitMessage.indexOf(" ") + 1))
                .create();

        System.out.println(rabbitMessage.substring(rabbitMessage.indexOf(" ") + 1));
        System.out.println(message.getSid());
        System.out.println(phoneNumber);
    }



}
