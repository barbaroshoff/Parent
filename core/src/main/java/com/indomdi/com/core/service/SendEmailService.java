package com.indomdi.com.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indomdi.com.core.config.JmsConfig;
import lombok.NonNull;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.indomdi.com.core.persistent.ForgottenUserPassword;
import com.indomdi.com.core.persistent.RegisterUser;


import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SendEmailService extends BaseServiceImpl  {


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private JmsTemplate jmsTemplate;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${mail.signup.sender:signup@iello.md}")
    private String signupSender;
    @Value("${mail.signup.subject:Account email verification needed")
    private String signupSubject;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Async
    public void sendSignupEmail(@NonNull @Valid RegisterUser user, String emailBody) {

        try {
            final MimeMessagePreparator preparator = mimeMessage -> {
                try {
                    mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});

                    mimeMessage.setFrom(new InternetAddress(signupSender));
                    mimeMessage.setSubject(signupSubject);
                    mimeMessage.setContent("<html><body>" + emailBody + "</body><html>", "text/html;charset=UTF-8");

                    log.info("Sent email: " + mimeMessage.getContent());
                } catch (final Exception e) {
                    log.error("Failed to build signup message for user {} and email {}: {}",
                            new Object[]{user.getUsername(), user.getEmail(), e.getMessage()}, e);
                }
            };

            emailSender.send(preparator);
        } catch (final MailException e) {
            final String message = message("Failed to send out signup email for user {} and email {}: {}", user.getUsername(), user.getEmail(), e.getMessage());
            log.error(message, e);
            sendToDlq(user, message);
        }
    }

    @Async
    public void sendFortgottenPassword(@NonNull @Valid ForgottenUserPassword user, String emailBody) {

        try {
            final MimeMessagePreparator preparator = mimeMessage -> {
                try {
                    mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});

                    mimeMessage.setFrom(new InternetAddress(signupSender));
                    mimeMessage.setSubject(signupSubject);
                    mimeMessage.setContent("<html><body>" + emailBody + "<p> please copy your secure code and reset password = '"+user.getSecureCode()+"'</p>"+"</body><html>", "text/html;charset=UTF-8");

                    log.info("Sent email: " + mimeMessage.getContent());
                } catch (final Exception e) {
                    log.error("Failed to build signup message for user {} and email {}: {}",
                            new Object[]{user.getUsername(), user.getEmail(), e.getMessage()}, e);
                }
            };

            emailSender.send(preparator);
        } catch (final MailException e) {
            final String message = message("Failed to send out signup email for user {} and email {}: {}", user.getUsername(), user.getEmail(), e.getMessage());
            log.error(message, e);
            sendToDlq(user, message);
        }
    }

    private void sendToDlq(@NonNull @Valid Object ob, String message) {
        jmsTemplate.send(JmsConfig.SIGNUP_DLQ_QUEUE, (MessageCreator) session -> {
            final ObjectMapper mapper = new ObjectMapper();
            String payload = "¯\\_(ツ)_/¯";
            try {
                payload = mapper.writeValueAsString(ob);
            } catch (final Exception e) {
                // swallow
            }

            final javax.jms.Message msg = session.createTextMessage(payload);
            msg.setJMSCorrelationID((String) MDC.get("CorrelationID"));
            msg.setStringProperty("createdBy", (String) MDC.get("CreatedBy"));
            msg.setStringProperty("timeZone", (String) MDC.get("TimeZone"));
            msg.setStringProperty("remoteAddr", (String) MDC.get("RemoteAddress"));
            msg.setStringProperty("logDate", formatter.format(ZonedDateTime.now()));
            msg.setStringProperty("description", message);

            return msg;
        });
    }
}
