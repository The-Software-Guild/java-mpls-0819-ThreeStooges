/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.sneakermarketplace.daos.BidDao;
import sg.sneakermarketplace.daos.ListingDao;
import sg.sneakermarketplace.daos.PurchaseDao;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.models.Status;

/**
 *
 * @author mac
 */
@Service
public class BidService {

    @Autowired
    BidDao bidDao;

    @Autowired
    ListingDao listingDao;

    @Autowired
    UserDetailsServiceImpl userService;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    PurchaseDao purchaseDao;

    @Transactional
    public Bid addBid(Bid toAdd, SiteUser user) throws InvalidBidException {

        // Bid logic to implement
        // date must be active for listing
        // price must be higher than other bids
        if (toAdd.getListing().getListDate().isAfter(LocalDate.now())
                || toAdd.getListing().getEndDate().isBefore(LocalDate.now())
                || toAdd.getListing().getStatus().getId() != 1) {
            throw new InvalidBidException("Listing " + toAdd.getListing().getId() + " is not active to be bid on");
        }
        List<Bid> allBids = bidDao.findByListing(toAdd.getListing());
        BigDecimal maxBid = allBids.stream().map(l -> l.getBidPrice())
                .max(BigDecimal::compareTo).orElse(toAdd.getListing()
                .getMinStartingPrice());
        if (toAdd.getBidPrice().compareTo(maxBid) <= 0) {
            throw new InvalidBidException("Invalid Bid must bid at least " + maxBid);
        }
        BigDecimal balance = userService.getEffectiveBalance(user);
        if (toAdd.getBidPrice().compareTo(balance) > 0) {
            throw new InvalidBidException("Current funds: " + balance + " is not enough to place bid " + toAdd.getBidPrice());
        }

        toAdd.setDate(LocalDate.now());

        return bidDao.save(toAdd);
    }

    public List<Bid> getBidsForBuyer(SiteUser user) {
        List<Bid> allBids = bidDao.findByBuyer(user);
        return allBids;
    }
    
    public List<Bid> getAllBidsOfListing(Listing toFind) {
        return bidDao.findByListing(toFind);
    }

    @Transactional
    public void finalizeBids() {
        // get all listings
        // check if any are out of date but still active
        // find winner if there is one and run logic
        List<Listing> allListings = listingDao.findAll();
        LocalDate test =  LocalDate.now();
        List<Listing> finishedListings = allListings.stream().filter(l
                -> l.getStatus().getId() == 1
                && l.getEndDate().isBefore(LocalDate.now())
        ).collect(Collectors.toList());

        for (Listing l : finishedListings) {
            List<Bid> allBids = bidDao.findByListing(l);
            Bid winner = allBids.stream().max((b1, b2)
                    -> b1.getBidPrice().compareTo(b2.getBidPrice())).orElse(null);
            if (winner != null) {
                Status sold = new Status();
                sold.setId(3);
                l.setStatus(sold);
                SiteUser seller = l.getSeller();
                seller.setMoneybalance(seller.getMoneybalance().add(winner.getBidPrice()));
                SiteUser buyer = winner.getBuyer();
                buyer.setMoneybalance(buyer.getMoneybalance().subtract(winner.getBidPrice()));
                Purchase p = new Purchase();
                p.setBuyer(buyer);
                p.setDateSold(LocalDate.now());
                p.setListing(l);
                p.setSalePrice(winner.getBidPrice());
                p.setSeller(seller);
                purchaseDao.save(p);
                userDao.updateUser(buyer);
                userDao.updateUser(seller);
            } else {
                Status sold = new Status();
                sold.setId(2);
                l.setStatus(sold);
            }
            listingDao.save(l);
        }
    }

}
