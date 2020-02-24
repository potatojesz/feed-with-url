package tklimczak.feed.with.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tklimczak.feed.with.url.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> { }
