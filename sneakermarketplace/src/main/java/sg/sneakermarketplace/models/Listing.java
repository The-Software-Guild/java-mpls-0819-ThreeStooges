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
@Entity(name = "listings")
public class Listing {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name="statusid",nullable=false)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name="modelid",nullable=false)
    private ShoeModel model;
    
    @ManyToOne
    @JoinColumn(name="shoeconditionid",nullable=false)
    private ShoeCondition shoeCondition;
    
    @ManyToOne
    @JoinColumn(name="shoesizeid",nullable=false)
    private Size size;
    
    @Column(nullable=false)
    private String description;
    
    @Column(name = "buynowprice", nullable=false)
    private BigDecimal buyNowPrice;
    
    @Column(name = "minstartingprice", nullable=false)
    private BigDecimal minStartingPrice;
    
    @Column(name = "listdate", nullable=false)
    private LocalDate listDate;
    
    @Column(name = "enddate", nullable=true)
    private LocalDate endDate;
    
    @ManyToOne
    @JoinColumn(name="typeid", nullable=false)
    private Type shoeType;
        
    @ManyToOne
    @JoinColumn(name="sellerid", nullable=false)
    private SiteUser seller;
    
    @Column(name="photo", nullable=true)
    private String photoPath;

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
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the model
     */
    public ShoeModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ShoeModel model) {
        this.model = model;
    }

    /**
     * @return the shoeCondition
     */
    public ShoeCondition getShoeCondition() {
        return shoeCondition;
    }

    /**
     * @param shoeCondition the shoeCondition to set
     */
    public void setShoeCondition(ShoeCondition shoeCondition) {
        this.shoeCondition = shoeCondition;
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the buyNowPrice
     */
    public BigDecimal getBuyNowPrice() {
        return buyNowPrice;
    }

    /**
     * @param buyNowPrice the buyNowPrice to set
     */
    public void setBuyNowPrice(BigDecimal buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    /**
     * @return the minStartingPrice
     */
    public BigDecimal getMinStartingPrice() {
        return minStartingPrice;
    }

    /**
     * @param minStartingPrice the minStartingPrice to set
     */
    public void setMinStartingPrice(BigDecimal minStartingPrice) {
        this.minStartingPrice = minStartingPrice;
    }

    /**
     * @return the listDate
     */
    public LocalDate getListDate() {
        return listDate;
    }

    /**
     * @param listDate the listDate to set
     */
    public void setListDate(LocalDate listDate) {
        this.listDate = listDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
     * @return the photoPath
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * @param photoPath the photoPath to set
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Type getShoeType() {
        return shoeType;
    }

    public void setShoeType(Type shoeType) {
        this.shoeType = shoeType;
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.model);
        hash = 97 * hash + Objects.hashCode(this.shoeCondition);
        hash = 97 * hash + Objects.hashCode(this.size);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.buyNowPrice);
        hash = 97 * hash + Objects.hashCode(this.minStartingPrice);
        hash = 97 * hash + Objects.hashCode(this.listDate);
        hash = 97 * hash + Objects.hashCode(this.endDate);
        hash = 97 * hash + Objects.hashCode(this.shoeType);
        hash = 97 * hash + Objects.hashCode(this.seller);
        hash = 97 * hash + Objects.hashCode(this.photoPath);
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
        final Listing other = (Listing) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photoPath, other.photoPath)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.shoeCondition, other.shoeCondition)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.buyNowPrice, other.buyNowPrice)) {
            return false;
        }
        if (!Objects.equals(this.minStartingPrice, other.minStartingPrice)) {
            return false;
        }
        if (!Objects.equals(this.listDate, other.listDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.shoeType, other.shoeType)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        return true;
    }
    
}
