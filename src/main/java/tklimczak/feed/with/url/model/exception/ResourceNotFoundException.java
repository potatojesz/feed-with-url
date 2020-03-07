package tklimczak.feed.with.url.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String text;

    public ResourceNotFoundException(String text) {
        super(text);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
