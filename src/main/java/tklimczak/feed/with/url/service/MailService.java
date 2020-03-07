package tklimczak.feed.with.url.service;

import org.springframework.stereotype.Service;
import tklimczak.feed.with.url.model.Email;

import javax.mail.MessagingException;

public interface MailService {
    void send(Email email, String text) throws MessagingException;
}
