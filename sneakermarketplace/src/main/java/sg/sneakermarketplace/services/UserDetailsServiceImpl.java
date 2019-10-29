/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.BidDao;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author Thomas
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDao dao;
    
    @Autowired
    BidDao bidDao;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        SiteUser toLoad = dao.getUserByUsername(userName);
        
        Set<GrantedAuthority> convertedRoles = new HashSet<>();
        
        toLoad.getRoles().stream().forEach( r -> convertedRoles.add(new SimpleGrantedAuthority(r.getRole())));
        
        return new User(userName, toLoad.getPassword(), convertedRoles);
        
    }
    
    public SiteUser getUserByUsername(String userName) throws UsernameNotFoundException {
        SiteUser toReturn = dao.getUserByUsername(userName);
        return toReturn;
    }
    
    public SiteUser getUserById(int id) {
        SiteUser toReturn = dao.getUserById(id);
        return toReturn;
    }
  
    public SiteUser createUser(SiteUser user){
        SiteUser toReturn = dao.createUser(user);
        
        return toReturn;
    }
    
    public void editUser(SiteUser user){
        dao.updateUser(user);
    }

    public BigDecimal getEffectiveBalance(SiteUser user) {
        List<Bid> allBids = bidDao.findByBuyer(user);
        List<Bid> maxBids = new ArrayList();
        List<Listing> allListings = new ArrayList();
        List<Bid> activeBids = allBids.stream().filter(b->b.getListing().getStatus().getId()==1).collect(Collectors.toList());
        for(Bid currentBid : activeBids) {
            if (allListings.contains(currentBid.getListing())) {
                Bid highestBid = maxBids.stream().filter(b->b.getListing()==currentBid.getListing()).findAny().orElse(null);
                if (currentBid.getBidPrice().compareTo(highestBid.getBidPrice()) > 0) {
                    maxBids.remove(highestBid);
                    maxBids.add(currentBid);
                    
                }
            } else {
                maxBids.add(currentBid);
                allListings.add(currentBid.getListing());
            }
        }
        BigDecimal moneyInBids = maxBids.stream().map(b->b.getBidPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return user.getMoneybalance().subtract(moneyInBids);
    }
    
}
