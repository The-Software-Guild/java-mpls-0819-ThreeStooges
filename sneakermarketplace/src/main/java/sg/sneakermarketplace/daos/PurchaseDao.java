/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author junho
 */
@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Integer>{
    List<Purchase> findByBuyer(SiteUser buyer);
}
