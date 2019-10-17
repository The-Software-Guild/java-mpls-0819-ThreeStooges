/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Thomas
 */
@Entity(name = "sizes")
public class Size {

    @Id
    private int shoeSize;
}
