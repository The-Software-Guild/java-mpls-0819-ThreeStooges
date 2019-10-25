/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.PurchaseDao;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author mac
 */
@Service
public class PurchaseService {
    @Autowired
    PurchaseDao pDao;
    
    public Purchase addPurchase(Purchase toAdd){
        return pDao.save(toAdd);
    }

    public List<Purchase> getPurchasesForBuyer(SiteUser user) {
        return pDao.findByBuyer(user);
    }
    
    public Purchase getPurchaseById (int id) {
        return pDao.getOne(id);
    }
}
