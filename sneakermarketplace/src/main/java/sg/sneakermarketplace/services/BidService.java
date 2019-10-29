/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.BidDao;
import sg.sneakermarketplace.daos.ListingDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author mac
 */
@Service
public class BidService {
    
    @Autowired
    BidDao bidDao;
    
//    @Autowired
//    ListingDao listingDao;
    
    public Bid addBid(Bid toAdd) throws InvalidBidException{
        
        // Bid logic to implement
        // date must be active for listing
        // price must be higher than other bids
        if(toAdd.getListing().getListDate().isAfter(LocalDate.now()) ||
                toAdd.getListing().getEndDate().isBefore(LocalDate.now()) ||
                toAdd.getListing().getStatus().getId() != 1) {
            throw new InvalidBidException("Listing " + toAdd.getListing().getId() + " is not active to be bid on");
        }
        List<Bid> allBids = bidDao.findByListing(toAdd.getListing());
        BigDecimal maxBid = allBids.stream().map(l -> l.getBidPrice())
                .max(BigDecimal::compareTo).orElse(toAdd.getListing()
                        .getMinStartingPrice());
        if(toAdd.getBidPrice().compareTo(maxBid) <= 0) {
            throw new InvalidBidException("Invalid Bid must bid at least " + maxBid);
        }
        
        toAdd.setDate(LocalDate.now());
        
        return bidDao.save(toAdd);
    }

    public List<Bid> getBidsForBuyer(SiteUser user) {
        //TODO: here we will check if any of the bids are finalized
        return bidDao.findByBuyer(user);
    }

}

