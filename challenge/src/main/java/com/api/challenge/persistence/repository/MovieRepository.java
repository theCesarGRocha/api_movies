package com.api.challenge.persistence.repository;

import com.api.challenge.persistence.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByOrderByVoteDesc();
    List<Movie> findAllByReleaseYear(Integer releaseYear);
    List<Movie> findAllByOrderByReleaseYearAsc();

    Optional<Movie> findById(Long Id);

//    @Query("select m.id, m.name, m.releaseYear, m.vote, m.details, m.genre from Movie m group by m.releaseYear order by m.id, m.releaseYear DESC")
//    List<Movie> findAllGroupByReleaseYear();

}
