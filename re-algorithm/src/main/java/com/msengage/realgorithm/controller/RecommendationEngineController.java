package com.msengage.realgorithm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendationEngineController
{
    @GetMapping("/recommend-movie/{customerId}")
    private String getMovieRecommendation(@PathVariable("customerId") int customerId){
        String response = "Not Found";
        return response;
    }
}
