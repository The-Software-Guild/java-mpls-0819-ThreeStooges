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
import org.springframework.transaction.annotation.Transactional;
import sg.sneakermarketplace.daos.BidDao;
import sg.sneakermarketplace.daos.PurchaseDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.models.Status;

/**
 *
 * @author mac
 */
@Service
public class PurchaseService {

    @Autowired
    PurchaseDao pDao;

    @Autowired
    BidDao bidDao;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    StatusService statusService;

    @Transactional
    public Purchase addPurchase(Purchase toAdd, SiteUser user) throws ServiceInvalidPurchaseException {

        //must be an active listing
        if (toAdd.getListing().getListDate().isAfter(LocalDate.now())
                || toAdd.getListing().getEndDate().isBefore(LocalDate.now())
                || toAdd.getListing().getStatus().getId() != 1) {
            throw new ServiceInvalidPurchaseException("Listing " + toAdd.getListing().getId() + " is not active and cannot be purchased.");
        }

        BigDecimal buyNowPrice = toAdd.getListing().getBuyNowPrice();
        List<Bid> allBids = bidDao.findByListing(toAdd.getListing());
        BigDecimal maxBid = allBids.stream().map(l -> l.getBidPrice())
                .max(BigDecimal::compareTo).orElse(toAdd.getListing()
                .getMinStartingPrice());

        //purchase price must be greater or equal to buyNowPrice and maxBid. 
        if (toAdd.getSalePrice().compareTo(buyNowPrice) < 0
                || toAdd.getSalePrice().compareTo(maxBid) < 0) {
            throw new ServiceInvalidPurchaseException("Purchase price must be greater or equal to the Buy Now price or Max Bid.");
        }

        BigDecimal balance = userService.getEffectiveBalance(user);
        if (toAdd.getSalePrice().compareTo(balance) > 0) {
            throw new ServiceInvalidPurchaseException("Insufficient funds to make purchase. Please add more money to balance.");
        }

        BigDecimal salePrice = toAdd.getSalePrice();
        Status sold = statusService.getStatusById(3);
        toAdd.getListing().setStatus(sold);

        user.setMoneybalance(user.getMoneybalance().subtract(salePrice));
        SiteUser seller = toAdd.getSeller();
        seller.setMoneybalance(seller.getMoneybalance().add(salePrice));
        userService.editUser(user);
        userService.editUser(seller);

        toAdd.setDateSold(LocalDate.now());

        return pDao.save(toAdd);
    }

    public List<Purchase> getPurchasesForBuyer(SiteUser user) {
        return pDao.findByBuyer(user);
    }

    public Purchase getPurchaseById(int id) {
        return pDao.getOne(id);
    }
}
