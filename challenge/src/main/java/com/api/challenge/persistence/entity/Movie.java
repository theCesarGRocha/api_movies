package com.api.challenge.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer releaseYear;
    private Integer vote;
    private String details;
    private String genre;
    @Transient
    private String url;

    public Movie() {
    }
}
