/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.services.BidService;
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
    BidService bidService;

    @Autowired
    UserDao userDao;

    @GetMapping("/dashboard")
    public String displayShoes(Model model, Principal pUser) {

        SiteUser user = userDao.getUserByUsername(pUser.getName());
        List<Listing> userPosts = listingService.getListingsForUser(user);
        List<Purchase> userPurchases = purchaseService.getPurchasesForBuyer(user);
        List<Bid> userBids = bidService.getBidsForBuyer(user);
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("userPurchases", userPurchases);
        model.addAttribute("userBids", userBids);
        model.addAttribute("balance", user.getMoneybalance());
        return "dashboard";
    }

    @PostMapping("/addBalance")
    public String addMoney(String money, Principal pUser) {
        SiteUser user = userDao.getUserByUsername(pUser.getName());
        user.setMoneybalance(user.getMoneybalance().add(new BigDecimal(money)));
        userDao.updateUser(user);
        return "redirect:dashboard";
    }
}
