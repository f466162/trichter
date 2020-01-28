package de.fgorsler.trichter.syslog;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class SyslogMessageHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        System.out.println(message);
    }
}
