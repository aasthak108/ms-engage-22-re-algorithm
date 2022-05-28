package com.msengage.realgorithm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msengage.realgorithm.model.Movie;
import com.msengage.realgorithm.model.User;
import com.msengage.realgorithm.service.MovieService;
import com.msengage.realgorithm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationEngineController
{
    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

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
        return recommendedMovies;
    }

    /*private List<String> getMovieRecommendation(User user)
    {
        List<Movie> movieList = getMoviesFromDataSet();
        List<String> featuresLiked = new ArrayList<>();
        List<String> moviesWatched = new ArrayList<>();
        for(Movie movie :  user.getWatchlist())
        {
            featuresLiked.addAll(movie.getProperties());
            moviesWatched.add(movie.getName());
        }
        List<String> recommendedMovies = new ArrayList<>();
        for(String feature : featuresLiked)
        {
            for(Movie movie :  movieList)
            {
                if(movie.getProperties().contains(feature)
                        && !moviesWatched.contains(movie.getName())
                        && !recommendedMovies.contains(movie.getName()))
                {
                    recommendedMovies.add(movie.getName());
                }
            }
        }
        return recommendedMovies;
    }*/

    /*private User getUserById(String id)
    {
        User selectedUser = null;
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = null;
        try
        {
            File userDataJSON = new ClassPathResource("/dataset/data-user.json").getFile();
            userList = Arrays.asList(mapper.readValue(userDataJSON, User[].class));
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
    }*/
}
