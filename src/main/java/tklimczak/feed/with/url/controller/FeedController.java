package tklimczak.feed.with.url.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tklimczak.feed.with.url.model.Email;
import tklimczak.feed.with.url.model.Url;
import tklimczak.feed.with.url.model.exception.ResourceNotFoundException;
import tklimczak.feed.with.url.model.payload.FeedPayload;
import tklimczak.feed.with.url.repository.EmailRepository;
import tklimczak.feed.with.url.repository.UrlRepository;
import tklimczak.feed.with.url.mail.MailService;
import tklimczak.feed.with.url.service.FeedWithUrlService;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Feed to email")
@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private transient EmailRepository emailRepository;
    @Autowired
    private transient UrlRepository urlRepository;
    @Autowired
    private transient FeedWithUrlService service;

    @CrossOrigin
    @PostMapping("/")
    ResponseEntity<String> feed(@RequestBody FeedPayload payload) throws InterruptedException {
        Optional<Email> email = emailRepository.findByEmail(payload.getEmail());
        Email entity = email.map(value -> update(value, payload)).orElseGet(() -> save(payload));
        Thread.sleep(100); //Added some sleep so JMeter graphs looks nicer :)
        return ResponseEntity.ok(entity.toString());
    }

    private Email save(FeedPayload payload) {
        final Email entity = new Email(payload.getEmail());
        entity.setUrls(payload.getUrls().stream().map(url -> new Url(url, entity)).collect(Collectors.toSet()));
        return emailRepository.save(entity);
    }

    private Email update(Email email, FeedPayload payload) {
        email.getUrls().addAll(payload.getUrls().stream().map(url -> new Url(url, email)).collect(Collectors.toSet()));
        return emailRepository.save(email);
    }

    @CrossOrigin
    @GetMapping("/send")
    ResponseEntity<String> sendFeed(@RequestParam String emailAddress) throws MessagingException {
        Optional<Email> email = emailRepository.findByEmail(emailAddress);
        if(email.isPresent()) {
            service.send(email.get());
            return ResponseEntity.ok("Sended RSS Feed to: " + emailAddress);
        } else {
            throw new ResourceNotFoundException("Email " + emailAddress + " not found. Unable to send feed.");
        }
    }
}
