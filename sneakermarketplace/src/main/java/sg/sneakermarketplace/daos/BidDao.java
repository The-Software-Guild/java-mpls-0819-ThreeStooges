/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author mac
 */
@Repository
@Profile({"production", "test"})
public interface BidDao extends JpaRepository<Bid, Integer> {

    List<Bid> findByBuyer(SiteUser buyer);
    
    List<Bid> findByListing(Listing shoe);

//    @Query("select b froms Bid b where listingid = ?1")
//    List<Bid> findByListing(Listing toFind);
}
