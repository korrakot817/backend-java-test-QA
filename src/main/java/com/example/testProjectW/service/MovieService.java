package com.example.testProjectW.service;

import com.example.testProjectW.Repository.MovieRepository;
import com.example.testProjectW.entity.Movie;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.model.movie.MovieRequestDto;
import com.example.testProjectW.model.movie.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    private static int lastMovieAccountNumber = 0;

    public List<Movie> listAll() throws NotFoundException {
        List<Movie> all = movieRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException();
        }

        return all;
    }

    public Movie getMovieByAccntMovieNo(String movieAccntNo) throws NotFoundException {
        Optional<Movie> byAccntMovieNo = movieRepository.findByMovieAccntNo(movieAccntNo);
        if (byAccntMovieNo.isEmpty()) {
            throw new NotFoundException();
        }

        return byAccntMovieNo.get();
    }
    public Movie createMovie(MovieRequestDto movieRequestDto) {
        Movie movies = new Movie();

        movies.setRowId(UUID.randomUUID().toString());
        movies.setMovieAccntNo(this.generateMovieAccountNumber());
        movies.setCreated(Calendar.getInstance().getTime());
        movies.setCreateBy(movieRequestDto.getRelateParty().getOrderCreator().getName());
        movies.setLastUpd(Calendar.getInstance().getTime());
        movies.setLastUpdBy(movieRequestDto.getRelateParty().getOrderCreator().getName());
        movies.setMovieTitle(movieRequestDto.getMovieTitle());
        movies.setYearReleased(movieRequestDto.getYearReleased());
        movies.setRating(movieRequestDto.getRating());

        if (Objects.nonNull(movieRequestDto.getDescription())) {
            movies.setDescription(movieRequestDto.getDescription());
        }

        return movieRepository.save(movies);
    }

    public Movie updateMovie (MovieRequestDto movieRequestDto, Movie oldMovie) {
        Movie movies = oldMovie;

        movies.setLastUpd(Calendar.getInstance().getTime());
        movies.setLastUpdBy(movieRequestDto.getRelateParty().getOrderCreator().getName());

        if (Objects.nonNull(movieRequestDto.getMovieTitle())) {
            movies.setMovieTitle(movieRequestDto.getMovieTitle());
        }

        if (Objects.nonNull(movieRequestDto.getYearReleased())) {
            movies.setYearReleased(movieRequestDto.getYearReleased());
        }

        if (Objects.nonNull(movieRequestDto.getRating())) {
            movies.setRating(movieRequestDto.getRating());
        }

        if (Objects.nonNull(movieRequestDto.getDescription())) {
            movies.setDescription(movieRequestDto.getDescription());
        }

        return movieRepository.save(movies);
    }

    public Movie getMovieByMovieTitle(String movieTitle) throws Exception {

        Optional<Movie> byMovieTitle = movieRepository.findByMovieTitle(movieTitle);
        if (!byMovieTitle.isPresent()) {
            throw new Exception("movie not found");
        }

        return byMovieTitle.get();
    }

    public void deleteByMovieAccntNo(String movieAccntNo, MovieRequestDto request) {
        movieRepository.deleteByMovieAccntNo(movieAccntNo);

    }

    public MovieResponseDto composeResponse(Movie movie) {
        MovieResponseDto response = new MovieResponseDto();

        response.setMovieAccntNo(movie.getMovieAccntNo());
        response.setMovieTitle(movie.getMovieTitle());
        response.setRating(movie.getRating());

        return response;
    }
    public String generateMovieAccountNumber() {
        // ตรวจสอบว่าเลขที่บัญชีซ้ำกันหรือไม่
        String accntMoviveNo;
        do {
            // เพิ่มค่าล่าสุดของเลขที่บัญชี
            lastMovieAccountNumber++;

            // สร้างเลขที่บัญชีในรูปแบบ "10000XX"
            accntMoviveNo = "20000" + String.format("%02d", lastMovieAccountNumber);
        } while (movieRepository.existsByMovieAccntNo(accntMoviveNo));

        return accntMoviveNo;
    }

}
