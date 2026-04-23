package com.poseidon.controllers;

import com.poseidon.domain.BidList;
import com.poseidon.domain.CurvePoint;
import com.poseidon.services.CurveService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CurveController {
    // TODO: Inject Curve Point service

    private final CurveService curveService;

    public CurveController(CurveService curveService){
        this.curveService = curveService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        List<CurvePoint> allCurvePoints = this.curveService.findAllCurvePoints();
        model.addAttribute("curvePoint", allCurvePoints);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(@ModelAttribute("curvePoint") CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if(result.hasErrors()){
            return "curvePoint/add";
        }
        this.curveService.addCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        model.addAttribute("curvePoint", this.curveService.findById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        this.curveService.updateCurvePoint(id, curvePoint);
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        System.out.println("Entrée delete route");
        CurvePoint curvePoint = this.curveService.findById(id);
        this.curveService.deleteCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }
}