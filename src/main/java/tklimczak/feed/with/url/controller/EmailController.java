package tklimczak.feed.with.url.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tklimczak.feed.with.url.model.Email;
import tklimczak.feed.with.url.model.payload.EmailPayload;
import tklimczak.feed.with.url.repository.EmailRepository;

@Api(value="Add email to feed")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private transient EmailRepository emailRepository;

    @CrossOrigin
    @PostMapping("/")
    ResponseEntity<Email> saveEmail(@RequestBody EmailPayload email) {
        Email entity = emailRepository.save(new Email(email.getEmail()));
        return ResponseEntity.ok(entity);
    }
}
