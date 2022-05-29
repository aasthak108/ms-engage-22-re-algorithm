package com.msengage.realgorithm;

import com.msengage.realgorithm.controller.RecommendationEngineController;
import com.msengage.realgorithm.service.MovieService;
import com.msengage.realgorithm.service.SortingService;
import com.msengage.realgorithm.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReAlgorithmApplicationTests
{
	@Test
	public void testGetMovieRecommendation()
	{
		String[] expectedResult =
				new String[]{"ant man", "avenger", "captain america", "captain marvel",
						"dr strange", "hulk"};
		RecommendationEngineController testController = new RecommendationEngineController();
		testController.setUserService(new UserService());
		testController.setMovieService(new MovieService());
		testController.setSortingService(new SortingService());
		List<String> actualResult = testController.getMovieRecommendation("01");
		Assertions.assertArrayEquals(expectedResult, actualResult.toArray());
	}
}
