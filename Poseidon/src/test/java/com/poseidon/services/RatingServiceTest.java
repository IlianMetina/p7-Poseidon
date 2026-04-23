package com.poseidon.services;

import com.poseidon.domain.Rating;
import com.poseidon.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating;

    @BeforeEach
    public void setUp(){
        rating = new Rating();
        rating.setMoodysRating("Aaa");
        rating.setSandPRating("AAA");
        rating.setFitchRating("AAA");
        rating.setOrderNumber(1);
    }

    @Test
    public void findAllRatingsTest(){
        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        List<Rating> result = ratingService.findAll();
        assertEquals(1, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    public void addRatingTest(){
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.addRating(rating);
        assertNotNull(result);
        assertEquals("Aaa", result.getMoodysRating());
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void updateRatingTest(){
        Rating updated = new Rating();
        updated.setMoodysRating("Baa");
        updated.setSandPRating("BBB");
        updated.setFitchRating("BBB");
        updated.setOrderNumber(2);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.updateRating(1, updated);

        assertEquals("Baa", result.getMoodysRating());
        assertEquals("BBB", result.getSandPRating());
        assertEquals("BBB", result.getFitchRating());
        assertEquals(2, result.getOrderNumber());
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void findRatingByIdTest(){
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        Rating result = ratingService.findById(1);

        assertNotNull(result);
        assertEquals("Aaa", result.getMoodysRating());
    }

    @Test
    public void deleteRatingTest(){
        doNothing().when(ratingRepository).delete(rating);

        ratingService.deleteRating(rating);
        verify(ratingRepository, times(1)).delete(rating);
    }

}
