package tklimczak.feed.with.url.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tklimczak.feed.with.url.model.Url;
import tklimczak.feed.with.url.model.payload.UrlPayload;
import tklimczak.feed.with.url.repository.UrlRepository;

@Api(value="Feed with URL")
@RestController
@RequestMapping("/url")
public class UrlController {

	@Autowired
	private transient UrlRepository urlRepository;

	@CrossOrigin
	@PostMapping("/")
	ResponseEntity<Url> saveUrl(@RequestBody UrlPayload url) {
		Url entity = urlRepository.save(new Url(url.getUrl()));
		return ResponseEntity.ok(entity);
	}
}
