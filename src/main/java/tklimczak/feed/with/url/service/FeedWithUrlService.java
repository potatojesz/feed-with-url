package tklimczak.feed.with.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tklimczak.feed.with.url.mail.MailService;
import tklimczak.feed.with.url.model.Email;
import tklimczak.feed.with.url.model.Url;
import tklimczak.feed.with.url.rss.model.Feed;
import tklimczak.feed.with.url.rss.model.FeedMessage;
import tklimczak.feed.with.url.rss.reader.FeedParser;

import javax.mail.MessagingException;

@Service
public class FeedWithUrlService {
    @Autowired
    private transient MailService mailService;
    @Autowired
    private transient FeedParser parser;

    public void send(Email email) throws MessagingException {
        final StringBuilder emailText = new StringBuilder();
        email.getUrls().stream()
                .map(Url::getUrl)
                .map(url -> parser.readFeed(url))
                .map(Feed::getMessages).forEach(emailText::append);

        mailService.send(email, emailText.toString());
    }
}
