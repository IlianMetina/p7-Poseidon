package com.poseidon.services;

import com.poseidon.domain.Rating;
import com.poseidon.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll(){
        return this.ratingRepository.findAll();
    }

    public Rating addRating(Rating rating){
        return this.ratingRepository.save(rating);
    }

    public Rating updateRating(Integer id, Rating rating){
        Rating ratingToUpdate = this.ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("No rating found"));
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setSandPRating(rating.getSandPRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());

        this.ratingRepository.save(ratingToUpdate);

        return ratingToUpdate;
    }

    public Rating findById(Integer id){
        return this.ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("No rating found"));
    }

    public void deleteRating(Rating rating){
        this.ratingRepository.delete(rating);
    }
}
