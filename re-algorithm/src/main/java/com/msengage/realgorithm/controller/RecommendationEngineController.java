package com.msengage.realgorithm.controller;

import com.msengage.realgorithm.model.User;
import com.msengage.realgorithm.service.MovieService;
import com.msengage.realgorithm.service.SortingService;
import com.msengage.realgorithm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RecommendationEngineController
{
    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    SortingService sortingService;

    @GetMapping("/recommend-movie/{customerId}")
    public List<String> getMovieRecommendation(@PathVariable("customerId") String customerId)
    {
        List<String> recommendedMovies = null;
        recommendedMovies = getMovieRecommendationById(customerId);
        return recommendedMovies;
    }

    private List<String> getMovieRecommendationById(String customerId)
    {
        List<String> recommendedMovies = null;
        User user = userService.getUserById(customerId);
        recommendedMovies = movieService.getMovieRecommendation(user);
        recommendedMovies = sortingService.sortStringArray(recommendedMovies);
        return recommendedMovies;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void setSortingService(SortingService sortingService) {
        this.sortingService = sortingService;
    }
}
