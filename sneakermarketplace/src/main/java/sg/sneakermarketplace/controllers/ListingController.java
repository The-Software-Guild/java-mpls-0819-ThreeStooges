/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sg.sneakermarketplace.models.Brand;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.PrimaryColor;
import sg.sneakermarketplace.models.SecondaryColor;
import sg.sneakermarketplace.models.Size;
import sg.sneakermarketplace.models.Type;
import sg.sneakermarketplace.services.ListingService;

/**
 *
 * @author mac
 */
@Controller
public class ListingController {

    @Autowired
    ListingService listingService;

    @GetMapping("/listing/{id}")
    public String displayListing(@PathVariable Integer id, Model model) {
        Listing toDisplay = listingService.getListingById(id);
                
        model.addAttribute("listing", toDisplay);
        
        return "SpecificShoe";
    }

}
