package com.msengage.realgorithm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msengage.realgorithm.model.Movie;
import com.msengage.realgorithm.model.User;
import com.msengage.realgorithm.service.MovieService;
import com.msengage.realgorithm.service.SortingService;
import com.msengage.realgorithm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
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
    private List<String> getMovieRecommendation(@PathVariable("customerId") String customerId)
    {
        List<String> recommendedMovies = null;
        recommendedMovies = getMovieRecommendationFromAlgorithm(customerId);
        return recommendedMovies;
    }

    private List<String> getMovieRecommendationFromAlgorithm(String customerId)
    {
        List<String> recommendedMovies = null;
        User user = userService.getUserById(customerId);
        recommendedMovies = movieService.getMovieRecommendation(user);
        recommendedMovies = sortingService.sortStringArray(recommendedMovies);
        return recommendedMovies;
    }
}
