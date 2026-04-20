package com.poseidon.controllers;

import com.poseidon.domain.BidList;
import com.poseidon.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Controller
public class BidListController {
    // TODO: Inject Bid service

    private final BidListService bidListService;

    public BidListController(BidListService bidlistService){
        this.bidListService = bidlistService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        model.addAttribute("bids", bidListService.findAllBids());
        return "bidList/list";

    }

    @PostMapping("/bidlist/update/{id}")
    public String updateBid(@PathVariable Integer id, @Valid BidList bidList, BindingResult result, Model model){
        this.bidListService.updateBid(id, bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if(result.hasErrors()) return "bidList/add";
        bidListService.addBid(bid);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        return "bidList/update";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        return "redirect:/bidList/list";
    }
}
