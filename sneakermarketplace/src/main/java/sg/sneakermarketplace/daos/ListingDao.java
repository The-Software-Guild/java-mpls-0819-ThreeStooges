/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.sneakermarketplace.models.Bid;
import sg.sneakermarketplace.models.Listing;

/**
 *
 * @author mac
 */
@Repository
public interface ListingDao extends JpaRepository<Listing, Integer> {
    
}