/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.services.ListingService;

/**
 *
 * @author Thomas
 */
@Controller
public class DashboardController {
    
    @Autowired
    ListingService listingService;
    
    @GetMapping("/dashboard")
    public String displayShoes(Model model) {
        
        //List<Listing> userPosts = listingService.getAllListingsForUser();

        
        return "dashboard";
    }
}
