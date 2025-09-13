package ir.dc.userAuthenticator.repository;

import ir.dc.userAuthenticator.dto.MovieStatus;
import ir.dc.userAuthenticator.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Page<Movie> findAllByStatusIs(MovieStatus status, Pageable pageable);
}
