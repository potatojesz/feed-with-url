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
import tklimczak.feed.with.url.service.MailService;

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
    private transient MailService mailService;

    @CrossOrigin
    @PostMapping("/")
    ResponseEntity<Email> feed(@RequestBody FeedPayload payload) {
        Optional<Email> email = emailRepository.findByEmail(payload.getEmail());
        Email entity = email.map(value -> update(value, payload)).orElseGet(() -> save(payload));
        return ResponseEntity.ok(entity);
    }

    private Email save(FeedPayload payload) {
        Email entity = new Email(payload.getEmail(), payload.getUrls().stream().map(url -> new Url(url, payload.getEmail())).collect(Collectors.toSet()));
        entity = emailRepository.save(entity);
        urlRepository.saveAll(entity.getUrls());
        return entity;
    }

    private Email update(Email email, FeedPayload payload) {
        email.getUrls().addAll(payload.getUrls().stream().map(url -> new Url(url, payload.getEmail())).collect(Collectors.toSet()));
        urlRepository.saveAll(email.getUrls());
        return emailRepository.save(email);
    }

    @CrossOrigin
    @GetMapping("/send")
    ResponseEntity<String> sendFeed(@RequestParam String emailAddress) throws MessagingException {
        Optional<Email> email = emailRepository.findByEmail(emailAddress);
        if(email.isPresent()) {
            mailService.send(email.get(), email.get().getUrls().stream().map(Url::getUrl).collect(Collectors.joining(System.lineSeparator())));
            return ResponseEntity.ok("Sended RSS Feed to: " + emailAddress);
        } else {
            throw new ResourceNotFoundException("Email " + emailAddress + " not found. Unable to send feed.");
        }
    }
}
