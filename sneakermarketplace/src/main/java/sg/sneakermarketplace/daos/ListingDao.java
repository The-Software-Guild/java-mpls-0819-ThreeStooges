/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author mac
 */
@Repository
@Profile({"production", "test"})
public interface ListingDao extends JpaRepository<Listing, Integer>, JpaSpecificationExecutor<Listing> {   
    List<Listing> findBySeller(SiteUser seller);
}
