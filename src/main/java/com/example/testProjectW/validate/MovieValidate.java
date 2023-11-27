package com.example.testProjectW.validate;

import com.example.testProjectW.Repository.MovieRepository;
import com.example.testProjectW.Repository.UserRepository;
import com.example.testProjectW.entity.Movie;
import com.example.testProjectW.entity.User;
import com.example.testProjectW.exception.ErrorDetail;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.exception.ValidateException;
import com.example.testProjectW.model.movie.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieValidate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    public void validateMoive(MovieRequestDto request) throws ValidateException {
        List<ErrorDetail> errorDetails = new ArrayList<>();

        if (Objects.isNull(request.getAccntNo())) {
            errorDetails.add(new ErrorDetail("accntNo", "Parameter not found"));
        }

        if (Objects.isNull(request.getMovieTitle())) {
            errorDetails.add(new ErrorDetail("movieTitle", "Parameter not found"));
        }

        if (Objects.isNull(request.getYearReleased())) {
            errorDetails.add(new ErrorDetail("yearReleased", "Parameter not found"));
        }

        if (Objects.nonNull(request.getRating())) {
            if (!Arrays.asList(new String[] {"G" ,"PG", "M", "MA", "R"}).contains(request.getRating())) {
                throw new ValidateException("rating error");
            }

        }else {
            errorDetails.add(new ErrorDetail("rating", "Parameter not found"));
        }

        if (Objects.isNull(request.getRelateParty())) {
            errorDetails.add(new ErrorDetail("relateParty", "Parameter not found"));
        }else {

            if (Objects.isNull(request.getRelateParty().getOrderCreator())) {
                errorDetails.add(new ErrorDetail("relateParty.orderCreator", "Parameter not found"));

            }else {
                if (Objects.isNull(request.getRelateParty().getOrderCreator().getName())) {
                    errorDetails.add(new ErrorDetail("relateParty.orderCreator.name", "Parameter not found"));
                }
            }
        }

        if (errorDetails.size() > 0) {
            throw new ValidateException("Missing or invalid parameter", errorDetails);
        }

        List<Movie> byMovieTitle = movieRepository.findAllByMovieTitle(request.getMovieTitle());
        if (!byMovieTitle.isEmpty()) {
            throw new ValidateException("can not create movie : movie duplicate");
        }

        Optional<User> byAccntNo = userRepository.findByAccntNo(request.getAccntNo());
        if (byAccntNo.isPresent()) {
            User user = byAccntNo.get();

            if (!Arrays.asList(new String[]{"manager", "leader", "floorStaff"}).contains(user.getRole())) {
                throw new ValidateException("can not create movie : " + user.getRole() + "not supported");
            }

        }else {
            throw new ValidateException("can not create movie : user not found");
        }

    }

    public Movie validateUpdate(MovieRequestDto request, String movieAccntNo) throws ValidateException, NotFoundException {
        List<ErrorDetail> errorDetails = new ArrayList<>();

        if (Objects.isNull(request.getRelateParty())) {
            errorDetails.add(new ErrorDetail("relateParty", "Parameter not found"));
        }else {

            if (Objects.isNull(request.getRelateParty().getOrderCreator())) {
                errorDetails.add(new ErrorDetail("relateParty.orderCreator", "Parameter not found"));

            }else {
                if (Objects.isNull(request.getRelateParty().getOrderCreator().getName())) {
                    errorDetails.add(new ErrorDetail("relateParty.orderCreator.name", "Parameter not found"));
                }
            }
        }

        if (errorDetails.size() > 0) {
            throw new ValidateException("Missing or invalid parameter", errorDetails);
        }

        Optional<User> byAccntNo = userRepository.findByAccntNo(request.getAccntNo());
        if (byAccntNo.isPresent()) {
            User user = byAccntNo.get();

            if (!Arrays.asList(new String[]{"manager", "leader", "floorStaff"}).contains(user.getRole())) {
                throw new ValidateException("can not update movie : " + user.getRole() + "not supported");
            }

        }else {
            throw new ValidateException("can not update movie : user not found ");
        }

        Optional<Movie> byAccntMovieNo = movieRepository.findByMovieAccntNo(movieAccntNo);
        if (byAccntMovieNo.isEmpty()) {
            throw new NotFoundException();
        }

        Movie movie = byAccntMovieNo.get();

        return movie;
    }

    public void validateDeleteMovie(MovieRequestDto request) throws ValidateException {
        List<ErrorDetail> errorDetails = new ArrayList<>();
        if (Objects.isNull(request.getAccntNo())) {
            errorDetails.add(new ErrorDetail("accntNo", "Parameter not found"));
        }

        if (errorDetails.size() > 0) {
            throw new ValidateException("Missing or invalid parameter", errorDetails);
        }

        Optional<User> byAccntNo = userRepository.findByAccntNo(request.getAccntNo());
        if (byAccntNo.isEmpty()) {
            throw new ValidateException("user not found");

        }else {
            User user = byAccntNo.get();
            if (!user.getRole().equals("manager")) {
                throw new ValidateException("role : " + user.getRole() + "not supported");
            }
        }
    }
}
