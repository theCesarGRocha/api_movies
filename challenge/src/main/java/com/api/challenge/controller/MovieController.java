package com.api.challenge.controller;

import com.api.challenge.persistence.entity.Movie;
import com.api.challenge.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /*
     * EndPoint to get all movies
     * */
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> movies(){
        try{
            List<Movie> movies = this.movieService.findAll();
            if (movies.size() > 0 ) {
                return ResponseEntity.ok(movies);
            } else {
                return ResponseEntity.ok(new ArrayList<>());
            }
        }catch(Exception e){
            System.out.println("****** Error Message " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /*
    * EndPoint to get all movies order by year
    * */

    @GetMapping("/movies/orderByYear")
    public ResponseEntity<List<Movie>> moviesOrderByReleaseYear() {
        try {
            List<Movie> movies = this.movieService.findAllMoviesOrderByReleaseYear();
            if (movies.size() > 0) {
                return ResponseEntity.ok(movies);
            } else {
                return ResponseEntity.ok(new ArrayList<>());
            }
        }catch(Exception e){
            System.out.println("****** Error Message " + e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    /*
     * EndPoint to get all movies in a year of Release
     * */
    @GetMapping("/movies/releaseYear/{year}")
    public ResponseEntity<List<Movie>> moviesByReleaseYear(@PathVariable("year") Integer year) throws SQLException{

        try{
           List<Movie> movies = this.movieService.findMoviesByReleaseYear(year);
           if (movies.size() > 0 ) {
               return ResponseEntity.ok(movies);
           } else {
               return ResponseEntity.ok(new ArrayList<>());
           }

       }catch(Exception e){
            System.out.println("****** Error Message " + e.getMessage());
           return ResponseEntity.notFound().build();
       }


    }

    /*
     * EndPoint to update votes in a movie
     * waiting for id of movie and vote +1 or -1
     * */
    @PostMapping("/movie/{id}/vote/{vote}")
    public ResponseEntity<Movie> updateVotesByIdMovie(@PathVariable("id") Long id, @PathVariable("vote") Integer vote){

        try{
            Optional<Movie> movie = this.movieService.updateVoteById(id, vote);
            return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        catch(HttpClientErrorException.NotFound e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/details/{id}/")
    public ResponseEntity<String> getDetailsByIdMovie(@PathVariable("id") Long id){

        try{
            String details = this.movieService.detailsOnMovie(id);
            if (details != null ) {
                return ResponseEntity.ok(details);
            } else {
                return ResponseEntity.ok("No details");
            }
        }
        catch(HttpClientErrorException.NotFound e){
            return ResponseEntity.notFound().build();
        }
    }

}
