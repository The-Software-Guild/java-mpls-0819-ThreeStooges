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
@Entity(name = "bids")
public class Bid {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name="listingid", nullable=false)
    private Listing listing;
    
    @Column(name="bidprice",nullable=false)
    private BigDecimal bidPrice;
    
    @Column(nullable=false)
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name="buyerid", nullable=false)    
    private SiteUser buyer;

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
     * @return the bidPrice
     */
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    /**
     * @param bidPrice the bidPrice to set
     */
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.listing);
        hash = 47 * hash + Objects.hashCode(this.bidPrice);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.buyer);
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
        final Bid other = (Bid) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.listing, other.listing)) {
            return false;
        }
        if (!Objects.equals(this.bidPrice, other.bidPrice)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        return true;
    }
    
}
