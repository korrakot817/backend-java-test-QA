package com.example.testProjectW.Repository;

import com.example.testProjectW.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {

    Optional<Movie> findByMovieTitle(String movieTitle);

    List<Movie> findAllByMovieTitle(String movieTitle);

    Optional<Movie> findByMovieAccntNo(String movieAccntNo);

    boolean existsByMovieAccntNo(String movieAccntNo);

    void deleteByMovieTitle(String movieTitle);

    void deleteByMovieAccntNo(String movieAccntNo);
}
