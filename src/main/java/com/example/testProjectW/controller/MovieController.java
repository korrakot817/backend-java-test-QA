package com.example.testProjectW.controller;

import com.example.testProjectW.entity.Movie;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.exception.ValidateException;
import com.example.testProjectW.model.movie.MovieRequestDto;
import com.example.testProjectW.model.movie.MovieResponseDto;
import com.example.testProjectW.service.MovieService;
import com.example.testProjectW.validate.MovieValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieValidate movieValidate;

    @GetMapping("/listAll")
    public List<Movie> listAll() throws NotFoundException {
        List<Movie> movies = movieService.listAll();

        return movies;
    }

    @GetMapping("/getMovie/{accntMovieNo}")
    public Movie getMovieByAccnt(String movieAccntNo) throws NotFoundException {
        Movie movieByAccntMovieNo = movieService.getMovieByAccntMovieNo(movieAccntNo);

        return movieByAccntMovieNo;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<MovieResponseDto> createMovie (@RequestBody MovieRequestDto requestDto) throws Exception {

        movieValidate.validateMoive(requestDto);
        Movie movie = movieService.createMovie(requestDto);

        MovieResponseDto response = movieService.composeResponse(movie);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{movieAccntNo}")
    public ResponseEntity<MovieResponseDto> updateMovie(@RequestBody MovieRequestDto request ,@PathVariable String movieAccntNo) throws ValidateException, NotFoundException {
        Movie movie = movieValidate.validateUpdate(request, movieAccntNo);
        Movie movieUpdated = movieService.updateMovie(request, movie);

        MovieResponseDto response = movieService.composeResponse(movieUpdated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value =  "/delete/{movieAccntNo}")
    public void deleeMovieByMovieAccntNo(@PathVariable String movieAccntNo, @RequestBody MovieRequestDto request) throws ValidateException {
        movieValidate.validateDeleteMovie(request);
        movieService.deleteByMovieAccntNo(movieAccntNo, request);
    }

}
