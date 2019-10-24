/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PurchaseService;

/**
 *
 * @author Thomas
 */
@Controller
public class DashboardController {
    
    @Autowired
    ListingService listingService;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    UserDao userDao;
    
    @GetMapping("/dashboard")
    public String displayShoes(Model model, Principal pUser) {
        
        SiteUser user = userDao.getUserByUsername(pUser.getName());
        List<Listing> userPosts = listingService.getListingsForUser(user);
        List<Purchase> userPurchases = purchaseService.getPurchasesForBuyer(user);
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("userPurchases", userPurchases);
        return "dashboard";
    }
}