/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Thomas
 */
@Entity(name = "sizes")
public class Size {

    @Id
    @Column(name="shoesize")
    private int shoeSize;

    /**
     * @return the shoeSize
     */
    public int getShoeSize() {
        return shoeSize;
    }

    /**
     * @param shoeSize the shoeSize to set
     */
    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.shoeSize;
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
        final Size other = (Size) obj;
        return this.shoeSize == other.shoeSize;
    }
    
    
}
