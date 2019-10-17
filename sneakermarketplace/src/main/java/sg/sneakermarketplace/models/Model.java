/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import java.math.BigDecimal;
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
@Entity(name = "models")
public class Model {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable=false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="brandId", nullable=false)
    private Brand brand;
    
    @Column(nullable=false)
    private int releaseYear;
    
    @ManyToOne
    @JoinColumn(name="colorId", nullable=false)
    private Color color;
    
    @Column(name="msrp_price", nullable=false)
    private BigDecimal msrpPrice;
    
}
