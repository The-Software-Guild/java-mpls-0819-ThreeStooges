/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Thomas
 */
@Entity(name = "purchases")
public class Purchase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name="listingId", nullable=false)
    private Listing listing;
    
    @Column(nullable=false)
    private BigDecimal salePrice;
    
    @ManyToOne
    @JoinColumn(name="sellerId", nullable=false)
    private SiteUser seller;
    
    @ManyToOne
    @JoinColumn(name="buyerId", nullable=false)
    private SiteUser buyer;
    
    @Column(nullable=false)
    private LocalDate dateSold;
}
