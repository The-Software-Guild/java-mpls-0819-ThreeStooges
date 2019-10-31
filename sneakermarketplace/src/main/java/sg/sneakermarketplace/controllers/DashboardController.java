/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.services.BidService;
import sg.sneakermarketplace.services.ListingService;
import sg.sneakermarketplace.services.PurchaseService;
import sg.sneakermarketplace.services.UserDetailsServiceImpl;

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
    UserDetailsServiceImpl userService;

    
    
    @Autowired
    UserDao userDao;

    @GetMapping("/dashboard")
    public String displayShoes(Model model, Principal pUser) {

        bidService.finalizeBids();

        SiteUser user = userDao.getUserByUsername(pUser.getName());
        List<Listing> userPosts = listingService.getListingsForUser(user);
        List<Purchase> userPurchases = purchaseService.getPurchasesForBuyer(user);
        List<Bid> userBids = bidService.getBidsForBuyer(user);
        BigDecimal balance = userService.getEffectiveBalance(user);
        List<SiteUser> users = userDao.getAllUsers();
        
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("userPurchases", userPurchases);
        model.addAttribute("userBids", userBids);
        model.addAttribute("balance", balance);
        model.addAttribute("users", users);
        return "dashboard";
    }

    @PostMapping("/addBalance")
    @Transactional
    public String addMoney(String money, Principal pUser) {
        SiteUser user = userDao.getUserByUsername(pUser.getName());
        user.setMoneybalance(user.getMoneybalance().add(new BigDecimal(money)));
        userDao.updateUser(user);
        return "redirect:dashboard";
    }
    
     @GetMapping("/deleteuser")
    public String deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        
        return "redirect:/dashboard";
    }
     
    @GetMapping("/edituser")
    public String editUserDisplay(Model model, Integer id) {
        SiteUser user = userDao.getUserById(id);
        List<Role> roleList = userDao.getAllRoles();
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);
        return "editUser";
    }

    @PostMapping("/edituser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        SiteUser user = userDao.getUserById(id);
        if(enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }
        
        Set<Role> roleList = new HashSet<>();
        for(String roleId : roleIdList) {
            Role role = userDao.getRoleById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
        userDao.updateUser(user);
        
        return "redirect:/dashboard";
    }
    
}
