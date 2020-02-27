package tklimczak.feed.with.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tklimczak.feed.with.url.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> { }
