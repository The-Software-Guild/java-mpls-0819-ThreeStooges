/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Listing> getAllListings(Map<String, String> allParams) {
        // build up a query for the search parameters
        // use jpa criteria builder? and specification <--- lets try this
        if (allParams == null || allParams.isEmpty()) {
            return listingDao.findAll();
        }

        List<Specification> specs = new ArrayList();
        String brandName = allParams.get("brand");
        if (brandName != null) {
            specs.add((listing, cq, cb) -> cb.equal(listing.get("model").get("brand").get("name"), brandName));
        }
        String colorName = allParams.get("color");
        if (colorName != null) {
            specs.add((listing, cq, cb) -> cb.equal(listing.get("model").get("primColor").get("name"), colorName));
        }

        Specification result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        // TODO add sorting :)
        return listingDao.findAll(result);
    }
    
    public Listing getListingById(int id) {
        return listingDao.getOne(id);
    }
    
    
}
