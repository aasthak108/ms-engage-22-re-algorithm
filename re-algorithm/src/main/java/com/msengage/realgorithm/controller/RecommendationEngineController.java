package com.msengage.realgorithm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msengage.realgorithm.model.Movie;
import com.msengage.realgorithm.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationEngineController
{
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
        List<Movie> movieList = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            File movieDataJSON = new ClassPathResource(
                    "/dataset/data-movie.json").getFile();
            movieList = mapper.readValue(movieDataJSON, ArrayList.class);
            User user = getUserById(customerId);
            recommendedMovies = getMovieRecommendation(movieList, user);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return recommendedMovies;
    }

    private List<String> getMovieRecommendation(List<Movie> movieList, User user)
    {
        List<String> recommendedMovies = null;
        List<String> featuresLiked = new ArrayList<>();
        for(Movie movie :  user.getWatchlist())
        {
            featuresLiked.addAll(movie.getProperties());
        }
        for(String feature : featuresLiked)
        {
            for(Movie movie :  user.getWatchlist())
            {
                if(movie.getProperties().contains(feature))
                {
                    recommendedMovies.add(movie.getName());
                }
            }
        }
        return recommendedMovies;
    }

    private User getUserById(String id)
    {
        User selectedUser = null;
        ObjectMapper mapper = new ObjectMapper();
        File userData = null;
        List<User> userList = null;
        try
        {
            userData = new ClassPathResource("/dataset/data-user.json").getFile();
            userList = mapper.readValue(userData, ArrayList.class);
            for(User user : userList)
            {
                if(user.getUserId().equalsIgnoreCase(id))
                {
                    selectedUser = user;
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return selectedUser;
    }
}
