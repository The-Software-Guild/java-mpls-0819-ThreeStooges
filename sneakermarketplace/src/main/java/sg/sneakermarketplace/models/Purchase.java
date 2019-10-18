/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.listing);
        hash = 17 * hash + Objects.hashCode(this.salePrice);
        hash = 17 * hash + Objects.hashCode(this.seller);
        hash = 17 * hash + Objects.hashCode(this.buyer);
        hash = 17 * hash + Objects.hashCode(this.dateSold);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.listing, other.listing)) {
            return false;
        }
        if (!Objects.equals(this.salePrice, other.salePrice)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.dateSold, other.dateSold)) {
            return false;
        }
        return true;
    }

    /**
     * @return the listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * @param listing the listing to set
     */
    public void setListing(Listing listing) {
        this.listing = listing;
    }

    /**
     * @return the salePrice
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the seller
     */
    public SiteUser getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(SiteUser seller) {
        this.seller = seller;
    }

    /**
     * @return the buyer
     */
    public SiteUser getBuyer() {
        return buyer;
    }

    /**
     * @param buyer the buyer to set
     */
    public void setBuyer(SiteUser buyer) {
        this.buyer = buyer;
    }

    /**
     * @return the dateSold
     */
    public LocalDate getDateSold() {
        return dateSold;
    }

    /**
     * @param dateSold the dateSold to set
     */
    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }
}
