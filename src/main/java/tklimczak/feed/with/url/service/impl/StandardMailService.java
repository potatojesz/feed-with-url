package tklimczak.feed.with.url.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tklimczak.feed.with.url.model.Email;
import tklimczak.feed.with.url.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class StandardMailService implements MailService {
    @Autowired
    private transient JavaMailSender javaMailSender;

    @Override
    public void send(Email email, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email.getEmail());
        mimeMessageHelper.setFrom(new InternetAddress("t_klimczak@interia.pl"));
        mimeMessageHelper.setSubject("RSS FEED");
        mimeMessageHelper.setText(text, false);
        javaMailSender.send(mimeMessage);
    }
}
