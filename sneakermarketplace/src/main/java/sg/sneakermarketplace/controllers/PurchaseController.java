/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.services.PurchaseService;

/**
 *
 * @author mac
 */
@Controller
public class PurchaseController {
    
    @Autowired
    PurchaseService pService;
    
    @GetMapping("/purchase/{id}")
    public String displayPurchase(@PathVariable Integer id, Model model) {
        Purchase toReturn = pService.getPurchaseById(id);
        
        model.addAttribute("purchase", toReturn);
        
        return "PurchaseConfirmation"; 
    }
    
}
