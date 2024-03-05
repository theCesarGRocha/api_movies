package com.api.challenge.service;

import com.api.challenge.persistence.entity.Movie;
import com.api.challenge.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;
    private final String link = "www.fake-netflix.com/";
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll(){
        return this.movieRepository.findAllByOrderByVoteDesc();
    }

//    public List<Movie> findMoviesOrderByVote(){
//        return this.movieRepository.
//    }

    public List<Movie> findMoviesByReleaseYear(Integer releaseYear){
        List<Movie> movies = this.movieRepository.findAllByReleaseYear(releaseYear);
        movies.forEach(movie -> movie.setUrl(link + movie.getName()));
        return movies;
    }
    public List<Movie> findAllMoviesOrderByReleaseYear(){
        return this.movieRepository.findAllByOrderByReleaseYearAsc();
    }
    public String detailsOnMovie(Long id){
        Optional<Movie> movie;
        String details = "";
        try {
            movie = this.movieRepository.findById(id);
            details = movie.get().getDetails();
        }catch(NotFoundException e){
            details = null;
        }
        return details;
    }
    public Optional<Movie> updateVoteById(Long Id, Integer vote){
        Optional<Movie> movie;
        try {
             movie = this.movieRepository.findById(Id);
            if(movie.isPresent()){
                Movie movieReal = movie.get();
                Integer votes = movieReal.getVote();
                votes = votes + vote;
                movieReal.setVote(votes);
                movieRepository.save(movieReal);
                movie = Optional.of(movieReal);

            }
        }catch(NotFoundException e){
            movie = Optional.of(null);
        }
        return movie;
    }
}
