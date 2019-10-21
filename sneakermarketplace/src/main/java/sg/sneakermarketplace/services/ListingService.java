/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.ListingDao;
import sg.sneakermarketplace.models.Listing;

/**
 *
 * @author Thomas
 */
@Service
public class ListingService {

    @Autowired
    ListingDao listingDao;
    
    public List<Listing> getAllListings() {
        return listingDao.findAll();
    }
    
    public Listing getListingById(int id) {
        return listingDao.getOne(id);
    }
    
    
}
