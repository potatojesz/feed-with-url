package tklimczak.feed.with.url.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "emails")
public class Email {
    public static final String ENTITY = "Url";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String email;

    public Email() {
    }

    public Email(@NotBlank String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return email;
    }
    public void setUrl(String email) {
        this.email = email;
    }
}
