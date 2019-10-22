/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.sneakermarketplace.models.Brand;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.PrimaryColor;
import sg.sneakermarketplace.services.BrandService;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PrimaryColorService;

/**
 *
 * @author Thomas
 */
@Controller
public class BrowseController {
    
    @Autowired
    ListingService listingService;
    
    @Autowired
    BrandService brandService;
    
    @Autowired
    PrimaryColorService primaryColorService;
    
    @GetMapping("/browse")
    public String displayShoes(@RequestParam(required = false) Map<String,String> allParams, Model model) {
        List<Listing> shoes = listingService.getAllListings(allParams);
        List<Brand> brands = brandService.getAllBrands();
        
        List<PrimaryColor> colors = primaryColorService.getAllPrimaryColors();
        model.addAttribute("shoes", shoes);
        model.addAttribute("brands", brands);
        //model.addAttribute("colors", colors);
        model.addAttribute("params", allParams);
        return "browse";
    }
    
    
}
