/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

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
import sg.sneakermarketplace.models.ShoeCondition;
import sg.sneakermarketplace.models.Size;
import sg.sneakermarketplace.models.Status;
import sg.sneakermarketplace.models.Type;
import sg.sneakermarketplace.services.BrandService;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PrimaryColorService;
import sg.sneakermarketplace.services.ShoeConditionService;
import sg.sneakermarketplace.services.SizeService;
import sg.sneakermarketplace.services.TypeService;

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
    
    @Autowired
    SizeService sizeService;
    
    @Autowired
    TypeService typeService;
    
    @Autowired
    ShoeConditionService shoeConditionService;
    
    @GetMapping({"/", "/home"})
    public String displayShoes(@RequestParam(required = false) Map<String,String> allParams, Model model) {
        List<Listing> shoes = listingService.getAllActiveListings(allParams);
        List<Brand> brands = brandService.getAllBrands();
        List<Size> sizes = sizeService.getAllSizes();
        List<PrimaryColor> colors = primaryColorService.getAllPrimaryColors();
        List<Type> types = typeService.getAllTypes();
        List<ShoeCondition> shoeConditions = shoeConditionService.getAllShoeConditions();
        model.addAttribute("shoes", shoes);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        model.addAttribute("types", types);
        model.addAttribute("shoeConditions", shoeConditions);
        model.addAttribute("params", allParams);
        return "home";
    }
    
    
}
