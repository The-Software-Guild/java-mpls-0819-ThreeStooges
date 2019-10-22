/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Brand;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.PrimaryColor;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SecondaryColor;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.models.Size;
import sg.sneakermarketplace.models.Type;
import sg.sneakermarketplace.services.BidService;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PurchaseService;

/**
 *
 * @author mac
 */
@Controller
public class ListingController {

    @Autowired
    ListingService listingService;
    
    @Autowired
    BidService bidService;
    
    @Autowired
    PurchaseService pService;
    
    @Autowired
    UserDao uDao;

    @GetMapping("/listing/{id}")
    public String displayListing(@PathVariable Integer id, Model model) {
        Listing toDisplay = listingService.getListingById(id);
                
        model.addAttribute("listing", toDisplay);
        
        return "SpecificShoe";
    }
    
    @PostMapping("addBid")
    public String addBid(HttpServletRequest request) {
        BigDecimal bid = new BigDecimal(request.getParameter("bidEntered"));
        int listingId = Integer.parseInt(request.getParameter("listingid"));
        
        Listing toAdd = listingService.getListingById(listingId);
        
        Bid newBid = new Bid();
        newBid.setBidPrice(bid);
        LocalDate now = LocalDate.now();
        newBid.setDate(now);
        newBid.setListing(toAdd);
        
        return "redirect:/listing/{id}";
    }
    
    @PostMapping("buyNow")
    public String addPurchae(HttpServletRequest request, Principal buyer) {
        int listingId = Integer.parseInt(request.getParameter("listing"));
        Listing toAdd = listingService.getListingById(listingId);
        
        int sellerId = Integer.parseInt(request.getParameter("seller"));
        SiteUser seller = uDao.getUserById(sellerId);
        
        LocalDate now = LocalDate.now();
        
        SiteUser user = uDao.getUserByUsername(buyer.getName());
        
        BigDecimal salePrice = new BigDecimal(request.getParameter("buynowprice"));
        
        Purchase newP = new Purchase();
        newP.setListing(toAdd);
        newP.setSeller(seller);
        newP.setDateSold(now);
        newP.setSalePrice(salePrice);
        newP.setBuyer(user);
        
        return "redirect:/listing/{id}";
    }

}
