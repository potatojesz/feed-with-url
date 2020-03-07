package tklimczak.feed.with.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tklimczak.feed.with.url.model.Email;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
    Optional<Email> findByEmail(String email);
}
