package com.poseidon.controllers;

import com.poseidon.domain.Rating;
import com.poseidon.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RatingController {
    // TODO: Inject Rating service

    private final RatingService ratingService;

    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> allRatings = this.ratingService.findAll();
        model.addAttribute("ratings", allRatings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(@ModelAttribute("ratings") Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "rating/add";
        }
        this.ratingService.addRating(rating);
        // TODO: check data valid and save to db, after saving return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        model.addAttribute("ratings", this.ratingService.findById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        this.ratingService.updateRating(id, rating);
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        this.ratingService.deleteRating(this.ratingService.findById(id));
        return "redirect:/rating/list";
    }
}
