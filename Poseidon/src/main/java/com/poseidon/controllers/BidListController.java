package com.poseidon.controllers;

import com.poseidon.domain.BidList;
import com.poseidon.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class BidListController {
    // TODO: Inject Bid service

    private final BidListService bidListService;

    public BidListController(BidListService bidlistService){
        this.bidListService = bidlistService;
    }

    @RequestMapping({"/bidList/list", "/bidlist/list"})
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        model.addAttribute("bids", bidListService.findAllBids());
        return "bidList/list";
    }

    @PostMapping({"/bidList/update/{id}", "/bidlist/update/{id}"})
    public String updateBid(@PathVariable Integer id, @Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model){
        this.bidListService.updateBid(id, bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(@ModelAttribute("bidList") BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if(result.hasErrors()) return "bidList/add";
        bidListService.addBid(bidList);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        BidList bid = this.bidListService.findById(id);
        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        BidList bid = this.bidListService.findById(id);
        this.bidListService.deleteBid(bid);
        return "redirect:/bidList/list";
    }
}
