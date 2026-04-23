package com.poseidon.controllers;

import com.poseidon.domain.Trade;
import com.poseidon.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TradeController {
    // TODO: Inject Trade service

    private final TradeService tradeService;

    public TradeController(TradeService tradeService){
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<Trade> allTrades = this.tradeService.findAll();
        model.addAttribute("trades", allTrades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(@ModelAttribute("trade") Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "trade/add";
        }

        this.tradeService.addTrade(trade);
        return "redirect:/trade/list";
        // TODO: check data valid and save to db, after saving return Trade list
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        model.addAttribute("trade", this.tradeService.findById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if(result.hasErrors()){
            return "/trade/add";
        }
        this.tradeService.updateTrade(id, trade);
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        this.tradeService.deleteTrade(this.tradeService.findById(id));
        return "redirect:/trade/list";
    }
}
