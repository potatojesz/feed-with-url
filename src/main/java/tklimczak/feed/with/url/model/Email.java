package tklimczak.feed.with.url.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "emails")
public class Email {
    public static final String ENTITY = "Url";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy="email", fetch = FetchType.EAGER)
    private Set<Url> urls;

    public Email() {
    }

    public Email(@NotBlank String email) {
        this.email = email;
    }

    public Email(@NotBlank String email, Set<Url> urls) {
        this.email = email;
        this.urls = urls;
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

    public Set<Url> getUrls() {
        return urls;
    }
    public void setUrls(Set<Url> urls) {
        this.urls = urls;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
