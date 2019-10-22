/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.BidDao;
import sg.sneakermarketplace.models.Bid;

/**
 *
 * @author mac
 */
@Service
public class BidService {
    
    @Autowired
    BidDao bidDao;
    
    public Bid addBid(Bid toAdd){
        return bidDao.save(toAdd);
    }

}

