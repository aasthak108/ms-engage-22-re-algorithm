package com.msengage.realgorithm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msengage.realgorithm.model.Movie;
import com.msengage.realgorithm.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MovieService
{
    public List<String> getMovieRecommendation(User user)
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
                /*
                Core Of Algorithm:
                Matching movie's feature with user watchlist feature to recommend
                Only adding movie which is not in watchlist and recommendation list
                */
                if(movie.getProperties().contains(feature)
                        && !moviesWatched.contains(movie.getName())
                        && !recommendedMovies.contains(movie.getName()))
                {
                    recommendedMovies.add(movie.getName());
                }
            }
        }
        return recommendedMovies;
    }

    private List<Movie> getMoviesFromDataSet()
    {
        List<Movie> movieList = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            File movieDataJSON = new ClassPathResource(
                    "/dataset/data-movie.json").getFile();
            movieList = Arrays.asList(mapper.readValue(movieDataJSON, Movie[].class));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return movieList;
    }
}
