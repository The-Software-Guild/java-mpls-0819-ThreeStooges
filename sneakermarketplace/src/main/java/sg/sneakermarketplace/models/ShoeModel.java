/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Thomas
 */
@Entity(name = "models")
public class ShoeModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable=false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="brandid", nullable=false)
    private Brand brand;
    
    @Column(name="releaseyear", nullable=false)
    private int releaseYear;
    
    @ManyToOne
    @JoinColumn(name="primarycolorid", nullable=false)
    private PrimaryColor primColor;
    
    @ManyToOne
    @JoinColumn(name="secondarycolorid", nullable=true)
    private SecondaryColor secondColor;
    
    @Column(name="msrp_price", nullable=false)
    private BigDecimal msrpPrice;
    
    @ManyToMany
    @JoinTable(name="models_types",
    joinColumns = {@JoinColumn(name = "modelId")},
    inverseJoinColumns = {@JoinColumn(name = "typeId")})
    private Set<Type> types;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the brand
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * @return the releaseYear
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear the releaseYear to set
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return the primColor
     */
    public PrimaryColor getPrimColor() {
        return primColor;
    }

    /**
     * @param primColor the primColor to set
     */
    public void setPrimColor(PrimaryColor primColor) {
        this.primColor = primColor;
    }

    /**
     * @return the secondColor
     */
    public SecondaryColor getSecondColor() {
        return secondColor;
    }

    /**
     * @param secondColor the secondColor to set
     */
    public void setSecondColor(SecondaryColor secondColor) {
        this.secondColor = secondColor;
    }

    /**
     * @return the msrpPrice
     */
    public BigDecimal getMsrpPrice() {
        return msrpPrice;
    }

    /**
     * @param msrpPrice the msrpPrice to set
     */
    public void setMsrpPrice(BigDecimal msrpPrice) {
        this.msrpPrice = msrpPrice;
    }

    /**
     * @return the types
     */
    public Set<Type> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.brand);
        hash = 79 * hash + this.releaseYear;
        hash = 79 * hash + Objects.hashCode(this.primColor);
        hash = 79 * hash + Objects.hashCode(this.secondColor);
        hash = 79 * hash + Objects.hashCode(this.msrpPrice);
        hash = 79 * hash + Objects.hashCode(this.types);
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
        final ShoeModel other = (ShoeModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.releaseYear != other.releaseYear) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.primColor, other.primColor)) {
            return false;
        }
        if (!Objects.equals(this.secondColor, other.secondColor)) {
            return false;
        }
        if (!Objects.equals(this.msrpPrice, other.msrpPrice)) {
            return false;
        }
        return Objects.equals(this.types, other.types);
    }
    
}
